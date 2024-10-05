package ie.atu.orderservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class OrderService {

    private final PaymentService paymentService;
    private final ObjectMapper objectMapper = new ObjectMapper();

   private final List<Order> ordersCompleted = new ArrayList<>();
   private final Queue<Order> pendingOrders = new LinkedList<>();

   public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public Queue<Order> getPendingOrders() {
       printContents();

       return pendingOrders;
   }

   public List<Order> getCompletedOrders() {
       printContents();

       return ordersCompleted;
   }

   public Order makeOrder(OrderRequest orderRequest) {
       Order order = orderRequest.getOrder();
       CardDetails cardDetails = orderRequest.getCardDetails();

       for (Order pendingOrder : pendingOrders) {
           if (Objects.equals(pendingOrder.getId(), order.getId())) {
               throw new ResponseStatusException(HttpStatus.CONFLICT, "An order with the same ID exists.");
           }
       }

       pendingOrders.add(order);
       printContents();
       paymentService.processPayment(cardDetails);

       return order;
   }

   public Order completeNextOrder() {
       var orderToComplete = pendingOrders.poll();
       if (orderToComplete == null) {
           throw new ResponseStatusException(HttpStatus.NO_CONTENT);
       }

       ordersCompleted.add(orderToComplete);
       printContents();

       return ordersCompleted.get(ordersCompleted.size()-1);
   }

   public Order skipNextOrder() {
       printContents();

       return pendingOrders.poll();
   }

   private void printContents() {
       System.out.println(
               "\n\nPending orders: " + pendingOrders + "," +
                       "\nCompleted orders: " + ordersCompleted
       );
   }
}

package ie.atu.orderservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Queue;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public Queue<Order> getOrders() {
        return orderService.getPendingOrders();
    }

    @GetMapping("/completed-orders")
    public List<Order> getCompletedOrders() {
        return  orderService.getCompletedOrders();
    }
    @PostMapping("/order")
    public Order makeOrder(@Valid @RequestBody Order order, @RequestBody CardDetails cardDetails) {
        return orderService.makeOrder(order, cardDetails);
    }

    @PostMapping("/order2")
    public Order makeOrder2(@RequestBody ObjectNode json) throws JsonProcessingException {
//        var order = json.get();
        return orderService.makeOrder(json);
    }

    @PostMapping("/process")
    public Order process() {
        return orderService.completeNextOrder();
    }

    @PostMapping("/skip")
    public Order skip() {
        return orderService.skipNextOrder();
    }
}

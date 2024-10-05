package ie.atu.orderservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment", url = "http://payment-service:8081")
public interface PaymentService {

    @GetMapping("/process-payment")
    ResponseEntity<String> processPayment(@RequestBody CardDetails cardDetails);
}

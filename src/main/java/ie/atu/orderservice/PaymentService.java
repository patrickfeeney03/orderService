package ie.atu.orderservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment", url = "http://localhost:8081")
public interface PaymentService {

    @GetMapping("/process-payment")
    public void processPayment(@RequestBody CardDetails cardDetails);
}

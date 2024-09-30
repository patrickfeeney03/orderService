package ie.atu.orderservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
    public class CardDetails {
    private String name;
    private Integer cardNumber;
    private Integer securityNumber;
}

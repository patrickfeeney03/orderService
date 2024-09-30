package ie.atu.orderservice;


import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Digits(integer = 5, fraction = 0, message = "Id has to be 5 digits long.")
    private Integer id;
    @Email(message = "Not a valid email.")
    private String associatedEmail;
    @Min(value = 0, message = "Price can't be negative")
    private Double totalOrderPrice;
}

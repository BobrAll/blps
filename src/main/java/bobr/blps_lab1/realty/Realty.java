package bobr.blps_lab1.realty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public abstract class Realty {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false)
    private Double totalArea;
    @Column(nullable = false)
    private Double totalPrice;
    @Column(nullable = false)
    private String address;

    public Double pricePerMeter() {
        return totalPrice / totalArea;
    }
}

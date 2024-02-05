package bobr.blps_lab1.realty.flat;

import bobr.blps_lab1.realty.Realty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Flat extends Realty {
    private Boolean haveBalcony;
    private Boolean isApartments;
    private Double kitchenArea;
    private Double livingArea;
    @Column(nullable = false)
    private Integer rooms;
    @Column(nullable = false)
    private Integer floor;
}

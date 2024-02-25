package bobr.blps_lab1.realty.flat;

import bobr.blps_lab1.image.Base64Image;
import bobr.blps_lab1.realty.Realty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Flat extends Realty {
    @OneToMany(mappedBy = "flat", cascade = CascadeType.ALL)
    private List<Base64Image> base64images;
    @Column(nullable = false)
    private Integer ownerId;
    private Boolean isBoosted;
    private Boolean haveBalcony;
    private Boolean isApartments;
    private Double kitchenArea;
    private Double livingArea;
    @Column(nullable = false)
    private Integer rooms;
    @Column(nullable = false)
    private Integer floor;
}

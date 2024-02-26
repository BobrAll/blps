package bobr.blps_lab.image;

import bobr.blps_lab.realty.flat.Flat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Base64Image {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Flat flat;

    @Column(columnDefinition = "TEXT")
    private String base64Image;

    public Base64Image(String base64Image, Flat flat) {
        this.base64Image = base64Image;
        this.flat = flat;
    }
}

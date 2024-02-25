package bobr.blps_lab1.realty.flat;

import bobr.blps_lab1.exceptions.flat.NoSuchFlatException;
import bobr.blps_lab1.image.Base64ImageService;
import bobr.blps_lab1.image.Base64Image;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Data
@RequiredArgsConstructor
public class FlatService {
    private final Base64ImageService imageService;
    private final FlatRepository flatRepository;

    public Flat findById(Integer flatId) {
        return flatRepository
                .findById(flatId)
                .orElseThrow(() -> new NoSuchFlatException(flatId));
    }

    public List<Flat> findAllByFilters(FlatCriteria searchFilters) {
        return flatRepository.findWithFilters(
                searchFilters.getMinTotalArea(),
                searchFilters.getMaxTotalArea(),
                searchFilters.getMinKitchenArea(),
                searchFilters.getMaxKitchenArea(),
                searchFilters.getMinLivingArea(),
                searchFilters.getMaxLivingArea(),
                searchFilters.getRooms(),
                searchFilters.getFloor(),
                searchFilters.getHaveBalcony(),
                searchFilters.getIsApartments()
                );
    }

    public void save(Flat flat) {
        flatRepository.save(flat);
    }

    public void delete(Integer flatId) {
        flatRepository.delete(findById(flatId));
    }

    public void boost(Integer flatId) {
        Flat flat = findById(flatId);

        flat.setIsBoosted(true);
        save(flat);
    }


    public void unboostAllFlats(Integer userId) {
        List<Flat> flats = flatRepository.findAllByOwnerId(userId);

        flats.forEach(flat -> flat.setIsBoosted(false));
        flatRepository.saveAll(flats);
    }

    public Integer findOwnerIdByFlatId(Integer flatId) {
        return flatRepository.findOwnerIdByFlatId(flatId);
    }

    public Flat toFlat(FlatAddRequest flatRequest) {
        Flat flat = Flat.builder()
                .totalArea(flatRequest.getTotalArea())
                .totalPrice(flatRequest.getTotalPrice())
                .address(flatRequest.getAddress())
                .ownerId(flatRequest.getOwnerId())
                .isBoosted(flatRequest.getIsBoosted())
                .haveBalcony(flatRequest.getHaveBalcony())
                .isApartments(flatRequest.getIsApartments())
                .kitchenArea(flatRequest.getKitchenArea())
                .livingArea(flatRequest.getLivingArea())
                .rooms(flatRequest.getRooms())
                .floor(flatRequest.getFloor())
                .build();

        if (flatRequest.getImageUrls() != null)
            flatRequest.getImageUrls()
                    .forEach(url -> imageService.save(
                            Base64ImageService.download(url, flat)
                    ));

        return flat;
    }
}

package bobr.blps_lab1.realty.flat;

import bobr.blps_lab1.exceptions.flat.NoSuchFlatException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class FlatService {
    private final FlatRepository flatRepository;

    public Flat find(Integer flatId) {
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
        flatRepository.delete(find(flatId));
    }
}

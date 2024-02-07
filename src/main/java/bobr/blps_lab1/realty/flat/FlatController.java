package bobr.blps_lab1.realty.flat;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/flats")
public class FlatController {
    private final FlatService flatService;
    //TODO remake filters (to custom sql)
    @GetMapping
    public List<Flat> getFlats(@RequestBody FlatCriteria filters) {
        return flatService
                .findAll()
                .stream()
                .filter(flat ->
                        (filters.getMaxTotalArea() == null || flat.getTotalArea() < filters.getMaxTotalArea())
                                && (filters.getMinTotalArea() == null || flat.getTotalArea() > filters.getMinTotalArea())

                                && (filters.getMaxLivingArea() == null || flat.getLivingArea() < filters.getMaxLivingArea())
                                && (filters.getMinLivingArea() == null || flat.getLivingArea() > filters.getMinLivingArea())

                                && (filters.getMaxKitchenArea() == null || flat.getKitchenArea() < filters.getMaxKitchenArea())
                                && (filters.getMinKitchenArea() == null || flat.getKitchenArea() > filters.getMinKitchenArea())

                                && (filters.getHaveBalcony() == null || flat.getHaveBalcony() == filters.getHaveBalcony())
                                && (filters.getIsApartments() == null || flat.getIsApartments() == filters.getIsApartments())

                                && (filters.getFloor() == null || flat.getFloor().equals(filters.getFloor()))
                                && (filters.getRooms() == null || Arrays.binarySearch(filters.getRooms(), flat.getRooms()) >= 0)
                )
                .toList();
    }

    @PostMapping()
    public void add(@RequestBody Flat flat) {
        flatService.save(flat);
    }

    @GetMapping("/{flatId}")
    public Flat getFlat(@PathVariable Integer flatId) {
        return flatService.find(flatId);
    }

    @DeleteMapping("/{flatId}")
    public void removeFlat(@PathVariable Integer flatId) {
        flatService.delete(flatId);
    }
}

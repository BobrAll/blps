package bobr.blps_lab1.realty.flat;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Flat")
@RequestMapping("/api/v1/flats")
public class FlatController {
    private final FlatService flatService;

    @GetMapping
    public List<Flat> getFlats(@RequestBody FlatCriteria filters) {
        return flatService.findAllByFilters(filters);
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

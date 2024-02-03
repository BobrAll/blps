package bobr.blps_lab1.businessLogic.flat;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/flats")
public class FlatController {
    private final FlatService flatService;

    @GetMapping
    public List<Flat> getFlats() {
        return flatService.findAll();
    }

    @PostMapping("/add")
    public Boolean add(@RequestBody Flat flat) {
        flatService.save(flat);
        return true;
    }

    @DeleteMapping("/remove/{flatId}")
    public void removeFlat(@PathVariable Integer flatId) {
        flatService.delete(flatId);
    }
}

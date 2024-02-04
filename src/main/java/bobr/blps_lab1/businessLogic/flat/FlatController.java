package bobr.blps_lab1.businessLogic.flat;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/flats")
public class FlatController {
    private final FlatService flatService;

    @GetMapping("/{flatId}")
    public Flat getFlat(@PathVariable Integer flatId) {
        return flatService.find(flatId);
    }

    @GetMapping
    public List<Flat> getFlats() {
        return flatService.findAll();
    }

    @PostMapping()
    public Boolean add(@RequestBody Flat flat) {
        flatService.save(flat);
        return true;
    }

    @DeleteMapping("/{flatId}")
    public void removeFlat(@PathVariable Integer flatId) {
        flatService.delete(flatId);
    }
}

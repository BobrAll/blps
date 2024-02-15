package bobr.blps_lab1.realty.flat;

import bobr.blps_lab1.user.User;
import bobr.blps_lab1.user.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Flat")
@RequestMapping("/api/v1/flats")
public class FlatController {
    private final UserService userService;
    private final FlatService flatService;

    @GetMapping
    public List<Flat> getFlats(@RequestBody FlatCriteria filters) {
        return flatService.findAllByFilters(filters);
    }

    @PostMapping()
    public void add(@RequestBody Flat flat) {
        User user = userService.getCurrentUser();

        flat.setOwnerId(user.getId());
        flatService.save(flat);
    }

    @GetMapping("/{flatId}")
    public Flat getFlat(@PathVariable Integer flatId) {
        return flatService.findById(flatId);
    }

    @DeleteMapping("/{flatId}")
    public void removeFlat(@PathVariable Integer flatId) {
        flatService.delete(flatId);
    }

    @PutMapping("/{flatId}/boost")
    public void boostFlat(@PathVariable Integer flatId) {
        flatService.boost(flatId);
    }
}

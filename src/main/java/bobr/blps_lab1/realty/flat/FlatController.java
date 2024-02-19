package bobr.blps_lab1.realty.flat;

import bobr.blps_lab1.user.User;
import bobr.blps_lab1.user.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public void add(@RequestBody Flat flat) {
        User user = userService.getCurrentUser();

        flat.setOwnerId(user.getId());
        flatService.save(flat);

        if (flat.getIsBoosted() && !user.getAuthorities().contains("flat.boost")) {
            userService.buySubscription(user);
        }
    }

    @GetMapping("/{flatId}")
    public Flat getFlat(@PathVariable Integer flatId) {
        return flatService.findById(flatId);
    }

    @DeleteMapping("/{flatId}")
    public ResponseEntity removeFlat(@PathVariable Integer flatId) {
        User currentUser = userService.getCurrentUser();

        if (
                flatService
                        .findOwnerIdByFlatId(flatId)
                        .equals(currentUser.getId()) ||
                        currentUser
                                .getAuthorities()
                                .contains("flat.delete")
        ) {
            flatService.delete(flatId);
            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.status(403).build();
    }

    @PutMapping("/{flatId}/boost")
    public void boostFlat(@PathVariable Integer flatId) {
        flatService.boost(flatId);
    }
}

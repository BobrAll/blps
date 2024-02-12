package bobr.blps_lab1.user;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/subscription")
@RequiredArgsConstructor
@Tag(name = "Subscription")
public class SubscriptionController {
    private final UserService userService;

    @PostMapping
    void buySubscription() {
        User user = userService.getCurrentUser();

        if (user.getRole().compareTo(Role.SUPERUSER) < 0)
            user.setRole(Role.SUPERUSER);

        userService.save(user);
    }

    @DeleteMapping
    void unsubscribe() {
        User user = userService.getCurrentUser();

        if (user.getRole().equals(Role.SUPERUSER)) {
            user.setRole(Role.USER);
            userService.save(user);
        }
    }
}


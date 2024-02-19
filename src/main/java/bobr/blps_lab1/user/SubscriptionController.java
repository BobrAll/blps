package bobr.blps_lab1.user;

import bobr.blps_lab1.exceptions.user.AlreadyHaveSuperuserPermissionsException;
import bobr.blps_lab1.exceptions.user.UserNotSubscribedException;
import bobr.blps_lab1.realty.flat.FlatService;
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
    private final FlatService flatService;
    private final UserService userService;

    @PostMapping
    void buySubscription() {
        userService.buySubscription(userService.getCurrentUser());
    }

    @DeleteMapping
    void unsubscribe() {
        User user = userService.getCurrentUser();

        if (user.getRole().equals(Role.SUPERUSER)) {
            user.setRole(Role.USER);
            flatService.unboostAllFlats(user.getId());
            userService.save(user);
        } else
            throw new UserNotSubscribedException();
    }
}


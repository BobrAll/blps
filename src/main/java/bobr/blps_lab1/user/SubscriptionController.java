package bobr.blps_lab1.user;

import bobr.blps_lab1.exceptions.user.AlreadyHaveSuperuserPermissionsException;
import bobr.blps_lab1.exceptions.user.UserNotSubscribedException;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@RestController
@RequestMapping("/api/v1/subscription")
@RequiredArgsConstructor
@Tag(name = "Subscription")
public class SubscriptionController {
    private final UserService userService;

    @PostMapping
    void buySubscription() {
        User user = userService.getCurrentUser();

        if (user
                .getAuthorities()
                .containsAll(Role.SUPERUSER.getAuthorities())
        ) {
            throw new AlreadyHaveSuperuserPermissionsException();
        } else {
            user.setRole(Role.SUPERUSER);
            userService.save(user);
        }
    }

    @DeleteMapping
    void unsubscribe() {
        User user = userService.getCurrentUser();

        if (user.getRole().equals(Role.SUPERUSER)) {
            user.setRole(Role.USER);
            userService.save(user);
        }
        else
            throw new UserNotSubscribedException();
    }
}


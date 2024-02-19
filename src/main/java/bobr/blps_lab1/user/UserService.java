package bobr.blps_lab1.user;

import bobr.blps_lab1.exceptions.user.AlreadyHaveSuperuserPermissionsException;
import bobr.blps_lab1.exceptions.user.NotEnoughMoneyException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    @Value("${SUBSCRIPTION_PRICE}")
    private Double subPrice;
    private final UserRepository repository;

    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public Optional<User> findByLogin(String login) {
        return repository.findUserByLogin(login);
    }

    public Optional<User> findById(Integer id) {
        return repository.findById(id);
    }

    public void save(User user) {
        repository.save(user);
    }

    public void delete(Integer userId) {
        repository.deleteById(userId);
    }

    public void block(Integer userId) {
        User user = findById(userId).orElseThrow();
        user.disable();
        save(user);
    }

    public void unblock(Integer userId) {
        User user = findById(userId).orElseThrow();
        user.enable();
        save(user);
    }

    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return findByLogin(auth.getName()).orElseThrow();
    }

    @Transactional
    public void buySubscription(User user) {
        if (user
                .getAuthorities()
                .containsAll(Role.SUPERUSER.getAuthorities())
        ) {
            throw new AlreadyHaveSuperuserPermissionsException();
        } else {
            if (user.getUsdBalance() < subPrice) {
                throw new NotEnoughMoneyException("You haven't enough money to buy subscription");
            }
            else {
                user.setUsdBalance(user.getUsdBalance() - subPrice);
            }
            user.setRole(Role.SUPERUSER);
            save(user);
        }
    }
}

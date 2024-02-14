package bobr.blps_lab1.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
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
}

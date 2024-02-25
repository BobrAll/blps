package bobr.blps_lab1;

import bobr.blps_lab1.user.Role;
import bobr.blps_lab1.user.User;
import bobr.blps_lab1.user.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;

@SpringBootApplication
public class BlpsLab1Application {
    public static void main(String[] args) {
        var context = SpringApplication.run(BlpsLab1Application.class, args);

        UserService userService = context.getBean(UserService.class);
        PasswordEncoder passwordEncoder = context.getBean(BCryptPasswordEncoder.class);

        userService.save(User.builder()
                .login("admin")
                .password(passwordEncoder.encode("admin"))
                .role(Role.ADMIN)
                .enabled(true)
                .build()
        );
    }
}

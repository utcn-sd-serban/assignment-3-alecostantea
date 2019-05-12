package ro.utcn.alecostantea.assignment1.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ro.utcn.alecostantea.assignment1.model.User;
import ro.utcn.alecostantea.assignment1.service.UserService;


@Component
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class UserSeed implements CommandLineRunner {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if(userService.findAll().isEmpty()){
            userService.save(new User(0,"sunca",passwordEncoder.encode("feliata")));
            userService.save(new User(0,"ale",passwordEncoder.encode("sarmale")));
            userService.save(new User(0,"emanuel",passwordEncoder.encode("purcel")));
        }
    }
}

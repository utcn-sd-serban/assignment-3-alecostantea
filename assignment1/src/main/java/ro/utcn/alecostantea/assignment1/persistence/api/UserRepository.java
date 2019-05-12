package ro.utcn.alecostantea.assignment1.persistence.api;

import ro.utcn.alecostantea.assignment1.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save (User user);

    Optional<User> findById(int id);
    Optional<User> findByUsername(String username);

    void remove(User user);

    List<User> findAll();
}

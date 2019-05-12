package ro.utcn.alecostantea.assignment1.persistence.memory;

import ro.utcn.alecostantea.assignment1.model.User;
import ro.utcn.alecostantea.assignment1.persistence.api.UserRepository;

import java.util.*;

public class InMemoryUserRepository implements UserRepository {

    private int currentId = 1;
    private final Map<Integer, User> data = new HashMap<>();

    @Override
    public Optional<User> findByUsername(String username) {
        for(User user : data.values()){
            if(user.getUsername().equals(username)){
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    @Override
    public User save(User user) {

        if(user.getId() != 0){
            data.put(user.getId(), user);
        } else {
            user.setId(currentId++);
            data.put(user.getId(),user);
        }
        return user;
        
    }

    @Override
    public Optional<User> findById(int id) {

        return Optional.ofNullable(data.get(id));

    }

    @Override
    public void remove(User user) {

        data.remove(user.getId());

    }

    @Override
    public List<User> findAll() {

        return new ArrayList<>(data.values());

    }
}

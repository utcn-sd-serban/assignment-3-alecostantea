package ro.utcn.alecostantea.assignment1.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.utcn.alecostantea.assignment1.dto.UserDTO;
import ro.utcn.alecostantea.assignment1.model.User;
import ro.utcn.alecostantea.assignment1.persistence.api.RepositoryFactory;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final RepositoryFactory factory;
    @Transactional
    public UserDTO save(UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUserName());
        user.setPassword(userDTO.getPassword());

        return UserDTO.ofEntity(factory.createUserRepository().save(user));

    }

    @Transactional
    public User save(User user){
        return factory.createUserRepository().save(user);
    }
    @Transactional
    public Optional<User> findById(int id){

        return factory.createUserRepository().findById(id);
    }
    @Transactional
    public void remove(User user){

        factory.createUserRepository().remove(user);

    }
    @Transactional
    public List<UserDTO> findAll(){

        return factory.createUserRepository().findAll().stream().map(UserDTO::ofEntity).collect(Collectors.toList());
    }

    public User login(String username,String password){
        User user = factory.createUserRepository().findByUsername(username).get();
        if(user.getPassword().equals(password)){
            return user;
        } else {
            return null;
        }
    }


}

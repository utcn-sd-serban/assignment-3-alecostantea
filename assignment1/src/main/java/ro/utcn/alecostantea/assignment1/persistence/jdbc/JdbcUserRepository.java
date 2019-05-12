package ro.utcn.alecostantea.assignment1.persistence.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import ro.utcn.alecostantea.assignment1.model.User;
import ro.utcn.alecostantea.assignment1.persistence.api.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JdbcUserRepository implements UserRepository {
    private final JdbcTemplate template;
    @Override
    public User save(User user) {
        if(user.getId() == 0){
            insert(user);
        } else {
            update(user);
        }
        
        return user;
    }

    @Override
    public Optional<User> findById(int id) {

        List<User> users = template.query("SELECT * FROM user WHERE id = ?",
                (resultSet,i) -> new User(resultSet.getInt("id"),
                        resultSet.getString("username"), resultSet.getString("password")),
                id);
        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
        
        
    }
    @Override
    public Optional<User> findByUsername(String userName) {

        List<User> users = template.query("SELECT * FROM user WHERE username = ?",
                (resultSet,i) -> new User(resultSet.getInt("id"),
                        resultSet.getString("username"), resultSet.getString("password")),
                userName);
        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));


    }

    @Override
    public void remove(User user) {

        template.update("DELETE FROM user WHERE id = ?", user.getId());

    }

    @Override
    public List<User> findAll() {

        return template.query("SELECT * FROM user", (resultSet,i) ->
                new User(resultSet.getInt("id"), resultSet.getString("username"), resultSet.getString("password")));
    }


    private int insert(User user) {

        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.setTableName("user");
        insert.setGeneratedKeyName("id");
        Map<String, Object> data = new HashMap<>();
        data.put("username",user.getUsername());
        data.put("password", user.getPassword());
        return insert.executeAndReturnKey(data).intValue();


    }

    private void update(User user){
        template.update("UPDATE user SET username = ?, password = ? WHERE id = ?",
                user.getUsername(), user.getPassword(), user.getId());
    }

}

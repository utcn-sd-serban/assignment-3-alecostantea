package ro.utcn.alecostantea.assignment1.persistence.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ro.utcn.alecostantea.assignment1.model.Question;
import ro.utcn.alecostantea.assignment1.model.Tag;
import ro.utcn.alecostantea.assignment1.persistence.api.TagRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class JdbcTagRepository implements TagRepository {

    private final JdbcTemplate template;
    @Override
    public Tag save(Tag tag) {
        if(tag.getId() == 0){
            insert(tag);
        } else {
            update(tag);
        }

        return tag;
    }

    @Override
    public Optional<Tag> findById(int id) {

        List<Tag> tags = template.query("SELECT * FROM tag WHERE id = ?",
                (resultSet,i) -> new Tag(resultSet.getInt("id"),
                        resultSet.getString("name"), resultSet.getInt("questionId")),
                id);
        return tags.isEmpty() ? Optional.empty() : Optional.of(tags.get(0));

    }

    @Override
    public void remove(Tag tag) {

        template.update("DELETE FROM tag WHERE id = ?", tag.getId());

    }

    @Override
    public List<Tag> findAll() {

        return template.query("SELECT * FROM tag", (resultSet,i) ->
                new Tag(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("questionId")));
    }

    private int insert(Tag tag) {

        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.setTableName("tag");
        insert.setGeneratedKeyName("id");
        Map<String, Object> data = new HashMap<>();
        data.put("name",tag.getName());
        data.put("questionId", tag.getQuestionId());
        return insert.executeAndReturnKey(data).intValue();


    }

    private void update(Tag tag){
        template.update("UPDATE tag SET name = ?, questionId = ? WHERE id = ?",
                tag.getName(), tag.getQuestionId(), tag.getId());
    }

    @Override
    public List<Question> getQuestionsWithTag(Tag tag) {

       return template.query("SELECT * FROM question JOIN tag ON question.id = tag.questionId WHERE tag.id = ?",
                (resultSet,i) -> new Question(resultSet.getInt("id"),
                        resultSet.getString("title"), resultSet.getString("text"),resultSet.getString("author")),
               tag.getId());
    }

    @Override
    public Optional<Tag> findByName(String name) {

        List<Tag> tags = template.query("SELECT * FROM tag WHERE name = ?",
                (resultSet,i) -> new Tag(resultSet.getInt("id"),
                        resultSet.getString("name"), resultSet.getInt("questionId")),
                name);
        return tags.isEmpty() ? Optional.empty() : Optional.of(tags.get(0));

    }
}

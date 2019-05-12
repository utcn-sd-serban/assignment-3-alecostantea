package ro.utcn.alecostantea.assignment1.persistence.api;

import ro.utcn.alecostantea.assignment1.model.Question;
import ro.utcn.alecostantea.assignment1.model.Tag;

import java.util.List;
import java.util.Optional;

public interface TagRepository {

    Tag save (Tag tag);

    Optional<Tag> findById(int id);

    void remove(Tag tag);

    List<Tag> findAll();

    List<Question> getQuestionsWithTag(Tag tag);

    Optional<Tag> findByName(String name);

}

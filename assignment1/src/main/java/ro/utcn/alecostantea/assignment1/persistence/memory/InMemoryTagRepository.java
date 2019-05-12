package ro.utcn.alecostantea.assignment1.persistence.memory;

import ro.utcn.alecostantea.assignment1.model.Question;
import ro.utcn.alecostantea.assignment1.model.Tag;
import ro.utcn.alecostantea.assignment1.persistence.api.QuestionRepository;
import ro.utcn.alecostantea.assignment1.persistence.api.TagRepository;

import java.util.*;

public class InMemoryTagRepository implements TagRepository {
    private int currentId = 1;
    private final Map<Integer, Tag> data = new HashMap<>();
    private final QuestionRepository questionRepo ;

    public InMemoryTagRepository(QuestionRepository questionRepo){
        this.questionRepo = questionRepo;
    }

    @Override
    public Tag save(Tag tag) {

        if(tag.getId() != 0){
            data.put(tag.getId(), tag);
        } else {
            tag.setId(currentId++);
            data.put(tag.getId(),tag);
        }
        return tag;

    }

    @Override
    public Optional<Tag> findById(int id) {

        return Optional.ofNullable(data.get(id));

    }

    @Override
    public void remove(Tag tag) {

        data.remove(tag.getId());

    }

    @Override
    public List<Tag> findAll() {

        return new ArrayList<>(data.values());

    }

    @Override
    public List<Question> getQuestionsWithTag(Tag tag) {
        ArrayList<Question> questions = new ArrayList<>();
        for(Tag t : data.values()){
            if (tag.getName().equals(t.getName()))
            {
                Question question = questionRepo.findById(t.getQuestionId()).get();
                questions.add(question);
            }
        }
        return questions;
    }

    @Override
    public Optional<Tag> findByName(String name) {

        for(Tag tag : data.values()){
            if (tag.getName().equals(name))
            {
                return Optional.of(tag);
            }
        }
        return Optional.empty();
    }
}

package ro.utcn.alecostantea.assignment1.persistence.memory;

import ro.utcn.alecostantea.assignment1.model.Question;
import ro.utcn.alecostantea.assignment1.persistence.api.QuestionRepository;

import java.util.*;

public class InMemoryQuestionRepository implements QuestionRepository {

    private int currentId = 1;
    private final Map<Integer, Question> data = new HashMap<>();

    @Override
    public synchronized Question save(Question question) {
        if(question.getId() != 0){
            data.put(question.getId(), question);
        } else {
            question.setId(currentId++);
            data.put(question.getId(),question);
        }
        return question;
    }

    @Override
    public Optional<Question> findById(int id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public  synchronized void remove(Question question) {

        data.remove(question.getId());

    }

    @Override
    public List<Question> findAll() {

        return new ArrayList<>(data.values());
    }
}

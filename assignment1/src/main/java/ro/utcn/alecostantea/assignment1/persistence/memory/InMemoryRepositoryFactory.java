package ro.utcn.alecostantea.assignment1.persistence.memory;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ro.utcn.alecostantea.assignment1.persistence.api.QuestionRepository;
import ro.utcn.alecostantea.assignment1.persistence.api.RepositoryFactory;
import ro.utcn.alecostantea.assignment1.persistence.api.TagRepository;
import ro.utcn.alecostantea.assignment1.persistence.api.UserRepository;

@Component
@ConditionalOnProperty(name = "a1.repository-type", havingValue ="MEMORY")
public class InMemoryRepositoryFactory implements RepositoryFactory {

    private final QuestionRepository repository = new InMemoryQuestionRepository();
    private final UserRepository repository1 = new InMemoryUserRepository();
    private final TagRepository repository2 = new InMemoryTagRepository(repository);

    @Override
    public TagRepository createTagRepository() {
        return repository2;
    }

    @Override
    public QuestionRepository createQuestionRepository() {

        return repository;

    }

    @Override
    public UserRepository createUserRepository() {
        return repository1;
    }
}

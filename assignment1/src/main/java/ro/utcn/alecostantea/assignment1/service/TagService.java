package ro.utcn.alecostantea.assignment1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.utcn.alecostantea.assignment1.model.Question;
import ro.utcn.alecostantea.assignment1.model.Tag;
import ro.utcn.alecostantea.assignment1.persistence.api.RepositoryFactory;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TagService {
    private final RepositoryFactory factory;
    @Transactional
    public Tag save(Tag tag){

        return factory.createTagRepository().save(tag);

    }

    public Optional<Tag> findById(int id){

        return factory.createTagRepository().findById(id);
    }
    @Transactional
    public void remove(Tag tag){

        factory.createTagRepository().remove(tag);

    }
    @Transactional
    public List<Tag> findAll(){

        return factory.createTagRepository().findAll();
    }
    @Transactional
    public Tag findByName(String name){
        return factory.createTagRepository().findByName(name).get();
    }
    @Transactional
    public List<Question> getQuestionsWithTag(Tag tag){
        return factory.createTagRepository().getQuestionsWithTag(tag);
    }
    
}

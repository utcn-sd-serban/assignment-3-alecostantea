package ro.utcn.alecostantea.assignment1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.utcn.alecostantea.assignment1.command.AddQuestionCommand;
import ro.utcn.alecostantea.assignment1.command.Command;
import ro.utcn.alecostantea.assignment1.dto.QuestionDTO;
import ro.utcn.alecostantea.assignment1.model.Question;
import ro.utcn.alecostantea.assignment1.model.Tag;
import ro.utcn.alecostantea.assignment1.persistence.api.RepositoryFactory;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class QuestionService {

    private final RepositoryFactory factory;

    @Transactional
    public Question save(Question question){

       return factory.createQuestionRepository().save(question);

    }
    @Transactional
    public Optional<Question> findById(int id){

        return factory.createQuestionRepository().findById(id);
    }
    @Transactional
    public void remove(Question question){

        factory.createQuestionRepository().remove(question);

    }
    @Transactional
    public List<Question> findAll(){

        return factory.createQuestionRepository().findAll();
    }

    @Transactional
    public Command addQuestion(QuestionDTO dto){
        AddQuestionCommand command = new AddQuestionCommand();
        Question question = new Question();
        question.setId(dto.getId());
        question.setText(dto.getText());
        question.setTitle(dto.getTitle());
        question.setAuthor(dto.getAuthor());
        command.setQuestion(question);
        //question = save(question);
        command.setTags(dto.getTags());
       
        command.execute(factory);
        return command;

    }

    @Transactional
    public List<Question> filterTitle(String title){
        List<Question> questions = findAll();
        List<Question> rez = new ArrayList<>();
        for(Question q: questions){
            if(q.getTitle().contains(title)){
                rez.add(q);
            }
        }
        return rez;
    }

    @Transactional
    public List<Question> filterTag(String tag){
        Tag tagFound = factory.createTagRepository().findByName(tag).get();
        return factory.createTagRepository().getQuestionsWithTag(tagFound);
    }


}


package ro.utcn.alecostantea.assignment1.command;

import lombok.Data;
import ro.utcn.alecostantea.assignment1.model.Question;
import ro.utcn.alecostantea.assignment1.model.Tag;
import ro.utcn.alecostantea.assignment1.persistence.api.RepositoryFactory;

import java.util.Optional;

@Data
public class AddQuestionCommand implements Command {
    private Question question;
    private String tags;


    @Override
    public void execute(RepositoryFactory factory) {
        this.question = factory.createQuestionRepository().save(this.question);
        for(String t: tags.split(" ")){
            Optional<Tag> tag = factory.createTagRepository().findByName(t);
            if(!tag.isPresent()){
                factory.createTagRepository().save(new Tag(0,t,question.getId()));
            }
        }
    }
}

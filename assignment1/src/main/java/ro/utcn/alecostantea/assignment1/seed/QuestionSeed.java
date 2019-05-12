package ro.utcn.alecostantea.assignment1.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ro.utcn.alecostantea.assignment1.model.Question;
import ro.utcn.alecostantea.assignment1.service.QuestionService;

@RequiredArgsConstructor
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class QuestionSeed implements CommandLineRunner {

    private final QuestionService questionsService;

    @Override
    public void run(String... args) throws Exception {
        if(questionsService.findAll().isEmpty()){
            questionsService.save(new Question(0,"Mare intrebare","dece am venit la facultatea asta","ale"));
        }
    }
}

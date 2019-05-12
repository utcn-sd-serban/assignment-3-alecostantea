package ro.utcn.alecostantea.assignment1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.utcn.alecostantea.assignment1.command.Command;
import ro.utcn.alecostantea.assignment1.dto.QuestionDTO;
import ro.utcn.alecostantea.assignment1.model.Question;
import ro.utcn.alecostantea.assignment1.service.QuestionService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    private List<Command> commandList = new ArrayList<>();

    @GetMapping("/questions")
    public List<Question> readAll() {
        return questionService.findAll();
    }

    @PostMapping("/questions")
    public void addQuestion(@RequestBody QuestionDTO dto){
        //System.out.println("java" + dto);

        Command command = questionService.addQuestion(dto);
        commandList.add(command);
    }
    @GetMapping("/questions/title/{title}")
    public List<Question> filterTitle(@PathVariable String title){
        return questionService.filterTitle(title);
    }

    @GetMapping("/questions/tag/{tag}")
    public List<Question> filterTag(@PathVariable String tag){
        return questionService.filterTag(tag);
    }

}

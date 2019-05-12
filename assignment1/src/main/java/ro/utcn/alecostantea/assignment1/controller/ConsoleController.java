package ro.utcn.alecostantea.assignment1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import ro.utcn.alecostantea.assignment1.model.Question;
import ro.utcn.alecostantea.assignment1.model.Tag;
import ro.utcn.alecostantea.assignment1.model.User;
import ro.utcn.alecostantea.assignment1.service.QuestionService;
import ro.utcn.alecostantea.assignment1.service.TagService;
import ro.utcn.alecostantea.assignment1.service.UserService;

import java.util.List;
import java.util.Scanner;


//@Component
@RequiredArgsConstructor

public class ConsoleController implements CommandLineRunner {

    private final QuestionService questionService;
    private final UserService userService;
    private final TagService tagService;
    Scanner scanner = new Scanner(System.in);
    User user;
    @Override
    public void run(String... args) throws Exception {

        boolean done = false;
        //put one user in the database to see if we can login
       // userService.save(new User(0,"alexandra","0000"));
        while(!done){
            print("Enter a command: ");
            String command = scanner.next().trim();
            done = handleCommand(command);
        }


    }

    private boolean handleCommand(String command){
        boolean done = false;
        switch(command) {
            case "list" :
                List<Question> list =questionService.findAll();
                if (list.isEmpty()) {
                    print("No questions!");
                } else {
                    list.forEach(i -> print(i.toString()));
                }
                break;

            case "exit" :
                done = true;
                break;

            case "add" :
                print("Enter title : ");
                String title = scanner.nextLine().trim();
                title = scanner.nextLine().trim();
                print("Enter text : ");
                String text = scanner.nextLine().trim();
                print("enter tags:");
                String[] tags = scanner.nextLine().split(" ");
                Question question = new Question(0,title,text,user.getUsername());
                questionService.save(question);
                for(String t : tags){
                    tagService.save(new Tag(0,t,question.getId()));
                }
                break;

            case "login":
                print("Username");
                String username = scanner.next().trim();
                print("password:");
                String password = scanner.next().trim();
                user = userService.login(username,password);
                if(user == null){
                    print("wrong username or password");
                } else {
                    print("Logged in as:");
                    print(user.toString());
                }
                break;


            case "search":
                print("Search by title or tag?");
                String criteria = scanner.next().trim();
                switch(criteria){
                    case "tag":
                        print("tag name:");
                        String tagName = scanner.next().trim();

                        Tag tag = tagService.findByName(tagName);
                        tagService.findAll().forEach(t -> print(t.toString()));
                        List<Question> questions = tagService.getQuestionsWithTag(tag);
                        questions.forEach(q -> print(q.toString()));
                        break;
                    case "title":
                        print("Enter title to search");
                        title = scanner.nextLine();
                        title = scanner.nextLine();

                       questions = questionService.findAll();
                        for(Question q : questions){
                            if(q.getTitle().indexOf(title) > -1){
                                print(q.toString());
                            }
                        }

                        break;

                    default:
                        break;
                }
                break;


            default :
                print("Unknown command!");
                break;


        }

        return done;
    }

    private void print(String string){
        System.out.println(string);
    }
}

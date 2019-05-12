package ro.utcn.alecostantea.assignment1.dto;

import lombok.Data;

@Data
public class QuestionDTO {
    private int id;
    private String title;
    private String text;
    private String author;
    private String tags;
}

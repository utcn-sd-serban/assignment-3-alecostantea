package ro.utcn.alecostantea.assignment1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Question {
    private int id;
    private String title;
    private String text;
    private String author;
}

package mk.finki.ukim.emt.emt.model.dto;

import jdk.jfr.Category;
import lombok.Data;
import mk.finki.ukim.emt.emt.model.Book;

@Data
public class BookDto {

    private String name;
    private String category;
    private Long authorId;
    private Integer availableCopies;

    public BookDto() {
    }

    public BookDto(String name, String category, Long authorId, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.authorId = authorId;
        this.availableCopies = availableCopies;
    }
}

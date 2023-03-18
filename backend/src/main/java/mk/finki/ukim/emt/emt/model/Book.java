package mk.finki.ukim.emt.emt.model;

import jakarta.persistence.*;
import lombok.Data;
import mk.finki.ukim.emt.emt.model.enumerations.BookCategory;

import java.util.Objects;

@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(columnDefinition="text")
    String name;

    @Enumerated(EnumType.STRING)
    BookCategory category;

    @ManyToOne
    Author author;

    Integer availableCopies;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return getId().equals(book.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name);
        sb.append("\nCategory: ").append(category);
        sb.append("\nAuthor: ").append(author);
        sb.append("\nAvailable copies: ").append(availableCopies);
        return sb.toString();
    }
}

package mk.finki.ukim.emt.emt.model;

import jakarta.persistence.*;
import lombok.Data;
import mk.finki.ukim.emt.emt.model.enumerations.BookCategory;

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


}

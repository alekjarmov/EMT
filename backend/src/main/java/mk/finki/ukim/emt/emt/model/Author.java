package mk.finki.ukim.emt.emt.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(columnDefinition="text")
    String name;
    @Column(columnDefinition="text")
    String surname;
    @ManyToOne
    Country country;

    public Author() {
    }

    public Author(String name, String surname, Country country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}

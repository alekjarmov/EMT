package mk.finki.ukim.emt.emt.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(columnDefinition="text")
    String name;
    @Column(columnDefinition="text")
    String continent;

    public Country() {
    }

    public Country(String name, String continent) {
        this.name = name;
        this.continent = continent;
    }
}

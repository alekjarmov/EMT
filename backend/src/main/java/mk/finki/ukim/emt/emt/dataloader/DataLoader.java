package mk.finki.ukim.emt.emt.dataloader;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.emt.emt.model.Author;
import mk.finki.ukim.emt.emt.model.Book;
import mk.finki.ukim.emt.emt.model.Country;
import mk.finki.ukim.emt.emt.model.enumerations.BookCategory;
import mk.finki.ukim.emt.emt.repository.AuthorRepository;
import mk.finki.ukim.emt.emt.repository.BookRepository;
import mk.finki.ukim.emt.emt.repository.CountryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final CountryRepository countryRepository;

    public DataLoader(AuthorRepository authorRepository, BookRepository bookRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.countryRepository = countryRepository;
    }

    @PostConstruct
    public void loadData() {
        if (!bookRepository.findAll().isEmpty()) {
            return;
        }
        Country country = new Country("Country 1", "Continent 1");
        countryRepository.save(country);

        for (int i = 1; i < 10; i++) {
            Author author = new Author("Author " + i, "Surname " + i, country);
            authorRepository.save(author);
        }
        for (int i = 0; i < 10; i++) {
            List<Author> authors = authorRepository.findAll();
            for (Author author : authors) {
                String id = author.getId().toString();
                Book newBook = new Book("Book " + i + " by " + id,
                        BookCategory.values()[i % BookCategory.values().length],
                        author, 10);
                bookRepository.save(newBook);
            }
        }

    }

}

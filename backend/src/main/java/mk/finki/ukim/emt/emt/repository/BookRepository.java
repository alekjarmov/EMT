package mk.finki.ukim.emt.emt.repository;

import mk.finki.ukim.emt.emt.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    // no need to add findAll() because it is already implemented in JpaRepository
    public List<Book> findAllByName (String name);
}

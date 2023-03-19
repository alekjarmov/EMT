package mk.finki.ukim.emt.emt.repository;

import mk.finki.ukim.emt.emt.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    public Optional<Author> findById(Long id);
}

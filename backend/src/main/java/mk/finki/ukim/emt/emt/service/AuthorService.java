package mk.finki.ukim.emt.emt.service;

import mk.finki.ukim.emt.emt.model.Author;

import java.util.Optional;

public interface AuthorService {
    Optional<Author> findById(Long id);
}

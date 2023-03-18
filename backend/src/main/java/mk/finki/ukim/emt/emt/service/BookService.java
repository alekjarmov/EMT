package mk.finki.ukim.emt.emt.service;

import mk.finki.ukim.emt.emt.model.Author;
import mk.finki.ukim.emt.emt.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    public List<Book> findAll();
    public Book save(String name,String category, Long authorId, Integer remainingCopies, Book book);
    public Optional<Book> findById(Long id);
    public void deleteById(Long id);
    public void rentBook(Long bookId);
}

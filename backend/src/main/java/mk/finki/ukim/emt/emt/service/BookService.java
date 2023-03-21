package mk.finki.ukim.emt.emt.service;

import mk.finki.ukim.emt.emt.model.Author;
import mk.finki.ukim.emt.emt.model.Book;
import mk.finki.ukim.emt.emt.model.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {
    public List<Book> findAll();

    public Book save(BookDto bookDto);

    public Book save(BookDto bookDto, Long id);

    public Book save(Book book);

    public Optional<Book> findById(Long id);

    public void deleteById(Long id);

    public void rentBook(Long bookId);

    public Book dtoToBook(BookDto bookDto);
}

package mk.finki.ukim.emt.emt.service.impl;

import jakarta.transaction.Transactional;
import mk.finki.ukim.emt.emt.model.Author;
import mk.finki.ukim.emt.emt.model.Book;
import mk.finki.ukim.emt.emt.model.enumerations.BookCategory;
import mk.finki.ukim.emt.emt.model.exceptions.AuthorDoesNotExistException;
import mk.finki.ukim.emt.emt.model.exceptions.BookDoesNotExistException;
import mk.finki.ukim.emt.emt.repository.BookRepository;
import mk.finki.ukim.emt.emt.service.AuthorService;
import mk.finki.ukim.emt.emt.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;

        this.authorService = authorService;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    @Transactional
    public Book save(String name, String category, Long authorId, Integer remainingCopies, Book book) {
        if (book == null) {
            book = new Book();
        }
        Author author = authorService.findById(authorId).orElseThrow(AuthorDoesNotExistException::new);
        book.setName(name);
        book.setCategory(BookCategory.valueOf(category));
        book.setAvailableCopies(remainingCopies);
        book.setAuthor(author);
        return bookRepository.save(book);
    }


    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void rentBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(BookDoesNotExistException::new);
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);
    }
}

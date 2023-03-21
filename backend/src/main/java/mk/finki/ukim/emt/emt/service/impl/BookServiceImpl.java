package mk.finki.ukim.emt.emt.service.impl;

import jakarta.transaction.Transactional;
import mk.finki.ukim.emt.emt.model.Author;
import mk.finki.ukim.emt.emt.model.Book;
import mk.finki.ukim.emt.emt.model.dto.BookDto;
import mk.finki.ukim.emt.emt.model.enumerations.BookCategory;
import mk.finki.ukim.emt.emt.model.exceptions.AuthorDoesNotExistException;
import mk.finki.ukim.emt.emt.model.exceptions.BookDoesNotExistException;
import mk.finki.ukim.emt.emt.model.exceptions.NoBooksAvailable;
import mk.finki.ukim.emt.emt.repository.BookRepository;
import mk.finki.ukim.emt.emt.service.AuthorService;
import mk.finki.ukim.emt.emt.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Book save(BookDto bookDto) { // used for adding a new book
        Book book = this.dtoToBook(bookDto);
        return this.bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book save(BookDto bookDto, Long id) { //used for editing a book
        Author author = authorService.findById(bookDto.getAuthorId()).orElseThrow(AuthorDoesNotExistException::new);
        Book book = bookRepository.findById(id).orElseThrow(BookDoesNotExistException::new);
        book.setName(bookDto.getName());
        book.setCategory(BookCategory.valueOf(bookDto.getCategory()));
        book.setAvailableCopies(bookDto.getAvailableCopies());
        book.setAuthor(author);
        return bookRepository.save(book);
    }


    @Override
    public Book save(Book book) {
        Long id = book.getId();
        if (id == null)
        {
            return bookRepository.save(book); //adding a new book here
        }
        // if the book already exists, we need to update it
        Book editBook = bookRepository.findById(id).orElseThrow(BookDoesNotExistException::new);
        editBook.setName(book.getName());
        editBook.setCategory(book.getCategory());
        editBook.setAvailableCopies(book.getAvailableCopies());
        editBook.setAuthor(book.getAuthor());
        return bookRepository.save(editBook);
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
        if (book.getAvailableCopies() == 0)
        {
            throw new NoBooksAvailable();
        }
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);
    }

    @Override
    public Book dtoToBook(BookDto bookDto) {
        Author author = authorService.findById(bookDto.getAuthorId()).orElseThrow(AuthorDoesNotExistException::new);
        return new Book(null, bookDto.getName(), BookCategory.valueOf(bookDto.getCategory()),
                author, bookDto.getAvailableCopies());
    }

    @Override
    public Page<Book> findAllByPage(Pageable pageInfo) {
        return bookRepository.findAll(pageInfo);
    }


}

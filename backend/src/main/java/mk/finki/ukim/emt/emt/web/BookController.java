package mk.finki.ukim.emt.emt.web;

import mk.finki.ukim.emt.emt.model.Book;
import mk.finki.ukim.emt.emt.model.dto.BookDto;
import mk.finki.ukim.emt.emt.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/list")
    public List<Book> listBooks() {
        return bookService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Book> addBook (@RequestBody BookDto book) {
        try {
            Book newBook = bookService.save(book);
            return ResponseEntity.ok(newBook);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook (@PathVariable Long id) {
        try {
            return ResponseEntity.ok(bookService.findById(id).orElseThrow());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> editBook (@PathVariable Long id, @RequestBody BookDto book) {
        try {
            return ResponseEntity.ok(bookService.save(book, id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Book> deleteBook (@PathVariable Long id) {
        try {
            bookService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping ("/rent/{id}")
    ResponseEntity<Book> rentBook (@PathVariable Long id) {
        try {
            bookService.rentBook(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}

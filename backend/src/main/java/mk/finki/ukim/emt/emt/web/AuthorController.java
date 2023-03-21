package mk.finki.ukim.emt.emt.web;

import mk.finki.ukim.emt.emt.model.Author;
import mk.finki.ukim.emt.emt.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/authors")
public class AuthorController {

    AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/list")
    public List<Author> listAuthors() {
        return authorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthor(@PathVariable Long id) {
        Author author = authorService.findById(id).orElse(null);
        if (author == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(author);
    }
}

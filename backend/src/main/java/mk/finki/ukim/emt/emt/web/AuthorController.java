package mk.finki.ukim.emt.emt.web;

import mk.finki.ukim.emt.emt.model.Author;
import mk.finki.ukim.emt.emt.service.AuthorService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

package mk.finki.ukim.emt.emt.model.exceptions;

public class AuthorDoesNotExistException extends RuntimeException{
    public AuthorDoesNotExistException() {
        super("Author does not exist");
    }
}

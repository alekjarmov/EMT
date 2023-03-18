package mk.finki.ukim.emt.emt.model.exceptions;

public class BookDoesNotExistException extends RuntimeException{
    public BookDoesNotExistException() {
        super("Book does not exist");
    }
}

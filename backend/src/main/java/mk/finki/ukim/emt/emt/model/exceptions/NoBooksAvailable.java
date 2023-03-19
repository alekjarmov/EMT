package mk.finki.ukim.emt.emt.model.exceptions;

public class NoBooksAvailable extends RuntimeException{
    public NoBooksAvailable() {
        super("No books available");
    }
}

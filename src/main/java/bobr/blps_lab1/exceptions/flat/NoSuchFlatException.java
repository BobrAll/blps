package bobr.blps_lab1.exceptions.flat;

public class NoSuchFlatException extends RuntimeException {
    public NoSuchFlatException(Integer id) {
        super(String.format("Flat with id=%d is doesn't exist.", id));
    }
}

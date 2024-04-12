package ac.su.springmvcinclass.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id) {
        super("No such user with id:" + id);
    }
}

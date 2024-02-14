package bobr.blps_lab1.exceptions.user;

import bobr.blps_lab1.exceptions.ExceptionBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(value = {
            AlreadyHaveSuperuserPermissionsException.class,
            UserNotSubscribedException.class
    })
    public ResponseEntity<Object> handleSuperuserExceptions(
            RuntimeException e) {
        HttpStatus status = HttpStatus.CONFLICT;

        return new ResponseEntity<>(new ExceptionBody(e.getMessage(), status), status);
    }
}
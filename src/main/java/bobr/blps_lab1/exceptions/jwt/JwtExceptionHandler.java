package bobr.blps_lab1.exceptions.jwt;

import bobr.blps_lab1.exceptions.ExceptionBody;
import bobr.blps_lab1.exceptions.flat.NoSuchFlatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class JwtExceptionHandler {

    @ExceptionHandler(value = {JwtNotFoundException.class})
    public ResponseEntity<Object> handleNoSuchFlatException(JwtNotFoundException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        return new ResponseEntity<>(new ExceptionBody(e.getMessage(), status), status);
    }
}

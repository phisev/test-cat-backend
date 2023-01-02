package phi.sev.catsbackend.adapters.web;

import java.util.NoSuchElementException;
import java.util.UUID;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Log4j2
@ControllerAdvice
public class ControllerAdviceHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NoSuchElementException.class})
    protected ResponseEntity<ErrorResponse> handleArgumentExceptions(Exception ex) {
        UUID referenceId = UUID.randomUUID();
        log.warn(referenceId + " Data Not Found Exception!", ex);
        return new ResponseEntity<>(new ErrorResponse(referenceId, HttpStatus.NOT_FOUND.value(), ex.getMessage()), HttpStatus.NOT_FOUND);
    }


}

record ErrorResponse(UUID referenceId, int httpCode, String description) {

}

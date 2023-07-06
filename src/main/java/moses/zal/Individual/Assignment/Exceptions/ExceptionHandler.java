package moses.zal.Individual.Assignment.Exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ExceptionHandler {

  @org.springframework.web.bind.annotation.ExceptionHandler
  public ResponseEntity<String> handleException (Exception exception) {
    if (exception instanceof DataIntegrityViolationException)
      return new ResponseEntity<>("Process failed! One or more fields that must be unique are not so in your request!",
              HttpStatus.CONFLICT);
    else if (exception instanceof ConstraintViolationException) {
      StringBuilder errors = new StringBuilder("Process failed due to the following error(s):\n");
      for (ConstraintViolation<?> violation : ((ConstraintViolationException) exception).getConstraintViolations())
        errors.append("* ").append(violation.getMessage()).append("\n");
      return ResponseEntity.badRequest().body(errors.toString());
    }
    else if (exception instanceof ResourceNotFoundException) {
      return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
    else
      return ResponseEntity.badRequest().body("Process failed!\n" + exception.getMessage());
  }
}

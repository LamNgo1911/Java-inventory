package integrify.inventory.presentation.errorHandlers;

import integrify.inventory.presentation.shared.ErrorEntity;
import integrify.inventory.presentation.shared.ErrorResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponseEntity> handleAllExceptions(Exception ex){
        ErrorResponseEntity errorResponseEntity = new ErrorResponseEntity();
        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setMessage("An unexpected error occurred: " + ex.getMessage());
        errorEntity.setField("Unknown");
        List<ErrorEntity> errors = new ArrayList<>();
        errors.add(errorEntity);
        errorResponseEntity.setErrors(errors);

        return new ResponseEntity<>(errorResponseEntity, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseEntity> handleValidationExceptions(MethodArgumentNotValidException ex){
        List<ErrorEntity> errors = new ArrayList<>();
        for(FieldError fieldError : ex.getBindingResult().getFieldErrors()){
            ErrorEntity errorEntity = new ErrorEntity();
            errorEntity.setField(fieldError.getField());
            errorEntity.setMessage(fieldError.getField() + " " + fieldError.getDefaultMessage());

            errors.add(errorEntity);
        }
        ErrorResponseEntity errorResponseEntity = new ErrorResponseEntity();
        errorResponseEntity.setErrors(errors);

        return new ResponseEntity<>(errorResponseEntity, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponseEntity> handleResourceNotFoundException(Exception ex) {
        ErrorEntity errorEntity = ErrorEntity.builder()
                .field("Resource")
                .message(ex.getMessage())
                .build();

        ErrorResponseEntity errorResponseEntity = ErrorResponseEntity.builder()
                .errors(List.of(errorEntity))
                .build();


        return new ResponseEntity<>(errorResponseEntity, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseEntity> handleBadRequestException(Exception ex) {
        ErrorEntity errorEntity = ErrorEntity.builder()
                .field("Request")
                .message(ex.getMessage())
                .build();

        ErrorResponseEntity errorResponseEntity = ErrorResponseEntity.builder()
                .errors(List.of(errorEntity))
                .build();

        return new ResponseEntity<>(errorResponseEntity, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ErrorResponseEntity> handleForbiddenException(Exception ex) {
        ErrorEntity errorEntity = ErrorEntity.builder()
                .field("Forbidden")
                .message(ex.getMessage())
                .build();

        ErrorResponseEntity errorResponseEntity = ErrorResponseEntity.builder()
                .errors(List.of(errorEntity))
                .build();

        return new ResponseEntity<>(errorResponseEntity, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorResponseEntity> handleUnauthorizedException(Exception ex) {
        ErrorEntity errorEntity = ErrorEntity.builder()
                .field("Authentication")
                .message(ex.getMessage())
                .build();

        ErrorResponseEntity errorResponseEntity = ErrorResponseEntity.builder()
                .errors(List.of(errorEntity))
                .build();

        return new ResponseEntity<>(errorResponseEntity, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(OutOfStockException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseEntity> handleOutOfStockException(Exception ex) {
        ErrorEntity errorEntity = ErrorEntity.builder()
                .field("Quantity")
                .message(ex.getMessage())
                .build();

        ErrorResponseEntity errorResponseEntity = ErrorResponseEntity.builder()
                .errors(List.of(errorEntity))
                .build();

        return new ResponseEntity<>(errorResponseEntity, HttpStatus.BAD_REQUEST);
    }
}

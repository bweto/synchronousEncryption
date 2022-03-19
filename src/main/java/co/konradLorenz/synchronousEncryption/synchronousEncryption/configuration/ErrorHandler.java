package co.konradLorenz.synchronousEncryption.synchronousEncryption.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value =  Exception.class)
    public ResponseEntity<Object> generalException(Exception exception){
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value =  RuntimeException.class)
    public ResponseEntity<Object> runtimeException(RuntimeException exception){
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

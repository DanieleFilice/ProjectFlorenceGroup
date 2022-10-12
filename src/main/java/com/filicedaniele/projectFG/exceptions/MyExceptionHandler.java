package com.filicedaniele.projectFG.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({UserAlreadyExistException.class})
    public ResponseEntity<?> userAlreadyExist(RuntimeException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("l'email dell'utente che stai inserendo esiste già");
    }

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<?> userNotFound(RuntimeException e){
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("l'email dell'utente che vuoi modificare non esiste");
    }

    @ExceptionHandler({UsersNotFound.class})
    public ResponseEntity<?> usersNotFound(RuntimeException e){
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("non sono presenti utenti");
    }
    @ExceptionHandler({MalformedFileException.class})
    public ResponseEntity<?> malformedFile(RuntimeException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Il file .csv non è nel formato atteso");
    }

}

package br.ueg.personalsystem.exception;

import br.ueg.genericarchitecture.enums.ApiErrorEnum;
import br.ueg.genericarchitecture.exception.ApiResponseExceptionHandler;
import br.ueg.genericarchitecture.exception.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomHandlerException extends ApiResponseExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageResponse> handleException(Exception ex) {
        ex.printStackTrace();
        MessageResponse messageResponse = mountMessageResponse(HttpStatus.BAD_REQUEST, ApiErrorEnum.GENERAL);

        return ResponseEntity.status(messageResponse.getStatusCode()).body(messageResponse);
    }
}

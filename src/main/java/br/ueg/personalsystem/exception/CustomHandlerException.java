package br.ueg.personalsystem.exception;

import br.ueg.personalsystem.base.enums.ApiErrorEnum;
import br.ueg.personalsystem.base.exception.ApiResponseExceptionHandler;
import br.ueg.personalsystem.base.exception.MessageResponse;
import br.ueg.personalsystem.enums.ErrorEnum;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomHandlerException extends ApiResponseExceptionHandler {

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<MessageResponse> handleException(FeignException ex) {
        ex.printStackTrace();
        MessageResponse messageResponse = mountMessageResponse(HttpStatus.BAD_REQUEST, ErrorEnum.GENERAL_ERROR);

        return ResponseEntity.status(messageResponse.getStatusCode()).body(messageResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageResponse> handleException(Exception ex) {
        ex.printStackTrace();
        MessageResponse messageResponse = mountMessageResponse(HttpStatus.BAD_REQUEST, ApiErrorEnum.GENERAL);

        return ResponseEntity.status(messageResponse.getStatusCode()).body(messageResponse);
    }
}

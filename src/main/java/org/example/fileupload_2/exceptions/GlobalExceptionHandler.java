package org.example.fileupload_2.exceptions;

import org.example.fileupload_2.dtos.InvalidTypeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UploadInvalidTypeException.class)
    public ResponseEntity<InvalidTypeResponse> uploadInvalidTypeExceptionHandler(UploadInvalidTypeException ex) {
        InvalidTypeResponse response = new InvalidTypeResponse(ex.getMessage(), ex.getFileType());

        return ResponseEntity.
                badRequest()
                .body(response);
    }

    @ExceptionHandler(SaveFileException.class)
    public ResponseEntity<InvalidTypeResponse> saveFileExceptionHandler(SaveFileException ex) {
        InvalidTypeResponse response = new InvalidTypeResponse(ex.getMessage(), ex.getFileType());

        return ResponseEntity.
                internalServerError()
                .body(response);
    }
}

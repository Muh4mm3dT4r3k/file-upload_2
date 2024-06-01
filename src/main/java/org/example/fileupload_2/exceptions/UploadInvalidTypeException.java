package org.example.fileupload_2.exceptions;

import lombok.Getter;

@Getter
public class UploadInvalidTypeException extends RuntimeException{
    private final String fileType;
    public UploadInvalidTypeException(String message, String fileType) {
        super(message);
        this.fileType = fileType;
    }

}


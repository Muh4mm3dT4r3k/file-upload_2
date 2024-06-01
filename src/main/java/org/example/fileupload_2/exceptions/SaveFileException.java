package org.example.fileupload_2.exceptions;

import lombok.Getter;

@Getter
public class SaveFileException extends RuntimeException{
    private String fileType;
    public SaveFileException(String message, String fileType){
        super(message);
        this.fileType = fileType;
    }
}

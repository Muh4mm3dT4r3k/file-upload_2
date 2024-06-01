package org.example.fileupload_2.dtos;

public record FileUploadResponse (
        String fileName,
        String fileDownloadUri,
        String fileType,
        long size
){
}

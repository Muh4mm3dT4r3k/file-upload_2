package org.example.fileupload_2.controllers;

import lombok.RequiredArgsConstructor;
import org.example.fileupload_2.dtos.FileUploadResponse;
import org.example.fileupload_2.services.FileUploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class UploadFileController {

    private final FileUploadService fileUploadService;


    @PostMapping("upload-image")
    public ResponseEntity<FileUploadResponse> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        return fileUploadService.uploadImage(file);
    }
}

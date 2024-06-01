package org.example.fileupload_2.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.example.fileupload_2.dtos.FileUploadResponse;
import org.example.fileupload_2.exceptions.SaveFileException;
import org.example.fileupload_2.exceptions.UploadInvalidTypeException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileUploadService {
    private static final ArrayList<String> IMAGE_ALLOWED_TYPES =
            new ArrayList<>(List.of("image/jpeg", "image/png", "image/jpg"));

    public ResponseEntity<FileUploadResponse> uploadImage(MultipartFile file) {
        Path uploadPath = Paths.get("uploads/images");
        String imageNewName;
        String type = file.getContentType()
                .substring(file.getContentType().indexOf("/") + 1);
        if (!checkValidImageType(file.getContentType())) {
            throw new UploadInvalidTypeException("Invalid Type Only. jpeg, jpg and png are allowed.",
                    type);
        }

        try {
            imageNewName = saveBinaryFiles(file, uploadPath);
        }catch (IOException ex) {
            throw new SaveFileException("sorry can't save file now. ", type);
        }

        return ResponseEntity
                .ok()
                .body(new FileUploadResponse(
                        imageNewName,
                        "api/v1/files/download/" + imageNewName,
                        type,
                        file.getSize()
                ));
    }




    /**
     * This method for raw and binary files such as images, audios and .ect
     * */

    private String saveBinaryFiles(MultipartFile file, Path uploadPath) throws IOException {
        String type = file.getContentType()
                .substring(file.getContentType().indexOf("/") + 1);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileNewName = RandomStringUtils.randomAlphanumeric(20)  + "." + type;

        try (
                var inputStream = file.getInputStream();
                ){
            Path filePath = uploadPath.resolve(fileNewName);
            System.out.println(filePath.toString());
            Files.copy(inputStream, filePath);

        }
        return fileNewName;

    }


    private void saveCharactersFiles(MultipartFile file, Path uploadPath) {
        // TODO: implement this method for save text files with Characters InputStream.
    }


        private boolean checkValidImageType(String type) {
        return IMAGE_ALLOWED_TYPES.contains(type);
    }
}

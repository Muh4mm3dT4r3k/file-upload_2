package org.example.fileupload_2.dtos;

public record InvalidTypeResponse(
        String message,
        String typeProvided
) {
}

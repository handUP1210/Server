package com.dev.handup.controller;

import com.dev.handup.domain.Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class UploadController {

    private final Uploader uploader;

    @PostMapping("/api/v1/upload")
    public String upload(@RequestParam("data") MultipartFile file) throws IOException {
        return uploader.upload(file, "build"); // S3 bucket의 static/ 폴더를 지정한 것.
    }
}

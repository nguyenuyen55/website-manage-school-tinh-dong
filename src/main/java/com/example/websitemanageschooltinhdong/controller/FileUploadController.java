package com.example.websitemanageschooltinhdong.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

@RestController
@RequestMapping("/api")
public class FileUploadController {
@Value("${file.upload-dir}")
String FILE_DIRECTORY;

    @PostMapping("/uploadFile")
    public ResponseEntity<Object> fileUpload(@RequestParam("File")MultipartFile file) throws IOException {
        File myFile = new File(FILE_DIRECTORY+ new Date().getTime()+file.getOriginalFilename());
        myFile.createNewFile();
        FileOutputStream fos= new FileOutputStream(myFile);
        fos.write(file.getBytes());
        fos.close();
        return new ResponseEntity<>("Uploas file susscessful", HttpStatus.OK);
    }
//    public ResponseEntity<>
}

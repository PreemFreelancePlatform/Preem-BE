//package com.bzwilson.bflp.controllers;
//
//import com.bzwilson.bflp.repositories.ImageRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.ResponseEntity.BodyBuilder;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/image")
//public class ImageController {
//
//    @Autowired
//    private ImageRepo imageRepo;
//
//
//    @PostMapping("/upload")
//    public BodyBuilder uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
//        System.out.println("Original Image Byte Size - " + file.getBytes().length);
//        ImageModel img = new ImageModel(file.getOriginalFilename(), file.getContentType(),
//                compressBytes(file.getBytes()));
//        imageRepo.save(img);
//        return ResponseEntity.status(HttpStatus.OK);
//
//    }
//
//    @GetMapping(path = "/get/{imageFile}")
//    public ImageModel getImage(@PathVariable("imageFile") String imageName) throws IOException {
//        final Optional<ImageModel> retrievedImage = imageRepo.findByName(imageName);
//        ImageModel img = new ImageModel(retrievedImage.get().getName(), retrievedImage.get().getType(),
//                decompressBytes(retrievedImage.get().getPicByte()));
//        return img;
//    }
//
//
//}

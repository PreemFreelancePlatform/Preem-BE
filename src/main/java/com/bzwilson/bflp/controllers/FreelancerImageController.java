//package com.bzwilson.bflp.controllers;
//
//import com.bzwilson.bflp.models.Freelancer;
//import com.bzwilson.bflp.models.FreelancerImageModel;
//import com.bzwilson.bflp.services.Freelancer.FreelancerService;
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
//@RequestMapping("/freelancer")
//public class
//FreelancerImageController {
//
//    @Autowired
//    private FreelancerService freelancerServices;
//
//
//        @PostMapping("/upload/{id}")
//        public BodyBuilder uploadImage(@RequestParam("imageFile") MultipartFile file, @PathVariable
//                long id) throws IOException {
//            Freelancer guy = freelancerServices.FindFreelancerById(id);
//            FreelancerImageModel img = new FreelancerImageModel(file.getOriginalFilename(), file.getContentType(),
//                    file.getBytes());
//            guy.setImage(img);
//            freelancerServices.save(guy);
//            return ResponseEntity.status(HttpStatus.OK);
//
//        }
//
////    @GetMapping(path = "/get/{imageFile}")
////    public ImageModel getImage(@PathVariable("imageFile") String imageName) throws IOException {
////        final Optional<ImageModel> retrievedImage = imageRepo.findByName(imageName);
////        ImageModel img = new ImageModel(retrievedImage.get().getName(), retrievedImage.get().getType(),
////                decompressBytes(retrievedImage.get().getPicByte()));
////        return img;
////    }
//
//
//}

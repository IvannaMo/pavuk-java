package com.needleandstitch.pavuk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.needleandstitch.pavuk.model.Image;
import com.needleandstitch.pavuk.model.ImageRequest;
import com.needleandstitch.pavuk.service.ImageService;


@Controller
@RequestMapping("/clothing-item-images")
@CrossOrigin
public class ImageController {
	@Autowired
	private ImageService imageService;


	@PostMapping("/save")
	public ResponseEntity<Image> saveImage(@RequestBody ImageRequest imageRequest) {
		 try {
			 Image image = imageService.saveImage(imageRequest);
			 return ResponseEntity.ok(image);
		 } catch (Exception e) {
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	     }
    }
}

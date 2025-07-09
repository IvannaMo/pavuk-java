package com.needleandstitch.pavuk.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.needleandstitch.pavuk.model.Image;
import com.needleandstitch.pavuk.model.ImageRequest;
import com.needleandstitch.pavuk.repository.ImageRepository;


@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    @PersistenceContext
    private EntityManager entityManager;

    
    @Transactional
    public Image saveImage(ImageRequest imageRequest) throws Exception {
    	String fileName = imageRequest.getName() + "-" + UUID.randomUUID().toString();
        
    	Path filePath = Paths.get("uploads/users-clothing/" + fileName + ".svg");
        Files.createDirectories(filePath.getParent());
        Files.write(filePath, imageRequest.getSvgData().getBytes());
        
        Image image = new Image();
        image.setName(fileName);
        image.setUrl("images/users-clothing/" + fileName + ".svg");
        image.setIsPrimary(true);        

        return imageRepository.save(image);
    }
}

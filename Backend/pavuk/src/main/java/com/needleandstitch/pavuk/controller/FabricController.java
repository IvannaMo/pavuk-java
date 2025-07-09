package com.needleandstitch.pavuk.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.needleandstitch.pavuk.model.Fabric;
import com.needleandstitch.pavuk.service.FabricService;
import jakarta.persistence.EntityNotFoundException;


@Controller
@RequestMapping("/fabrics")
@CrossOrigin
public class FabricController {
	@Autowired
    private FabricService fabricService;


    @GetMapping
    public ResponseEntity<List<Fabric>> getAllFabrics() {
        List<Fabric> fabrics = fabricService.findAll();
        return ResponseEntity.ok(fabrics);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fabric> getFabricById(@PathVariable Long id) {
        try {
        	Fabric fabric = fabricService.findById(id);
            return ResponseEntity.ok(fabric);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

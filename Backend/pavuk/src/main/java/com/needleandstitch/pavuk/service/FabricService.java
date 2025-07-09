package com.needleandstitch.pavuk.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.needleandstitch.pavuk.model.Fabric;
import com.needleandstitch.pavuk.repository.FabricRepository;


@Service
public class FabricService {
    @Autowired
    private FabricRepository fabricRepository;

    @PersistenceContext
    private EntityManager entityManager;

    
    @Transactional(readOnly = true)
    public Fabric findById(Long id) {
        return fabricRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fabric not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<Fabric> findAll() {
        return fabricRepository.findAll();
    }
}

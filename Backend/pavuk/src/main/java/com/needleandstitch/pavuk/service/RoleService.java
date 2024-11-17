package com.needleandstitch.pavuk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.needleandstitch.pavuk.model.Role;
import com.needleandstitch.pavuk.repository.RoleRepository;

import jakarta.persistence.EntityNotFoundException;


@Service
public class RoleService {
	@Autowired
    private RoleRepository roleRepository;
	
	@Transactional(readOnly = true)
    public Role findByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Role not found: " + name));
    }
}

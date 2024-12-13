package com.needleandstitch.pavuk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.needleandstitch.pavuk.model.Order;


public interface OrderRepository extends JpaRepository<Order, Long> {}

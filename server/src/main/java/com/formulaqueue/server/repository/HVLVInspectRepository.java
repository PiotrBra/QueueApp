package com.formulaqueue.server.repository;

import com.formulaqueue.server.dao.HVLVInspectNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HVLVInspectRepository extends JpaRepository<HVLVInspectNumber, Long> {
}

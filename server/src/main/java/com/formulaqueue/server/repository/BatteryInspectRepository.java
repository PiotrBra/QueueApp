package com.formulaqueue.server.repository;

import com.formulaqueue.server.dao.BatteryInspectNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatteryInspectRepository extends JpaRepository<BatteryInspectNumber, Long> {
}

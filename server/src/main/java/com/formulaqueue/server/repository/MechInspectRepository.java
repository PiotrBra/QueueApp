package com.formulaqueue.server.repository;

import com.formulaqueue.server.dao.MechInspectNumber;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Function;

public interface MechInspectRepository extends JpaRepository<MechInspectNumber, Long> {
}
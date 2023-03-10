package com.jaz.lektury.repositories;

import com.jaz.lektury.tables.Epoch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EpochRepository extends JpaRepository<Epoch, Long> {}

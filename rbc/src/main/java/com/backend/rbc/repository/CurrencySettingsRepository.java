package com.backend.rbc.repository;

import com.backend.rbc.entities.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencySettingsRepository extends JpaRepository<Settings, Long> {
}

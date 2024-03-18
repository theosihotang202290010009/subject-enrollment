package com.theo.enrollment.repository;

import com.theo.enrollment.entity.Period;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PeriodRepository extends JpaRepository<Period, String> {
    @Modifying
    @Query(value = "INSERT INTO m_period (id, period) VALUES (:id, :period)", nativeQuery = true)
    void create(@Param("id") String id,
                @Param("period") String period);

    @Modifying
    @Query(value = "UPDATE m_period SET period = :period WHERE id = :id", nativeQuery = true)
    void update(@Param("id") String id,
                @Param("period") String period);
}

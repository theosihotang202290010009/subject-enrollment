package com.theo.enrollment.repository;

import com.theo.enrollment.entity.Enroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface EnrollRepository extends JpaRepository<Enroll, String>, JpaSpecificationExecutor<Enroll> {
    @Modifying
    @Query(value = "INSERT INTO m_enroll (id, date, student_id) " +
            "VALUES (:id, :date, :studentId)", nativeQuery = true)
    void create(@Param("id") String id,
                @Param("date")Date date,
                @Param("studentId") String studentId);
}

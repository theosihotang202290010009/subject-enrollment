package com.theo.enrollment.repository;

import com.theo.enrollment.entity.EnrollDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EnrollDetailRepository extends JpaRepository<EnrollDetail, String> {
    @Modifying
    @Query(value = "INSERT INTO m_enroll_detail (id, course_id, enroll_id, period_id) " +
            "VALUES (:id, :courseId, :enrollId, :periodId)", nativeQuery = true)
    void create(@Param("id") String id,
                @Param("courseId") String courseId,
                @Param("enrollId") String enrollId,
                @Param("periodId") String periodId);
}

package com.theo.enrollment.repository;

import com.theo.enrollment.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourseRepository extends JpaRepository<Course, String>, JpaSpecificationExecutor<Course> {
    @Modifying
    @Query(value = "INSERT INTO m_course(id, course_name, sks) VALUES (:id, :name, :sks)", nativeQuery = true)
    void createCourse(@Param("id") String id,
                      @Param("name") String name,
                      @Param("sks") Integer sks);

    @Query(value = "SELECT * FROM m_course WHERE id = :id", nativeQuery = true)
    Course getCourseId(@Param("id") String id);

    @Modifying
    @Query(value = "UPDATE m_course SET course_name = :name, sks = :sks WHERE id = :id", nativeQuery = true)
    void updateCourse(@Param("id") String id,
                      @Param("name") String name,
                      @Param("sks") Integer sks);

    @Modifying
    @Query(value = "DELETE FROM m_course WHERE id = :id", nativeQuery = true)
    void deleteCourse(@Param("id") String id);
}

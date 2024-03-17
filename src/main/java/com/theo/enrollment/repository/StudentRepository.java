package com.theo.enrollment.repository;

import com.theo.enrollment.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String>, JpaSpecificationExecutor<Student> {
    @Modifying
    @Query(value = "INSERT INTO m_student(id, birth_date, major, student_name) VALUES(:id, :birthDate, :major, :name) ", nativeQuery = true)
    void createStudent(@Param("id") String id,
                       @Param("birthDate") Date birthDate,
                       @Param("major") String major,
                       @Param("name") String name);

    @Query(value = "SELECT * FROM m_student s WHERE LOWER(s.student_name) like %:name%", nativeQuery = true)
    List<Student> findName(@Param("name") String name);

    @Modifying
    @Query(value = "UPDATE m_student SET student_name = :name, major = :major, birth_date = :birthDate WHERE id = :id", nativeQuery = true)
    void update(@Param("id") String id,
                   @Param("name") String name,
                   @Param("major") String major,
                   @Param("birthDate") Date birthDate);

    @Query(value = "SELECT * FROM m_student WHERE id = :id", nativeQuery = true)
    Student getStudentId(@Param("id") String id);


    @Modifying
    @Query(value = "UPDATE m_student SET is_active = :status WHERE id = :id", nativeQuery = true)
    void updateStatus(@Param("id") String id,
                      @Param("status") Boolean status);

//    @Query(value = "SELECT * FROM m_student s WHERE " +
//            "(:#{#request.id} IS NULL OR s.id = :#{#request.id}) OR " +
//            "(:#{#request.name} IS NULL OR LOWER(s.student_name) LIKE %:#{#request.name}%) OR " +
//            "(:#{#request.major} IS NULL OR LOWER(s.major) LIKE %:#{#request.major}%)"+
////            "(:#{#request?.birthDate} IS NULL OR s.birth_date = :#{#request?.birthDate})" +
//            "", nativeQuery = true)
//    List<Student> getAll(@Param("request")SearchStudentRequest request);
}

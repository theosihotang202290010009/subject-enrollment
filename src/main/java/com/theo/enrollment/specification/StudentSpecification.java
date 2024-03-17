package com.theo.enrollment.specification;

import com.theo.enrollment.dto.request.student.SearchStudentRequest;
import com.theo.enrollment.entity.Student;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class StudentSpecification {
    public static Specification<Student> getSpecification(SearchStudentRequest request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (request.getId() != null) {
                predicates.add(
                        criteriaBuilder.equal(root.get("id"), request.getId())
                );
            }

            if (request.getName() != null) {
                predicates.add(
                        criteriaBuilder.like(criteriaBuilder.lower(
                                root.get("name")), "%" + request.getName().toLowerCase() + "%"
                        )
                );
            }

            if (request.getMajor() != null){
                predicates.add(
                        criteriaBuilder.like(criteriaBuilder.lower(
                                root.get("name")), "%" + request.getMajor() + "%"
                        )
                );
            }

            if (request.getBirthDate() != null){
                predicates.add(
                        criteriaBuilder.equal(
                                root.get("birthDate"), request.getBirthDate()
                        )
                );
            }
            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };
    }
}

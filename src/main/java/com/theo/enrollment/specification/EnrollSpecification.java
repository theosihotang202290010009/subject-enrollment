package com.theo.enrollment.specification;

import com.theo.enrollment.dto.request.enroll.SearchEnrollRequest;
import com.theo.enrollment.entity.Enroll;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class EnrollSpecification {
    public static Specification<Enroll> getSpecification(SearchEnrollRequest request){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (request.getId() != null){
                predicates.add(
                        criteriaBuilder.equal(
                                root.get("id"), request.getId()
                        )
                );
            }

            if (request.getStudentName() != null){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(
                                        root.get("student").get("name")), "%"+ request.getStudentName() + "%"
                        )
                );
            }

            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };
    }
}

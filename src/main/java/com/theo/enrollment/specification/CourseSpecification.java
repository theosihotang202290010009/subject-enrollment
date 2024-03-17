package com.theo.enrollment.specification;

import com.theo.enrollment.entity.Course;
import com.theo.enrollment.dto.request.course.SearchCourseRequest;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class CourseSpecification {
    public static Specification<Course> getSpecification(SearchCourseRequest request){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (request.getId() != null){
                predicates.add(
                        criteriaBuilder.equal(
                                root.get("id"), request.getId()
                        )
                );
            }

            if (request.getCourseName() != null){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(
                                        root.get("courseName")),  "%" + request.getCourseName().toLowerCase() + "%"
                        )
                );
            }

            if (request.getSks() != null){
                predicates.add(
                        criteriaBuilder.equal(
                                root.get("sks"), request.getSks()
                        )
                );
            }

            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };
    }
}

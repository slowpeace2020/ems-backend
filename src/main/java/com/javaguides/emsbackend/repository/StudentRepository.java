package com.javaguides.emsbackend.repository;

import com.javaguides.emsbackend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {

}

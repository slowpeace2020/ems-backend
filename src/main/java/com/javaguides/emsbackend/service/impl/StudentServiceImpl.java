package com.javaguides.emsbackend.service.impl;

import com.javaguides.emsbackend.dto.StudentDto;
import com.javaguides.emsbackend.entity.Student;
import com.javaguides.emsbackend.exception.ResourceNotFoundException;
import com.javaguides.emsbackend.mapper.StudentMapper;
import com.javaguides.emsbackend.repository.StudentRepository;
import com.javaguides.emsbackend.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;


    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        Student student = StudentMapper.mapToStudent(studentDto);
        Student savedStudent =studentRepository.save(student);
        return StudentMapper.mapToStudentDto(savedStudent);
    }

    @Override
    public StudentDto getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(()->new ResourceNotFoundException("Student is not exists with given id"));
        return  StudentMapper.mapToStudentDto(student);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map((student)->StudentMapper.mapToStudentDto(student)).collect(Collectors.toList());
    }

    @Override
    public StudentDto updateStudent(Long studentId, StudentDto updatedStudent) {
        Student student = studentRepository.findById(studentId).orElseThrow(()->new ResourceNotFoundException("Student is not exists with given id: "+studentId));
        student.setFirstName(updatedStudent.getFirstName());
        student.setLastName(updatedStudent.getLastName());
        student.setEmail(updatedStudent.getEmail());
        Student updatedStudentByRepo = studentRepository.save(student);
        return StudentMapper.mapToStudentDto(updatedStudentByRepo);
    }

    @Override
    public void deleteStudent(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(()->new ResourceNotFoundException("Student is not exists with given id: "+studentId));
        studentRepository.deleteById(studentId);
    }
}

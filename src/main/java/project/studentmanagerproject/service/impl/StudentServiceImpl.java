package project.studentmanagerproject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.studentmanagerproject.entity.Student;
import project.studentmanagerproject.repository.StudentRepository;
import project.studentmanagerproject.service.StudentService;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public String addStudent(Student student) {
        studentRepository.save(student);
        return "Student Added Successfully ! ";
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

}

package project.studentmanagerproject.service;

import project.studentmanagerproject.entity.Student;

import java.util.List;

public interface StudentService {
    String addStudent(Student student);
    List<Student> getAllStudents();
    Student getAStudent(int id);
    void saveOrUpdate(Student student);
}

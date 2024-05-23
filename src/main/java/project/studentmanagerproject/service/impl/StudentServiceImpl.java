package project.studentmanagerproject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import project.studentmanagerproject.entity.Student;
import project.studentmanagerproject.repository.StudentRepository;
import project.studentmanagerproject.service.StudentService;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getAStudent(int id) {
        return studentRepository.findStudentById(id);
    }

    @Override
    public void saveOrUpdate(Student student) {
        Student exist = studentRepository.findStudentById(student.getId());
        if (exist == null) {
            studentRepository.save(student);
        } else {
            exist.setFirstname(student.getFirstname());
            exist.setLastname(student.getLastname());
            exist.setEmail(student.getEmail());
            exist.setBirth(student.getBirth());
            studentRepository.save(exist);
        }
    }

    @Override
    public void deleteStudent(int id) {
        studentRepository.delete(studentRepository.findStudentById(id));
    }

    @Override
    public Page<Student> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.studentRepository.findAll(pageable);
    }

}

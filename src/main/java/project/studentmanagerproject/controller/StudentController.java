package project.studentmanagerproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.studentmanagerproject.entity.Student;
import project.studentmanagerproject.service.StudentService;

import java.util.Optional;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/add-student")
    public String addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @GetMapping("/all-students")
    public String showAllStudent(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "all-students";
    }

    @GetMapping("/update-student")
    public String showUpdateStudentForm(
            @RequestParam("id") int id,
            Model model) {
        Student student = studentService.getAStudent(id);
        if (student != null) {
            model.addAttribute("student", student);
            return "update-student-form";
        } else {
            return "redirect:/all-students";
        }
    }

    @PostMapping("/update-student")
    public String submit(@ModelAttribute("student") Student student) {
        studentService.saveOrUpdate(student);
        return "all-students";
    }
}

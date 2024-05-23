package project.studentmanagerproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.studentmanagerproject.entity.Student;
import project.studentmanagerproject.service.StudentService;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/add-student")
    public String addStudentForm() {
        return "add-student-form";
    }

    @PostMapping("/add-student")
    public String addStudent(Student student) {
        studentService.addStudent(student);
        return "redirect:/all-students";
    }

    @GetMapping("/all-students")
    public String showAllStudent(Model model) {
        return findPaginated(1, model);
    }

    @GetMapping("/update-student")
    public String showUpdateStudentForm(
            @RequestParam("id") int id,
            Model model) {
        Student student = studentService.getAStudent(id);
        model.addAttribute("student", student);
        return "update-student-form";
    }

    @PostMapping("/update-student")
    public String submit(@ModelAttribute("student") Student student) {
        studentService.saveOrUpdate(student);
        return "redirect:/students/all-students";
    }

    @PostMapping("/delete-student")
    public String deleteStudent(@RequestParam("id") int id) {
        studentService.deleteStudent(id);
        return "redirect:/students/all-students";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                Model model) {
        int pageSize = 5;

        Page<Student> page = studentService.findPaginated(pageNo, pageSize);
        List<Student> students = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("students", students);
        return "all-students";
    }
}

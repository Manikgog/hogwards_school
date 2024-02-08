package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable Long studentId) {
        Student student = studentService.getStudentById(studentId);
        if (student ==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(){
        return ResponseEntity.ok(studentService.getAll());
    }
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        Student newStudent = studentService.createStudent(student);
        return ResponseEntity.ok(newStudent);
    }
    @PutMapping()
    public ResponseEntity<Student> updateStudent(@RequestBody Student student){
        Student updatedStudent = studentService.editStudentById(student);
        if(updatedStudent == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping
    public ResponseEntity<Student> deleteStudent(@RequestBody Student student){
        Student deletedStudent = studentService.deleteStudent(student.getId());
        if(deletedStudent == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deletedStudent);
    }
}

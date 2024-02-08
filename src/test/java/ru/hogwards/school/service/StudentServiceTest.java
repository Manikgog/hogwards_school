package ru.hogwards.school.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

public class StudentServiceTest {
    private final StudentService studentService;
    public StudentServiceTest(){
        this.studentService = new StudentService();
    }

    @BeforeEach
    public void init(){
        studentService.createStudent(new Student(0L, "Chendler Bing", 25));
        studentService.createStudent(new Student(0L, "Geller Ross", 25));
        studentService.createStudent(new Student(0L, "Joe Tribiani", 25));
        studentService.createStudent(new Student(0L, "Rachel Grin", 25));
        studentService.createStudent(new Student(0L, "Monica Geller", 25));
    }

    @Test
    public void positive_createStudent_Test(){
        long listOfStudentsSizeBeforeCreation = studentService.getAll().size();
        studentService.createStudent(new Student(0L, "Fuiby Buffey", 26));
        long expected_listOfStudentsSize = listOfStudentsSizeBeforeCreation + 1;
        Assertions.assertEquals(expected_listOfStudentsSize, studentService.getAll().size());

        listOfStudentsSizeBeforeCreation = studentService.getAll().size();
        studentService.createStudent(new Student(0L, "Ganter Johns", 27));
        expected_listOfStudentsSize = listOfStudentsSizeBeforeCreation + 1;
        Assertions.assertEquals(expected_listOfStudentsSize, studentService.getAll().size());
    }

    @Test
    public void positive_deleteStudent_Test(){
        long listOfStudents_Size_beforeDeletion = studentService.getAll().size();
        Student expectedStudent = new Student(1L, "Chendler Bing", 25);
        Student deletedStudent = studentService.deleteStudent(1L);
        long expected_SizeOfStudentsList = listOfStudents_Size_beforeDeletion - 1;
        Assertions.assertEquals(expected_SizeOfStudentsList, studentService.getAll().size());
        Assertions.assertEquals(expectedStudent, deletedStudent);

        listOfStudents_Size_beforeDeletion = studentService.getAll().size();
        expectedStudent = new Student(5L, "Monica Geller", 25);
        deletedStudent = studentService.deleteStudent(5L);
        expected_SizeOfStudentsList = listOfStudents_Size_beforeDeletion - 1;
        Assertions.assertEquals(expected_SizeOfStudentsList, studentService.getAll().size());
        Assertions.assertEquals(expectedStudent, deletedStudent);
    }

    @Test void negative_deleteStudent_Test(){
        long expected_SizeOfListStudents = studentService.getAll().size();
        Student expectedStudent = null;
        Student deletedStudent = studentService.deleteStudent(10L);
        Assertions.assertEquals(expectedStudent, deletedStudent);
        Assertions.assertEquals(expected_SizeOfListStudents, studentService.getAll().size());

        deletedStudent = studentService.deleteStudent(6L);
        Assertions.assertEquals(expectedStudent, deletedStudent);
        Assertions.assertEquals(expected_SizeOfListStudents, studentService.getAll().size());
    }

    @Test
    public void positive_editStudent_Test(){
        Student expected = new Student(1L, "Chendler Bing", 26);
        Student actual = studentService.editStudentById(new Student(1L, "Chendler Bing", 26));
        Assertions.assertEquals(expected, actual);

        expected = new Student(5L, "Monica Bing", 26);
        Assertions.assertEquals(expected, studentService.editStudentById(new Student(5L, "Monica Bing", 26)));
    }

    @Test
    public void negative_editStudent_Test(){
        Assertions.assertNull(studentService.editStudentById(new Student(6L, "Chendler Bing", 24)));
        Assertions.assertNull(studentService.editStudentById(new Student(0L, "Chendler Bing", 24)));
    }

    @Test
    public void positive_getStudentById_Test(){
        Assertions.assertEquals(new Student(1L, "Chendler Bing", 25), studentService.getStudentById(1L));
        Assertions.assertEquals(new Student(2L, "Geller Ross", 25), studentService.getStudentById(2L));
    }

    @Test
    public void negative_getStudentById_Test(){
        Assertions.assertNull(studentService.getStudentById(0L));
        Assertions.assertNull(studentService.getStudentById(6L));
    }

    @Test
    public void getAll_Test(){
        List<Student> expectedList = List.of(
            new Student(1L, "Chendler Bing", 25),
            new Student(2L, "Geller Ross", 25),
            new Student(3L, "Joe Tribiani", 25),
            new Student(4L, "Rachel Grin", 25),
            new Student(5L, "Monica Geller", 25)
        );

        Assertions.assertEquals(expectedList, studentService.getAll());
    }

}

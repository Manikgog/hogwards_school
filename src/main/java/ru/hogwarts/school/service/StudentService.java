package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {
    Map<Long, Student> students = new HashMap<>();
    private static Long countId = 1L;
    public Student createStudent(Student student){
        student.setId(countId);
        students.put(countId, student);
        countId++;
        return student;
    }
    public Student deleteStudent(Long id){
        if(students.containsKey(id)){
            return students.remove(id);
        }
        return null;
    }
    public Student editStudentById(Student student){
        if(students.containsKey(student.getId())){
            students.put(student.getId(), student);
            return student;
        }
        return null;
    }

    public Student getStudentById(Long id){
        if(students.containsKey(id)){
            return students.get(id);
        }
        return null;
    }

    public List<Student> getAll(){
        return students.values().stream().toList();
    }

    public List<Student> getStudentsByAge(int age){
        return students
                .values()
                .stream()
                .filter(student -> student.getAge() == age)
                .toList();
    }

}

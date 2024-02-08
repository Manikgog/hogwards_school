package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;
import java.util.List;

@RestController
@RequestMapping("faculties")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{facultyId}")
    public ResponseEntity<Faculty> getStudent(@PathVariable Long facultyId) {
        Faculty faculty = facultyService.getFacultyById(facultyId);
        if (faculty == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping
    public ResponseEntity<List<Faculty>> getAllFaculties(){
        return ResponseEntity.ok(facultyService.getAll());
    }
    @PostMapping
    public ResponseEntity<Faculty> createStudent(@RequestBody Faculty faculty){
        Faculty newFaculty = facultyService.createFaculty(faculty);
        return ResponseEntity.ok(newFaculty);
    }
    @PutMapping()
    public ResponseEntity<Faculty> updateStudent(@RequestBody Faculty faculty){
        Faculty updatedFaculty = facultyService.editFaculty(faculty);
        if(updatedFaculty == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(updatedFaculty);
    }

    @DeleteMapping
    public ResponseEntity<Faculty> deleteFaculty(@RequestBody Faculty faculty){
        Faculty deletedFaculty = facultyService.deleteFaculty(faculty.getId());
        if(deletedFaculty == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deletedFaculty);
    }
}

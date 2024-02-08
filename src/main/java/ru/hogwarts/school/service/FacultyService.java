package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FacultyService {
    Map<Long, Faculty> faculties = new HashMap<>();
    private static Long countId = 1L;
    public Faculty createFaculty(Faculty faculty){
        faculties.put(countId, faculty);
        countId++;
        return faculty;
    }
    public Faculty deleteFaculty(Long id){
        if(faculties.containsKey(id)){
            return faculties.remove(id);
        }
        return null;
    }
    public Faculty editFaculty(Faculty faculty){
        if(faculties.containsKey(faculty.getId())){
            faculties.put(faculty.getId(), faculty);
            return faculty;
        }
        return null;
    }

    public Faculty getFaculty(Faculty faculty){
        if(faculties.containsKey(faculty.getId())){
            Faculty foundFaculty = faculties.get(faculty.getId());
            return foundFaculty;
        }
        return null;
    }
    public Faculty getFacultyById(Long facultyId){
        if(faculties.containsKey(facultyId)){
            Faculty foundFaculty = faculties.get(facultyId);
            return foundFaculty;
        }
        return null;
    }


    public List<Faculty> getAll(){
        return faculties.values().stream().toList();
    }
}

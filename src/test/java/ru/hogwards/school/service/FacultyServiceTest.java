package ru.hogwards.school.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;

public class FacultyServiceTest {
    private final FacultyService facultyService;
    public FacultyServiceTest(){
        facultyService = new FacultyService();
    }

    @BeforeEach
    public void init(){
        facultyService.createFaculty(new Faculty(1L, "Гриффиндор", "золотой и красный"));
        facultyService.createFaculty(new Faculty(2L, "Когтевран", "синий и бронзовый"));
    }

    @Test
    public void positive_createFaculty_Test(){
        long listOfFacultiesSizeBeforeCreation = facultyService.getAll().size();
        facultyService.createFaculty(new Faculty(3L, "Пуффендуй", "жёлтый и чёрный"));
        long expected_listOfFacultiesSize = listOfFacultiesSizeBeforeCreation + 1;
        Assertions.assertEquals(expected_listOfFacultiesSize, facultyService.getAll().size());

        listOfFacultiesSizeBeforeCreation = facultyService.getAll().size();
        facultyService.createFaculty(new Faculty(4L, "Слизерин", "зелёный и серебряный"));
        expected_listOfFacultiesSize = listOfFacultiesSizeBeforeCreation + 1;
        Assertions.assertEquals(expected_listOfFacultiesSize, facultyService.getAll().size());
    }

    @Test
    public void positive_deleteFaculty_Test(){
        long listOfFaculties_Size_beforeDeletion = facultyService.getAll().size();
        Faculty expected = new Faculty(1L, "Гриффиндор", "золотой и красный");
        Faculty deleted = facultyService.deleteFaculty(1L);
        long expected_SizeOfList = listOfFaculties_Size_beforeDeletion - 1;
        Assertions.assertEquals(expected_SizeOfList, facultyService.getAll().size());
        Assertions.assertEquals(expected, deleted);

        listOfFaculties_Size_beforeDeletion = facultyService.getAll().size();
        expected = new Faculty(2L, "Когтевран", "синий и бронзовый");
        deleted = facultyService.deleteFaculty(2L);
        expected_SizeOfList = listOfFaculties_Size_beforeDeletion - 1;
        Assertions.assertEquals(expected_SizeOfList, facultyService.getAll().size());
        Assertions.assertEquals(expected, deleted);
    }

    @Test void negative_deleteFaculty_Test(){
        long expected_SizeOfList = facultyService.getAll().size();
        Faculty expected = null;
        Faculty deleted = facultyService.deleteFaculty(10L);
        Assertions.assertEquals(expected, deleted);
        Assertions.assertEquals(expected_SizeOfList, facultyService.getAll().size());

        deleted = facultyService.deleteFaculty(6L);
        Assertions.assertEquals(expected, deleted);
        Assertions.assertEquals(expected_SizeOfList, facultyService.getAll().size());
    }

    @Test
    public void positive_editFaculty_Test(){
        Faculty expected = new Faculty(1L, "Гриффиндор", "золотой");
        Faculty actual = facultyService.editFaculty(new Faculty(1L, "Гриффиндор", "золотой"));
        Assertions.assertEquals(expected, actual);

        expected = new Faculty(2L, "Когтевран", "синий");
        Assertions.assertEquals(expected, facultyService.editFaculty(new Faculty(2L, "Когтевран", "синий")));
    }

    @Test
    public void negative_editFaculty_Test(){
        Assertions.assertNull(facultyService.editFaculty(new Faculty(0L, "Когтевран", "синий")));
        Assertions.assertNull( facultyService.editFaculty(new Faculty(3L, "Когтевран", "синий")));
    }

    @Test
    public void positive_getFacultyById_Test(){
        Assertions.assertEquals(new Faculty(1L, "Гриффиндор", "золотой и красный"), facultyService.getFacultyById(1L));
        Assertions.assertEquals(new Faculty(2L, "Когтевран", "синий и бронзовый"), facultyService.getFacultyById(2L));
    }

    @Test
    public void negative_getFacultyById_Test(){
        Assertions.assertNull(facultyService.getFacultyById(0L));
        Assertions.assertNull( facultyService.getFacultyById(6L));
    }

    @Test
    public void getAll_Test(){
        List<Faculty> expectedList = List.of(
                new Faculty(1L, "Гриффиндор", "золотой и красный"),
                new Faculty(2L, "Когтевран", "синий и бронзовый")
        );

        Assertions.assertEquals(expectedList, facultyService.getAll());
    }
}

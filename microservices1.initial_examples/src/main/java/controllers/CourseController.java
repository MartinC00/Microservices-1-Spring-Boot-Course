package controllers;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/courses")
public class CourseController {

    private List<Course> courses = new ArrayList<>();

    @PostConstruct
    public void init(){
        courses.add(new Course("Spring", 2, "Morning"));
        courses.add(new Course("Spring Boot", 3, "Afternoon"));
        courses.add(new Course("Python", 1, "Evening"));
        courses.add(new Course("Java EE", 3, "Afternoon"));
        courses.add(new Course("Spring Boot", 2, "Evening"));
        courses.add(new Course("Java 8", 5, "Morning"));
    }

    @GetMapping
    public List<Course> getAllCursos(){
        return courses;
    }

    @GetMapping(value = "/{name}")
    public List<Course> findCursoByName(@PathVariable("name") String nombre){
        return courses.stream()
                .filter(course -> course.getName().equals(nombre))
                .collect(Collectors.toList());
    }

    @DeleteMapping
    public void deleteCurso(@RequestParam String nombre){
        courses.removeIf(aCourse -> aCourse.getName().equals(nombre));
    }

    @PutMapping
    public List<Course> updateCurso(@RequestBody Course course){
        return courses = courses.stream()
                                .map(c -> c.getName().equals(course.getName()) ? course : c)
                                .collect(Collectors.toList());
    }

    @PostMapping
    public List<Course> addCurso(@RequestBody Course aCourse){
        courses.add(aCourse);
        return courses;
    }
}

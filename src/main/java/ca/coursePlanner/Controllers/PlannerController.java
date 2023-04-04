package ca.coursePlanner.Controllers;

import ca.coursePlanner.Model.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlannerController {
    private CSVHelper csvHelper = new CSVHelper();
    private CourseManager courseManager = new CourseManager();
    private List<CourseOffering> courses;

    @GetMapping("/api/dump-model")
    public void dumpModel() {
        courseManager.importCoursesFromCsvFile("data/small_data.csv");
//        courseManager.importCoursesFromCsvFile("data/course_data_2018.csv");
        courseManager.dumpCourseOfferings();
    }
}

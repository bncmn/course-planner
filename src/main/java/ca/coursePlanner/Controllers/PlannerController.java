package ca.coursePlanner.Controllers;

import ca.coursePlanner.Model.*;
import ca.coursePlanner.Wrappers.ApiCourseWrapper;
import ca.coursePlanner.Wrappers.ApiDepartmentWrapper;
import ca.coursePlanner.Wrappers.ApiOfferingWrapper;
import ca.coursePlanner.Wrappers.ApiSectionWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PlannerController {
    private CSVHelper csvHelper = new CSVHelper();
    private CourseManager courseManager = new CourseManager();
    private List<ApiDepartmentWrapper> departments = new ArrayList<>();
    private int nextId = 0;

    /*
    GENERAL
     */
    @GetMapping("/api/dump-model")
    public void dumpModel() {
        courseManager.dumpCourseOfferings();
    }

    @GetMapping("/api/about")
    public ResponseObject getAuthors() {
        return new ResponseObject("Cool Course Planner", "Diego Buencamino and Matt Tsai");
    }

    /*
    DATA ACCESS
     */
    @GetMapping("/api/departments")
    public List<ApiDepartmentWrapper> getDepartments() {
        for (Department d : courseManager.getDepartments()) {
            departments.add(new ApiDepartmentWrapper(nextId, d.getName()));
            nextId++;
        }
        return departments;
    }

    @GetMapping("/api/departments/{deptId}/courses")
    public List<ApiCourseWrapper> getCourses(@PathVariable("deptId") int deptId) {
        try {
            List<ApiCourseWrapper> courses = new ArrayList<>();
            int courseId = 0;
            for (Course c : courseManager.getDepartments().get(deptId).getCourses()){
                courses.add(new ApiCourseWrapper(courseId, c.getCatalogNumber()));
                courseId++;
            }
            return courses;
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department ID is invalid!");
        }
    }

    @GetMapping("/api/departments/{deptId}/courses/{courseId}/offerings")
    public List<ApiOfferingWrapper> getOfferings(@PathVariable("deptId") int deptId, @PathVariable("courseId") int courseId) {
        try {
            List<ApiOfferingWrapper> offerings = new ArrayList<>();
            int offeringId = 0;
            for (CourseOffering co : courseManager.getDepartments().get(deptId).getCourses().get(courseId).getOfferings()){
                offerings.add(new ApiOfferingWrapper(
                        offeringId,
                        co.getSemester(),
                        co.getLocation(),
                        co.getInstructors()
                ));
                offeringId++;
            }
            return offerings;
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "One of Department ID or Course ID is invalid!");
        }
    }

//    @GetMapping("/api/departments/{deptId}/courses/{courseId}/offerings/{offeringId}")
//    public ApiSectionWrapper getSpecificOffering(@PathVariable("deptId") int deptId, @PathVariable("courseId") int courseId, @PathVariable("offeringId") int offeringId) {
//        try {
//            CourseOffering fetchedOffering = courseManager.getDepartments().get(deptId).getCourses().get(courseId).getOfferings().get(offeringId);
//
//            //return new ApiSectionWrapper(fetchedOffering.getEnrolmentCapacity(), fetchedOffering.getEnrolmentTotal(), fetchedOffering.getComponentCode());
//        }
//        catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "One of Department ID or Course ID is invalid!");
//        }
//    }
}

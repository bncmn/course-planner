package ca.coursePlanner.Controllers;

import ca.coursePlanner.Model.*;
import ca.coursePlanner.Wrappers.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PlannerController {
    private static CourseManager courseManager = new CourseManager();
    private static List<ApiDepartmentWrapper> departments = courseManager.getWrappedDepartments();

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
        return departments;
    }

    @GetMapping("/api/departments/{deptId}/courses")
    public List<ApiCourseWrapper> getCourses(@PathVariable("deptId") int deptId) {
        try {
            List<ApiCourseWrapper> courses = new ArrayList<>();
            int courseId = 0;
            for (Course c : courseManager
                    .getDepartments().get(deptId)
                    .getCourses()) {
                courses.add(new ApiCourseWrapper(courseId, c.getCatalogNumber()));
                courseId++;
            }
            return courses;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department ID is invalid!");
        }
    }

    @GetMapping("/api/departments/{deptId}/courses/{courseId}/offerings")
    public List<ApiOfferingWrapper> getOfferings(@PathVariable("deptId") int deptId,
                                                 @PathVariable("courseId") int courseId) {
        try {
            List<ApiOfferingWrapper> offerings = new ArrayList<>();
            int offeringId = 0;
            for (CourseOffering co : courseManager
                    .getDepartments().get(deptId)
                    .getCourses().get(courseId)
                    .getOfferings()) {
                offerings.add(new ApiOfferingWrapper(
                        offeringId,
                        co.getSemester(),
                        co.getLocation(),
                        co.getInstructors()
                ));
                offeringId++;
            }
            return offerings;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "One of Department ID or Course ID is invalid!");
        }
    }

    @GetMapping("/api/departments/{deptId}/courses/{courseId}/offerings/{courseOfferingId}")
    public List<ApiSectionWrapper> getSpecificOffering(@PathVariable("deptId") int deptId,
                                                       @PathVariable("courseId") int courseId,
                                                       @PathVariable("courseOfferingId") int courseOfferingId) {
        try {
            List<ApiSectionWrapper> sections = new ArrayList<>();
            for (Section s : courseManager
                    .getDepartments().get(deptId)
                    .getCourses().get(courseId)
                    .getOfferings().get(courseOfferingId)
                    .getSections()) {
                sections.add(new ApiSectionWrapper(
                        s.getComponentCode(),
                        s.getEnrolmentTotal(),
                        s.getEnrolmentCapacity()
                ));
            }
            return sections;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "One of Department ID or Course ID is invalid!");
        }
    }

//    @PostMapping("/api/addoffering")
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public void addOffering(@RequestBody String semesterCode,
//                            String departmentName,
//                            String catalogNumber,
//                            String location,
//                            int enrollmentCap,
//                            String componentCode,
//                            int enrollmentTotal,
//                            String instructor) {
//        try {
//            courseManager.addOrUpdateCourseOffering(departmentName, catalogNumber, semesterCode, location, instructor, enrollmentCap, enrollmentTotal, componentCode);
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "One of the variable is invalid!");
//        }
//    }


    @PostMapping("/api/addoffering")
    @ResponseStatus(HttpStatus.CREATED)
    public void addOffering(@RequestParam("semester") String semesterCode,
                            @RequestParam("subjectName") String departmentName,
                            @RequestParam("catalogNumber") String catalogNumber,
                            @RequestParam("location") String location,
                            @RequestParam("enrollmentCap") int enrollmentCap,
                            @RequestParam("component") String componentCode,
                            @RequestParam("enrollmentTotal") int enrollmentTotal,
                            @RequestParam("instructor") String instructor) {
        try {
            courseManager.addOrUpdateCourseOffering(departmentName, catalogNumber, semesterCode, location, instructor, enrollmentCap, enrollmentTotal, componentCode);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "One of the variable is invalid!");
        }

    }
}

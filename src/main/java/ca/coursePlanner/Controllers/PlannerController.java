package ca.coursePlanner.Controllers;

import ca.coursePlanner.Model.*;
import ca.coursePlanner.Wrappers.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

/**
 * PlannerController handles communication (GET/POST/DELETE requests) between the HTML front-end
 * and the model back-end. The controller holds an instance of courseManager, a list of
 * departments wrapped for the API, and a list of watchers for the Watchers functionality.
 *
 * @author Diego Buencamino
 * @author Matt Tsai
 */

@RestController
public class PlannerController {
    private static CourseManager courseManager = new CourseManager();
    private static List<ApiDepartmentWrapper> departments = courseManager.getWrappedDepartments();
    private static List<Watcher> watchers = new ArrayList<>();
    private int id = 0;

    /* GENERAL */
    @GetMapping("/api/dump-model")
    public void dumpModel() {
        courseManager.dumpCourseOfferings();
    }

    @GetMapping("/api/about")
    public ResponseObject getAuthors() {
        return new ResponseObject("Cool Course Planner", "Diego Buencamino and Matt Tsai");
    }

    /* DATA ACCESS */
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "One of Department ID, Course ID, or Offering ID is invalid!");
        }
    }

    @PostMapping("/api/addoffering")
    @ResponseStatus(HttpStatus.CREATED)
    public void addOffering(@RequestBody ApiPayloadOfferingWrapper payload) {
        try {
            courseManager.addOrUpdateCourseOffering(
                    payload.subjectName,
                    payload.catalogNumber,
                    payload.semester,
                    payload.location,
                    payload.instructor,
                    payload.enrollmentCap,
                    payload.enrollmentTotal,
                    payload.component
            );
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "One of the variables is invalid!");
        }
    }

    /* WATCHERS */
    @GetMapping("/api/watchers")
    public List<Watcher> getWatchers() {
        return watchers;
    }

    @PostMapping("/api/watchers")
    @ResponseStatus(HttpStatus.CREATED)
    public Watcher createWatcher(@RequestBody ApiPayloadWatcherWrapper payload) {
        try {
            Department watchDept = courseManager.getDepartments().get(payload.deptId);
            Course watchCourse = courseManager.getDepartments().get(payload.deptId).getCourses().get(payload.courseId);

            if (watchDept != null && watchCourse != null) {
                Watcher newWatcher = new Watcher(id, watchDept, watchCourse);
                watchers.add(newWatcher);
                id++;
                return newWatcher;
            }
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "One of Department ID or Course ID is invalid!");
        }
        return null;
    }

    @GetMapping("/api/watchers/{id}")
    public Watcher getWatcher(@PathVariable("id") int watcherId) {
        try {
            return watchers.get(watcherId);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The watcher ID is invalid!");
        }
    }

    @DeleteMapping("/api/watchers/{id}")
    public void deleteWatcher(@PathVariable("id") int watcherId) {
        try {
            Watcher watcher = watchers.get(watcherId);
            watcher.getCourse().removeWatcher(watcher);
            watchers.remove(watcher);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The watcher ID is invalid!");
        }
    }
}

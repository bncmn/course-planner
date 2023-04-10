package ca.coursePlanner.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseManager {
    private List<RawCourseListing> rawCourseList = new ArrayList<>();
    private List<Department> departments = new ArrayList<>();

    public CourseManager() {
//        importCoursesFromCsvFile("data/course_data_2018.csv");
        importCoursesFromCsvFile("data/small_data.csv");
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void importCoursesFromCsvFile(String dataFile) {
        this.rawCourseList = (new CSVHelper().parseCSV(dataFile));

        Map<String, Department> departmentMap = new HashMap<>();
        Map<String, Course> addedCourses = new HashMap<>();
        for (RawCourseListing rcl : rawCourseList) {
            String subject = rcl.getSubject();
            String catalogNumber = rcl.getCatalogNumber();
            String semester = rcl.getSemester();
            String location = rcl.getLocation();
            String componentCode = rcl.getComponentCode();

            // Get or create the department
            Department department = departmentMap.get(subject);
            if (department == null) {
                department = new Department(subject);
                departmentMap.put(subject, department);
                departments.add(department);
            }

            // Check if the course has already been added
            Course course = addedCourses.get(catalogNumber + semester + location + componentCode);

            if (course == null) {
                // Get or create the course
                List<Course> courses = department.getCourses();
                course = null;
                for (Course c : courses) {
                    if (c.getCatalogNumber().equals(catalogNumber)) {
                        course = c;
                        break;
                    }
                }
                if (course == null) {
                    course = new Course(catalogNumber);
                    courses.add(course);
                }

                // Create the offering and add it to the course
                CourseOffering offering = new CourseOffering(
                        semester,
                        location,
                        rcl.getEnrolmentCapacity(),
                        rcl.getEnrolmentTotal(),
                        rcl.getInstructors(),
                        componentCode
                );
                course.getOfferings().add(offering);
                addedCourses.put(catalogNumber + semester + location + componentCode, course);
            } else {
                // Add the enrolment numbers to the existing offering
                CourseOffering offering = course.getOfferings().get(course.getOfferings().size() - 1);
                offering.setEnrolmentCapacity(offering.getEnrolmentCapacity() + rcl.getEnrolmentCapacity());
                offering.setEnrolmentTotal(offering.getEnrolmentTotal() + rcl.getEnrolmentTotal());
            }
        }
    }

    public void dumpCourseOfferings() {
        String currentDepartment = null;
        for (Department department : departments) {
            currentDepartment = department.getName();
            for (Course course : department.getCourses()) {
                System.out.println(currentDepartment + " " + course.getCatalogNumber());
                String lastSemester = "";
                String lastInstructors = "";
                for (CourseOffering offering : course.getOfferings()) {
                    if (!offering.getSemester().equals(lastSemester) || !offering.getInstructors().equals(lastInstructors)) {
                        System.out.println("\t" + offering.getSemester() + " in " + offering.getLocation() + " by " + offering.getInstructors());
                        lastSemester = offering.getSemester();
                        lastInstructors = offering.getInstructors();
                    }
                    System.out.println("\t Type=" + offering.getComponentCode() + ", Enrolment=" + offering.getEnrolmentTotal() + "/" + offering.getEnrolmentCapacity());
                }
            }
        }

    }
}
package ca.coursePlanner.Model;

import ca.coursePlanner.Wrappers.ApiDepartmentWrapper;

import java.util.*;

public class CourseManager {
    private static List<RawCourseListing> rawCourseList = new ArrayList<>();
    private static List<Department> departments = new ArrayList<>();

    public CourseManager() {
//        importCoursesFromCsvFile("data/course_data_2018.csv");
        importCoursesFromCsvFile("data/small_data.csv");
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public List<ApiDepartmentWrapper> getWrappedDepartments() {
        int deptId = 0;
        List<ApiDepartmentWrapper> wrappedDepartments = new ArrayList<>();

        for (Department d : departments) {
            wrappedDepartments.add(new ApiDepartmentWrapper(
                    deptId,
                    d.getName()
            ));
            deptId++;
        }
        return wrappedDepartments;
    }

    public void importCoursesFromCsvFile(String dataFile) {
        this.rawCourseList = (new CSVHelper().parseCSV(dataFile));

        for (RawCourseListing rcl : rawCourseList) {
            String subject = rcl.getSubject();
            String catalogNumber = rcl.getCatalogNumber();
            String semester = rcl.getSemester();
            String location = rcl.getLocation();
            String instructors = rcl.getInstructors();
            int enrolmentCapacity = rcl.getEnrolmentCapacity();
            int enrolmentTotal = rcl.getEnrolmentTotal();
            String componentCode = rcl.getComponentCode();

            // Try to find an existing department with the same subject.
            Department department = null;
            for (Department d : departments) {
                if (d.getName().equals(subject)) {
                    department = d;
                    break;
                }
            }

            // Create a new department if one does not exist.
            if (department == null) {
                department = new Department(subject);
                departments.add(department);
            }

            // Try to find an existing course with the same catalog number.
            Course course = null;
            List<Course> courses = department.getCourses();
            for (Course c : courses) {
                if (c.getCatalogNumber().equals(catalogNumber)) {
                    course = c;
                    break;
                }
            }

            // Create a new course if one does not exist.
            if (course == null) {
                course = new Course(catalogNumber);
                courses.add(course);
            }

            // Try to find an existing offering.
            CourseOffering offering = null;
            List<CourseOffering> offerings = course.getOfferings();
            for (CourseOffering o : offerings) {
                if (o.getSemester().equals(semester) && o.getLocation().equals(location)) {
                    offering = o;
                    break;
                }
            }

            // Create a new offering if one does not exist.
            if (offering == null) {
                offering = new CourseOffering(semester, location, instructors);
                offerings.add(offering);
            }
            else {
                // If an existing offering is found, update its instructors.
                List<String> existingInstructors = new ArrayList<String>(Arrays.asList(offering.getInstructors().split(",")));
                String[] newInstructors = instructors.split(",");
                for (String instructor : newInstructors) {
                    if (instructor != null && !instructor.trim().isEmpty() && !existingInstructors.contains(instructor.trim())) {
                        existingInstructors.add(instructor.trim());
                    }
                }
                offering.setInstructors(String.join(", ", existingInstructors));
            }

            // Try to find an existing section with the same componentCode.
            Section section = null;
            List<Section> sections = offering.getSections();
            for (Section s : sections) {
                if (s.getComponentCode().equals(componentCode)) {
                    section = s;
                    break;
                }
            }

            // If an existing section is found, update its enrolment. Otherwise, add a new section.
            if (section != null) {
                section.setEnrolmentTotal(section.getEnrolmentTotal() + enrolmentTotal);
                section.setEnrolmentCapacity(section.getEnrolmentCapacity() + enrolmentCapacity);
            }
            else {
                offering.getSections().add(new Section(enrolmentCapacity, enrolmentTotal, componentCode));
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
                    for (Section section : offering.getSections()) {
                        System.out.println("\t\tType=" + section.getComponentCode() + ", Enrolment=" + section.getEnrolmentTotal() + "/" + section.getEnrolmentCapacity());
                    }
                }
            }
        }
    }
}
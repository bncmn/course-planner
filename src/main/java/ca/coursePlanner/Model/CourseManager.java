package ca.coursePlanner.Model;

import ca.coursePlanner.Wrappers.ApiDepartmentWrapper;

import java.util.*;

public class CourseManager {
    private static List<RawCourseListing> rawCourseList = new ArrayList<>();
    private static List<Department> departments = new ArrayList<>();

    public CourseManager() {
        importCoursesFromCsvFile("data/course_data_2018.csv");
//        importCoursesFromCsvFile("data/small_data.csv");
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

//    public void importCoursesFromCsvFile(String dataFile) {
//        this.rawCourseList = (new CSVHelper().parseCSV(dataFile));
//
//        for (RawCourseListing rcl : rawCourseList) {
//            String subject = rcl.getSubject();
//            String catalogNumber = rcl.getCatalogNumber();
//            String semester = rcl.getSemester();
//            String location = rcl.getLocation();
//            String instructors = rcl.getInstructors();
//            int enrolmentCapacity = rcl.getEnrolmentCapacity();
//            int enrolmentTotal = rcl.getEnrolmentTotal();
//            String componentCode = rcl.getComponentCode();
//
//            // Try to find an existing department with the same subject.
//            Department department = null;
//            for (Department d : departments) {
//                if (d.getName().equals(subject)) {
//                    department = d;
//                    break;
//                }
//            }
//
//            // Create a new department if one does not exist.
//            if (department == null) {
//                department = new Department(subject);
//                departments.add(department);
//                // Sort the departments name
//                Collections.sort(departments, new Comparator<Department>() {
//                    @Override
//                    public int compare(Department d1, Department d2) {
//                        return d1.getName().compareTo(d2.getName());
//                    }
//                });
//            }
//
//            // Try to find an existing course with the same catalog number.
//            Course course = null;
//            List<Course> courses = department.getCourses();
//            for (Course c : courses) {
//                if (c.getCatalogNumber().equals(catalogNumber)) {
//                    course = c;
//                    break;
//                }
//            }
//
//            // Create a new course if one does not exist.
//            if (course == null) {
//                course = new Course(catalogNumber);
//                courses.add(course);
//            }
//
//            // Try to find an existing offering.
//            CourseOffering offering = null;
//            List<CourseOffering> offerings = course.getOfferings();
//            for (CourseOffering o : offerings) {
//                if (o.getSemester().equals(semester) && o.getLocation().equals(location)) {
//                    offering = o;
//                    break;
//                }
//            }
//
//            // Create a new offering if one does not exist.
//            if (offering == null) {
//                offering = new CourseOffering(semester, location, instructors);
//                offerings.add(offering);
//            }
//            else {
//                // If an existing offering is found, update its instructors.
//                List<String> existingInstructors = new ArrayList<String>(Arrays.asList(offering.getInstructors().split(",")));
//                String[] newInstructors = instructors.split(",");
//
//                for (String instructor : newInstructors) {
//                    if (instructor != null && !instructor.trim().isEmpty() && !existingInstructors.contains(instructor.trim())) {
//                        existingInstructors.add(instructor.trim());
//                    }
//                }
//                offering.setInstructors(String.join(", ", existingInstructors));
//            }
//
//            // Try to find an existing section with the same componentCode.
//            Section section = null;
//            List<Section> sections = offering.getSections();
//            for (Section s : sections) {
//                if (s.getComponentCode().equals(componentCode)) {
//                    section = s;
//                    break;
//                }
//            }
//
//            // If an existing section is found, update its enrolment. Otherwise, add a new section.
//            if (section != null) {
//                section.setEnrolmentTotal(section.getEnrolmentTotal() + enrolmentTotal);
//                section.setEnrolmentCapacity(section.getEnrolmentCapacity() + enrolmentCapacity);
//            }
//            else {
//                offering.getSections().add(new Section(enrolmentCapacity, enrolmentTotal, componentCode));
//            }
//        }
//    }

    public void importCoursesFromCsvFile(String dataFile) {
        this.rawCourseList = (new CSVHelper().parseCSV(dataFile));

        for (RawCourseListing rcl : rawCourseList) {
            String subject = rcl.getSubject().trim();
            String catalogNumber = rcl.getCatalogNumber().trim();
            String semester = rcl.getSemester().trim();
            String location = rcl.getLocation().trim();
            String instructors = rcl.getInstructors().trim();
            int enrolmentCapacity = rcl.getEnrolmentCapacity();
            int enrolmentTotal = rcl.getEnrolmentTotal();
            String componentCode = rcl.getComponentCode().trim();

            addOrUpdateCourseOffering(subject, catalogNumber, semester, location, instructors, enrolmentCapacity, enrolmentTotal, componentCode);
        }
    }
    public void addOrUpdateCourseOffering(String subject, String catalogNumber, String semester, String location, String instructors, int enrolmentCapacity, int enrolmentTotal, String componentCode) {
        Department department = findOrCreateDepartment(subject);
        Course course = findOrCreateCourse(department, catalogNumber);
        CourseOffering offering = findOrCreateOffering(course, semester, location, instructors);
        updateOfferingInstructors(offering, instructors);
        Section section = updateOrAddSection(offering, componentCode, enrolmentCapacity, enrolmentTotal);

        if (section != null) {
            course.updateWatchers(section, semester);
        }
    }

    private Department findOrCreateDepartment(String subject) {
        for (Department d : departments) {
            if (d.getName().equals(subject)) {
                return d;
            }
        }

        Department department = new Department(subject);
        departments.add(department);
        sortDepartmentsByName();
        return department;
    }
    private void sortDepartmentsByName() {
        Collections.sort(departments, new Comparator<Department>() {
            @Override
            public int compare(Department d1, Department d2) {
                return d1.getName().compareTo(d2.getName());
            }
        });
    }

    private Course findOrCreateCourse(Department department, String catalogNumber) {
        for (Course c : department.getCourses()) {
            if (c.getCatalogNumber().equals(catalogNumber)) {
                return c;
            }
        }

        Course course = new Course(catalogNumber);
        department.getCourses().add(course);
        return course;
    }

    private CourseOffering findOrCreateOffering(Course course, String semester, String location, String instructors) {
        for (CourseOffering o : course.getOfferings()) {
            if (o.getSemester().equals(semester) && o.getLocation().equals(location)) {
                return o;
            }
        }

        CourseOffering offering = new CourseOffering(semester, location, instructors);
        course.getOfferings().add(offering);
        return offering;
    }

    /*BUG: duplicated instructors*/
    private void updateOfferingInstructors(CourseOffering offering, String instructors) {
        List<String> existingInstructors = new ArrayList<String>(Arrays.asList(offering.getInstructors().split(", ")));
        String[] newInstructors = instructors.split(", ");

        for (String instructor : newInstructors) {
            if (instructor != null && !instructor.trim().isEmpty() && !existingInstructors.contains(instructor.trim())) {
                existingInstructors.add(instructor.trim());
            }
        }

        offering.setInstructors(String.join(", ", existingInstructors).replace("\"", ""));
    }

    private Section updateOrAddSection(CourseOffering offering, String componentCode, int enrolmentCapacity, int enrolmentTotal) {
        for (Section s : offering.getSections()) {
            if (s.getComponentCode().equals(componentCode)) {
                s.setEnrolmentTotal(s.getEnrolmentTotal() + enrolmentTotal);
                s.setEnrolmentCapacity(s.getEnrolmentCapacity() + enrolmentCapacity);
                return null;
            }
        }

        Section newSection = new Section(enrolmentCapacity, enrolmentTotal, componentCode);
        offering.getSections().add(newSection);
        return newSection;
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
package ca.coursePlanner.Model;

import java.util.ArrayList;
import java.util.List;

public class CourseManager {
    private List<CourseOffering> offerings = new ArrayList<>();
    private List<String> courses = new ArrayList<>();

    public void importCoursesFromCsvFile(String dataFile) {
        this.offerings = (new CSVHelper().parseCSV(dataFile));

        for (CourseOffering co : offerings) {
            if (!courses.contains(co.getCourseCode())) {
                addCourse(co.getCourseCode());
            }
        }
    }

    private void addCourse(String courseCode) {
        courses.add(courseCode);
    }

    public void addOffering(CourseOffering courseOffering) {
        offerings.add(courseOffering);
    }

    public List<String> getCourses() {
        return courses;
    }

    public List<CourseOffering> getOfferings() {
        return offerings;
    }

    private void aggregateOfferings() {
        for (CourseOffering co : offerings) {
            co.getCourseCode();
        }
    }

    public void dumpCourseOfferings() {
        for (String c : courses) {
            System.out.println(c);

            for (CourseOffering co : offerings) {
                String offeringCode = co.getSubject() + " " + co.getCatalogNumber();
                if (c.equals(offeringCode)) {
                    System.out.println("\t" + co.getSemester().getRawSemesterCode() + " (" + co.getSemester().getYear() + " " + co.getSemester().getSeason() + ") in " + co.getLocation() + " by " + co.getInstructors());
                    System.out.println("\t Type = " + co.getComponentCode() + ", Enrolment = " + co.getEnrolmentTotal() + "/" + co.getEnrolmentCap());
                }
            }
        }
    }
}

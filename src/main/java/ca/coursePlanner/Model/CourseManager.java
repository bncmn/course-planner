package ca.coursePlanner.Model;

import java.util.List;

public class CourseManager {
    private List<CourseOffering> offerings;

    public void importCoursesFromCsvFile(String dataFile) {
        this.offerings = (new CSVHelper().parseCSV(dataFile));
    }

    public void addCourse(CourseOffering course) {
        offerings.add(course);
    }

    public List<CourseOffering> getOfferings() {
        return offerings;
    }

    public void dumpCourseOfferings() {
        for (CourseOffering c : offerings) {
            System.out.println(c.getSubject() + " " + c.getCatalogNumber());

        }
    }
}

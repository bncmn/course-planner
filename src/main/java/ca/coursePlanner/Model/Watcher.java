package ca.coursePlanner.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Watcher uses the Observer pattern to watch for changes in offerings of a course.
 * Each instance contains the Department and Course to be watched (i.e. CMPT 225).
 * The list of events is passed to the API front-end to be displayed on the Watchers page.
 */

public class Watcher {
    private int id;
    private Department department;
    private Course course;
    private List<String> events = new ArrayList<>();

    public Watcher(int watcherId, Department department, Course course) {
        this.id = watcherId;
        this.department = department;
        this.course = course;

        course.addWatcher(this);
    }

    public int getId() {
        return id;
    }

    public Department getDepartment() {
        return department;
    }

    public Course getCourse() {
        return course;
    }

    public List<String> getEvents() {
        return events;
    }

    public void update(Section section, String semester) {
        events.add(
                new Date() + ": Added section " + section.getComponentCode() + " with enrollment ("
                        + section.getEnrolmentTotal() + " / " + section.getEnrolmentCapacity() + ") to offering "
                        + decodeSemesterSeason(semester) + " " + decodeYear(semester)
        );
    }

    private String decodeYear(String semesterCode) {
        final int CONSTANT_CENTURY = 1900;
        String yearSubstring = semesterCode.substring(0, 3);

        int decodedYear = CONSTANT_CENTURY
                + (100 * Character.getNumericValue(yearSubstring.charAt(0)))
                + (10 * Character.getNumericValue(yearSubstring.charAt(1)))
                + Character.getNumericValue(yearSubstring.charAt(2));

        return Integer.toString(decodedYear);
    }

    private String decodeSemesterSeason(String semesterCode) {
        char semesterInt = semesterCode.charAt(semesterCode.length() - 1);

        return switch (semesterInt) {
            case '1' -> "Spring";
            case '4' -> "Summer";
            case '7' -> "Fall";
            default -> null;
        };
    }
}

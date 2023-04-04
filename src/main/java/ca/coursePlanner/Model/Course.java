package ca.coursePlanner.Model;

import java.util.Objects;

public class Course {
    private String subject;
    private String catalogNumber;

    public Course(String subject, String catalogNumber) {
        this.subject = subject;
        this.catalogNumber = catalogNumber;
    }

    public String getSubject() {
        return subject;
    }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public String getCourseCode() {
        return subject + " " + catalogNumber;
    }

    @Override
    public boolean equals(Object o){
        if (o == this) return true;

        if (!(o instanceof Course)) return false;

        Course that = (Course) o;
        return Objects.equals(subject, that.subject)
                && Objects.equals(catalogNumber, that.catalogNumber);
    }
}

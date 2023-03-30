package ca.coursePlanner.Model;

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
}

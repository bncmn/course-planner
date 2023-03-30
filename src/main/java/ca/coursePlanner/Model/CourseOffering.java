package ca.coursePlanner.Model;

public class CourseOffering {
    private Semester semester;
    private String subject;
    private String catalogNumber;
    private String location;
    private String enrolmentCap;
    private String enrolmentTotal;
    private String instructors;
    private String componentCode;

    public CourseOffering(String semester, String subject, String catalogNumber, String location, String enrolmentCap, String enrolmentTotal, String instructors, String componentCode) {
        this.semester = new Semester(semester);
        this.subject = subject;
        this.catalogNumber = catalogNumber;
        this.location = location;
        this.enrolmentCap = enrolmentCap;
        this.enrolmentTotal = enrolmentTotal;
        this.instructors = instructors;
        this.componentCode = componentCode;
    }

    public Semester getSemester() {
        return semester;
    }

    public String getSubject() {
        return subject;
    }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public String getLocation() {
        return location;
    }

    public String getEnrolmentCap() {
        return enrolmentCap;
    }

    public String getEnrolmentTotal() {
        return enrolmentTotal;
    }

    public String getInstructors() {
        return instructors;
    }

    public String getComponentCode() {
        return componentCode;
    }
}

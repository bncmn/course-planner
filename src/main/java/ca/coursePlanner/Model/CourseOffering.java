package ca.coursePlanner.Model;

public class CourseOffering extends Course {
    private Semester semester;
    private String location;
    private String enrolmentCap;
    private String enrolmentTotal;
    private String instructors;
    private String componentCode;

    public CourseOffering(String semester, String subject, String catalogNumber, String location, String enrolmentCap, String enrolmentTotal, String instructors, String componentCode) {
        super(subject, catalogNumber);
        this.semester = new Semester(semester);
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
        return super.getSubject();
    }

    public String getCatalogNumber() {
        return super.getCatalogNumber();
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

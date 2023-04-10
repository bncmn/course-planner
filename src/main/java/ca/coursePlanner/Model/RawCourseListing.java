package ca.coursePlanner.Model;

public class RawCourseListing {
    private String semester;
    private String subject;
    private String catalogNumber;
    private String location;
    private int enrolmentCapacity;
    private int enrolmentTotal;
    private String instructors;
    private String componentCode;

    public RawCourseListing(String semester, String subject, String catalogNumber, String location, int enrolmentCapacity, int enrolmentTotal, String instructors, String componentCode) {
        this.semester = semester;
        this.subject = subject;
        this.catalogNumber = catalogNumber;
        this.location = location;
        this.enrolmentCapacity = enrolmentCapacity;
        this.enrolmentTotal = enrolmentTotal;
        this.instructors = instructors;
        this.componentCode = componentCode;
    }

    public String getSemester() {
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
    public int getEnrolmentCapacity() {
        return enrolmentCapacity;
    }
    public int getEnrolmentTotal() {
        return enrolmentTotal;
    }
    public String getInstructors() {
        return instructors;
    }

    public String getComponentCode() {
        return componentCode;
    }
}

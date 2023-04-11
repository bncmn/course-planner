package ca.coursePlanner.Wrappers;

public class ApiSectionWrapper {
    public int sectionId;
    public int enrollmentCap;
    public int enrollmentTotal;
    public String type;

    public ApiSectionWrapper(String componentCode, int enrollmentTotal , int enrollmentCap) {
        this.enrollmentCap = enrollmentCap;
        this.enrollmentTotal = enrollmentTotal;
        this.type = componentCode;
    }
}

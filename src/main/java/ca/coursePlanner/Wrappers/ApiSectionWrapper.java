package ca.coursePlanner.Wrappers;

public class ApiSectionWrapper {
    public int enrolmentCapacity;
    public int enrolmentTotal;
    public String componentCode;

    public ApiSectionWrapper(int enrolmentCapacity, int enrolmentTotal, String componentCode) {
        this.enrolmentCapacity = enrolmentCapacity;
        this.enrolmentTotal = enrolmentTotal;
        this.componentCode = componentCode;
    }
}

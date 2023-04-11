package ca.coursePlanner.Model;

public class Section {
    private int enrolmentCapacity;
    private int enrolmentTotal;
    private String componentCode;

    public Section(int enrolmentCapacity, int enrolmentTotal, String componentCode) {
        this.enrolmentCapacity = enrolmentCapacity;
        this.enrolmentTotal = enrolmentTotal;
        this.componentCode = componentCode;
    }

    public int getEnrolmentCapacity() {
        return enrolmentCapacity;
    }

    public int getEnrolmentTotal() {
        return enrolmentTotal;
    }

    public String getComponentCode() {
        return componentCode;
    }

    public void setEnrolmentCapacity(int enrolmentCapacity) {
        this.enrolmentCapacity = enrolmentCapacity;
    }

    public void setEnrolmentTotal(int enrolmentTotal) {
        this.enrolmentTotal = enrolmentTotal;
    }

}

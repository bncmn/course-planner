package ca.coursePlanner.Model;

/**
 * Section models a component of a course offering. As some classes will have a
 * lecture and a lab/tutorial component, each instance of this object will serve
 * as one "component". This section contains information about:
 *  - what component of the offering it is (LEC/LAB/TUT/etc.)
 *  - the enrolment numbers of the specific component.
 *
 * @author Diego Buencamino
 * @author Matt Tsai
 */

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

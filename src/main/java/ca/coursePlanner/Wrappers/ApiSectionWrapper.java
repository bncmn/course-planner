package ca.coursePlanner.Wrappers;

/**
 * This wrapper packages instances of the Section object to be displayed
 * by the front-end.
 *
 * @author Diego Buencamino
 * @author Matt Tsai
 */

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

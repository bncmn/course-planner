package ca.coursePlanner.Wrappers;

/**
 * This wrapper packages requests to POST a new offering to be sent to the actual
 * model.
 *
 * @author Diego Buencamino
 * @author Matt Tsai
 */

public class ApiPayloadOfferingWrapper {
    public String semester;
    public String subjectName;
    public String catalogNumber;
    public String location;
    public int enrollmentCap;
    public String component;
    public int enrollmentTotal;
    public String instructor;

    public ApiPayloadOfferingWrapper(String semester, String subjectName, String catalogNumber, String location, int enrollmentCap, String component, int enrollmentTotal, String instructor) {
        this.semester = semester;
        this.subjectName = subjectName;
        this.catalogNumber = catalogNumber;
        this.location = location;
        this.enrollmentCap = enrollmentCap;
        this.component = component;
        this.enrollmentTotal = enrollmentTotal;
        this.instructor = instructor;
    }
}

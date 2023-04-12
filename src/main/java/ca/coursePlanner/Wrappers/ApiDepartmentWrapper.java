package ca.coursePlanner.Wrappers;

/**
 * This wrapper packages instances of the Department object to be displayed
 * by the front-end.
 *
 * @author Diego Buencamino
 * @author Matt Tsai
 */

public class ApiDepartmentWrapper {
    public int deptId;
    public String name;

    public ApiDepartmentWrapper(int departmentId, String name) {
        this.deptId = departmentId;
        this.name = name;
    }
}

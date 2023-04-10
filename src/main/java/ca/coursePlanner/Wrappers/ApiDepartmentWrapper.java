package ca.coursePlanner.Wrappers;

public class ApiDepartmentWrapper {
    public int deptId;
    public String name;

    public ApiDepartmentWrapper(int departmentId, String name) {
        this.deptId = departmentId;
        this.name = name;
    }
}

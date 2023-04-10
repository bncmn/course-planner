package ca.coursePlanner.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Course {
    private String catalogNumber;

    private List<CourseOffering> offerings = new ArrayList<>();

    public Course(String catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public List<CourseOffering> getOfferings() {
        return offerings;
    }

    public void addNewOffering(CourseOffering offering) {
        offerings.add(offering);
    }

    @Override
    public boolean equals(Object o){
        if (o == this) return true;

        if (!(o instanceof Course)) return false;

        Course that = (Course) o;
        return Objects.equals(catalogNumber, that.catalogNumber);
    }
}

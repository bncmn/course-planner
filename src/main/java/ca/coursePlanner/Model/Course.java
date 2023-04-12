package ca.coursePlanner.Model;

import java.util.*;

public class Course {
    private String catalogNumber;

    private List<CourseOffering> offerings = new ArrayList<>();

    private List<Watcher> watchers = new ArrayList<>();


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

    public void addWatcher(Watcher watcher) {
        watchers.add(watcher);
    }

    public void removeWatcher(Watcher watcher) {
        watchers.remove(watcher);
    }

    public void updateWatchers(Section section, String semester) {
        for (Watcher w : watchers) {
            w.update(section, semester);
        }
    }


    @Override
    public boolean equals(Object o){
        if (o == this) return true;

        if (!(o instanceof Course)) return false;

        Course that = (Course) o;
        return Objects.equals(catalogNumber, that.catalogNumber);
    }
}

package ca.coursePlanner.Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {
    // Will be changed in part 2 to take path to input file.
    public List<CourseOffering> parseCSV(String dataFile) {
        String line = "";
        String csvSeparator = ",";

        List<CourseOffering> importedOfferings = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(dataFile))) {
            // Parse lines in the CSV file
            while ((line = br.readLine()) != null) {
                String[] values = line.split(csvSeparator);

                String semester = values[0];
                String subject = values[1];
                String catalogNumber = values[2];
                String location = values[3];
                String enrolmentCap = values[4];
                String enrolmentTotal = values[5];
                String instructors = values[6];
                String componentCode = values[7];

                // Build new CourseOffering object
                CourseOffering currentOffering = new CourseOffering(
                        semester,
                        subject,
                        catalogNumber,
                        location,
                        enrolmentCap,
                        enrolmentTotal,
                        instructors,
                        componentCode
                );

                importedOfferings.add(currentOffering);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return importedOfferings;
    }
}

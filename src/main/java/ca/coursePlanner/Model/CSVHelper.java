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
            // Skip first line that contains column headers
            br.readLine();

            // Parse lines in the CSV file
            while ((line = br.readLine()) != null) {
                String[] lineIn = line.split(csvSeparator);

                String semester = lineIn[0];
                String subject = lineIn[1];
                String catalogNumber = lineIn[2];
                String location = lineIn[3];
                String enrolmentCap = lineIn[4];
                String enrolmentTotal = lineIn[5];
                String instructors = lineIn[6];
                String componentCode = lineIn[7];

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

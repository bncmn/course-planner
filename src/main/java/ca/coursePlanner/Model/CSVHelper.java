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
                // Regex sourced from: https://www.baeldung.com/java-split-string-commas
                String[] lineIn = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);;

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
                // Aggregation by semester, location, componentCode
                boolean sameSection = false;
                for (CourseOffering c : importedOfferings){
                    if (c.equals(currentOffering)) {
                        sameSection = true;
                        c.hasSameCampus(instructors);
                        c.addSpot(enrolmentCap, enrolmentTotal, instructors);
                        break;
                    }
                }
                if (!sameSection) {
                    importedOfferings.add(currentOffering);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return importedOfferings;
    }
}

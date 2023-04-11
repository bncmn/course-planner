package ca.coursePlanner.Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {
    // Will be changed in part 2 to take path to input file.
    public List<RawCourseListing> parseCSV(String dataFile) {
        String line = "";
        String csvSeparator = ",";

        List<RawCourseListing> rawOfferings = new ArrayList<>();

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
                int enrolmentCap = Integer.parseInt(lineIn[4]);
                int enrolmentTotal = Integer.parseInt(lineIn[5]);
                String instructors = lineIn[6];
                String componentCode = lineIn[7];

                // Build new RawCourseListing object
                RawCourseListing rawListing = new RawCourseListing(
                        semester,
                        subject,
                        catalogNumber,
                        location,
                        enrolmentCap,
                        enrolmentTotal,
                        instructors,
                        componentCode
                );

                rawOfferings.add(rawListing);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rawOfferings;
    }
}

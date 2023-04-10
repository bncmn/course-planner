package ca.coursePlanner.Wrappers;

public class ApiOfferingWrapper {
    public int offeringId;
    public String semesterCode;
    public String term;
    public String year;
    public String location;
    public String instructors;

    public ApiOfferingWrapper(int offeringId, String semester, String location, String instructors) {
        this.offeringId = offeringId;
        this.semesterCode = semester;
        this.term = decodeSemesterSeason(semester);
        this.year = decodeYear(semester);
        this.location = location;
        this.instructors = instructors;
    }

    private String decodeYear(String semesterCode) {
        final int CONSTANT_CENTURY = 1900;
        String yearSubstring = semesterCode.substring(0, 3);

        int decodedYear = CONSTANT_CENTURY
                + (100 * Character.getNumericValue(yearSubstring.charAt(0)))
                + (10 * Character.getNumericValue(yearSubstring.charAt(1)))
                + Character.getNumericValue(yearSubstring.charAt(2));

        return Integer.toString(decodedYear);
    }

    private String decodeSemesterSeason(String semesterCode) {
        char semesterInt = semesterCode.charAt(semesterCode.length() - 1);

        return switch (semesterInt) {
            case '1' -> "Spring";
            case '4' -> "Summer";
            case '7' -> "Fall";
            default -> null;
        };
    }
}

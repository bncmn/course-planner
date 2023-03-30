package ca.coursePlanner.Model;

public class Semester {
    private String year;
    private String season;
    private String rawSemesterCode;

    public Semester(String semesterCode) {
        this.rawSemesterCode = semesterCode;
        this.year = decodeYear(semesterCode);
        this.season = decodeSemesterSeason(semesterCode);
    }

    public String getRawSemesterCode() {
        return rawSemesterCode;
    }

    public String getYear() {
        return year;
    }

    public String getSeason() {
        return season.toString();
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
            case '1' -> "SPRING";
            case '4' -> "SUMMER";
            case '7' -> "FALL";
            default -> null;
        };
    }
}

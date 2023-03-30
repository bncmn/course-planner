package ca.coursePlanner.Model;

public class Semester {
    enum SemesterSeason {
        SPRING,
        SUMMER,
        FALL
    }
    private String year;
    private SemesterSeason season;

    public Semester(String semesterCode) {
        this.year = decodeYear(semesterCode);
        this.season = decodeSemesterSeason(semesterCode);
    }

    private String decodeYear(String semesterCode) {
        final int CONSTANT_CENTURY = 1900;
        String yearSubstring = semesterCode.substring(0, 3);

        int decodedYear = CONSTANT_CENTURY
                + (100 * yearSubstring.charAt(0))
                + (10 * yearSubstring.charAt(1))
                + yearSubstring.charAt(2);

        return Integer.toString(decodedYear);
    }

    private SemesterSeason decodeSemesterSeason(String semesterCode) {
        int semesterInt = semesterCode.charAt(semesterCode.length() - 1);

        return switch (semesterInt) {
            case 1 -> SemesterSeason.SPRING;
            case 4 -> SemesterSeason.SUMMER;
            case 7 -> SemesterSeason.FALL;
            default -> null;
        };
    }
}

package ru.nsu.kozlov;

import java.util.ArrayList;
import java.util.HashMap;
import ru.nsu.kozlov.enums.AssessmentType;
import ru.nsu.kozlov.enums.EducationForm;
import ru.nsu.kozlov.enums.GradeType;

/**
 * implementation of student's record book.
 */
public class RecordBook {
    private final int totalMarks = 88;
    private String studentName;
    private ArrayList<Session> sessions;
    private EducationForm educationForm;

    private int[] maxNumbersOfGrades;
    private int[] numbersOfGrades;

    /**
     * record book constructor.
     * @param studentName name of student
     * @param educationForm paid or budget
     */
    public RecordBook(String studentName, EducationForm educationForm) {
        this.studentName = studentName;
        this.sessions = new ArrayList<>();
        this.educationForm = educationForm;

        this.maxNumbersOfGrades = new int[] {15, 13, 2, 19, 35, 7, 3, 1};
        this.numbersOfGrades = new int[] {0, 0, 0, 0, 0, 0, 0, 0};

        this.sessions.add(new Session(1, 2, 3, 1, 3, 3, 3, 0, 0));
        this.sessions.add(new Session(2, 2, 3, 1, 3, 3, 2, 0, 0));
        this.sessions.add(new Session(3, 3, 2, 0, 2, 6, 0, 0, 0));
        this.sessions.add(new Session(4, 2, 1, 0, 5, 5, 0, 0, 0));
        this.sessions.add(new Session(5, 2, 2, 0, 3, 4, 0, 0, 0));
        this.sessions.add(new Session(6, 2, 2, 0, 2, 6, 0, 0, 0));
        this.sessions.add(new Session(7, 2, 0, 0, 1, 4, 1, 1, 0));
        this.sessions.add(new Session(8, 0, 0, 0, 0, 4, 1, 2, 1));
    }

    void addGrade(Grade grade) {
        int sessionNumber = grade.getSessionNumber() - 1;
        int indexOfType = grade.getAssessmentType().ordinal();

        if (numbersOfGrades[indexOfType] < maxNumbersOfGrades[indexOfType]) {
            if (sessions.get(sessionNumber).addGrade(grade) == 0) {
                numbersOfGrades[indexOfType]++;
            }
        }
    }

    double getGradePointAverage() {
        int totalSum =  sessions.stream()
                .flatMap(session -> session.getGrades().stream())
                .filter(grade -> grade.getAssessmentType() == AssessmentType.EXAM
                              || grade.getAssessmentType() == AssessmentType.DIFF_PASS
                              || grade.getAssessmentType() == AssessmentType.DIPLOMA)
                .mapToInt(Grade::getGrade)
                .sum();
        long totalCount =  sessions.stream()
                .flatMap(session -> session.getGrades().stream())
                .filter(grade -> grade.getAssessmentType() == AssessmentType.EXAM
                        || grade.getAssessmentType() == AssessmentType.DIFF_PASS
                        || grade.getAssessmentType() == AssessmentType.DIPLOMA)
                .count();

        return (totalCount == 0) ? 0 : (double) totalSum / totalCount;
    }

    boolean possibleToGetBudget() {
        int lastSessionNumber = 0;
        for (int i = 0; i < 8; i++) {
            if (sessions.get(i).getNumberOfGrades() == 0) {
                lastSessionNumber = i;
                break;
            }
        }

        System.out.println(lastSessionNumber);

        if (lastSessionNumber < 2) {
            return false;
        }

        long badMarks = 0;

        int finalLastSessionNumber = lastSessionNumber;
        badMarks = sessions.stream()
                .filter(session -> session.getSessionNumber() >= finalLastSessionNumber - 1)
                .flatMap(session -> session.getGrades().stream())
                .filter(grade -> grade.getAssessmentType() == AssessmentType.EXAM
                                && grade.getGrade() < 4)
                .count();

        return badMarks == 0 && educationForm == EducationForm.PAID;
    }

    boolean possibleToGetRedDiploma() {

        HashMap<String, Grade> hashMapGrades = new HashMap<>();

        for (Session session : sessions) {
            for (Grade grade : session.getGrades()) {
                if (grade.getAssessmentType() != AssessmentType.PASS) {
                    hashMapGrades.put(grade.getSubjectName(), grade);
                }
            }
        }

        ArrayList<Grade> grades = new ArrayList<>(hashMapGrades.values());

        int total = 0;

        for (Grade grade : grades) {
            if (grade.getAssessmentType() == AssessmentType.DIPLOMA && grade.getGrade() != 5) {
                return false;
            }

            if ((grade.getAssessmentType() == AssessmentType.EXAM
                    || grade.getAssessmentType() == AssessmentType.DIFF_PASS)
                    &&  grade.getGrade() < 4) {
                return false;
            }

            if (grade.getGrade() < 5) {
                total += grade.getGrade();
            }
        }

        return ((double) total / totalMarks) * 100 < 25;
    }

    boolean possibleToGetIncreasedScholarship() {
        int lastSessionNumber = 0;
        for (int i = 0; i < 8; i++) {
            if (sessions.get(i).getNumberOfGrades() == 0) {
                lastSessionNumber = i;
                break;
            }
        }

        if (lastSessionNumber == 0) {
            return true;
        }

        for (Grade grade : sessions.get(lastSessionNumber - 1).getGrades()) {
            if (grade.getEnumGrade() == GradeType.FAILED || grade.getGrade() < 5) {
                return false;
            }
        }

        return true;
    }
}
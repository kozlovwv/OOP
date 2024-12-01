package ru.nsu.kozlov;

import ru.nsu.kozlov.enums.AssessmentType;
import ru.nsu.kozlov.enums.GradeType;

public class Grade {
    private final int sessionNumber;
    private final String subjectName;
    private final AssessmentType assessmentType;
    private final GradeType grade;

    public Grade(int sessionNumber, String subjectName,
                 AssessmentType assessmentType, GradeType grade) {
        this.sessionNumber = sessionNumber;
        this.subjectName = subjectName;
        this.assessmentType = assessmentType;
        this.grade = grade;
    }

    public AssessmentType getAssessmentType() {
        return assessmentType;
    }

    public int getGrade() {
        return switch (grade) {
            case FIVE -> 5;
            case FOUR -> 4;
            case THREE -> 3;
            case TWO -> 2;
            case PASSED -> 1;
            case FAILED -> 0;
        };
    }

    public GradeType getEnumGrade() {
        return grade;
    }

    public int getSessionNumber() {
        return sessionNumber;
    }

    public String getSubjectName() {
        return subjectName;
    }
}

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
        int value = 0;
        switch (grade) {
            case FIVE:
                value = 5;
                break;
            case FOUR:
                value = 4;
                break;
            case THREE:
                value = 3;
                break;
            case TWO:
                value = 2;
                break;
            case PASSED:
                value = 1;
                break;
            case FAILED:
                value = 0;
                break;
        }
        return value;    
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

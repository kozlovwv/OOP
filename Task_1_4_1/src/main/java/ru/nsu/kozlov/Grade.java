package ru.nsu.kozlov;

import ru.nsu.kozlov.enums.AssessmentType;
import ru.nsu.kozlov.enums.GradeType;

/**
 * implementation of grade with fields for number of session,
 * name of subject, type of assessment.
 */
public class Grade {
    private final int sessionNumber;
    private final String subjectName;
    private final AssessmentType assessmentType;
    private final GradeType grade;

    /**
     * grade constructor.
     *
     * @param sessionNumber number of session
     * @param subjectName name of subject
     * @param assessmentType type of assessment
     * @param grade grade
     */
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

    /**
     * getting grade by its string enum(string) representation.
     *
     * @return grade like number
     */
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
            default:
                value = -1;
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

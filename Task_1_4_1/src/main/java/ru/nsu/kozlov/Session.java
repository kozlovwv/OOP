package ru.nsu.kozlov;

import ru.nsu.kozlov.enums.AssessmentType;

import java.util.ArrayList;

public class Session {
    private int sessionNumber;
    private ArrayList<Grade> grades;

    private int[] gradesCount;
    private int[] gradesMaxCount;

    public Session(int sessionNumber,
                   int taskNumber, int testNumber,
                   int colloquiumNumber, int examNumber,
                   int diffPassNumber, int passNumber,
                   int defenceOfPracticeNumber, int diplomaNumber) {
        this.sessionNumber = sessionNumber;
        this.grades = new ArrayList<>();

        this.gradesMaxCount = new int[] {taskNumber, testNumber, colloquiumNumber,
            examNumber, diffPassNumber, passNumber, defenceOfPracticeNumber, diplomaNumber};
        this.gradesCount = new int[] {0, 0, 0, 0, 0, 0, 0, 0};
    }

    int addGrade(Grade grade) {
        AssessmentType type = grade.getAssessmentType();
        int indexOfType = type.ordinal();

        if (gradesCount[indexOfType] < gradesMaxCount[indexOfType]) {
            grades.add(grade);
            gradesCount[indexOfType]++;
            return 0;
        }

        return 1;
    }

    public int getSessionNumber() {
        return sessionNumber;
    }

    public ArrayList<Grade> getGrades() {
        return grades;
    }

    public int getNumberOfGrades() {
        return grades.size();
    }

    public int[] getGradesCountArray() {
        return gradesCount;
    }

    public int[] getGradesMaxCountArray() {
        return gradesMaxCount;
    }
}

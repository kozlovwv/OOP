package ru.nsu.kozlov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import ru.nsu.kozlov.enums.AssessmentType;
import ru.nsu.kozlov.enums.EducationForm;
import ru.nsu.kozlov.enums.GradeType;

class RecordBookTest {

    @Test
    void test1() {
        RecordBook recordBook = new RecordBook("Kozlov", EducationForm.BUDGET);

        recordBook.addGrade(new Grade(1, "OS", AssessmentType.EXAM, GradeType.FIVE));
        recordBook.addGrade(new Grade(1, "A",  AssessmentType.DIFF_PASS, GradeType.FOUR));
        recordBook.addGrade(new Grade(1, "B",  AssessmentType.EXAM, GradeType.FIVE));
        recordBook.addGrade(new Grade(2, "C",  AssessmentType.EXAM, GradeType.FIVE));
        recordBook.addGrade(new Grade(2, "D",  AssessmentType.EXAM, GradeType.FOUR));
        recordBook.addGrade(new Grade(8, "E",  AssessmentType.DIPLOMA, GradeType.FIVE));

        assertEquals(recordBook.getGradePointAverage(), ((double) (5 * 4 + 4 * 2) / 6));
        assertFalse(recordBook.possibleToGetIncreasedScholarship());
        assertTrue(recordBook.possibleToGetRedDiploma());
    }

    @Test
    void test2() {
        RecordBook recordBook = new RecordBook("Kozlov", EducationForm.BUDGET);

        assertTrue(recordBook.possibleToGetIncreasedScholarship());

        recordBook.addGrade(new Grade(1, "OS", AssessmentType.EXAM, GradeType.FIVE));
        recordBook.addGrade(new Grade(1, "A",  AssessmentType.EXAM, GradeType.FOUR));
        recordBook.addGrade(new Grade(1, "B",  AssessmentType.EXAM, GradeType.THREE));

        assertFalse(recordBook.possibleToGetIncreasedScholarship());

        recordBook.addGrade(new Grade(2, "C",  AssessmentType.EXAM, GradeType.FIVE));
        recordBook.addGrade(new Grade(2, "D",  AssessmentType.EXAM, GradeType.FOUR));
        recordBook.addGrade(new Grade(2, "E",  AssessmentType.EXAM, GradeType.FIVE));

        assertFalse(recordBook.possibleToGetIncreasedScholarship());

        recordBook.addGrade(new Grade(3, "C",  AssessmentType.EXAM, GradeType.FIVE));
        recordBook.addGrade(new Grade(3, "D",  AssessmentType.EXAM, GradeType.FIVE));

        assertTrue(recordBook.possibleToGetIncreasedScholarship());
    }

    @Test
    void test3() {
        RecordBook recordBook = new RecordBook("Kozlov", EducationForm.BUDGET);

        recordBook.addGrade(new Grade(1, "OS", AssessmentType.EXAM, GradeType.FIVE));
        recordBook.addGrade(new Grade(1, "A",  AssessmentType.DIFF_PASS, GradeType.FOUR));
        recordBook.addGrade(new Grade(1, "B",  AssessmentType.EXAM, GradeType.FIVE));
        recordBook.addGrade(new Grade(2, "C",  AssessmentType.EXAM, GradeType.FIVE));
        recordBook.addGrade(new Grade(2, "D",  AssessmentType.EXAM, GradeType.FOUR));
        recordBook.addGrade(new Grade(8, "E",  AssessmentType.DIPLOMA, GradeType.FOUR));

        assertFalse(recordBook.possibleToGetRedDiploma());
    }

    @Test
    void test4() {
        RecordBook recordBook = new RecordBook("Kozlov", EducationForm.BUDGET);

        recordBook.addGrade(new Grade(1, "OS", AssessmentType.EXAM, GradeType.FIVE));
        recordBook.addGrade(new Grade(1, "A",  AssessmentType.DIFF_PASS, GradeType.FOUR));
        recordBook.addGrade(new Grade(1, "B",  AssessmentType.EXAM, GradeType.THREE));
        recordBook.addGrade(new Grade(2, "C",  AssessmentType.EXAM, GradeType.FIVE));
        recordBook.addGrade(new Grade(2, "D",  AssessmentType.EXAM, GradeType.FOUR));

        assertFalse(recordBook.possibleToGetRedDiploma());
    }

    @Test
    void test5() {
        RecordBook recordBook = new RecordBook("Kozlov", EducationForm.PAID);

        recordBook.addGrade(new Grade(1, "OS", AssessmentType.EXAM, GradeType.FIVE));
        recordBook.addGrade(new Grade(1, "A",  AssessmentType.DIFF_PASS, GradeType.FOUR));
        recordBook.addGrade(new Grade(1, "B",  AssessmentType.EXAM, GradeType.FIVE));
        recordBook.addGrade(new Grade(2, "C",  AssessmentType.EXAM, GradeType.FIVE));
        recordBook.addGrade(new Grade(2, "D",  AssessmentType.EXAM, GradeType.FOUR));

        assertTrue(recordBook.possibleToGetBudget());
    }
}
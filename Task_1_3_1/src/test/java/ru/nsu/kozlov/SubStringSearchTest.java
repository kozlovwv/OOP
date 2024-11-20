package ru.nsu.kozlov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class SubStringSearchTest {
    @Test
    void test1() {
        ArrayList<Long> arrList = new ArrayList<>();
        arrList.add(0L);
        arrList.add(10L);

        try {
            File myObj = new File("input.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
            FileWriter myWriter = new FileWriter("input.txt");
            myWriter.write("test1\uD83C\uDF89\uD83C\uDF3A\uD83D"
                    + "\uDD25\uD83C\uDF88\uD83C\uDF1Ftest1");
            myWriter.close();
            assertEquals(arrList, SubStringSearch.find("input.txt", "test1"));
            if (myObj.delete()) {
                System.out.println("Deleted the file: " + myObj.getName());
            } else {
                System.out.println("Failed to delete the file.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred during test 1");
        }
    }

    @Test
    void test2() {
        ArrayList<Long> arrList = new ArrayList<>();
        try {
            File myObj = new File("input.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
            FileWriter myWriter = new FileWriter("input.txt");
            for (long i = 0L; i < 7064091L; i++) { //size of file - 2GB
                myWriter.write("我好好好好好好好好好好好好好好好好好好好好好好好好好好好好" +
                        "好好好好好好好好好好好好好好好好好好好好好好好好好好好好好好好好好好好好好好" +
                        "好好好好好好好好好好好好好好好好好好好好好好好好好好好好好好好好好好\n");
                arrList.add(i * 102L);
            }
            myWriter.close();
            assertEquals(arrList, SubStringSearch.find("input.txt", "我"));
            if (myObj.delete()) {
                System.out.println("Deleted the file: " + myObj.getName());
            } else {
                System.out.println("Failed to delete the file.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred during test 1");
        }
    }

    @Test
    void test3() {
        ArrayList<Long> arrList = new ArrayList<>();
        try {
            File myObj = new File("input.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
            FileWriter myWriter = new FileWriter("input.txt");
            for (long i = 0L; i < 4564259L; i++) { //size of file - 4GB
                myWriter.write("\uD83D\uDE0A\uD83C\uDF1F\uD83C\uDF08\uD83C\uDF89\uD83C\uDF3A"
                    + "\uD83C\uDFB6\uD83C\uDF1E\uD83E\uDD8B\uD83C\uDF3C\uD83D\uDCAB\uD83C\uDF3F"
                    + "\uD83C\uDF38\uD83C\uDF3B\uD83C\uDF08\uD83C\uDF1F\uD83C\uDF1F\uD83C\uDF08"
                    + "\uD83C\uDF89\uD83C\uDF3A\uD83C\uDFB6\uD83C\uDF1E\uD83E\uDD8B\uD83C\uDF3C"
                    + "\uD83D\uDCAB\uD83C\uDF3F\uD83C\uDF38\uD83C\uDF3B\uD83C\uDF08\uD83C\uDF1F"
                    + "\uD83C\uDF1F\uD83C\uDF08\uD83C\uDF89\uD83C\uDF3A\uD83C\uDFB6\uD83C\uDF1E"
                    + "\uD83E\uDD8B\uD83C\uDF3C\uD83D\uDCAB\uD83C\uDF3F\uD83C\uDF38\uD83C\uDF3B"
                    + "\uD83C\uDF08\uD83C\uDF1F\uD83C\uDF1F\uD83C\uDF08\uD83C\uDF89\uD83C\uDF3A"
                    + "\uD83C\uDFB6\uD83C\uDF1E\uD83E\uDD8B\uD83C\uDF3C\uD83D\uDCAB\uD83C\uDF3F"
                    + "\uD83C\uDF38\uD83C\uDF3B\uD83C\uDF08\uD83C\uDF1F\uD83C\uDF1F\uD83C\uDF08"
                    + "\uD83C\uDF89\uD83C\uDF3A\uD83C\uDFB6\uD83C\uDF1E\uD83E\uDD8B\uD83C\uDF3C"
                    + "\uD83D\uDCAB\uD83C\uDF3F\uD83C\uDF38\uD83C\uDF3B\uD83C\uDF08\uD83C\uDF1F"
                    + "\uD83C\uDF1F\uD83C\uDF08\uD83C\uDF89\uD83C\uDF3A\uD83C\uDFB6\uD83C\uDF1E"
                    + "\uD83E\uDD8B\uD83C\uDF3C\uD83D\uDCAB\uD83C\uDF3F\uD83C\uDF38\uD83C\uDF3B"
                    + "\uD83C\uDF08\uD83C\uDF1F\uD83C\uDF1F\uD83C\uDF08\uD83C\uDF89\uD83C\uDF3A"
                    + "\uD83C\uDFB6\uD83C\uDF1E\uD83E\uDD8B\uD83C\uDF3C\uD83D\uDCAB\uD83C\uDF3F"
                    + "\uD83C\uDF38\uD83C\uDF3B\uD83C\uDF08\uD83C\uDF1F\uD83C\uDF1F\uD83C\uDF08"
                    + "\uD83C\uDF89\uD83C\uDF3A\uD83C\uDFB6\uD83C\uDF1E\uD83E\uDD8B\uD83C\uDF3C"
                    + "\uD83D\uDCAB\uD83C\uDF3F\uD83C\uDF38\uD83C\uDF3B\uD83C\uDF08\uD83C\uDF1F"
                    + "\uD83C\uDF1F\uD83C\uDF08\uD83C\uDF89\uD83C\uDF3A\uD83C\uDFB6\uD83C\uDF1E"
                    + "\uD83E\uDD8B\uD83C\uDF3C\uD83D\uDCAB\uD83C\uDF3F\uD83C\uDF38\uD83C\uDF3B"
                    + "\uD83E\uDD8B\uD83C\uDF3C\uD83D\uDCAB\uD83C\uDF3F\uD83C\uDF38\uD83C\uDF3B"
                    + "\uD83E\uDD8B\uD83C\uDF3C\uD83D\uDCAB\uD83C\uDF3F\uD83C\uDF38\uD83C\uDF3B"
                    + "\uD83E\uDD8B\uD83C\uDF3C\uD83D\uDCAB\uD83C\uDF3F\uD83C\uDF38\uD83C\uDF3B"
                    + "\uD83E\uDD8B\uD83C\uDF3C\uD83D\uDCAB\uD83C\uDF3F\uD83C\uDF38\uD83C\uDF3B"
                    + "\uD83E\uDD8B\uD83C\uDF3C\uD83D\uDCAB\uD83C\uDF3F\uD83C\uDF38\uD83C\uDF3B"
                    + "\uD83E\uDD8B\uD83C\uDF3C\uD83D\uDCAB\uD83C\uDF3F\uD83C\uDF38\uD83C\uDF3B"
                    + "\uD83E\uDD8B\uD83C\uDF3C\uD83D\uDCAB\uD83C\uDF3F\uD83C\uDF38\uD83C\uDF3B"
                    + "\uD83E\uDD8B\uD83C\uDF3C\uD83D\uDCAB\uD83C\uDF3F\uD83C\uDF38\uD83C\uDF3B"
                    + "\uD83E\uDD8B\uD83C\uDF3C\uD83D\uDCAB\uD83C\uDF3F\uD83C\uDF38\uD83C\uDF3B"
                    + "\uD83E\uDD8B\uD83C\uDF3C\uD83D\uDCAB\uD83C\uDF3F\uD83C\uDF38\uD83C\uDF3B"
                    + "\uD83E\uDD8B\uD83C\uDF3C\uD83D\uDCAB\uD83C\uDF3F\uD83C\uDF38\uD83C\uDF3B"
                    + "\uD83E\uDD8B\uD83C\uDF3C\uD83D\uDCAB\uD83C\uDF3F\uD83C\uDF38\uD83C\uDF3B"
                    + "\uD83E\uDD8B\uD83C\uDF3C\uD83D\uDCAB\uD83C\uDF3F\uD83C\uDF38\uD83C\uDF3B"
                    + "\uD83E\uDD8B\uD83C\uDF3C\uD83D\uDCAB\uD83C\uDF3F\uD83C\uDF38\uD83C\uDF3B"
                    + "\uD83E\uDD8B\uD83C\uDF3C\uD83D\uDCAB\uD83C\uDF3F\uD83C\uDF38\uD83C\uDF3B"
                    + "\uD83E\uDD8B\uD83C\uDF3C\uD83D\uDCAB\uD83C\uDF3F\uD83C\uDF38\uD83C\uDF3B"
                    + "\uD83E\uDD8B\uD83C\uDF3C\uD83D\uDCAB\uD83C\uDF3F\uD83C\uDF38\uD83C\uDF3B"
                    + "\uD83E\uDD8B\uD83C\uDF3C\uD83D\uDCAB\uD83C\uDF3F\uD83C\uDF38\uD83C\uDF3B"
                    +"\uD83C\uDF08\uD83C\uDF1F\n");
                arrList.add(i * 236);
            }
            myWriter.close();
            assertEquals(arrList, SubStringSearch.find("input.txt", "\uD83D\uDE0A"));
            if (myObj.delete()) {
                System.out.println("Deleted the file: " + myObj.getName());
            } else {
                System.out.println("Failed to delete the file.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred during test 1");
        }
    }
}
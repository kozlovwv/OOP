package ru.nsu.kozlov;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * class implements method for finding substring in file.
 */
public class SubStringSearch {
    static ArrayList<Long> find(String fileName, String subString) {
        ArrayList<Long> indexes = new ArrayList<>();
        byte[] arr = subString.getBytes(StandardCharsets.UTF_8);
        int arrLength = arr.length;
        int bufferSize = arrLength * 2;
        long currPos = -1;

        try (BufferedInputStream inputStream =
                     new BufferedInputStream(new FileInputStream(fileName))) {
            byte[] buffer = new byte[bufferSize];
            byte[] helper1 = new byte[arrLength - 1];
            Arrays.fill(helper1, (byte) 0b10101010);
            byte[] helper2 = new byte[bufferSize - arrLength + 1];
            int bytesRead;

            while ((bytesRead = inputStream.read(helper2)) != -1) {
                System.arraycopy(helper1, 0, buffer, 0, helper1.length);
                System.arraycopy(helper2, 0, buffer, helper1.length, helper2.length);
                for (int i = 0; i < bytesRead; i++) {
                    if (isStartByte(buffer[i])) {
                        boolean correct = true;
                        currPos++;
                        for (int j = 0; j < arrLength; j++) {
                            if (arr[j] != buffer[i + j]) {
                                correct = false;
                                break;
                            }
                        }
                        if (correct) {
                            indexes.add(currPos);
                        }
                    }
                }
                helper1 = Arrays.copyOfRange(buffer,
                        buffer.length - (arrLength - 1), buffer.length);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }

        return indexes;
    }

    private static boolean isStartByte(byte currByte) {
        boolean cond1 = (currByte >> 7) == 0b00000000;
        boolean cond2 = (currByte & 0b11110000) == 0b11110000;
        boolean cond3 = (currByte & 0b11100000) == 0b11100000;
        boolean cond4 = (currByte & 0b11110000) == 0b11110000;
        return cond1 || cond2 || cond3 || cond4;
    }
}

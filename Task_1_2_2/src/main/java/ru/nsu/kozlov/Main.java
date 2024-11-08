package ru.nsu.kozlov;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        HashTable<String, Integer> hashTable = new HashTable<>();

        hashTable.add("q", 1);
        hashTable.add("w", 1);
        hashTable.add("e", 1);

        System.out.println(hashTable);

        Iterator<Cell<String, Integer>> iterator = hashTable.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
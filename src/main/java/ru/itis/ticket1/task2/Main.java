package ru.itis.ticket1.task2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LexicographicalComparator lexicographicalComparator = new LexicographicalComparator();
        List<Integer> integers = new ArrayList<>();
        integers.add(2);
        integers.add(10);
        System.out.println(Collections.min(integers, lexicographicalComparator));
    }
}

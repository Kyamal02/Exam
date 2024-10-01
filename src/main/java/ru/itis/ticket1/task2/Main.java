package ru.itis.ticket1.task2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Создаем экземпляр нашего лексикографического компаратора
        LexicographicalComparator lexicographicalComparator = new LexicographicalComparator();

        // Создаем список целых чисел для сравнения
        List<Integer> integers = new ArrayList<>();
        integers.add(2);
        integers.add(10);
        // Находим минимальное число в списке с использованием нашего компаратора
        // В лексикографическом порядке "10" меньше, чем "2"
        System.out.println(Collections.min(integers, lexicographicalComparator));
    }
}

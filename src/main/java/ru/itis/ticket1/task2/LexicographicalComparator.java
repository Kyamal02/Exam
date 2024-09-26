package ru.itis.ticket1.task2;

import java.util.Comparator;

public class LexicographicalComparator implements Comparator<Integer> {
    // ["2", "10", "1", "21", "12"]
    // ["1", "10", "12", "2", "21"]
    // При этом они сравниваются не как числовые значения,
    // а как последовательности символов.
    // Строки сравниваются посимвольно слева направо, основываясь
    //на значении каждого символа в соответствии с их порядком в таблице символов
    // Так слова находятся в обычных словарях

    //Возвращает отрицательное число, если o1 меньше o2.
    //Возвращает ноль, если o1 равно o2.
    //Возвращает положительное число, если o1 больше o2.


    @Override
    public int compare(Integer o1, Integer o2) {
        int lengthO1 = countDigits(o1);
        int lengthO2 = countDigits(o2);
        int minLength = Math.min(lengthO1, lengthO2);

        // Сравниваем цифры посимвольно слева направо до длины меньшего числа
        for (int i = 1; i <= minLength; i++) {
            int digitO1 = getDigitFromLeft(o1, lengthO1, i);
            int digitO2 = getDigitFromLeft(o2, lengthO2, i);

            if (digitO1 != digitO2) {
                return digitO1 - digitO2;
            }
        }

        // Если все цифры совпадают до minLength, то меньшее число по длине считается меньшим
        if (lengthO1 == lengthO2) {
            return 0;
        } else if (lengthO1 < lengthO2) {
            return -1;
        } else {
            return 1;
        }
    }

    // Метод для получения цифры с позиции слева
    private int getDigitFromLeft(int number, int length, int position) {
        int divisor = (int) Math.pow(10, length - position);
        return (number / divisor) % 10;
    }

    // Метод для подсчета количества цифр в числе
    private int countDigits(int number) {
        if (number == 0) return 1;  // Учитываем случай с числом 0
        int count = 0;
        while (number != 0) {
            number /= 10;
            count++;
        }
        return count;
    }
}


//если можно было бы конвертировать в строки
//    @Override
//    public int compare(Integer a, Integer b) {
//        // Преобразуем целые числа в строки
//        String strA = a.toString();
//        String strB = b.toString();
//
//        // Сравниваем их лексикографически
//        return strA.compareTo(strB);
//    }


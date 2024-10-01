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
        // Определяем количество цифр в каждом числе
        int lengthO1 = countDigits(o1);
        int lengthO2 = countDigits(o2);

        // Находим минимальную длину между двумя числами для сравнения
        int minLength = Math.min(lengthO1, lengthO2);

        // Сравниваем цифры посимвольно слева направо до длины меньшего числа
        for (int i = 1; i <= minLength; i++) {
            // Получаем i-ю цифру слева для каждого числа
            int digitO1 = getDigitFromLeft(o1, lengthO1, i);
            int digitO2 = getDigitFromLeft(o2, lengthO2, i);

            // Если цифры на текущей позиции не равны, определяем порядок
            if (digitO1 != digitO2) {
                // Разница между цифрами определяет порядок чисел
                return digitO1 - digitO2;
            }
        }

        // Если все цифры совпадают до minLength,
        // то меньшее число по длине считается меньшим (например, "123" меньше "1234")
        if (lengthO1 == lengthO2) {
            // Числа полностью идентичны
            return 0;
        } else if (lengthO1 < lengthO2) {
            // Первое число короче, значит оно меньше
            return -1;
        } else {
            // Второе число короче, значит первое число больше
            return 1;
        }
    }

    /**
     *
     * @param number исходное число
     * @param length общее количество цифр в числе
     * @param position позиция цифры слева (1 - самая левая цифра)
     * @return (number / divisor) % 10 (возвращает цифру на позиции)
     */
    private int getDigitFromLeft(int number, int length, int position) {
        int divisor = (int) Math.pow(10, length - position);
        return (number / divisor) % 10;
    }

    /**
     *
     * @param number число для которого хотим посчитать количество цифр
     * @return количество чисел в числе
     */
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


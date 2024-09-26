package ru.itis.ticket1.task1;

public class Main {
    public static void main(String[] args) {
        MatrixCode matrixCode = new MatrixCode(new int[][]{
                {1, 0, 0, 2},
                {0, 0, 3, 0},
                {4, 0, 0, 0},
                {0, 5, 0, 0}
        });


// Так делать нельзя, так как мы будем изменять оригинальную ссылку first
//        while (matrixCode.first.next != null) {
//            System.out.print(matrixCode.first.value);
//            matrixCode.first = matrixCode.first.next;
//        }


        //Нужно делать так
//         Мы не изменяем оригинальную ссылку на объект Node first
//         а создаем другую ссылку, которая ссылается на этот же объект
        System.out.println("Вывод связного списка:");
        Node current = matrixCode.first;  // Сохраняем оригинальный указатель first
        while (current != null) {
            System.out.print(current.value + " ");  // Печатаем значение текущего узла
            current = current.next;           // Переходим к следующему узлу
        }

        System.out.print("\n \n" + "Вывод матрицы по связному списку(decode):");
        //Вызываем метод decode и его результат записываем в переменную result
        int[][] result = matrixCode.decode();
        // Проходимся по всему массиву и выводим в консоль
        for (int i = 0; i < result.length; i++) {
            System.out.print("\n");
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j] + " ");
            }
        }

        //Вывод в консоль результата метода sumDiag(), который высчитывает сумму диагонали
        System.out.print("\n \n" + "Cумма диагонали: " + matrixCode.sumDiag());
        //удаление элемента из связного списка
        matrixCode.delete(0, 0);
        System.out.print("\n \n" + "Удалили элемент i=0 j=0 и теперь выведем массив:");
        // теперь после удаления мы можем еще раз декодировать наш список в
        // массив и проверить удалилась ли переменная
        result = matrixCode.decode();
        // Проходимся по всему массиву и выводим в консоль
        for (int i = 0; i < result.length; i++) {
            System.out.print("\n");
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j] + " ");
            }
        }
        System.out.print("\n \n" + "Теперь вставим в i=0 j=0 value = 10 и выведем связный список: ");
        matrixCode.insert(0, 0, 10);

        current = matrixCode.first;  // Сохраняем оригинальный указатель first
        while (current != null) {
            System.out.print(current.value + " ");  // Печатаем значение текущего узла
            current = current.next;           // Переходим к следующему узлу
        }

        System.out.print("\n \n" + "Вывод обновленной матрицы по связному списку(decode):");
        //Вызываем метод decode и его результат записываем в переменную result
        result = matrixCode.decode();
        // Проходимся по всему массиву и выводим в консоль
        for (int i = 0; i < result.length; i++) {
            System.out.print("\n");
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j] + " ");
            }
        }

    }
}

package ru.itis.ticket1.task1;


public class MatrixCode {
    // Фиксированный размер квадратной матрицы
    // static говорит о том, что переменная принадлежит классу, а не объекту
    // final о том, что переменную нельзя изменять
    static final int SIZE = 4;

    // Ссылка на первый элемент связного списка
    Node first;
    // Ссылка на последний элемент списка
    Node last;

    // Конструктор, принимающий двумерный массив и создающий связный список ненулевых элементов
    // конструктор требуется для создании объекта класса MatrixCode.
    // Пример создания:
    //  MatrixCode matrixCode = new MatrixCode(new int[][]{
    //                {1, 0, 0, 2},
    //                {0, 0, 3, 0},
    //                {4, 0, 0, 0},
    //                {0, 5, 0, 0}
    //        });
    public MatrixCode(int[][] matrix) {
        // Проверяем, что матрица квадратная и имеет размер SIZE x SIZE
        if (matrix.length != SIZE || matrix[0].length != SIZE) {
            System.out.println("Должна задаваться квадратная матрица размерности nxn, где n=" + SIZE);
        }
        // Проходим по всем элементам матрицы
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                // Если элемент не равен нулю, добавляем его в связный список
                if (matrix[i][j] != 0) {
                    if (first == null) {
                        // Если список пуст, создаем первый элемент
                        first = new Node(i, j, matrix[i][j]);
                        last = first; // Первый элемент также является последним
                    } else {
                        // Создаем новый узел
                        Node current = new Node(i, j, matrix[i][j]);
                        // Присоединяем новый узел к концу списка
                        last.next = current;
                        // Обновляем ссылку на последний элемент
                        last = current;
                    }
                }
            }
        }
    }

    // Метод для восстановления исходной матрицы с нулевыми элементами из списка
    // метод возвращает двумерный массив
    public int[][] decode() {
        // Создаем новый двумерный массив размера SIZE x SIZE, заполненный нулями по умолчанию
        int[][] result = new int[SIZE][SIZE];
        // Начинаем с первого узла списка
        Node current = first;
        // Проходим по всему списку
        while (current != null) {
            // Восстанавливаем значение в соответствующей позиции матрицы
            result[current.i][current.j] = current.value;
            // Переходим к следующему узлу
            current = current.next;
        }
        // Возвращаем восстановленную матрицу
        return result;
    }

    // Метод для вставки элемента в матрицу на позицию (i, j)
    public void insert(int i, int j, int value) {
        // Если вставляемое значение равно нулю, удаляем элемент из списка
        if (value == 0) {
            delete(i, j);
            return;
        }

        // Создаем новый узел с заданными координатами и значением
        Node newNode = new Node(i, j, value);

        // Если список пуст, вставляем новый узел как первый и последний элемент
        if (first == null) {
            first = last = newNode;
            return;
        }

        Node current = first;
        Node previous = null;

        // Проходим по списку, чтобы найти правильную позицию для вставки
        while (current != null) {
            // Если элемент с такими координатами уже существует, заменяем его значение
            if (current.i == i && current.j == j) {
                current.value = value;
                return;
            }

            //Если номер строки нового элемента меньше текущего (i < current.i),
            // то новый элемент должен быть вставлен перед текущим.
            //Если номера строк равны (i == current.i) и номер столбца
            // нового элемента меньше (j < current.j), то также вставляем новый элемент перед текущим.
            if (i < current.i || (i == current.i && j < current.j)) {
                newNode.next = current;
                if (previous == null) {
                    // Вставка в начало списка
                    first = newNode;
                } else {
                    // Вставка между предыдущим и текущим элементом
                    previous.next = newNode;
                }
                return;
            }

            // Переходим к следующему узлу
            previous = current;
            current = current.next;
        }

        // Если дошли до конца списка, вставляем новый элемент в конец
        last.next = newNode;
        last = newNode;
    }
   // public void insert(int i, int j, int valeu){
//        if (valeu == 0){
//            delete(i, j);
//            return;
//        }
//        Node newnode = new Node(i, j, valeu);
//        Node current = first;
//        Node previous = null;
//        while (current != null){
//            if (current.i == i && current.j == j){
//                current.value = valeu;
//                return;
//            }
//            if (current.i > i || (current.j > j && current.i == i)){
//                newnode.next = current;
//                if (previous == null){
//                    previous = first;
//                }
//                else {
//                    previous.next = newnode;
//                }
//                return;
//            }
//            previous = current;
//            current = current.next;
//        }
//    }



    // Метод для удаления элемента из списка на позиции (i, j)
    public void delete(int i, int j) {
        // Если список пуст, ничего не делаем
        if (first == null) {
            return;
        }

        // Если первый элемент имеет нужные координаты
        if (first.i == i && first.j == j) {
            // Удаляем первый элемент
            first = first.next;
            return;
        }

        // Проходим по списку начиная со второго элемента
        Node current = first;
        while (current.next != null) {
            // Если следующий элемент имеет нужные координаты
            if (current.next.i == i && current.next.j == j) {
                // Удаляем следующий элемент, перенаправляя ссылку
                current.next = current.next.next;
                // Если удаленный элемент был последним, обновляем ссылку на последний элемент
                if (current.next == null) {
                    last = current;
                }
                return;
            }
            // Переходим к следующему узлу
            current = current.next;
        }
    }

    // Метод для вычисления суммы элементов главной диагонали
    public int sumDiag() {
        Node current = first;  // Начинаем с первого узла списка
        int sum = 0;           // Инициализируем сумму
        while (current != null) {
            // Проверяем, находится ли элемент на главной диагонали (i == j)
            if (current.i == current.j) {
                // Добавляем значение элемента к сумме
                sum += current.value;
            }
            // Переходим к следующему узлу
            current = current.next;
        }
        // Возвращаем вычисленную сумму
        return sum;
    }

}

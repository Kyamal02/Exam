package ru.itis.ticket1.task1;

public class Node {
    int i;          // Номер строки в матрице
    int j;          // Номер столбца в матрице
    int value;      // Значение элемента матрицы
    Node next;      // Ссылка на следующий узел в списке

    // Конструктор узла, принимает координаты элемента и его значение
    public Node(int i, int j, int value) {
        this.i = i;              // Инициализируем номер строки
        this.j = j;              // Инициализируем номер столбца
        this.value = value;      // Инициализируем значение элемента
        this.next = null;        // Изначально следующий узел отсутствует (null)
    }

    // Переопределяем метод toString для удобства вывода информации об узле
    // нужно для удобства работы в консоли
    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

}
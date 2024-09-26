package ru.itis.test;

class Animal {
    private String name;

    // Конструктор
    public Animal(String name) {
        this.name = name;
    }

    // Метод для получения имени
    public String getName() {
        return name;
    }

    // Метод для еды
    void eat() {
        System.out.println(name + " is eating.");
    }

    // Метод для сна
    void sleep() {
        System.out.println(name + " is sleeping.");
    }
}


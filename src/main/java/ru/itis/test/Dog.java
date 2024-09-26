package ru.itis.test;

class Dog extends Animal implements Playable {
    // Конструктор
    public Dog(String name) {
        super(name);
    }

    // Собака лает
    void bark() {
        System.out.println(getName() + " is barking: Woof!");
    }

    // Переопределим метод sleep, чтобы собака сначала лаяла, а потом ложилась спать
    @Override
    void sleep() {
        bark();
        System.out.println(getName() + " is now sleeping.");
    }

    // Реализуем метод интерфейса Playable
    @Override
    public void play() {
        System.out.println(getName() + " is playing with a ball.");
    }
}

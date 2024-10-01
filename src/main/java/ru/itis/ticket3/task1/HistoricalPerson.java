package ru.itis.ticket3.task1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, представляющий историческую личность.
 */
public class HistoricalPerson {

    private String name;       // Имя исторической личности
    private String country;    // Страна
    private int birthYear;     // Год рождения
    private int deathYear;     // Год смерти


    // Пустой конструктор

    public HistoricalPerson() {
    }


    /**
     * Статический метод для чтения исторических личностей из файла.
     *
     * @param filepath путь к файлу с данными
     * @return список объектов HistoricalPerson
     */
    public static List<HistoricalPerson> readHistoricalPersons(String filepath) {
        // Создадим пустой лист, в который мы будем добавлять персонажей
        // Одна строка == один объект класса HistoricalPerson
        List<HistoricalPerson> list = new ArrayList<>();
        try (BufferedReader bf = new BufferedReader(new FileReader(filepath))) {
            String line;
            // Читаем файл построчно
            while ((line = bf.readLine()) != null) {
                // Разбиваем строку(line.split(",")) по запятым
                String[] strings = line.split(",");
                // Создаем новый объект класса HistoricalPerson и заполняем поля
                HistoricalPerson hp = new HistoricalPerson();
                // метод strip() убирает пробелы с начала и с конца строки
                hp.setName(strings[0].strip());
                hp.setCountry(strings[1].strip());
                hp.setBirthYear(Integer.parseInt(strings[2].strip()));
                hp.setDeathYear(Integer.parseInt(strings[3].strip()));
                // Добавляем личность в лист
                list.add(hp);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // возвращаем лист личностей
        return list;
    }


    // Геттеры и сеттеры для полей класса

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public void setDeathYear(int deathYear) {
        this.deathYear = deathYear;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public int getDeathYear() {
        return deathYear;
    }

    @Override
    public String toString() {
        return "HistoricalPerson{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", birthYear=" + birthYear +
                ", deathYear=" + deathYear +
                '}';
    }
}

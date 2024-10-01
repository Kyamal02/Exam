package ru.itis.ticket3.task1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, представляющий историческое событие.
 */
public class HistoricalEvent {
    private String eventName;  // Название события
    private String personName; // Имя исторической личности, связанной с событием
    private int startYear;     // Год начала события
    private int endYear;       // Год окончания события

    // Пустой конструктор
    public HistoricalEvent() {
    }


    /**
     * Статический метод для чтения исторических событий из файла.
     *
     * @param filepath путь к файлу с данными
     * @return список объектов HistoricalEvent
     */

    public static List<HistoricalEvent> readHistoricalEvents(String filepath) {
        // Создадим пустой лист, в который мы будем добавлять ивенты
        // Одна строка == один объект класса HistoricalEvent
        List<HistoricalEvent> list = new ArrayList<>();
        try (BufferedReader bf = new BufferedReader(new FileReader(filepath))) {
            String line;
            // Читаем файл построчно
            while ((line = bf.readLine()) != null) {
                // Разбиваем строку(line.split(",")) по запятым
                String[] strings = line.split(",");
                // Создаем новый объект класса HistoricalEvent и заполняем поля
                HistoricalEvent he = new HistoricalEvent();
                // метод strip() убирает пробелы с начала и с конца строки
                he.setEventName(strings[0].strip());
                he.setPersonName(strings[1].strip());
                he.setStartYear(Integer.parseInt(strings[2].strip()));
                he.setEndYear(Integer.parseInt(strings[3].strip()));
                // Добавляем событие в список
                list.add(he);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Возвращаем список событий
        return list;

    }


    // Геттеры и сеттеры для полей класса

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public String getEventName() {
        return eventName;
    }

    public String getPersonName() {
        return personName;
    }

    public int getStartYear() {
        return startYear;
    }

    public int getEndYear() {
        return endYear;
    }


}

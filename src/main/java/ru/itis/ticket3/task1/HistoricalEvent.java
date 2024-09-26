package ru.itis.ticket3.task1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HistoricalEvent {
    private String eventName;
    private String personName;
    private int startYear;
    private int endYear;

    public HistoricalEvent() {
    }

    public HistoricalEvent(String eventName, String personName, int startYear, int endYear) {
        this.eventName = eventName;
        this.personName = personName;
        this.startYear = startYear;
        this.endYear = endYear;
    }

    public static List<HistoricalEvent> readHistoricalEvents(String filepath) {
        List<HistoricalEvent> list = new ArrayList<>();
        try (BufferedReader bf = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = bf.readLine()) != null) {

                String[] strings = line.split(",");

                HistoricalEvent he = new HistoricalEvent();
                he.setEventName(strings[0].strip());
                he.setPersonName(strings[1].strip());
                he.setStartYear(Integer.parseInt(strings[2].strip()));
                he.setEndYear(Integer.parseInt(strings[3].strip()));

                list.add(he);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;

    }

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

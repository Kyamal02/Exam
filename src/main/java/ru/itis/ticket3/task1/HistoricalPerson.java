package ru.itis.ticket3.task1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HistoricalPerson {

    private String name;
    private String country;
    private int birthYear;
    private int deathYear;

    public HistoricalPerson() {
    }

    public HistoricalPerson(String name, String country, int birthYear, int deathYear) {
        this.name = name;
        this.country = country;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }


    public static List<HistoricalPerson> readHistoricalPersons(String filepath) {
        List<HistoricalPerson> list = new ArrayList<>();
        try (BufferedReader bf = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = bf.readLine()) != null) {

                String[] strings = line.split(",");
                HistoricalPerson hp = new HistoricalPerson();

                hp.setName(strings[0].strip());
                hp.setCountry(strings[1].strip());
                hp.setBirthYear(Integer.parseInt(strings[2].strip()));
                hp.setDeathYear(Integer.parseInt(strings[3].strip()));
                list.add(hp);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }


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

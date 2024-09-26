package ru.itis.ticket3.task1;


import java.util.*;

public class HistoricalApp {
    public static void main(String[] args) {

        List<HistoricalPerson> historicalPersonList = HistoricalPerson.readHistoricalPersons(
                "src/main/resources/Исторический личности.txt");
        System.out.println(historicalPersonList);

        List<HistoricalEvent> historicalEventList = HistoricalEvent.readHistoricalEvents(
                "src/main/resources/Исторические события.txt");
        System.out.println(historicalEventList);

        Map<String, List<HistoricalPerson>> result = HistoricalApp.generateAllCountryStatistics(historicalPersonList);

        // Вывод статистики (Страна -- количество личностей)
        System.out.println("\nСтатистика по странам:");
        for (String country : result.keySet()) {
            List<HistoricalPerson> hps =
                    result.getOrDefault(country, null);
            System.out.println(country + ": Личностей — " + hps.size());
        }

        HistoricalApp.generateCountryStatistics(result, historicalEventList, "Франция");


//        try (BufferedReader bf = new BufferedReader(new FileReader("src/main/resources/Исторический личности.txt"))) {
//            String line;
//            while ((line = bf.readLine()) != null) {
//                line = line.strip();
//                String[] strings = line.split(",");
//                for (int i = 0; i < strings.length; i++) {
//                    System.out.println(strings[i]);
//                }
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

    }


    public static Map<String, List<HistoricalPerson>> generateAllCountryStatistics(List<HistoricalPerson> persons) {
        Map<String, List<HistoricalPerson>> countryPersonCount = new HashMap<>();
        for (HistoricalPerson person : persons) {
            if (countryPersonCount.containsKey(person.getCountry())) {
                List<HistoricalPerson> list = countryPersonCount.get(person.getCountry());
                list.add(person);
            } else {
                List<HistoricalPerson> list = new ArrayList<>();
                list.add(person);
                countryPersonCount.put(person.getCountry(), list);
            }
        }
        return countryPersonCount;
    }

    public static void generateCountryStatistics(Map<String, List<HistoricalPerson>> map, List<HistoricalEvent> eventsList, String country) {
        List<HistoricalPerson> personList = map.get(country);

        // Карта для хранения статистики по каждому человеку и событиям
        Map<String, List<HistoricalEvent>> personEventMap = new HashMap<>();

        // Пройдемся по списку событий
        for (HistoricalEvent event : eventsList) {
            // Для каждого события найдем соответствующую личность в списке personList
            for (HistoricalPerson person : personList) {
                // Проверяем, связано ли событие с этой личностью
                if (event.getPersonName().equals(person.getName())) {
                    // Рассчитаем середину жизни личности
                    int middleOfLife = (person.getBirthYear() + person.getDeathYear()) / 2;

                    // Проверяем, начинается ли событие во второй половине жизни
                    if (event.getStartYear() > middleOfLife) {
                        // Если личность найдена, добавляем событие к соответствующей личности
                        if (!personEventMap.containsKey(person.getName())) {
                            personEventMap.put(person.getName(), new ArrayList<>());
                        }
                        personEventMap.get(person.getName()).add(event);
                    }
                }
            }
        }

        // Выводим статистику
        System.out.println("\nСтатистика по стране: " + country);
        for (String personName : personEventMap.keySet()) {
            List<HistoricalEvent> events = personEventMap.get(personName);
            System.out.println(personName + ": Событий — " + events.size());
            for (HistoricalEvent event : events) {
                System.out.println("\t" + event.getEventName() + " (" + event.getStartYear() + "-" + event.getEndYear() + ")");
            }
        }
    }


}

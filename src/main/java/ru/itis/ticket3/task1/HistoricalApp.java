package ru.itis.ticket3.task1;


import java.util.*;

public class HistoricalApp {
    public static void main(String[] args) {

        // Считываем список исторических личностей из файла и сохраняем в коллекцию
        List<HistoricalPerson> historicalPersonList = HistoricalPerson.readHistoricalPersons(
                "src/main/resources/Исторический личности.txt");
        System.out.println(historicalPersonList);

        // Считываем список исторических событий из файла и сохраняем в коллекцию
        List<HistoricalEvent> historicalEventList = HistoricalEvent.readHistoricalEvents(
                "src/main/resources/Исторические события.txt");
        System.out.println(historicalEventList);

        // Генерируем статистику по всем странам и их историческим личностям
        Map<String, List<HistoricalPerson>> result = HistoricalApp.generateAllCountryStatistics(historicalPersonList);

        // Выводим статистику по каждой стране (Страна -- количество личностей)
        System.out.println("\nСтатистика по странам:");
        for (String country : result.keySet()) {
            List<HistoricalPerson> hps =
                    // Получаем список исторических личностей для каждой страны
                    result.getOrDefault(country, null);
            System.out.println(country + ": Личностей — " + hps.size());
        }
        // Генерируем и выводим статистику для конкретной страны (например, "Франция")
        HistoricalApp.generateCountryStatistics(result, historicalEventList, "Франция");


    }

    /**
     * Метод для генерации статистики по всем странам.
     * Создает карту, где ключом является название страны,
     * а значением — список исторических личностей из этой страны.
     *
     * @param persons список всех исторических личностей
     * @return карта страна -> список исторических личностей
     */
    public static Map<String, List<HistoricalPerson>> generateAllCountryStatistics(List<HistoricalPerson> persons) {
        // Инициализируем карту для хранения статистики
        Map<String, List<HistoricalPerson>> countryPersonCount = new HashMap<>();
        // Проходим по списку всех исторических личностей
        for (HistoricalPerson person : persons) {
            // Проверяем, есть ли уже эта страна в карте
            if (countryPersonCount.containsKey(person.getCountry())) {
                // Если есть, добавляем личность в существующий список
                List<HistoricalPerson> list = countryPersonCount.get(person.getCountry());
                list.add(person);
            } else {
                // Если нет, создаем новый список и добавляем его в карту
                List<HistoricalPerson> list = new ArrayList<>();
                list.add(person);
                countryPersonCount.put(person.getCountry(), list);
            }
        }
        // Возвращаем сгенерированную карту
        return countryPersonCount;
    }


    /**
     * Метод для генерации статистики по конкретной стране.
     * Выводит список имен исторических личностей из указанной страны,
     * у которых есть хотя бы одно историческое событие,
     * начавшееся во второй половине их жизни.
     *
     * @param map        карта страна -> список исторических личностей
     * @param eventsList список всех исторических событий
     * @param country    название страны для генерации статистики
     */
    public static void generateCountryStatistics(Map<String, List<HistoricalPerson>> map, List<HistoricalEvent> eventsList, String country) {
        // Получим из мапы соответствующую страну
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
                        // Если личность еще не добавлена в карту, инициализируем список событий
                        if (!personEventMap.containsKey(person.getName())) {
                            personEventMap.put(person.getName(), new ArrayList<>());
                        }
                        // Добавляем событие в список событий данной личности
                        personEventMap.get(person.getName()).add(event);
                    }
                }
            }
        }

        // Выводим статистику по указанной стране
        System.out.println("\nСтатистика по стране: " + country);
        for (String personName : personEventMap.keySet()) {
            // Получаем список событий для каждой личности
            List<HistoricalEvent> events = personEventMap.get(personName);
            System.out.println(personName + ": Событий — " + events.size());
            // Выводим информацию о каждом событии
            for (HistoricalEvent event : events) {
                System.out.println("\t" + event.getEventName() + " (" + event.getStartYear() + "-" + event.getEndYear() + ")");
            }
        }
    }


}

package ru.itis.ticket3.task2;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class Main {

    public static void main(String[] args) {
        // Задаем количество потоков
        int numberOfThreads = 5;

        // Создаем и запускаем потоки в цикле
        Thread[] threads = new Thread[numberOfThreads];
        for (int i = 0; i < numberOfThreads; i++) {
            String inputFilePath = "src/main/java/ru/itis/ticket3/task2/input.txt";
            String outputFilePath = "src/main/java/ru/itis/ticket3/task2/output" + (i + 1) + ".txt";

            threads[i] = new Thread(new FileProcessorTask(inputFilePath, outputFilePath));
            threads[i].start();
        }

        // Ожидаем завершения всех потоков
        for (int i = 0; i < numberOfThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Все файлы успешно созданы в отдельных потоках.");


//        try (InputStream inputStream = new FileInputStream("src/main/java/ru/itis/ticket3/task2/output1.txt");
//             BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream)) {
//
//            byte[] bytes = new byte[4];
//            StringBuilder result = new StringBuilder();
//
//            int byteRead;
//            while ((byteRead = bufferedInputStream.read()) != -1) {
//                if (byteRead == '\n') {
//                    // Если встретили '\n', добавляем перевод строки в результат
//                    result.append("\n");
//                } else {
//                    // Если это не '\n', значит начинаем читать 4 байта для числа
//                    bytes[0] = (byte) byteRead;
//                    for (int i = 1; i < 4; i++) {
//                        byteRead = bufferedInputStream.read();
//                        if (byteRead == -1) {
//                            throw new IOException("Неправильное количество байтов прочитано для числа.");
//                        }
//                        bytes[i] = (byte) byteRead;
//                    }
//                    int value = ByteBuffer.wrap(bytes).getInt();  // Преобразуем 4 байта обратно в int
//                    result.append(value).append(" ");
//                }
//            }
//
//            System.out.println(result.toString().strip());  // Печатаем восстановленную строку
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

    }
}

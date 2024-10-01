package ru.itis.ticket3.task2.Test;

import java.io.*;
import java.nio.ByteBuffer;

public class Test {

    public static void main(String[] args) {
        //   Первый пример
//        try (
//                // работаем с байтами
//                InputStream inputStream = new FileInputStream("src/main/java/ru/itis/ticket3/task2/Test/котики.jpg");
//                BufferedInputStream bur = new BufferedInputStream(inputStream);
//
//                OutputStream outputStream = new FileOutputStream("src/main/java/ru/itis/ticket3/task2/Test/output.txt");
//                BufferedOutputStream bur2 = new BufferedOutputStream(outputStream);
//        ) {
//            byte[] buffer = new byte[4024];
//            int read = bur.read(buffer);
//            while (read != -1) {
//                outputStream.write(buffer, 0, read);
//                read = bur.read(buffer);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }


//        File file = new File("src/main/java/ru/itis/ticket3/task2/копия_картинки.png");
//        try (
//                InputStream inputStream = new FileInputStream("src/main/java/ru/itis/ticket3/task2/output.txt");
//                BufferedInputStream bur = new BufferedInputStream(inputStream);
//                OutputStream outputStream = new FileOutputStream(file);
//                BufferedOutputStream bur2 = new BufferedOutputStream(outputStream);
//        ) {
//            byte[] buffer = new byte[4024];
//            int read = bur.read(buffer);
//            while (read != -1) {
//                bur2.write(buffer, 0, read);
//                read = bur.read(buffer);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }


//        // Второй пример
//        try (Reader reader = new FileReader("src/main/java/ru/itis/ticket3/task2/Test/input.txt");
//             BufferedReader bufferedReader = new BufferedReader(reader);
//
//             OutputStream outputStream = new FileOutputStream("src/main/java/ru/itis/ticket3/task2/Test/output.txt", true);
//             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
//        ) {
//            String line = bufferedReader.readLine();
//            String[] strings = line.split("\\s+");
//            for (int i = 0; i < strings.length; i++) {
//                int value = Integer.parseInt(strings[i]);
//                byte[] bytes = ByteBuffer.allocate(4).putInt(value).array();
//                bufferedOutputStream.write(bytes, 0, bytes.length);
//            }
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }


//        try (InputStream inputStream = new FileInputStream("src/main/java/ru/itis/ticket3/task2/Test/output.txt");
//             BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream)) {
//
//            byte[] bytes = new byte[4];  // Поскольку мы записываем int, нам нужно читать по 4 байта за раз
//            StringBuilder result = new StringBuilder();
//
//            int bytesRead;
//            while ((bytesRead = bufferedInputStream.read(bytes)) != -1) {
//                if (bytesRead < 4) {
//                    // Если прочитано менее 4 байтов, выбрасываем ошибку
//                    throw new IOException("Неправильное количество байтов прочитано: " + bytesRead);
//                }
//                int value = ByteBuffer.wrap(bytes).getInt();  // Преобразуем 4 байта обратно в int
//                result.append(value).append(" ");  // Добавляем число и пробел к результату
//            }
//
//            System.out.println(result.toString().trim());  // Печатаем восстановленную строку
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
    }
}

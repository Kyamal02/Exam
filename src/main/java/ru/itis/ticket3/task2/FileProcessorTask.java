package ru.itis.ticket3.task2;

import java.io.*;
import java.nio.ByteBuffer;

public class FileProcessorTask implements Runnable {
    private final String inputFilePath;
    private final String outputFilePath;

    public FileProcessorTask(String inputFilePath, String outputFilePath) {
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;
    }

    @Override
    public void run() {
        try {
            // Создаем выходной файл, если его еще нет
            File outputFile = new File(outputFilePath);
            if (!outputFile.exists()) {
                outputFile.createNewFile();
            }

            try (
                    Reader reader = new FileReader(inputFilePath);
                    BufferedReader bufferedReader = new BufferedReader(reader);
                    OutputStream outputStream = new FileOutputStream(outputFilePath, true);
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream)
            ) {
                String line = bufferedReader.readLine();
                while (line != null) {
                    String[] strings = line.split("\\s+");
                    for (String string : strings) {
                        int value = Integer.parseInt(string);
                        byte[] bytes = ByteBuffer.allocate(4).putInt(value).array();

                        bufferedOutputStream.write(bytes, 0, bytes.length);
                    }
                    // Добавляем специальный символ для обозначения конца строки
                    bufferedOutputStream.write('\n');
                    line = bufferedReader.readLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}


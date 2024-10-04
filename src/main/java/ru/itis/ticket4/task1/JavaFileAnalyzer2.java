package ru.itis.ticket4.task1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JavaFileAnalyzer2 {
    public static void main(String[] args) {
        List<ClassInfo> classes = new ArrayList<>();
        List<VariableInfo> variables = new ArrayList<>();

        File folder = new File("src/main/java/ru/itis/ticket4/task1/source"); // путь к исходным файлам
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".java"));

        if (files != null) {
            for (File file : files) {
                analyzeJavaFile(file, classes, variables);
            }
        }

        generateStatistics(classes, variables);
    }

    private static void analyzeJavaFile(File file, List<ClassInfo> classes, List<VariableInfo> variables) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            String currentClassName = null;
            String packageName = null;
            String type = null;
            String name = null;

            while ((line = br.readLine()) != null) {
                line = line.trim();

                if (line.startsWith("package ")) {
                    packageName = line.substring(8, line.indexOf(';')).trim();
                } else if (line.startsWith("public class ")) {
                    currentClassName = line.split(" ")[2];
                    classes.add(new ClassInfo(currentClassName, packageName));
                } else if (isVariableDeclaration(line)) {
                    String[] parts = line.split("\\s+");

                    // Проверка на наличие модификатора доступа
                    if (parts.length == 2) {
                        // Если parts.length == 2, значит, модификатор отсутствует
                        type = parts[0];
                        System.out.println(type);
                        name = parts[1].replace(";", "");

                    } else if (parts.length >= 3) {
                        // Если parts.length >= 3, значит, есть модификатор
                        type = parts[1];
                        name = parts[2].replace(";", "");
                    }

                    variables.add(new VariableInfo(type, name, "атрибут класса", currentClassName));
                } else if (line.contains("(") && line.contains(")")) {
                    extractMethodParameters(line, currentClassName, variables);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static boolean isVariableDeclaration(String line) {
        line = line.trim();

        // Проверяем, что строка заканчивается точкой с запятой и не является методом или пустой строкой
        if (!line.endsWith(";") || line.contains("(") || line.isEmpty()) {
            return false;
        }

        // Исключаем строки, начинающиеся с ключевых слов, таких как return или других операторов
        if (line.startsWith("return") || line.startsWith("if") || line.startsWith("else") || line.startsWith("for") ||
                line.startsWith("while") || line.startsWith("switch") || line.startsWith("try") || line.startsWith("catch") ||
                line.startsWith("finally")) {
            return false;
        }

        // Разделяем строку по пробелам
        String[] parts = line.split("\\s+");

        // Проверяем, что строка имеет хотя бы два слова (тип и имя)
        if (parts.length < 2) {
            return false;
        }

        // Проверяем, начинается ли строка с типа данных или с модификатора доступа
        String firstPart = parts[0];
        return isValidType(firstPart) || firstPart.matches("private|protected|public|final|static");
    }

    private static boolean isValidType(String type) {
        // Допустимые примитивные типы данных и строка String
        List<String> validTypes = List.of("int", "double", "float", "long", "short", "byte", "boolean", "char", "String");

        // Проверяем, является ли тип примитивным или пользовательским типом (начинается с заглавной буквы)
        return validTypes.contains(type);
    }


    private static void extractMethodParameters(String line, String currentClassName, List<VariableInfo> variables) {
        int startIndex = line.indexOf('(');
        int endIndex = line.indexOf(')');
        if (startIndex < endIndex) {
            String paramsString = line.substring(startIndex + 1, endIndex);
            String[] params = paramsString.split(",");
            for (String param : params) {
                String[] parts = param.trim().split("\\s+");
                if (parts.length == 2) {
                    variables.add(new VariableInfo(parts[0], parts[1], "параметр метода", currentClassName));
                }
            }
        }
    }

    private static void generateStatistics(List<ClassInfo> classes, List<VariableInfo> variables) {
        for (ClassInfo classInfo : classes) {
            long attributeCount = 0;
            long methodParamCount = 0;



            for (VariableInfo var : variables) {
                if (var.className.equals(classInfo.className)) {
                    if (var.declarationType.equals("атрибут класса")) {
                        attributeCount++;
                    } else if (var.declarationType.equals("параметр метода")) {
                        methodParamCount++;
                    }
                }
            }

            System.out.println("Класс: " + classInfo.className);
            System.out.println("Атрибутов у класса: " + attributeCount);
            System.out.println("Параметров у всех методов: " + methodParamCount);
            System.out.println();
        }

        long intCount = 0;
        for (VariableInfo var : variables) {
            if (var.type.equals("int")) {
                intCount++;
            }
        }

        boolean moreThanHalfAreInt = intCount > (variables.size() / 2);
        System.out.println("Больше половины всех объявленных всех объявленных переменных из считанных файлов имеют тип int: " + moreThanHalfAreInt);
    }


    static class ClassInfo {
        //название класса
        String className;
        //Название пакета
        String packageName;

        ClassInfo(String className, String packageName) {
            this.className = className;
            this.packageName = packageName;
        }

        @Override
        public String toString() {
            return "ClassInfo{" +
                    "className='" + className + '\'' +
                    ", packageName='" + packageName + '\'' +
                    '}';
        }
    }

    static class VariableInfo {
        // тип переменной
        String type;
        // название переменной
        String name;
        // вид объявления
        String declarationType;
        // класс, где переменная объявлена
        String className;

        VariableInfo(String type, String name, String declarationType, String className) {
            this.type = type;
            this.name = name;
            this.declarationType = declarationType;
            this.className = className;
        }

        @Override
        public String toString() {
            return "VariableInfo{" +
                    "type='" + type + '\'' +
                    ", name='" + name + '\'' +
                    ", declarationType='" + declarationType + '\'' +
                    ", className='" + className + '\'' +
                    '}';
        }
    }


}

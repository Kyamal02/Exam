package ru.itis.ticket4.task1;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaFileAnalyzer {
    public static void main(String[] args) {


        List<ClassInfo> classes = new ArrayList<>();
        List<VariableInfo> variables = new ArrayList<>();

        File folder = new File("src/main/java/ru/itis/ticket4/task1/source"); // путь к исходным файлам
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                parseJavaFile(file, classes, variables);
            }
        }

        for (ClassInfo classInfo : classes) {
            long attributeCount = 0;
            long methodParamCount = 0;

            for (VariableInfo variable : variables) {
                if (variable.className.equals(classInfo.className)) {
                    if (variable.declarationType.equals("attribute")) {
                        attributeCount++;
                    } else if (variable.declarationType.equals("method parameter")) {
                        methodParamCount++;
                    }
                }
            }

            System.out.println("Class: " + classInfo.className);
            System.out.println("Attributes: " + attributeCount);
            System.out.println("Method Parameters: " + methodParamCount);
            System.out.println();



            // b. Проверить, что больше половины всех объявленных переменных имеют тип int
            long intCount = variables.stream()
                    .filter(var -> var.type.equals("int"))
                    .count();
            boolean moreThanHalfAreInt = intCount > (variables.size() / 2);

            System.out.println("More than half of the variables are of type int: " + moreThanHalfAreInt);
        }
    }

    private static void parseJavaFile(File file, List<ClassInfo> classes, List<VariableInfo> variables) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            String currentClassName = null;
            String packageName = null;

            while ((line = br.readLine()) != null) {
                line = line.trim();

                // Получение имени пакета
                if (line.startsWith("package ")) {
                    packageName = line.replace("package ", "").replace(";", "").trim();
                }

                // Получение имени класса
                if (line.startsWith("public class ")) {
                    currentClassName = line.split(" ")[2];
                    classes.add(new ClassInfo(currentClassName, packageName));
                }

                // Поиск объявлений переменных
                Pattern pattern = Pattern.compile("(private|public|protected)\\s+(\\w+)\\s+(\\w+);");
                Matcher matcher = pattern.matcher(line);
                if (matcher.find() && currentClassName != null) {
                    String type = matcher.group(2);
                    String name = matcher.group(3);
                    variables.add(new VariableInfo(type, name, "attribute", currentClassName));
                }

                // Поиск параметров метода
                pattern = Pattern.compile("\\(([^)]+)\\)");
                matcher = pattern.matcher(line);
                if (matcher.find() && currentClassName != null) {
                    String[] params = matcher.group(1).split(",");
                    for (String param : params) {
                        param = param.trim();
                        if (!param.isEmpty()) {
                            String[] parts = param.split(" ");
                            if (parts.length == 2) {
                                String type = parts[0];
                                String name = parts[1];
                                variables.add(new VariableInfo(type, name, "method parameter", currentClassName));
                            }
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClassInfo {
        String className;
        String packageName;

        ClassInfo(String className, String packageName) {
            this.className = className;
            this.packageName = packageName;
        }
    }

    static class VariableInfo {
        String type;
        String name;
        String declarationType;
        String className;

        VariableInfo(String type, String name, String declarationType, String className) {
            this.type = type;
            this.name = name;
            this.declarationType = declarationType;
            this.className = className;
        }
    }
}

package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    public static String getProperty(String fileName, String key) {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream
                ("src/test/properties" + File.separator + fileName)) {
            properties.load(fileInputStream);
            return properties.getProperty(key);
        } catch (IOException e) {
            System.out.println("created exception");
            e.printStackTrace();
            return null;
        }
    }
}

//PropertiesReader класс, чтобы выносить настройки проекта (например, URL тестируемого сайта, логины, пароли, таймауты или окружение) из кода в специальные конфигурационные файлы.
//Нужен: Чтобы при изменении настроек не приходилось переписывать код тестов. Достаточно изменить одно значение в файле настроек.
//запоминать код не надо, пишут 1 раз. Нужен чтобы вынести работу с файлами настроек в одно место).
//Конструкция try-with-resources гарантирует, что файл автоматически закроется после чтения
//String fileName — указывает, из какого именно файла нужно прочитать данные (например, "config.properties", "stage.properties").
//String key — указывает, какой конкретно параметр из этого файла нужно достать (например, "url", "username" или "timeout").
//  "src/test/properties" — папка, в которой лежит файл с настройками.
//  +File.separator — системный разделитель путей. В Windows это (\), а в macOS (/).
// Использование File.separator делает код универсальным, чтобы он одинаково хорошо работал на любых компьютерах.
// + fileName — добавляет к пути имя конкретного файла, переданного в метод (например, config.properties).
//Итог: если вы передадите в метод имя файла config.properties, на компьютере с Windows эта строчка превратится в путь: src/test/properties\config.properties.


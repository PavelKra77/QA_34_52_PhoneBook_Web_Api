// @DataProvider — спецаннотация TestNG. Она обозначает, что этот метод поставляет тестовые данные.
//Iterator<UserLombok> — формат возвращаемого значения. TestNG автоматически запустит ваш @Test столько раз,
// сколько объектов будет находиться в этом Iterator.
//FileReader умеет читать только отдельные символы или массивы символов. У него нет метода readLine().
//BufferedReader через readLine() читает текст от начала и до символа переноса строки (\n или \r\n).
// 1. FileReader открывает соединение с файлом на диске
// FileReader fileReader = new FileReader("src/test/resources/data.csv");
// 2. BufferedReader оборачивает fileReader, добавляя буфер и метод readLine()
// BufferedReader bufferedReader = new BufferedReader(fileReader);
// 3. Теперь можно удобно читать файл построчно с высокой скоростью
// String line = bufferedReader.readLine();

package data_providers;

import dto.UserLombok;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserDataProvider {
    @DataProvider
    public Iterator<UserLombok> dataProviderWrongPasswordOrEmail(){
        List<UserLombok> list = new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader
                (new FileReader("src/test/resources/wrong_email_password .csv"))){
            String line =bufferedReader.readLine();
            while (line != null){
                String[] splitLine = line.split(",");  //line.split(",") разбивает строку на массив из двух элементов по запятой.
                list.add(UserLombok.builder()
                        .username(splitLine[0])
                        .password(splitLine[1])
                        .build());
                line = bufferedReader.readLine();
            }
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("created exception");
        }
        return list.listIterator();

    }
}

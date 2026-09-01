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
                String[] splitLine = line.split(",");
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
        return list.listIterator();    }
}

// @DataProvider — спецаннотация TestNG. Она обозначает, что этот метод готовит данные для автотестов.
//1. Iterator<UserLombok> — Заголовок метода обещает: Я верну указатель (Iterator), который выдает объекты UserLombok.
// TestNG автоматически запустит ваш @Test столько раз,
// сколько объектов будет находиться в этом Iterator. На выходе метод отдаст список пользователей,
// по которому тест сможет прошвырнуться по очереди (перебрать их через Iterator).
//2. List<UserLombok>  Создаем пустой список list, куда будем складывать прочитанных из файла пользователей.
//С использованием <UserLombok> компилятор гарантирует, что в коллекцию попадают только объекты класса UserLombok
//ArrayList массив. он автоматически растягивается, когда вы добавляете новые элементы
//Обычный список (List) — это просто коробка, где лежат готовые объекты.
//Iterator — это указатель (палец), который умеет шагать по этой коробке от первого элемента к последнему.
//3. FileReader открывает файл с данными "src/test/re..
// Он умеет читать только отдельные символы или массивы символов. У него нет метода readLine().
// 4. BufferedReader оборачивает fileReader, чтобы читать его не по одной букве, а целыми строками.
// через readLine() читает текст от начала и до символа переноса строки (\n или \r\n).
// объект bufferedReader — это  читатель со сдвигающимся курсором.Он запоминает место, на котором остановился в предыдущий раз.
// 5. String line = bufferedReader.readLine(); Читаем самую первую строчку из файла и сохраняем ее в переменную line
// 6. Цикл while читает файл построчно, пока строчки не закончатся. Берет строку вида user@test.com,12345.
// 7. line.split(",") разбивает строку на 2элемента по запятой. логин (splitLine[0]) и пароль (splitLine[1]).
// 8. Создаем нового пользователя (объект UserLombok)и добавляем в наш список list, заполняем его логином и паролем под индексом 0...
// 9. line = bufferedReader.readLine Читаем следующую строчку файла. Цикл повторяется заново для новой строки.
// 10. return list.listIterator();используется для того, чтобы передать подготовленные тестовые данные в TestNG.
// list.listIterator(), создали «указатель», чтобы фреймворк тестов мог постепенно брасывать данные в тест по одному пользователю.

// Iterator<UserLombok> в заголовке —  обещание метода «Этот метод в итоге отдаст итератор (переборщик,закладку), который по очереди выдает объекты типа UserLombok».
//list.listIterator() в конце —выполнение обещания. Сам list ( return list) вернуть нельзя, потому что заголовок требует Iterator.
// Поэтому ты вызываешь метод .listIterator() у списка, который превращает список в нужный формат.
//return list.listIterator()  отдаёт фреймворку TestNG специальную «закладку» (итератор), указывающую на начало списка list.

// String line = bufferedReader.readLine():Метод берет самую первую строчку: "user1@test.com,pass1".
//Кладет её в переменную line =  "user1@test.com,pass1". BufferedReader запоминает: «Я остановился на конце 1-й строки».
// в конце цикла while Вызывается line = bufferedReader.readLie.
// BufferedReader помнит, где остановился, двигается дальше и считывает 2-ю строку, Переменная line перезаписывается
// Когда ты создаешь new BufferedReader(...), Java открывает файл и ставит курсор (закладку) в самое начало — перед первой строкой.
//Первый вызов bufferedReader.readLine() считывает символы до ближайшего переноса строки (Enter), отдает их тебе и сдвигает внутренний курсор на начало 2-й строки.
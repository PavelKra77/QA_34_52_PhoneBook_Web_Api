package utils;

import dto.User;
import dto.UserLombok;
import net.datafaker.Faker; // Java-библиотека, предназначенная для генерации фейковых (тестовых) данных

public class UserFactory {
    static Faker faker = new Faker();

    // static — ключевое слово, которое делает поле общим для всех экземпляров класса
//    public static void main(String[] args) {
//        String firstName = faker.name().firstName();
//        System.out.println(firstName);
//        String lastName = faker.name().lastName();
//        System.out.println(lastName);
//        String email = faker.internet().emailAddress();
//        System.out.println(email);
//    }
// пример String address = faker.address().fullAddress();
//        String phoneNumber = faker.phoneNumber().phoneNumber()
//        String company = faker.company().name()

    public static UserLombok positiveUser(){
        UserLombok user = UserLombok.builder()
                .username(faker.internet().emailAddress())
                .password("Qwert123!")
                .build();
        return user;
    }
}

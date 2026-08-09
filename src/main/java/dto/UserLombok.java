package dto;

import lombok.*;
//Lombok позволяет убрать весь «шаблонный» код (геттеры, сеттеры, конструкторы)
// и заменяет его короткими аннотациями.

@Getter
@Setter
@ToString
@Builder //Добавляет удобный паттерн проектирования Builder. Позволяет создавать
// объект в красивом стиле: UserLombok user = UserLombok.builder().username("admin").password("123").build();
@AllArgsConstructor //Cоздает пустой конструктор без параметров (public UserLombok() {}).
@NoArgsConstructor //создает конструктор со всеми параметрами (public UserLombok(String username, String password) {}).

public class UserLombok {
    private String username;
    private String password;
}

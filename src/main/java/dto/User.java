package dto; //(Data Transfer Object) — шаблон-контейнер для хранения
// и передачи данных о пользователе (логин и пароль)
// между разными частями автотестов.

public class User {
    private String username;
    private String password;
//private - нельзя обратиться напрямую (например, user.username не сработает).
// Это принцип инкапсуляции.

    public User() {
    }
    //Пустой конструктор: Позволяет создать «пустого» пользователя без сразу
    //заданных логина и пароля User user = new User();,чтобы заполнить их позже.

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    //Конструктор с параметрами: Позволяет сразу создать пользователя с готовыми
    // данными в одну строчку: User user = new User("admin", "12345");.

    public String getUsername() {
        return username;
    }
    //возвращает логин пользователя.

    public void setUsername(String username) {
        this.username = username;
    }
    //позволяет изменить или записать логин.

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override  //Указывает, что мы переопределяем стандартный метод Java.
    public String toString() {   //toString() превращает объект в понятный текст, а не адрес в памяти
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';

    }
}




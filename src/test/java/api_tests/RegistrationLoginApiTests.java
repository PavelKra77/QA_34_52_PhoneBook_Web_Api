package api_tests;

import data_providers.UserDataProvider;
import dto.UserLombok;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseApi;

import java.io.IOException;

import static utils.UserFactory.*;
import static utils.PropertiesReader.*;

public class RegistrationLoginApiTests implements BaseApi {

    @Test
    public void registrationApiPositiveTest(){
        UserLombok user = positiveUser();
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL+REGISTRATION_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(response);
        Assert.assertEquals(response.code(), 200);

    }


    @Test
    public void registrationApiWrongPasswordNegativeTest(){
        UserLombok user = positiveUser();
        user.setPassword("qwerty123!");
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL+REGISTRATION_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(response);
        Assert.assertEquals(response.code(), 400);

    }


    @Test
    public void registrationApiDuplicateUserNegativeTest(){
        UserLombok user = positiveUser();
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL+REGISTRATION_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            OK_HTTP_CLIENT.newCall(request).execute();
            response = OK_HTTP_CLIENT.newCall(request).execute();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(response);
        Assert.assertEquals(response.code(), 409);

    }


    @Test
    public void registrationApiWrongFormatNegativeTest(){
        UserLombok user = positiveUser();
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), TEXT);
        Request request = new Request.Builder()
                .url(BASE_URL+REGISTRATION_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(response);
        Assert.assertEquals(response.code(), 500);

    }

    @Test
    public void registrationApiWrongLetterPasswordNegativeTest(){
        UserLombok user = positiveUser();
        user.setPassword("ФАРА123!");
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL+REGISTRATION_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(response);
        Assert.assertEquals(response.code(), 400);

    }

    @Test
    public void registrationApiWrongEmailFormatNegativeTest(){
        UserLombok user = positiveUser();
        user.setUsername("pav.kravets86gmail.com");
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL+REGISTRATION_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(response);
        Assert.assertEquals(response.code(), 400);

    }

    @Test
    public void registrationApiEmptyFieldsNegativeTest() {
        UserLombok user = UserLombok.builder()
                .username("")
                .password("")
                .build();
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + REGISTRATION_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(response);
        Assert.assertEquals(response.code(), 400);
    }
    @Test(dataProvider = "dataProviderWrongPasswordOrEmail", dataProviderClass = UserDataProvider.class)
    public void registrationApiWrongPasswordsNegativeTest(UserLombok user){
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + REGISTRATION_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(response);
        Assert.assertEquals(response.code(), 400);
    }

    @Test
    public void loginApiPositiveTest(){
        UserLombok user = UserLombok.builder()
                .username(getProperty("base.properties","email"))
                .password(getProperty("base.properties","password"))
                .build();
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + LOGIN_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(response);
        Assert.assertEquals(response.code(), 200);

    }


    @Test
    public void loginApiWrongPasswordNegativeTest(){
        UserLombok user = UserLombok.builder()
                .username(getProperty("base.properties","email"))
                .password("Mwerty123!")
                .build();
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + LOGIN_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(response);
        Assert.assertEquals(response.code(), 401);

    }

    @Test
    public void loginApiWrongEmailTest(){
        UserLombok user = UserLombok.builder()
                .username("pav.kravets86gmail.com")
                .password(getProperty("base.properties","password"))
                .build();
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + LOGIN_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(response);
        Assert.assertEquals(response.code(), 401);

    }

    @Test
    public void loginApiWrongFormatNegativeTest(){
        String invalidJson = "{\"username\": [], \"password\": \"12345678\"}";
        RequestBody requestBody = RequestBody.create(invalidJson, TEXT);
        //RequestBody requestBody = RequestBody.create("{\"username\": null}",TEXT);
        Request request = new Request.Builder()
                .url(BASE_URL+LOGIN_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(response);
        Assert.assertEquals(response.code(), 400);

    }

    @Test
    public void loginApiEmptyPasswordFieldNegativeTest() {
        UserLombok user = UserLombok.builder()
                .username((getProperty("base.properties","email")))
                .password("")
                .build();
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + LOGIN_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(response);
        Assert.assertEquals(response.code(), 401);


    }

    @Test
    public void loginApiEmptyEmailFieldNegativeTest() {
        UserLombok user = UserLombok.builder()
                .username("")
                .password((getProperty("base.properties","password")))
                .build();
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + LOGIN_URL)
                .post(requestBody)
                .build();

        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(response);
        Assert.assertEquals(response.code(), 401);
    }


    @Test
    public void loginApiWrongShortPasswordNegativeTest(){
        UserLombok user = UserLombok.builder()
                .username(getProperty("base.properties","email"))
                .password("Qwerty1!")
                .build();
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + LOGIN_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(response);
        Assert.assertEquals(response.code(), 401);

    }


}

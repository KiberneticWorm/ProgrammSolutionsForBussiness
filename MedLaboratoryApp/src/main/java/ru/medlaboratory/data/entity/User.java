package ru.medlaboratory.data.entity;

public class User {

    private int userId;
    private String userName;
    private String password;
    private String fio;
    private String imagePath;
    private int age;
    private String typeUser;

    public User() { }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }

    public User(int userId,
                String userName,
                String password,
                String fio,
                int age,
                String typeUser,
                String imagePath) {
        this.imagePath = imagePath;
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.fio = fio;
        this.age = age;
        this.typeUser = typeUser;
    }

    public int getUserId() {
        return userId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getFio() {
        return fio;
    }

    public int getAge() {
        return age;
    }

    public String getTypeUser() {
        return typeUser;
    }
}

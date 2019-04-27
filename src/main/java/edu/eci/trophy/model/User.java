package edu.eci.trophy.model;

public class User {

    public String id;

    private String name;
    private String email;
    private String password;
    private String userName;
    private int trophyPoints;

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String username, String name, String email, String password, int trophyPoints) {
        this.userName = username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.trophyPoints = trophyPoints;
    }

    public int getTrophyPoints() {
        return trophyPoints;
    }

    public void setTrophyPoints(int trophyPoints) {
        this.trophyPoints = trophyPoints;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setId(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", trophyPoints=" + trophyPoints +
                '}';
    }
}

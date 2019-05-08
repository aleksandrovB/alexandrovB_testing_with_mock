package com.clouway.UserDatabase;

public class User {
    private String username;
    private String age;

    public User(String username, String age) {
        this.username = username;
        this.age = age;
    }

    /**
     * Getters and setters
     */
    public String getUsername() {return username;}
    public String getAge() {return age;}
    public void setUsername(String username) {this.username = username;}
    public void setAge(String age) {this.age = age;}
}

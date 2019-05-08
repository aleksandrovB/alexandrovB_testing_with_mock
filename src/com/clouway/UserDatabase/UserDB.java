package com.clouway.UserDatabase;

public interface UserDB {
    void add(User user);
    User getByName(String name);
}

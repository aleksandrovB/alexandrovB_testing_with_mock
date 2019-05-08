package com.clouway.UserDatabase;


import com.clouway.Exceptions.InvalidUserAge;
import com.clouway.Exceptions.NoSuchUser;

public class UserRepository {
    private UserDB userDB;
    private Validator validator;

    /**
     * Constructor
     */
    public UserRepository(UserDB userDB, Validator validator) {
        this.userDB = userDB;
        this.validator = validator;
    }

    /**
     * Adds user into userDB if inputs are correct
     * @param user class containing users name and age as Strings
     * @throws InvalidUserAge when age is not validated or parsed
     */
    public void registerUser(User user) throws InvalidUserAge {
        try {
            Integer.parseInt(user.getAge());
            if(!validator.validateAge(user)){
                throw new InvalidUserAge(String.format("%s is not a valid age!",user.getAge()));
            } else {
                userDB.add(user);
            }
        } catch (NumberFormatException e){
            throw new InvalidUserAge(String.format("%s is not a valid age!",user.getAge()));
        }
    }

    /**
     * Gets user by his name and checks if his age is above or 18
     * @param name name by which user is returned
     * @return true if users age is above or 18
     * @throws NoSuchUser thrown if there is no user with this username
     */
    public boolean isAdult(String name) throws NoSuchUser {
        try {
            return Integer.parseInt(userDB.getByName(name).getAge()) >= 18;
        } catch (NullPointerException e){
            throw new NoSuchUser(String.format("There is no user with username:%s",name));
        }
    }
}

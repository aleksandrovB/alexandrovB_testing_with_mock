package com.clouway.testing;

import com.clouway.Exceptions.InvalidUserAge;
import com.clouway.Exceptions.NoSuchUser;
import com.clouway.UserDatabase.User;
import com.clouway.UserDatabase.UserDB;
import com.clouway.UserDatabase.UserRepository;
import com.clouway.UserDatabase.Validator;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class DatabaseTest {
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private UserDB userDBMock = context.mock(UserDB.class);
    private Validator validatorMock = context.mock(Validator.class);

    private UserRepository uRepo = new UserRepository(userDBMock,validatorMock);
    private User john = new User("John","31");
    private User johnJr = new User("John jr","9");


    /**
     * Tries to add user with age lower than expected (10-100)
     * Expects one call of Validator and 0 of UserDB
     */
    @Test(expected = InvalidUserAge.class)
    public void addUserWithLowAge() throws InvalidUserAge {
        context.checking(new Expectations(){{
            oneOf(validatorMock).validateAge(johnJr);
            will(returnValue(false));
            never(userDBMock).add(johnJr);
        }});
            uRepo.registerUser(johnJr);
    }

    /**
     * Adds user with proper age
     */
    @Test
    public void addProperUser() {
        context.checking(new Expectations(){{
            oneOf(validatorMock).validateAge(john);
            will(returnValue(true));
            oneOf(userDBMock).add(john);
        }});
        try {
            uRepo.registerUser(john);
        }catch (NumberFormatException | InvalidUserAge e){
            e.printStackTrace();
        }
    }

    /**
     * Tests if isAdult returns false for user at age of 15
     * @throws NoSuchUser thrown if userDB cant find user with such name
     */
    @Test
    public void testingMinorUser() throws NoSuchUser {
        User johnJrSr = new User("Johns older son","15");
        context.checking(new Expectations(){{
            oneOf(userDBMock).getByName(johnJrSr.getUsername());
            will(returnValue(johnJrSr));
        }});
        assertFalse(uRepo.isAdult(johnJrSr.getUsername()));
    }

    /**
     * Tests if isAdult returns true for user at age of 31
     * @throws NoSuchUser thrown if userDB cant find user with such name
     */
    @Test
    public void testingAdultUser() throws NoSuchUser {
        context.checking(new Expectations(){{
            oneOf(userDBMock).getByName(john.getUsername());
            will(returnValue(john));
        }});
        assertTrue(uRepo.isAdult(john.getUsername()));
    }

    /**
     * Tests if invalid user name is handled
     * @throws NoSuchUser thrown if userDB cant find user with such name
     */
    @Test(expected = NoSuchUser.class)
    public void testingInvalidName() throws NoSuchUser {
        context.checking(new Expectations(){{
            oneOf(userDBMock).getByName(johnJr.getUsername());
            will(returnValue(null));
        }});
        uRepo.isAdult(johnJr.getUsername());
    }

    /**
     * Tests when String age is not a parsable string correct exceptions are thrown
     * @throws InvalidUserAge thrown if users age is not parsable string or validated
     */
    @Test(expected = InvalidUserAge.class)
    public void throwsExceptionWithIncorrectString() throws InvalidUserAge {
        User bob = new User("bob","twenty");

        uRepo.registerUser(bob);
    }
}

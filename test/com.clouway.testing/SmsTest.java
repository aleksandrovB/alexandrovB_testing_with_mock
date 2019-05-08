package com.clouway.testing;

import com.clouway.Exceptions.InvalidSMSInput;
import com.clouway.SMSPackage.SMS;
import com.clouway.SMSPackage.Client;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class SmsTest {
    private final String lorem = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
            "Nam ullamcorper elit et tortor finibus, vitae semper purus amet. ";
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();
    private Client receiver = context.mock(Client.class);

    @Test(expected = InvalidSMSInput.class)
    public void sendFailureDueIncorrectTitle() throws InvalidSMSInput {
        new SMS(receiver).send(null,"small message");
    }

    @Test(expected = InvalidSMSInput.class)
    public void sendFailureDueIncorrectMessage() throws InvalidSMSInput {
        new SMS(receiver).send("this is sms title","");
    }

    @Test(expected = InvalidSMSInput.class)
    public void sendFailureDueLongMessage() throws InvalidSMSInput {
        new SMS(receiver).send("lorem ipsum",lorem);
    }

    @Test
    public void receiveSms() throws InvalidSMSInput {
        context.checking(new Expectations(){{
                oneOf(receiver).receive("sms title","and sms message");
            }});
        new SMS(receiver).send("sms title","and sms message");

    }


}

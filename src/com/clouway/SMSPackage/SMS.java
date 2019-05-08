package com.clouway.SMSPackage;


import com.clouway.Exceptions.InvalidSMSInput;

import java.util.LinkedList;
import java.util.List;

public class SMS {
    private Client receiver;

    /**
     * Constructor
     * @param receiver client that receives SMSes from this class
     */
    public SMS(Client receiver) {
        this.receiver = receiver;
    }

    /**
     * Sends SMS containing title and message to receiver
     * @param title title of SMS
     * @param message message of SMS
     * @return list of this SMS title and message
     * @throws InvalidSMSInput throws it if title or message is null or empty,
     * if message is null or longer than 120 characters
     */
    public List<String> send(String title, String message) throws InvalidSMSInput {
        try {
            message = message.trim();
            if (title.equals("")) {
                throw new InvalidSMSInput("Title cannot be empty!");
            } else {
                if (message.length() > 120 || message.length() <= 1) {
                    throw new InvalidSMSInput("Message has to be between 1 and 120 characters long!");
                } else {
                    receiver.receive(title, message);
                    List<String> smsContent = new LinkedList<>();
                    smsContent.add(title);
                    smsContent.add(message);
                    return smsContent;
                }
            }
        }catch (NullPointerException e){
            throw new InvalidSMSInput("Title and message cannot be null!");
        }
    }
}

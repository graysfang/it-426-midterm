package models;

import java.util.ArrayList;

/**
 * Created by Hillary on 11/8/2017.
 */
public class RemindersModel {

    private static ArrayList<String> messages;
    final private static String FILE_NAME = "reminders.txt";

    public void addMessage(String message)
    {
        messages.add(message);
        UtilitiesIO.writeOut(FILE_NAME, messages);
    }

    public void removeMessage(String message)
    {
        for (String item: messages)
        {
            if(item.toLowerCase().equals(message.toLowerCase()))
            {
                messages.remove(item);
                break;
            }
        }
        UtilitiesIO.writeOut(FILE_NAME, messages);
    }

    public ArrayList<String> getMessages()
    {
        messages = new ArrayList<>();

        messages = UtilitiesIO.readIn(FILE_NAME);

        return messages;
    }

}

package models;

import java.util.ArrayList;

/**
 * Model / Controller for RemindersUI
 *
 * @author Nicholas Perez, Hillary Wagoner, Bo Zhang
 * @version 1.0
 */
public class RemindersModel {

    private static ArrayList<String> messages;
    final private static String FILE_NAME = "reminders.txt";

    /**
     * Adds a message to reminders.txt
     *
     * @param message the message to be added to reminders
     */
    public void addMessage(String message)
    {
        messages.add(message);
        UtilitiesIO.writeOut(FILE_NAME, messages);
    }

    /**
     * Removes a message and updates the file
     *
     * @param message the message to be removed
     */
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

    /**
     * Reads in the message reminders from a text file
     *
     * @return an ArrayList with all the messages from file
     */
    public ArrayList<String> getMessages()
    {
        messages = new ArrayList<>();

        messages = UtilitiesIO.readIn(FILE_NAME);

        return messages;
    }

}

package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.RemindersModel;


public class RemindersUI {

    final private static int ROW_HEIGHT = 28;
    final private static int LIST_ROW_PADDING = 2;
    private static RemindersModel remindersModel = new RemindersModel();
    private static ObservableList messages = FXCollections.observableArrayList();

    public static Scene reminders(Stage stage)
    {
        GridPane gridPane = TempleteUI.generateGridPane();
        gridPane.getStyleClass().add("smallButtons");

        populateMessages();

        HBox addReminderBox = new HBox();
        addReminderBox.setSpacing(10);
        addReminderBox.setPadding(new Insets(0, 0, 10, 0));
        addReminderBox.getStyleClass().add("trails");

        Label addReminderLabel = new Label("Add Reminder: ");
        TextField addReminderField = new TextField();
        Button addReminder = new Button("Add Reminder");

        addReminderBox.getChildren().addAll(addReminderLabel, addReminderField);

        HBox addRemoveButtons = new HBox();
        addRemoveButtons.setSpacing(5);

        VBox remindersList = new VBox();
        remindersList.setPadding(new Insets(10, 0, 0, 0));

        ListView reminders = new ListView(messages);
        listPrefHeight(reminders);
        reminders.setMaxWidth(225);
        remindersList.getChildren().add(reminders);


        Button removeReminder = new Button("Remove Reminder");
        addRemoveButtons.getChildren().addAll(addReminder, removeReminder, TempleteUI.homeBar(stage));

        gridPane.add(addReminderBox, 0, 1, 3, 1);
        gridPane.add(addRemoveButtons, 0, 3, 3, 1);
        gridPane.add(remindersList, 0, 5, 3, 1);

        addReminder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                if (!addReminderField.getText().isEmpty())
                {
                    remindersModel.addMessage(addReminderField.getText());
                    addReminderField.clear();
                    populateMessages();
                    listPrefHeight(reminders);
                }
            }
        });

        removeReminder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                remindersModel.removeMessage((String) reminders.getSelectionModel().getSelectedItem());
                populateMessages();
                listPrefHeight(reminders);
            }
        });

        return new Scene(gridPane, 350, 500);
    }

    private static void listPrefHeight(ListView list)
    {
        list.setPrefHeight(messages.size() * ROW_HEIGHT + LIST_ROW_PADDING);
    }

    private static void populateMessages()
    {
        messages.clear();
        messages.addAll(remindersModel.getMessages());
    }
}

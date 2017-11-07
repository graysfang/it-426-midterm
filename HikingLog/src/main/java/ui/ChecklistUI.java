package ui;/*
 * Nicholas Perez
 * 11/6/2017
 * ChecklistUI.java
 *
 * DESCRIPTION
 */

import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.ChecklistModel;

import java.util.ArrayList;

/**
 * DESCRIPTION
 *
 * @author Nicholas Perez
 * @version 1.0
 **/
public class ChecklistUI
{
    private static ChecklistModel checklistModel = new ChecklistModel();


    public static Scene checklist(Stage stage)
    {
        GridPane gridPane = TempleteUI.generateGridPane();
        ArrayList<String> checklist = checklistModel.getChecklist();

        String[] checklistItems = {"Backpack","Water Bottle","Compass","First Aid Kit","Map","Camping Kit"};
        VBox checklistVBox = new VBox();

        CheckBox testCheckbox = new CheckBox(checklistItems[0]);

        BorderPane borderPane = new BorderPane();

        for (String item: checklist)
        {

            checklistVBox.getChildren().add(new CheckBox(item));
        }
        checklistVBox.getStyleClass().add("checklist");

        borderPane.setCenter(checklistVBox);



        gridPane.add(borderPane, 0, 0, 4,1);



        return new Scene(gridPane, 350, 650);
    }
}

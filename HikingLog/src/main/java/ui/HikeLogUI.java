package ui;
/*
 * Nicholas Perez
 * 10/30/2017
 * HikeLogUI.java
 *
 * DESCRIPTION
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import trails.Trails;

/**
 * DESCRIPTION
 *
 * @author Nicholas Perez
 * @version 1.0
 **/
public class HikeLogUI
{
    private Stage stage;
    public static final int MAX_WIDTH = 300;

    public HikeLogUI(Stage stage)
    {
        this.stage = stage;
    }

    public Scene assemble()
    {
        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(15, 25, 15, 25));

        gridPane.getColumnConstraints().addAll(
                new ColumnConstraints(100),
                new ColumnConstraints(100),
                new ColumnConstraints(100));

        //create and add three rows: trails, checklist, reminders, logo

        //for button creation/adding later
        for(int i = 0; i<4; i++)
        {

        }

        //HBox row = new HBox();
        Button trails = new Button("Trails");
        Button checklist = new Button("Checklist");
        Button reminders = new Button("Reminders");

        trails.setOnAction(event -> stage.setScene(TrailsUI.trails()));

        trails.setMaxWidth(MAX_WIDTH);
        checklist.setMaxWidth(MAX_WIDTH);
        reminders.setMaxWidth(MAX_WIDTH);

        Image logo = new Image("trails_logo.png");

        ImageView logoView = new ImageView();
        logoView.setImage(logo);

        VBox vBox = new VBox();
        vBox.setSpacing(15);
        vBox.getChildren().addAll(trails, checklist, reminders, logoView);
        vBox.getStyleClass().add("wideButtons");

        gridPane.getStylesheets().add("styles.css");
        gridPane.add(vBox, 0, 3, 3, 1);


        return new Scene(gridPane, 350, 650);
    }

    private Scene home(){return null;}
    private Scene trails(){return null;}
    private Scene aTrail(){return null;}
    private Scene checklist(){return null;}
    private Scene reminders(){return null;}
}

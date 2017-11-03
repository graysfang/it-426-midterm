package ui;
/*
 * Nicholas Perez, Hillary Wagoner, Bo Zhang
 * 10/30/2017
 * HikeLogUI.java
 *
 * DESCRIPTION
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * DESCRIPTION
 *
 * @author Nicholas Perez, Hillary Wagoner, Bo Zhang
 * @version 1.0
 **/
public class HikeLogUI extends Application
{

    public static final int MAX_WIDTH = 300;

    @Override
    public void start(Stage stage) throws Exception
    {
        stage.setTitle("Hiking Log");
        stage.setScene(checklist());
        stage.show();
    }

    private Scene assemble()
    {
        BorderPane basePane = new BorderPane();
        GridPane gridPane = generateGridPane();
        VBox vBox = new VBox();

        vBox.setSpacing(35);
        vBox.getStyleClass().add("wideButtons");



        gridPane.add(vBox, 0, 3, 3, 1);

        String[] buttonLabels = {"Trails", "Checklist", "Reminders"};
        Button[] menuButtons = new Button[buttonLabels.length];

        for(int i = 0; i<3; i++)
        {
            menuButtons[i] = new Button(buttonLabels[i]);
            menuButtons[i].setMaxWidth(MAX_WIDTH);
            vBox.getChildren().add(menuButtons[i]);
        }

        Image logo = new Image("trails_logo.png");
        ImageView logoView = new ImageView();
        logoView.setImage(logo);

        basePane.setTop(gridPane);
        basePane.setBottom(logoView);
        basePane.setPadding(new Insets(15, 25, 15, 25));
        basePane.getStylesheets().add("styles.css");
        return new Scene(basePane, 350, 650);
    }

    private Scene home(){return null;}
    private Scene trails(){return null;}
    private Scene aTrail(){return null;}
    private Scene checklist()
    {
        GridPane gridPane = generateGridPane();

        String[] checklistItems = {"Backpack","Water Bottle","Compass","First Aid Kit","Map","Camping Kit"};



        VBox checklistBox = new VBox();

        CheckBox testCheckbox = new CheckBox(checklistItems[0]);


        checklistBox.getStyleClass().add("checklist");
        checklistBox.getChildren().add(testCheckbox);
        gridPane.add(checklistBox, 0,0);


        return new Scene(gridPane, 350, 650);
    }
    private Scene reminders(){return null;}

    private GridPane generateGridPane()
    {
        GridPane gridPane = new GridPane();

        gridPane.getColumnConstraints().addAll(
                new ColumnConstraints(100),
                new ColumnConstraints(100),
                new ColumnConstraints(100));

        gridPane.getStylesheets().add("styles.css");

        return gridPane;
    }
}

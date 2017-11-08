package ui;

/*
 * Nicholas Perez, Hillary Wagoner, Bo Zhang
 * 10/30/2017
 * HomeUI.java
 *
 * Defualt landing ui for app
 */

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Defualt landing ui for app
 *
 * @author Nicholas Perez, Hillary Wagoner, Bo Zhang
 * @version 1.0
 **/

public class HomeUI
{
    private static final int MAX_WIDTH = 300;

    /**
     * Constructs the home screen
     * @param stage current stage
     * @return built stage
     */
    public static Scene assemble(Stage stage)
    {

        BorderPane basePane = new BorderPane();
        GridPane gridPane = TempleteUI.generateGridPane();
        VBox vBox = new VBox();

        vBox.setSpacing(35);
        vBox.getStyleClass().add("wideButtons");

        gridPane.add(vBox, 0, 3, 3, 1);

        String[] buttonLabels = {"Trails", "Checklist", "Reminders"};
        Button[] menuButtons = new Button[buttonLabels.length];

        for (int i = 0; i < 3; i++)
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
        basePane.getStylesheets().add("styles.css");

        for (Button button : menuButtons)
        {
            String label = button.getText();

            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event)
                {
                    if (label.equals("Trails"))
                    {
                        stage.setScene(TrailsUI.trails(stage));
                    } else if (label.equals("Checklist"))
                    {
                        stage.setScene((ChecklistUI.checklist(stage)));
                    }
                }
            });
        }

        return new Scene(basePane, 350, 500);
    }
}

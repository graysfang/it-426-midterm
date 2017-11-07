package ui;/*
 * Nicholas Perez, Hillary Wagoner, Bo Zhang
 * 11/6/2017
 * TempleteUI.java
 *
 * Basic template class to handle the creation of common view components
 */

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Basic template class to handle the creation of common view components
 *
 * @author Nicholas Perez, Hillary Wagoner, Bo Zhang
 * @version 1.0
 **/
public class TempleteUI
{
    /**
     * Basic GridPane creation
     * @return the gridpane
     */
    public static GridPane generateGridPane()
    {
        GridPane gridPane = new GridPane();

        gridPane.getColumnConstraints().addAll(
                new ColumnConstraints(100),
                new ColumnConstraints(100),
                new ColumnConstraints(100));

        gridPane.setPadding(new Insets(15, 25, 15, 25));
        gridPane.getStylesheets().add("styles.css");

        return gridPane;
    }

    /**
     * Basic home bar creation
     * @param stage the current stage
     * @return a VBox with a home button.Returns the user to the home screen
     */
    public static VBox homeBar(Stage stage)
    {
        Button homeButton = new Button("Home");
        VBox homeBox = new VBox();

        homeButton.setOnAction(event ->
        {
            stage.setScene(HikeLogUI.assemble(stage));
        });

        homeBox.getChildren().add(homeButton);

        homeBox.getStyleClass().add("smallButtons");

        homeBox.setAlignment(Pos.CENTER);
        return homeBox;
    }

}

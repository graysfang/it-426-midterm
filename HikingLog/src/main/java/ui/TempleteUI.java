package ui;/*
 * Nicholas Perez
 * 11/6/2017
 * TempleteUI.java
 *
 * DESCRIPTION
 */

import javafx.geometry.Insets;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

/**
 * DESCRIPTION
 *
 * @author Nicholas Perez
 * @version 1.0
 **/
public class TempleteUI
{
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

}

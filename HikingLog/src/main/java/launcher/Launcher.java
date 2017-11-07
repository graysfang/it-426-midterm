package launcher;
/*
 * Nicholas Perez, Hillary Wagoner, Bo Zhang
 * 10/30/2017
 * Launcher.java
 *
 * DESCRIPTION
 */

import javafx.application.Application;
import javafx.stage.Stage;
import ui.HomeUI;

/**
 * DESCRIPTION
 *
 * @author Nicholas Perez, Hillary Wagoner, Bo Zhang
 * @version 1.0
 **/
public class Launcher extends Application
{
    public void start(Stage stage) throws Exception
    {
        stage.setTitle("Hiking Log");
        stage.setScene(HomeUI.assemble(stage));
        stage.show();
    }

    public static void main(String[] args)
    {
        Application.launch(args);

    }
}

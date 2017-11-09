package launcher;
/*
 * Nicholas Perez, Hillary Wagoner, Bo Zhang
 * 10/30/2017
 * Launcher.java
 *
 * Driver class for the app
 */

import javafx.application.Application;
import javafx.stage.Stage;
import ui.HomeUI;

/**
 * Driver class for the app
 *
 * @author Nicholas Perez, Hillary Wagoner, Bo Zhang
 * @version 1.0
 **/
public class Launcher extends Application
{

    /**
     * Loads the ui
     * @param stage current stage to be shown
     * @throws Exception if stage does'nt exist?
     */
    public void start(Stage stage)
    {
        stage.setTitle("Hiking Log");
        stage.setScene(HomeUI.assemble(stage));
        stage.show();
    }

    /**
     * the main
     * @param args default params
     */
    public static void main(String[] args)
    {
        Application.launch(args);

    }
}

package launcher;
/*
 * Nicholas Perez
 * 10/30/2017
 * Launcher.java
 *
 * DESCRIPTION
 */

import javafx.application.Application;
import javafx.stage.Stage;
import ui.HikeLogUI;

/**
 * DESCRIPTION
 *
 * @author Nicholas Perez
 * @version 1.0
 **/
public class Launcher extends Application
{
    public void start(Stage stage) throws Exception
    {
        HikeLogUI hikeLogUI = new HikeLogUI(stage);

        stage.setTitle("Hiking Log");
        stage.setScene(hikeLogUI.assemble());
        stage.show();
    }

    public static void main(String[] args)
    {
        Application.launch(args);

    }
}

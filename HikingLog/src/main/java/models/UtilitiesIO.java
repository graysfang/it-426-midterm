package models;/*
 * Nicholas Perez
 * 11/6/2017
 * UtilitiesIO.java
 *
 * DESCRIPTION
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * DESCRIPTION
 *
 * @author Nicholas Perez
 * @version 1.0
 **/
public class UtilitiesIO
{
    public static ArrayList<String> readIn(String filename)
    {
        ArrayList<String> lines = new ArrayList<>();
        Scanner reader = null;


        try
        {
            reader = new Scanner(new FileInputStream((filename)));

            while(reader.hasNextLine())
            {
                lines.add(reader.nextLine());
            }
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("Error reading/writing from file: "+ ex.getMessage());
        }
        finally
        {
            reader.close();
            return lines;
        }
    }
}

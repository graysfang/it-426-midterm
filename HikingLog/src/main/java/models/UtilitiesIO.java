package models;/*
 * Nicholas Perez, Hillary Wagoner, Bo Zhang
 * 11/6/2017
 * UtilitiesIO.java
 *
 * DESCRIPTION
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * DESCRIPTION
 *
 * @author Nicholas Perez, Hillary Wagoner, Bo Zhang
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
            reader = new Scanner(new FileInputStream(("src/main/resources/"+filename)));

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

    public static void writeOut(String filename, ArrayList<String> data)
    {
        PrintWriter writer = null;

        try
        {
            writer = new PrintWriter(new FileOutputStream("src/main/resources/"+filename, false));

            for (String line : data)
            {
                writer.println(line);
            }
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("Error reading/writing from file: "+ ex.getMessage());
        }
        finally
        {
            writer.close();
        }
    }

    public static void writeOutAppend(String filename, String data)
    {
        PrintWriter writer = null;

        try
        {
            writer = new PrintWriter(new FileOutputStream("src/main/resources/"+filename, true));

            writer.println(data);
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("Error reading/writing from file: "+ ex.getMessage());
        }
        finally
        {
            writer.close();
        }
    }
}

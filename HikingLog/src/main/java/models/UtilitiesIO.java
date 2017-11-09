package models;/*
 * Nicholas Perez, Hillary Wagoner, Bo Zhang
 * 11/6/2017
 * UtilitiesIO.java
 *
 * DESCRIPTION
 */

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
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
public class UtilitiesIO {
    public static ArrayList<String> readIn(String filename)
    {
        ArrayList<String> lines = new ArrayList<>();
        Scanner reader = null;

        try
        {
            reader = new Scanner(new FileInputStream(("src/main/resources/" + filename)));

            while (reader.hasNextLine())
            {
                lines.add(reader.nextLine());
            }
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("Error reading/writing from file: " + ex.getMessage());
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
            writer = new PrintWriter(new FileOutputStream("src/main/resources/" + filename, false));

            for (String line : data)
            {
                writer.println(line);
            }
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("Error reading/writing from file: " + ex.getMessage());
        }
        finally
        {
            writer.close();
        }
    }
}

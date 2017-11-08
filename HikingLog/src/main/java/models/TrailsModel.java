package models;
/*
 * Nicholas Perez, Hillary Wagoner, Bo Zhang
 * 11/1/2017
 * TrailsModel.java
 *
 * DESCRIPTION
 */

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

/**
 * DESCRIPTION
 *
 * @author Nicholas Perez, Hillary Wagoner, Bo Zhang
 * @version 1.0
 **/
public class TrailsModel {

    private static final String TRAIL_TAG_NAME = "trail";
    private static final String FILE_PATH = "src/main/resources/trails.xml";
    private static final String DELIMS = "[ ,]";

    private HashMap<String, Integer> trailAndIndex;
    private ArrayList<String> trails;
    private String trailName;
    private String stepCountHistory;
    private Integer trailIndex;


    public void addTrail(String trailName)
    {
        if (!trailName.trim().equals("") && !doesTrailExist(trailName))
        {
            try
            {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                Document doc = docBuilder.parse(FILE_PATH);

                Node root = doc.getFirstChild();

                Node newTrail = doc.createElement(TRAIL_TAG_NAME);
                ((Element) newTrail).setAttribute("id", trailName);
                root.appendChild(newTrail);

                Node createSteps = doc.createElement("steps");
                newTrail.appendChild(createSteps);

                Node createHeartRate = doc.createElement("heartrate");
                newTrail.appendChild(createHeartRate);

                writeToXMLFile(doc);

            }
            catch (ParserConfigurationException pce)
            {
                pce.printStackTrace();
            }
            catch (TransformerException tfe)
            {
                tfe.printStackTrace();
            }
            catch (IOException ioe)
            {
                ioe.printStackTrace();
            }
            catch (SAXException sae)
            {
                sae.printStackTrace();
            }
        }
    }

    private boolean doesTrailExist(String trailName)
    {
        for (String trail : trails)
        {
            if (trail.equalsIgnoreCase(trailName))
            {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getTrails()
    {
        trails = new ArrayList<>();

        readInTrails();

        // Obtaining an iterator for the entry set
        Iterator it = trailAndIndex.entrySet().iterator();

        // Iterate through HashMap entries(Key-Value pairs)
        while (it.hasNext())
        {
            Map.Entry me = (Map.Entry) it.next();
            trails.add((String) me.getKey());
        }

        return trails;
    }

    public void removeTrail(String trailName)
    {
        trails.remove(trailName);
        Integer trailIndex = trailAndIndex.get(trailName);
        try
        {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(FILE_PATH);

            Element allTrails = doc.getDocumentElement();
            Node trailToBeRemoved = allTrails.getElementsByTagName(TRAIL_TAG_NAME).item(trailIndex);
            allTrails.removeChild(trailToBeRemoved);

            writeToXMLFile(doc);
        }
        catch (ParserConfigurationException pce)
        {
            pce.printStackTrace();
        }

        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        catch (SAXException sae)
        {
            sae.printStackTrace();
        }
        catch (TransformerException e)
        {
            e.printStackTrace();
        }
        readInTrails();

    }

    public void setSelectedTrail(String trailName)
    {
        this.trailName = trailName;
        setTrailIndex(trailAndIndex.get(trailName));
    }

    private void setTrailIndex(Integer trailIndex)
    {
        this.trailIndex = trailIndex;
    }

    public String getSelectedTrail()
    {
        return trailName;
    }

    public void addHeartRate(int heartRate)
    {

    }

    public void addStepCount(String steps)
    {
        try
        {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(FILE_PATH);

            Node trail = doc.getElementsByTagName(TRAIL_TAG_NAME).item(trailIndex);

            //get the child node(s) of the trail
            NodeList trailChildren = trail.getChildNodes();

            //find the steps child node
            for (int i = 0; i < trailChildren.getLength(); i++)
            {
                Node child = trailChildren.item(i);

                if ("steps".equals(child.getNodeName()))
                {
                    child.setTextContent(steps);
                }
            }

            writeToXMLFile(doc);
        }
        catch (ParserConfigurationException pce)
        {
            pce.printStackTrace();
        }

        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        catch (SAXException sae)
        {
            sae.printStackTrace();
        }
        catch (TransformerException e)
        {
            e.printStackTrace();
        }
    }

    public String getStepCountHistory()
    {
        try
        {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(FILE_PATH);

            Node trail = doc.getElementsByTagName(TRAIL_TAG_NAME).item(trailIndex);

            NodeList trailChildren = trail.getChildNodes();
            //find the steps child node
            for (int i = 0; i < trailChildren.getLength(); i++)
            {
                Node child = trailChildren.item(i);

                if ("steps".equals(child.getNodeName()))
                {
                    stepCountHistory = child.getTextContent();
                }
            }

        }
        catch (ParserConfigurationException | IOException pce)
        {
            pce.printStackTrace();
        }

        catch (SAXException sae)
        {
            sae.printStackTrace();
        }
        finally
        {
            if (!stepCountHistory.isEmpty())
            {
                return stepCountHistory;
            }else {
                String tempMessage = "Error, Please try again";
                return tempMessage;
            }
        }
    }

    public void clearSelectedTrailInformation()
    {
        trailName = "";
        trailIndex = null;
        stepCountHistory = "";
    }

    private int[] parseStringtoInt(String count)
    {
        String[] tokens = count.split(DELIMS);
        int[] countHistory = new int[tokens.length];

        for (int i = 0; i < tokens.length; i++)
        {
            countHistory[i] = Integer.valueOf(tokens[i]);
            ;
        }

        return countHistory;
    }

    private void readInTrails()
    {
        trailAndIndex = new HashMap<>();

        try
        {
            //assumble xml parser
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(FILE_PATH);

            //get all parent trail tags from xml file
            NodeList trail = doc.getElementsByTagName(TRAIL_TAG_NAME);

            for (int i = 0; i < trail.getLength(); i++)
            {
                trailAndIndex.put(trail.item(i).getAttributes().getNamedItem("id").getNodeValue(), (Integer) i);
            }
        }
        catch (ParserConfigurationException pce)
        {
            pce.printStackTrace();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        catch (SAXException sae)
        {
            sae.printStackTrace();
        }
    }

    private void writeToXMLFile(Document doc) throws TransformerException
    {
        // write the content into xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(FILE_PATH));
        transformer.transform(source, result);
    }
}

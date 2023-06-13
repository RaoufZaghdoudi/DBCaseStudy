package db.caseStudy.utils;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

@Component
public class WagonSectionIndicatorUtil {

    // Get the XML element representing the waggon with the given number
    public Element getWaggon(Element train, int waggonNumber) {
        NodeList waggons = ((Element) train.getElementsByTagName("waggons").item(0)).getElementsByTagName("waggon");

        // Iterate over the waggons and find the one with the matching number
        for (int i = 0; i < waggons.getLength(); i++) {
            Element waggonElement = (Element) waggons.item(i);
            String wNumber = waggonElement
                    .getElementsByTagName("number")
                    .item(0)
                    .getTextContent();

            // Check if the waggon number matches the desired number
            if (wNumber != "") {
                if (Integer.parseInt(wNumber) == waggonNumber) {
                    return waggonElement;
                }
            }
        }

        return null; // Return null if no matching waggon is found
    }

    // Get the XML element representing the train with the given number
    public Element getTrainElement(Element station, int trainNumber) {
        NodeList tracks = ((Element) station.getElementsByTagName("tracks").item(0)).getElementsByTagName("track");

        // Iterate over the tracks and find the train with the matching number
        for (int i = 0; i < tracks.getLength(); i++) {
            Element trackElement = (Element) tracks.item(i);
            NodeList trains = ((Element) trackElement.getElementsByTagName("trains").item(0)).getElementsByTagName("train");

            // Iterate over the trains in the track
            for (int j = 0; j < trains.getLength(); j++) {
                Element trainElement = (Element) trains.item(j);
                String tNumber = ((Element) trainElement
                        .getElementsByTagName("trainNumbers")
                        .item(0)).getElementsByTagName("trainNumber").item(0)
                        .getTextContent();

                // Check if the train number matches the desired number
                if (tNumber != "") {
                    if (Integer.parseInt(tNumber) == trainNumber) {
                        return trainElement;
                    }
                }
            }
        }

        return null; // Return null if no matching train is found
    }

    // Get the XML element representing the station with the given ril100 value
    public Element getStation(String ril100) {
        String WLUPFolderPath = "./src/main/resources/WagonLineUpPlans/";

        File folder = new File(WLUPFolderPath);
        File[] files = folder.listFiles((dir, name) -> name.split("_")[0].equals(ril100));
        File stationFile = files[0];

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(stationFile);
            Element station = document.getDocumentElement();
            return station;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

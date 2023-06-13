package db.caseStudy.services;

import db.caseStudy.utils.WagonSectionIndicatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class WagonSectionIndicatorService {

    @Autowired
    private WagonSectionIndicatorUtil wSIUtil;

    public List<String> getWagonSections(String ril100, int trainNumber, int waggonNumber) {
        List<String> secIdentifiers = new ArrayList<String>();

        Element waggon = wSIUtil.getWaggon(wSIUtil.getTrainElement(wSIUtil.getStation(ril100), trainNumber), waggonNumber);
        NodeList sections = ((Element) waggon.getElementsByTagName("sections").item(0)).getElementsByTagName("identifier");

        // Iterate over the sections and add their identifiers to the list
        for (int i = 0; i < sections.getLength(); i++) {
            String sectionIdentifier = sections.item(i).getTextContent();
            secIdentifiers.add(sectionIdentifier);
        }

        return secIdentifiers;
    }
}
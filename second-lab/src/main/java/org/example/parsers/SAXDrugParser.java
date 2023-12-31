package org.example.parsers;

import org.example.util.XMLCreator;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SAXDrugParser extends ParserXML {
    private static final Logger log = Logger.getLogger(SAXDrugParser.class.getName());
    // Constructor that takes an XMLCreator as a parameter

    public SAXDrugParser(XMLCreator xmlCreator) {
        this.xmlCreator = xmlCreator;
    }
    // Method to parse an XML file using the SAX parser

    @Override
    public void parse(String xmlPath){
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            saxParser.parse(xmlPath, drugHandler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            log.log(Level.SEVERE, "Exception: ", e);
        }
    }
    // Method to create an XML file from the parsed data

    @Override
    public void createXML() {
        xmlCreator.buildXML(drugHandler.getMedicine().getDrugList(),"src/main/resources/sax.xml");
    }
}

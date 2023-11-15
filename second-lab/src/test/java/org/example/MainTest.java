package org.example;

import org.apache.commons.io.FileUtils;
import org.example.parsers.*;
import org.example.util.XMLCreator;
import org.example.util.XMLValidator;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MainTest {
    private static final Logger log = Logger.getLogger(MainTest.class.getName());
    // JUnit test method

    @Test
    public void testMain(){
        // Create an XMLCreator for generating XML files

        XMLCreator xmlCreator = new XMLCreator();
        // Create parsers for different parsing methods

        DOMParser domParser = new DOMParser(xmlCreator);
        SAXDrugParser saxDrugParser = new SAXDrugParser(xmlCreator);
        STAXParser staxParser = new STAXParser(xmlCreator);
        // Paths to XML and XSD files

        String XML = "src/main/resources/input.xml";
        String XSD = "src/main/resources/input.xsd";
        // Validate the XML file against the XSD schema

        if(XMLValidator.validateXML(XML, XSD)){
            log.info("XML is valid");
        }
        else log.info("XML is not valid");
        // Parse the XML file using SAX and create an XML file

        saxDrugParser.parse(XML);
        saxDrugParser.createXML();
        // Parse the XML file using STAX and create an XML file

        staxParser.parse(XML);
        staxParser.createXML();
        // Parse the XML file using DOM and create an XML file

        domParser.parse(XML);
        domParser.createXML();
        try {
            // Compare the content of the generated XML files

            Assert.assertTrue(FileUtils.contentEquals(new File("src/main/resources/dom.xml"),
                    new File("src/main/resources/sax.xml")));
            Assert.assertTrue(FileUtils.contentEquals(new File("src/main/resources/sax.xml"),
                    new File("src/main/resources/stax.xml")));
        }catch (IOException e){
            log.log(Level.SEVERE, "Exception: ", e);
        }
    }
}
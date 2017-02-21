package sax;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.File;
import java.io.IOException;

public class Sax {

    private static final File file = new File("src\\main\\resources\\menu.xml");

    public void saxParser() throws SAXException, IOException {
        XMLReader reader = XMLReaderFactory.createXMLReader();
        MenuSaxHandler handler = new MenuSaxHandler();
        reader.setContentHandler(handler);
        reader.parse(new InputSource(String.valueOf(file)));

        reader.setFeature("http://xml.org/sax/features/validation", true);

        reader.setFeature("http://xml.org/sax/features/namespaces", true);

        reader.setFeature("http://xml.org/sax/features/string-interning", true);

        reader.setFeature("http://apache.org/xml/features/validation/schema", false);

    }









}

package stax;


import bean.Food;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;



public class StaxParser {

    private static final File file = new File("src\\main\\resources\\menu.xml");


    public enum MenuTagName {
        DESCRIPTION, PRICE, PORTION, NAME, DISH, MENU;

        public static MenuTagName getElementTagName(String element) {
            switch (element) {
                case "menu":
                    return MENU;
                case "dish":
                    return DISH;
                case "name":
                    return NAME;
                case "price":
                    return PRICE;
                case "description":
                    return DESCRIPTION;
                case "portion":
                    return PORTION;

                default:
                    throw new EnumConstantNotPresentException(MenuTagName.class, element);
            }


        }

    }


    public void staxParser() throws FileNotFoundException, XMLStreamException {

        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try {
            InputStream input = new FileInputStream(file);
            XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
            List<Food> menu = process(reader);

        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    private static List<Food> process(XMLStreamReader reader) throws XMLStreamException {
        List<Food> menu = new ArrayList<Food>();
        Food food = null;
        MenuTagName elementName = null;
        while (reader.hasNext()) {

            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    elementName = MenuTagName.getElementTagName(reader.getLocalName());
                    switch (elementName) {
                        case DISH:
                            food = new Food();
                            String id = reader.getAttributeValue(null, "id");
                            food.setId(id);
                            String dishType = reader.getAttributeValue(null, "type");
                            System.out.println("ID: " + id);
                            System.out.println("Type: " + dishType);
                            break;

                        case NAME:

                            String title = reader.getAttributeValue(null, "title");
                            food.setTitle(title);
                            System.out.println("Title: " + title);
                            break;
                    }
                    break;
                case XMLStreamConstants.CHARACTERS:
                    String text = reader.getText().trim();
                    if (text.isEmpty()) {
                        break;
                    }
                    switch (elementName) {

                        case PRICE:
                            food.setPrice(Integer.parseInt(text));
                            System.out.println("Price: " + food.getPrice());
                            break;
                        case DESCRIPTION:
                            food.setDescription(text);
                            System.out.println("Description: " + food.getDescription());
                            break;
                        case PORTION:
                            food.setPortion(text);
                            System.out.println("Portion: " + food.getPortion());
                            break;
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    elementName = MenuTagName.getElementTagName(reader.getLocalName());
                    switch (elementName) {
                        case DISH:
                            menu.add(food);
                            System.out.println();

                    }
            }
        }
        return menu;
    }


}







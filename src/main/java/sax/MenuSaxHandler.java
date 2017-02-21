package sax;

import bean.Food;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;


public class MenuSaxHandler extends DefaultHandler {

    public enum MenuTagName {

        DESCRIPTION, PRICE, PORTION, NAME, DISH, MENU
    }

    private List<Food> foodList = new ArrayList<Food>();
    private Food food;
    private StringBuilder text;

    public List<Food> getFoodList() {
        return foodList;
    }

    public void startDocument() throws SAXException {
        System.out.println("Parsing started.");
    }

    public void endDocument() throws SAXException {
        System.out.println("Parsing ended.");
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        text = new StringBuilder();
        if (qName.equals("menu")) {
            food = new Food();

        } else if (qName.equals("dish")) {
            System.out.println();
            food.setId(attributes.getValue("id"));
            System.out.println("ID: " + food.getId());
            food.setType(attributes.getValue("type"));
            System.out.println("Type: " + food.getType());
            System.out.println();
        } else if (qName.equals("name")) {
            System.out.println();
            food.setTitle(attributes.getValue("title"));
            System.out.println("Title: " + food.getTitle());

        }
    }

    public void characters(char[] buffer, int start, int length) {
        text.append(buffer, start, length);
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        MenuTagName tagName = MenuTagName.valueOf(qName.toUpperCase().replace("-", "_"));

        switch (tagName) {

            case PRICE:
                food.setPrice(Integer.parseInt(text.toString()));
                System.out.println("Price: " + food.getPrice());
                break;
            case DESCRIPTION:
                food.setDescription(text.toString());
                System.out.println("Description: " + food.getDescription());
                break;
            case PORTION:
                food.setPortion(text.toString());
                System.out.println("Portion: " + food.getPortion());
                break;
            case MENU:
                foodList.add(food);
                food = null;

                break;
        }
    }

    public void warning(SAXParseException exception) {
        System.err.println("WARNING: line " + exception.getLineNumber() + ": "
                + exception.getMessage());
    }

    public void error(SAXParseException exception) {
        System.err.println("ERROR: line " + exception.getLineNumber() + ": "
                + exception.getMessage());
    }

    public void fatalError(SAXParseException exception) throws SAXException {
        System.err.println("FATAL: line " + exception.getLineNumber() + ": "
                + exception.getMessage());
        throw (exception);
    }


}

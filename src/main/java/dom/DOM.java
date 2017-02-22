package dom;

import bean.Food;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class DOM {

    private static final File file = new File("src\\main\\resources\\menu.xml");



    public void parser() throws IOException, SAXException, ParserConfigurationException {


        DOMParser parser = new DOMParser();


        parser.parse(String.valueOf(file));
        Document document = parser.getDocument();

        Element root = document.getDocumentElement();


        NodeList foodNodes = root.getElementsByTagName("dish");
        Food food = null;

        for (int i = 0; i < foodNodes.getLength(); i++) {

            food = new Food();

            if (foodNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element foodElement = (Element) foodNodes.item(i);

                food.setId(foodElement.getAttribute("id"));
                food.setType(foodElement.getAttribute("type"));

                System.out.println();
                System.out.println("ID: " + food.getId() + " Type: " + food.getType());
                System.out.println();

                NodeList name = root.getElementsByTagName("name");
                for (int j = 0; j <name.getLength(); j++) {

                    if (name.item(j).getNodeType() == Node.ELEMENT_NODE) {

                        Element nameElement = (Element) name.item(j);

                        food.setTitle(nameElement.getAttribute("title"));

                        food.setPortion(getSingleChild(nameElement, "portion").getTextContent().trim());
                        food.setPrice(Integer.parseInt(getSingleChild(nameElement, "price").getTextContent().trim()));
                        food.setDescription(getSingleChild(nameElement, "description").getTextContent().trim());

                        System.out.println("Title: " + food.getTitle() + " Description: "  + food.getDescription() + " Portion: "
                        + food.getPortion() + " Price: " + food.getPrice());
                    }

                }
            }


        }
    }



    private static Element getSingleChild(Element element, String childName) {
        NodeList nlist = element.getElementsByTagName(childName);
        Element child = (Element) nlist.item(0);
        return child;
    }


}






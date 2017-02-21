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
import java.util.ArrayList;
import java.util.List;

public class DOM {

    private static final File file = new File("src\\main\\resources\\menu.xml");



    public void parser(String tagname) throws IOException, SAXException, ParserConfigurationException {


        DOMParser parser = new DOMParser();


        parser.parse(String.valueOf(file));
        Document document = parser.getDocument();

        Element root = document.getDocumentElement();

        List<Food> menu = new ArrayList<Food>();
        NodeList foodNodes = root.getElementsByTagName(tagname);

        for (int i = 0; i < foodNodes.getLength(); i++) {

            menu.add(getFood(foodNodes.item(i)));
        }
        for (Food f : menu) {
            System.out.println(f.getTitle() + ", " + f.getId() + ", " + f.getType() + ", " + f.getDescription() + ", "
                    + f.getPortion() + ", " + f.getPrice());
        }
    }

    private Food getFood(Node node) {

        Food food = new Food();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element foodElement = (Element) node;
            food.setId(foodElement.getAttribute("id"));
            food.setTitle(foodElement.getAttribute("title"));
            food.setType(foodElement.getAttribute("type"));
            food.setPortion(getSingleChild(foodElement, "portion").getTextContent().trim());
            food.setPrice(Integer.parseInt(getSingleChild(foodElement, "price").getTextContent().trim()));
            food.setDescription(getSingleChild(foodElement, "description").getTextContent().trim());
        }
        return food;
    }


    private static Element getSingleChild(Element element, String childName) {
        NodeList nlist = element.getElementsByTagName(childName);
        Element child = (Element) nlist.item(0);
        return child;
    }


}






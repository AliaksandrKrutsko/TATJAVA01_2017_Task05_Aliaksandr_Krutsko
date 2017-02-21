package bean;

import java.io.Serializable;


public class Food implements Serializable {

    private String id;
    private String type;
    private String title;
    private String description;
    private String portion;
    private int price;

    public Food() {

    }

    public String getId() { return id; }
    public String getType() {return type;}
    public String getTitle() {return title;}
    public String getDescription() {return description;}
    public String getPortion() {return portion;}
    public int getPrice() {return price;}

    public void setId(String id) {this.id = id;}
    public void setType(String type) {this.type = type;}
    public void setTitle(String title) {this.title = title;}
    public void setDescription(String description) {this.description = description;}
    public void setPortion(String portion) {this.portion = portion;}
    public void setPrice(int price) {this.price = price;}







}

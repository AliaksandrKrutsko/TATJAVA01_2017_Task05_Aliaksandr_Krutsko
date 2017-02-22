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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Food other = (Food) obj;
        if (id != other.id)
            return false;
        if (type != other.type)
            return false;
        if (title != other.title)
            return false;
        if (description != other.description)
            return false;
        if (portion != other.portion)
            return false;
        if (price != other.price)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id.hashCode();
        result = prime * result + type.hashCode();
        result = prime * result + price;
        result = prime * result + title.hashCode();
        result = prime * result + description.hashCode();
        result = prime * result + portion.hashCode();
        return result;
    }






}

package model;

public class Book {
    private String name;
    private String author;
    private int ID;
    private String ImageSrc;
    private String category;
    private String details;

    public Book(String name, String author, int ID, String imageSrc, String category, String details) {
        this.name = name;
        this.author = author;
        this.ID = ID;
        ImageSrc = imageSrc;
        this.category = category;
        this.details = details;
    }

    public Book() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getImageSrc() {
        return ImageSrc;
    }

    public void setImageSrc(String imageSrc) {
        ImageSrc = imageSrc;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}

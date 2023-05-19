package model;
import java.util.Calendar;

public abstract class Product {

    // Attributes
    private String id;
    private String name;
    private int numPages;
    private Calendar publicationDate;
    private String url;
    private double price;
    private int totalPagesRead;

    // Methods
    public Product (String id, String name, int numPages, Calendar publicationDate, String url, double price) {
        this.id = id;
        this.name = name;
        this.numPages = numPages;
        this.publicationDate = publicationDate;
        this.url = url;
        this.price = price;
        this.totalPagesRead = 0;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNumPages() {
        return numPages;
    }

    public Calendar getPublicationDate() {
        return publicationDate;
    }

    public String getUrl() {
        return url;
    }

    public double getPrice() {
        return price;
    }

    public int getTotalPagesRead() {
        return totalPagesRead;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name =  name;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }

    public void setPublicationDate(Calendar publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTotalPagesRead(int totalPagesRead) {
        this.totalPagesRead = totalPagesRead;
    }

    public abstract String modify(int attribute, String update);

    public Calendar stringCalendar(String date){
        Calendar cal = Calendar.getInstance();
        int day;
        int month;
        int year;
        String[] dateParts = date.split("-");
        day=Integer.parseInt(dateParts[0]);
        month=Integer.parseInt(dateParts[1]);
        year=Integer.parseInt(dateParts[2]);
        cal.set(year, month, day);
        return cal;
    }

    public String sellInfo(){
        String msg = "";
        msg = "ID: " + this.getId() + ", Name: " + this.getName() + ", Price: $" + this.getPrice();
        return msg;
    }
}

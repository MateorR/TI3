package model;
import java.util.Calendar;

public class Book extends Product {
    // Attributes
    private String description;
    private int copiesSold;

    // Relations
    private BookGenre genre;

    // Methods
    public Book(String id, String name, int numPages, Calendar publicationDate, String url, double price, String description, int genre) {
        super(id, name, numPages, publicationDate, url, price);
        this.description = description;
        this.copiesSold = 0;
        this.genre = BookGenre.values()[genre];
    }

    public String getDescription() {
        return description;
    }

    public int getCopiesSold() {
        return copiesSold;
    }

    public BookGenre getGenre() {
        return genre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCopiesSold(int copiesSold) {
        this.copiesSold = copiesSold;
    }

    public void setGenre(BookGenre genre) {
        this.genre = genre;
    }

    public String modify(int attribute, String update){
        String msg="";
        switch(attribute){
            case 1:
                this.setName(update);
                msg="Name updated";
                break;
            case 2:
                this.setNumPages(Integer.parseInt(update));
                msg="Number of pages updated";
                break;
            case 3:
                this.setPublicationDate(stringCalendar(update));
                msg="Publication date updated";
                break;
            case 4:
                this.setUrl(update);
                msg="Url updated";
                break;
            case 5:
                this.setPrice(Double.parseDouble(update));
                msg="Price updated";
                break;
            case 6:
                description=update;
                msg="Description updated";
                break;
            case 7:
                genre=(BookGenre.values()[Integer.parseInt(update)]);
                msg="Genre updated";
                break;
        }
        return msg;
    }

    
}

package model;
import java.util.Calendar;

public class Magazine extends Product {
    //Attributes
    private int activeSuscripstions;

    //relations
    private BroadcastPeriodicity period;
    private MagCategory category;

    //Methods
    public Magazine(String id, String name, int numPages, Calendar publicationDate, String url, double price, int period, int category) {
        super(id, name, numPages, publicationDate, url, price);
        this.activeSuscripstions = 0;
        this.period = BroadcastPeriodicity.values()[period];
        this.category = MagCategory.values()[category];
    }
    

    public int getActiveSuscripstions() {
        return activeSuscripstions;
    }

    public void setActiveSuscripstions(int activeSuscripstions) {
        this.activeSuscripstions = activeSuscripstions;
    }

    public BroadcastPeriodicity getPeriod() {
        return period;
    }

    public void setPeriod(BroadcastPeriodicity period) {
        this.period = period;
    }

    public MagCategory getCategory() {
        return category;
    }

    public void setCategory(MagCategory category) {
        this.category = category;
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
                period=BroadcastPeriodicity.values()[Integer.parseInt(update)];
                msg="Periodicity updated";
                break;
            case 7:
                category=MagCategory.values()[Integer.parseInt(update)];
                msg="Category updated";
                break;                
        }
        return msg;
    }

}
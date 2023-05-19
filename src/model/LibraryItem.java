package model;

import java.util.Calendar;

public class LibraryItem implements Payable {
    // Attributes
    private Calendar operationDate;
    private int currentPage;

    // Relations
    private Product prod;
    

    // Methods
    public LibraryItem(Product prod) {
        this.prod = prod;
        this.operationDate = transactionDate();
        this.currentPage = 0;
    }

    public Product getProd() {
        return prod;
    }

    public Calendar getOperationDate() {
        return operationDate;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setProd(Product prod) {
        this.prod = prod;
    }

    public void setOperationDate(Calendar operationDate) {
        this.operationDate = operationDate;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public Calendar transactionDate(){
        Calendar cal= Calendar.getInstance();
        return cal;
    }

    public double totalValue(){
        double value = prod.getPrice();
        return value;
    }

    public boolean validateItemId(String id){
        boolean valid=false;
        if(prod.getId().equals(id)){
            valid=true;
        }
        return valid;
    }

    /*public String libraryInfo(){
        String msg="";
        if (prod instanceof Book){
            Book book = (Book) prod;
            msg= "Book: " + book.getName() + "\n" + "Genre: " + book.getGenre() + "\n" + "Current page: " + currentPage + "\n" + "Last read: " + operationDate.getTime() + "\n" + "Price: " + totalValue();
        }
        else{
            Magazine magazine = (Magazine) prod;
            msg= "Magazine: " + magazine.getName() + "\n" + "Current page: " + currentPage + "\n" + "Last read: " + operationDate.getTime() + "\n" + "Price: " + totalValue();
        }
        return msg;
    }
    */

    public String readingInfo(){
        String msg= "Reading: " + getProd().getName() + "\n" + "Current page: " + getCurrentPage() + " of " + getProd().getNumPages();
        return msg;

    }

    public void nextReadingPage(){
        if (currentPage<getProd().getNumPages()){
            currentPage++;
        } else if (currentPage==getProd().getNumPages()){
            currentPage=0;
        }
    }

    public void previousReadingPage(){
        if (currentPage>0){
            currentPage--;
        } else if (currentPage==0){
            currentPage=getProd().getNumPages();
        }
    }

}


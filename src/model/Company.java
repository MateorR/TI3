package model;
import java.util.ArrayList;
import java.util.Calendar;

public class Company {
    // Attributes
    private String name;
    private int totalBooks;
    private int totalMagazines;
    private ArrayList<String> readingErrors;

    // Relations
    private ArrayList<User> users;
    private ArrayList<Product> products;
    
    // Constants
    public static final int REGULAR_MAX_BOOKS = 5;
    public static final int REGULAR_MAX_MAGAZINES = 2;
    public static final int MAX_BOOKS = 4096;
    public static final int MAX_MAGAZINES = 46656;
    public static final String[] BOOK_CHARS = new String[]{"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
    public static final String[] MAGAZINE_CHARS = new String[]{"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    public static final int ID_CHARS=3;
    
    // Methods
    public Company(String name) {
        this.name = name;
        totalBooks = 0;
        totalMagazines = 0;
        users = new ArrayList<User>();
        products = new ArrayList<Product>();
        readingErrors = defReadingErrors();
    }

    public ArrayList<String> defReadingErrors() {
        ArrayList<String> readingErrors = new ArrayList<String>();
        readingErrors.add("The user doesn't exist");
        readingErrors.add("The product doesn't exist");
        readingErrors.add("You don't have this item in your library");
        return readingErrors;
    }

    public String getName() {
        return name;
    }

    public int getTotalBooks() {
        return totalBooks;
    }

    public int getTotalMagazines() {
        return totalMagazines;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotalBooks(int totalBooks) {
        this.totalBooks = totalBooks;
    }

    public void setTotalMagazines(int totalMagazines) {
        this.totalMagazines = totalMagazines;
    }

    public String addUser(String name, String id) {
        String msg="";
        if (validUserId(id)) {
            users.add(new RegularU(name, id, REGULAR_MAX_BOOKS, REGULAR_MAX_MAGAZINES));
            msg = "User added";
        } else {
            msg = "The id already exists, then the user couldn't be added";
        }

        return msg;
    }

    public String addUser(String name, String id, int status) { 
        String msg="";
        if (validUserId(id)) {
            users.add(new PremiumU(name, id, PaymentStatus.values()[status]));
            msg = "User added";
        } else {
            msg = "The id already exists, then the user couldn't be added";
        }
        return msg;
    }

    public boolean validUserId(String id) {
        boolean possible = true;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                possible = false;
            }
        }
        return possible;
    }
    
    public String addBook(String name, int numPages, Calendar publicationDate, String url, double price, String description, int genre) {
        String msg="";
        String id=createId(BOOK_CHARS);
        if (totalBooks < MAX_BOOKS && !id.equals("")) {
            products.add(new Book(id, name, numPages, publicationDate, url, price, description, genre));
            msg = "Book added";
            setTotalBooks(getTotalBooks() + 1);
        } else {
            msg = "The maximum number of books has been reached, then the book couldn't be added";
        }
        return msg;
    }

    public String addMagazine(String name, int numPages, Calendar publicationDate, String url, double price, int category, int bPeriod) {
        String msg="";
        String id=createId(MAGAZINE_CHARS);
        if (totalMagazines < MAX_MAGAZINES && !id.equals("")){
            products.add(new Magazine(id, name, numPages, publicationDate, url, price, bPeriod, category));
            msg = "Magazine added";
            setTotalMagazines(getTotalMagazines() + 1);
        } else {
            msg = "The maximum number of magazines has been reached, then the magazine couldn't be added";
        }
        return msg;
    }

    public String createId(String [] chars){
        String id="";
        int random;
        int count=0;
        boolean valid=true;
        while(valid){
            random=(int)(Math.random()*chars.length);
            id+=chars[random];
            count++;
            if (count==ID_CHARS){
                if (validProductId(id)){
                    valid=false;
                } else if(validProductId(id)==false){
                    id="";
                    count=0;
                }
            }
        }
        return id;        
    }

    public Boolean validProductId(String id){
        Boolean possible=true;
        for(int i=0;i<products.size();i++){
            if(products.get(i).getId().equals(id)){
                possible=false;
            }
        }
        return possible;
    }

    public String modifyProd(String id, int attribute, String update){
        String msg="";
        boolean valid=true;
        if (validProductId(id)){
            msg="The product doesn't exist";
        } else {
            for(int i=0; i<products.size() && valid;i++){
                if(products.get(i).getId().equals(id)){
                    msg=products.get(i).modify(attribute, update);
                    valid=false;
                }
            }
        }
        return msg;
    }

    public String deleteProduct(String id){
        String msg="";
        boolean valid=true;
        if (validProductId(id)){
            msg="The product doesn't exist";
        } else {
            for(int i=0; i<products.size() && valid;i++){
                if(products.get(i).getId().equals(id)){
                    products.remove(i);
                    msg="Product deleted";
                    valid=false;
                }
            }
        }
        return msg;
    }
    
    public String showAvailableBooks(){
        String msg="";
        if(products.size()>=1){
            for(int i=0; i<products.size();i++){
                if(products.get(i) instanceof Book){
                    msg+=products.get(i).sellInfo();
                }
            }
        } else {
            msg="There are no products available";
        }
        return msg;
    }

    public String buyBook(String idUser, String idProd){
        String msg="";
        boolean valid1=true;
        boolean valid2=true;
        LibraryItem item=null;
        if (validUserId(idUser)){
            msg="The user doesn't exist";
        } else if (!checkBook(idProd)){
            msg="The book doesn't exist";
        } else {
            for(int i=0; i<products.size() && valid1;i++){
                if (products.get(i).getId().equals(idProd)){
                    item=new LibraryItem(products.get(i));
                    valid1=false;
                }
            }
            for(int i=0; i<users.size() && valid2;i++){
                if(users.get(i).getId().equals(idUser)){
                    msg=users.get(i).buy(item);
                    valid2=false;
                }
            }
        }
        if(msg.equals("Product added")){
            updateCopiesSold(item);
        }
        return msg;
    }

    public void updateCopiesSold(LibraryItem item){
        Book book=(Book)item.getProd();
        for (int i=0; i<products.size();i++){
            if(products.get(i).getId().equals(book.getId())){
                book=(Book)products.get(i);
                book.setCopiesSold(book.getCopiesSold()+1);
                products.set(i, book);                
            }
        }
    }

    public boolean checkBook(String id){
        boolean isBook=false;
        boolean valid=true;
        for(int i=0; i<products.size() && valid;i++){
            if(products.get(i) instanceof Book && products.get(i).getId().equals(id)){
                isBook=true;
                valid=false;
            }
        }
        return isBook;
    }

    public String showAvailableMagazines(){
        String msg="";
        if(products.size()>=1){
            for(int i=0; i<products.size();i++){
                if(products.get(i) instanceof Magazine){
                    msg+=products.get(i).sellInfo();
                }
            }
        } else {
            msg="There are no products available";
        }
        return msg;
    }

    public String suscribeMagazine(String idUser, String idProd){
        String msg="";
        boolean valid1=true;
        boolean valid2=true;
        LibraryItem item=null;
        if (validUserId(idUser)){
            msg="The user doesn't exist";
        } else if (!checkMagazine(idProd)){
            msg="The magazine doesn't exist";
        } else {
            for(int i=0; i<products.size() && valid1;i++){
                if (products.get(i).getId().equals(idProd)){
                    item=new LibraryItem(products.get(i));
                    valid1=false;
                }
            }
            for(int i=0; i<users.size() && valid2;i++){
                if(users.get(i).getId().equals(idUser)){
                    msg=users.get(i).buy(item);
                    valid2=false;
                }
            }
        }
        if(msg.equals("Product added")){
            updateSubscribers(item);
        }
        return msg;        
    }

    public void updateSubscribers(LibraryItem item){
        Magazine magazine=(Magazine)item.getProd();
        for (int i=0; i<products.size();i++){
            if(products.get(i).getId().equals(magazine.getId())){
                magazine=(Magazine)products.get(i);
                magazine.setActiveSuscripstions(magazine.getActiveSuscripstions()+1);
                products.set(i, magazine);                
            }
        }
    }

    public boolean checkMagazine(String id){
        boolean isMagazine=false;
        boolean valid=true;
        for(int i=0; i<products.size() && valid;i++){
            if(products.get(i) instanceof Magazine && products.get(i).getId().equals(id)){
                isMagazine=true;
                valid=false;
            }
        }
        return isMagazine;
    }

    public String enterReadingSesion(String idUser, String idProd, int page){
        String msg="";
        if (validUserId(idUser)){
            msg="The user doesn't exist";
        } else if (validProductId(idProd)){
            msg="The product doesn't exist";
        } else {
            for(int i=0; i<users.size();i++){
                if(users.get(i).getId().equals(idUser)){
                    msg=users.get(i).read(page, idProd);
                }
            }
        }
        
        return msg;

    }

    public boolean possibleReading(String message){
        boolean possible=true;
        for (int i=0;i<readingErrors.size();i++){
            if(message.equals(readingErrors.get(i))){
                possible=false;
            }
        }
        return possible;
    }

    public void modReadingPage(String idUser, String idProd, int page, int nextPrev){
        for(int i=0; i<users.size();i++){
            if(users.get(i).getId().equals(idUser)){
                users.get(i).modReadingPage(idProd,page,nextPrev);
            }
        }
        if (nextPrev==1){
            for(int i=0; i<products.size();i++){
                if(products.get(i).getId().equals(idProd)){
                    products.get(i).setTotalPagesRead(products.get(i).getTotalPagesRead()+1);
                }
            }
        }
    }

    public String showPersonalLibrary(String idUser, int libPage){
        String msg="";
        if (validUserId(idUser)){
            msg="The user doesn't exist";
        } else {
            for(int i=0; i<users.size();i++){
                if(users.get(i).getId().equals(idUser)){
                    msg=users.get(i).showPersonalLibrary(libPage);
                }
            }
        }
        return msg;
    }

    public int libraryPages(String idUser){
        int total=0;
        for(int i=0; i<users.size();i++){
            if(users.get(i).getId().equals(idUser)){
                total=users.get(i).libraryPages();
            }
        }
        return total;
    }

    public boolean validLibrary(String msg){
        boolean valid=true;
        if (msg=="The user doesn't exist"){
            valid=false;
        } 
        return valid;
    }
}
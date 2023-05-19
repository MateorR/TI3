package model;
import java.util.Scanner;

import javax.sound.midi.MetaMessage;

import java.util.Calendar;

public class RdxManager {

    public static final Company rdx = new Company("Readx");
    public static final Scanner load = new Scanner(System.in);
    public static void main(String[] args) {
        boolean continuity = true;
        int sys =-1;
        System.out.println("***************************************************");
        System.out.println("            Welcome to the ReadX system            ");
        System.out.println("***************************************************");
        while (continuity){
            System.out.println("");
            System.out.println("1. Add a new user");
            System.out.println("2. Product managment");
            System.out.println("3. Creatre objects for testing");
            System.out.println("4. Buy a book");
            System.out.println("5. Suscribe to a magazine");
            System.out.println("6. Enter to a reading sesion");
            System.out.println("7. Show personal library");   
            System.out.println("8. Reports"); 
            System.out.println("0. Exit");
            System.out.println("");
            sys=load.nextInt();
            load.nextLine();
            switch(sys){
                case 1:
                    addUser();
                    break;
                case 2:
                    productMangment();
                    break;
                case 3:
                    objectsTesting();
                    break;
                case 4:
                    buyBook();
                    break;
                case 5:
                    suscribeMagazine();
                    break;
                case 6:
                    enterReadingSesion();
                    break;
                case 7:
                    showPersonalLibrary();
                    break;
                case 8:
                    reports();
                    break;
                case 0:
                    System.out.println("thanks for using the system");
                    continuity=false;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

    public static void addUser(){
        boolean continuity=true;
        int typeUser=0;
        while (continuity){
            System.out.println("What type of user do you want to add?");
            System.out.println("1. Regular user");
            System.out.println("2. Premium user");
            System.out.println("");
            typeUser=load.nextInt();
            load.nextLine();
            switch(typeUser){
                case 1:
                    addRegularUser();
                    continuity=false;
                    break;  
                case 2:
                    addPremiumUser();  
                    continuity=false;                 
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
            
    }

    public static void addRegularUser(){
        String name;
        String id;
        System.out.println("Enter the name of the user");
        name=load.nextLine();
        name.toLowerCase();
        System.out.println("Enter the id of the user");
        id=load.nextLine();
        System.out.println("\n"+ rdx.addUser(name, id));
    }

    public static void addPremiumUser(){
        String name;
        String id;
        System.out.println("Enter the name of the user");
        name=load.nextLine();
        name.toLowerCase();
        System.out.println("Enter the id of the user");
        id=load.nextLine();
        System.out.println("\n"+ rdx.addUser(name, id, 0));
        
    }

    public static void productMangment(){
        boolean continuity=true;
        int sys=0;
        while (continuity){
            System.out.println("");
            System.out.println("1. Add a new product");
            System.out.println("2. Modify a product");
            System.out.println("3. Delete a product");
            sys=load.nextInt();
            load.nextLine();
            switch(sys){
                case 1:
                    addProduct();
                    continuity=false;
                    break;
                case 2:
                    modifyProduct();
                    continuity=false;
                    break;
                case 3:
                    deleteProduct();
                    continuity=false;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }

        }
    }

    public static void addProduct(){
        boolean continuity=true;
        int typeProduct=0;
        while (continuity){
            System.out.println("What type of product do you want to add?");
            System.out.println("1. Book");
            System.out.println("2. Magazine");
            System.out.println("");
            typeProduct=load.nextInt();
            load.nextLine();
            switch(typeProduct){
                case 1:
                    addBook();
                    continuity=false;
                    break;  
                case 2:
                    addMagazine();  
                    continuity=false;                 
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

    public static void addBook(){
        String name;
        int numPages;
        Calendar publicationDate;
        String url;
        double price;
        String description;
        int genre;
        System.out.println("Enter the name of the book");
        name=load.nextLine();
        System.out.println("Enter the number of pages of the book");
        numPages=load.nextInt();
        load.nextLine();
        System.out.println("Enter the publication date of the book");
        publicationDate=addCalendar();
        System.out.println("Enter the url of the book");
        url=load.nextLine();
        System.out.println("Enter the price of the book");
        price=load.nextDouble();
        load.nextLine();
        System.out.println("Enter a short description for the book");
        description=load.nextLine();
        genre=bookGenre();
        System.out.println("\n"+ rdx.addBook(name, numPages, publicationDate, url, price, description, genre));      
    }

    public static int bookGenre(){
        int genre=0;
        do { 
            System.out.println("Enter the genre of the book" + "\n");
            System.out.println("1. Science fiction");
            System.out.println("2. Fantasy");
            System.out.println("3. Historic novel");
            genre=load.nextInt();
            load.nextLine();
        } while (genre<1 || genre>3);
        return genre-1;
    }

    public static void addMagazine(){
        String name;
        int numPages;
        Calendar publicationDate;
        String url;
        double price;
        int category;
        int bPeriod;
        System.out.println("Enter the name of the magazine");
        name=load.nextLine();
        System.out.println("Enter the number of pages of the magazine");
        numPages=load.nextInt();
        load.nextLine();
        System.out.println("Enter the publication date of the magazine");
        publicationDate=addCalendar();
        System.out.println("Enter the url of the magazine");
        url=load.nextLine();
        System.out.println("Enter the price of the magazine");
        price=load.nextDouble();
        load.nextLine();
        category=magazineCategory();
        bPeriod=broadcastPeriod();
        System.out.println("\n"+ rdx.addMagazine(name, numPages, publicationDate, url, price, category, bPeriod));
    }

    public static int magazineCategory(){
        int categ=0;
        do { 
            System.out.println("Enter the category of the magazine" + "\n");
            System.out.println("1. Variety");
            System.out.println("2. Design");
            System.out.println("3. Scientific");
            categ=load.nextInt();
            load.nextLine();
        } while (categ<1 || categ>3);
        return categ-1;
    }

    public static int broadcastPeriod(){
        int period=0;
        do { 
            System.out.println("Enter the broadcast period of the magazine" + "\n");
            System.out.println("1. Daily");
            System.out.println("2. Weekly");
            System.out.println("3. Monthly");
            System.out.println("4. Yearly");
            period=load.nextInt();
            load.nextLine();
        } while (period<1 || period>4);
        return period - 1 ;
    }

    public static Calendar addCalendar(){
        int day;
        int month;
        int year;
        Calendar date=Calendar.getInstance();
        System.out.println("Enter the day");
        day=load.nextInt();
        load.nextLine();
        System.out.println("Enter the month");
        month=load.nextInt();
        load.nextLine();
        System.out.println("Enter the year");
        year=load.nextInt();
        load.nextLine();
        date.set(year, month, day);
        return date;
        
    }

    public static void modifyProduct(){
        boolean continuity=true;
        int typeProduct=0;
        while (continuity){
            System.out.println("What type of product do you want to modify?");
            System.out.println("1. Book");
            System.out.println("2. Magazine");
            System.out.println("");
            typeProduct=load.nextInt();
            load.nextLine();
            switch(typeProduct){
                case 1:
                    modifyBook();
                    continuity=false;
                    break;  
                case 2:
                    modifyMagazine();  
                    continuity=false;                 
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }
    
    public static void modifyBook(){
        String id;
        int sys=0;
        Boolean continuity=true;
        String update;
        System.out.println("Enter the id of the book you want to modify" + "\n");
        id=load.nextLine();
        while(continuity){
            System.out.println("What do you want to modify?" + "\n");
            System.out.println("1. Name");
            System.out.println("2. Number of pages");
            System.out.println("3. Publication date");
            System.out.println("4. Url");
            System.out.println("5. Price");
            System.out.println("6. Description");
            System.out.println("7. Genre");
            sys=load.nextInt();
            load.nextLine();
            if (sys >=1 && sys <=7){
                continuity=false;
            } else {
                System.out.println("Invalid option");
            }
        }
        if (sys==3){
            update=updateCalendar();
        } else if (sys==7){
            update= (""+ bookGenre());
        } else {
            System.out.println("Enter the new value");
            update=load.nextLine();
        }
        System.out.println("\n"+ rdx.modifyProd(id, sys, update));
    }

    public static void modifyMagazine(){
        String id;
        int sys=0;
        Boolean continuity=true;
        String update;
        System.out.println("Enter the id of the magazine you want to modify" + "\n");
        id=load.nextLine();
        while(continuity){
            System.out.println("What do you want to modify?" + "\n");
            System.out.println("1. Name");
            System.out.println("2. Number of pages");
            System.out.println("3. Publication date");
            System.out.println("4. Url");
            System.out.println("5. Price");
            System.out.println("6. Broadcast period");
            System.out.println("7. Category");
            sys=load.nextInt();
            load.nextLine();
            if (sys >=1 && sys <=7){
                continuity=false;
            } else {
                System.out.println("Invalid option");
            }
        }
        if (sys==3){
            update=updateCalendar();
        } else if(sys==6){
            update= (""+ broadcastPeriod());
        } else if (sys==7){
            update= (""+ magazineCategory());
        } else {
            System.out.println("Enter the new value");
            update=load.nextLine();
        }
        System.out.println("\n"+ rdx.modifyProd(id, sys, update));
    }

    public static String updateCalendar(){
        String update="";
        System.out.println("Enter the new day");
        update=load.nextLine();
        update+="-";
        System.out.println("Enter the new month");
        update+=load.nextLine();
        update+="-";
        System.out.println("Enter the new year");
        update+=load.nextLine();
        return update;
    }

    public static void deleteProduct(){
        String id;
        System.out.println("Enter the id of the product you want to delete");
        id=load.nextLine();
        System.out.println("\n"+ rdx.deleteProduct(id));
    }

    public static void buyBook(){
        String idProd;
        String idUser;
        int sys=0;
        boolean continuity=true;
        System.out.println("Enter the id of the user who wants to buy the book");
        idUser=load.nextLine();
        while (continuity){
            System.out.println("1. Buy book by Id");
            System.out.println("2. Show available books");
            sys=load.nextInt();
            load.nextLine();
            switch(sys){
                case 1:
                    System.out.println("Enter the id of the book you want to buy");
                    idProd=load.nextLine();
                    System.out.println("\n"+ rdx.buyBook(idUser,idProd));
                    continuity=false;
                    break;
                case 2:
                    System.out.println("\n"+ rdx.showAvailableBooks());
                    System.out.println("Enter the id of the book you want to buy");
                    idProd=load.nextLine();
                    System.out.println("\n"+ rdx.buyBook(idUser,idProd));
                    continuity=false;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

    public static void suscribeMagazine(){
        String idProd;
        String idUser;
        int sys=0;
        boolean continuity=true;
        System.out.println("Enter the id of the user who wants to suscribe to the magazine");
        idUser=load.nextLine();
        while (continuity){
            System.out.println("1. Suscribe to magazine by Id");
            System.out.println("2. Show available magazines");
            sys=load.nextInt();
            load.nextLine();
            switch(sys){
                case 1:
                    System.out.println("Enter the id of the magazine you want to suscribe");
                    idProd=load.nextLine();
                    System.out.println("\n"+ rdx.suscribeMagazine(idUser,idProd));
                    continuity=false;
                    break;
                case 2:
                    System.out.println("\n"+ rdx.showAvailableMagazines());
                    System.out.println("Enter the id of the magazine you want to suscribe");
                    idProd=load.nextLine();
                    System.out.println("\n"+ rdx.suscribeMagazine(idUser,idProd));
                    continuity=false;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

    public static void enterReadingSesion(){
        String idProd;
        String idUser;
        int page;
        String message;
        System.out.println("Enter the id of the user who wants to enter the reading session");
        idUser=load.nextLine();
        System.out.println("Enter the id of the product you want to read"); 
        idProd=load.nextLine();
        System.out.println("If you know the page you want to read, enter it, if not, enter 0");
        page=load.nextInt();
        load.nextLine();
        page--;
        message=rdx.enterReadingSesion(idUser,idProd, page);
        if (rdx.possibleReading(message)){
            readingMenu(idUser, idProd, page);
        } else {
            System.out.println("\n"+ message);
        }
    }

    public static void readingMenu(String idUser, String idProd, int page){
        String option;
        boolean continuity=true;
        while (continuity){
            System.out.println(rdx.enterReadingSesion(idUser,idProd, page) + "\n");
            System.out.println("Press N to go to the next page");
            System.out.println("Press P to go to the previous page");
            System.out.println("Press L to go to the library");
            System.out.println("Press E to exit the reading session");
            option=load.nextLine();
            option=option.toUpperCase();
            switch(option){
                case "N":
                    nextReadingPage(idUser, idProd, page);
                    break;
                case "P":
                    previousReadingPage(idUser, idProd, page);
                    break;
                case "L":
                    continuity=false;
                    break;
                case "E":
                    continuity=false;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

    public static void nextReadingPage(String idUser, String idProd, int page){
        rdx.modReadingPage(idUser, idProd, page,1);
    }

    public static void previousReadingPage(String idUser, String idProd, int page){
        rdx.modReadingPage(idUser, idProd, page,0);
    }

    public static void showPersonalLibrary(){
        String idUser;
        String message;
        System.out.println("Enter the id of the user whose library you want to see");
        idUser=load.nextLine();
        message=rdx.showPersonalLibrary(idUser,0);
        if (rdx.validLibrary(message)){            
            
        }
    }

    public static void libraryMenu(String idUser){
        String option;
        int pageCount=0;
        int totalPages=rdx.libraryPages(idUser);
        boolean continuity=true;
        while(continuity){
            System.out.println(rdx.showPersonalLibrary(idUser, pageCount) + "\n");
            System.out.println("Press C, to type XY coordenate of the product you want to read");
            System.out.println("Press I, to type the ID of the product you want to read");
            System.out.println("Press N to go to the next library page");
            System.out.println("Press P to go to the previous library page");
            System.out.println("Press E to exit the library");
            option=load.nextLine();
            option=option.toUpperCase();
            switch(option){
                case "C":
                    break;
                case "I":
                    break;
                case "N":
                    pageCount++;
                    if (pageCount>totalPages){
                        pageCount=0;
                    }
                    break;
                case "P":
                    pageCount--;
                    if (pageCount<0){
                        pageCount=totalPages;
                    }
                    break;
                case "E":
                    continuity=false;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }
}

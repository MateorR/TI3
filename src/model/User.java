package model;
import java.util.Calendar;
import java.util.ArrayList;

public abstract class User {
    // Attributes
    private String name;
    private String id;
    private Calendar linkageDate;

    // Relations
    protected ArrayList<LibraryPage> library;

    // Methods
    public User(String name, String id) {
        this.name = name;
        this.id = id;
        library= new ArrayList<LibraryPage>();
        library.add(new LibraryPage());
        linkageDate = Calendar.getInstance();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public Calendar getLinkageDate() {
        return linkageDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id; 
    }

    public abstract String buy(LibraryItem item);

    public abstract String read(int page, String id);

    public int isInLibrary(String id){
        int pos=0;
        boolean valid=true;
        for(int i=0; i<library.size() && valid;i++){
            if(library.get(i).isInLibrary(id)!=0){
                pos=library.get(i).isInLibrary(id);
                valid=false;
            }
        }
        return pos;
    }

    public int pageOfReading(String id){
        int page=-1;
        boolean valid=true;
        for(int i=0; i<library.size() && valid;i++){
            if(library.get(i).isInLibrary(id)!=0){
                page=i;
                valid=false;
            }
        }
        return page;
    }
    
    public void modReadingPage(String id, int LibPage, int nextPrev){
        int pos=isInLibrary(id);
        int realPage=pageOfReading(id);
        if (LibPage==-1){
            LibPage=realPage;
        }
        library.get(LibPage).modReadingPage(pos, nextPrev);
    }

    public String showPersonalLibrary(int libPage){
        String msg="";
        msg=library.get(libPage).pageDisplay();
        return msg;
    }
    
    public int libraryPages(){
        return library.size();
    }
}

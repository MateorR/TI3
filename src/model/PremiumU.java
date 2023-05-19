package model;

public class PremiumU extends User {
    // Relations
    private PaymentStatus paymentStatus;

    // Methods
    public PremiumU(String name, String id, PaymentStatus paymentStatus) {
        super(name, id);
        this.paymentStatus = paymentStatus;
    }

    public String buy(LibraryItem item){
        String msg="";
        boolean valid=true;
        for(int i=0;i<library.size() && valid;i++){
            if(library.get(i).checkAvailablility()==true){
                msg=library.get(i).addLibraryItem(item);
                valid=false;
            }
        }
        if (msg.equals("")){
            library.add(new LibraryPage());
            msg=library.get(library.size()-1).addLibraryItem(item);
        }
        return msg;
    }

    public String read(int page, String id){
        String msg= "";
        int pos=isInLibrary(id);
        if (page==-1){
            page=pageOfReading(id);
        }
        if (pos==0){
            msg="You don't have this item in your library";
        } else {
            msg= "-------------------------------------------- \n";
            msg+= "Active Reading sesion: \n";
            msg+= library.get(page).readingInfo(pos) + "\n";
        }
        return msg;
    }
    
}

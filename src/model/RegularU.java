package model;

public class RegularU extends User implements Advertaisable {
    // Attributes
    private int maxBooks;
    private int maxMagazines;

    // Methods
    public RegularU(String name, String id, int maxBooks, int maxMagazines) {
        super(name, id);
        this.maxBooks = maxBooks;
        this.maxMagazines = maxMagazines;
    }


    public String buy(LibraryItem item){
        String msg="";
        if(item.getProd() instanceof Book){
            if(maxBooks>0){
                msg=trueBuy(item);
            } else if (maxBooks==0){
                msg="You have reached the maximum number of books";
            }
        } else if(item.getProd() instanceof Magazine){
            if(maxMagazines>0){
                msg=trueBuy(item);
            } else if (maxMagazines==0){
                msg="You have reached the maximum number of magazines";
            }
        }
        return msg;
    }
    
    public String trueBuy(LibraryItem item){
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
        if(item.getProd() instanceof Book){
            maxBooks--;
        } else if(item.getProd() instanceof Magazine){
            maxMagazines--;
        }
        return msg;   
    }

    public String read(int page, String id){
        String msg= "";
        int genAdProbability= (int)(Math.random()*5);
        int in= (int)(Math.random()*4);
        int pos=isInLibrary(id);
        if (page==-1){
            page=pageOfReading(id);
        }
        if (pos==0){
            msg="You don't have this item in your library";
        } else if (genAdProbability==in){
            msg=adsDisplay();
            library.get(page).modReadingPage(pos, 0);
        } else{
            msg= "-------------------------------------------- \n";
            msg+= "Active Reading sesion: \n";
            msg+= library.get(page).readingInfo(pos) + "\n";
        }
        return msg;
    }

    public String adsDisplay(){
        String ad = ADS[(int)(Math.random()*ADS.length)];
        return ad;
    }

    
}

package model;

public class LibraryPage {
    // Attributes
    private LibraryItem[][] items;

    // Methods
    public LibraryPage() {
        items = new LibraryItem[5][5];
    }

    public LibraryItem[][] getItems() {
        return items;
    }

    public void setItems(LibraryItem[][] items) {
        this.items = items;
    }

    public String addLibraryItem(LibraryItem item){
        String msg="";
        boolean valid=false;
        for(int i=0;i<items.length && valid;i++){
            for(int j=0;j<items[0].length && valid;j++){
                if(items[i][j]==null){
                    items[i][j]=item;
                    msg="Product added";
                    valid=false;
                }
            }
        }
        return msg;
    }

    public boolean checkAvailablility(){
        boolean available=false;
        boolean valid = true;
        for(int i=0;i<items.length && valid;i++){
            for(int j=0;j<items[0].length && valid;j++){
                if(items[i][j]==null){
                    available=true;
                    valid=false;
                }
            }
        }
        return available;
    }

    public String pageDisplay(){
        boolean valid=true;
        String msg="--------------------------------" + "\n";
        msg+= "      " + 0 + "  |  " + 1 + "  |  " + 2 + "  |  " + 3 + "  |  " + 4 + " \n";
        for(int i=0;i<items.length && valid;i++){
            msg+= i;
            for(int j=0;j<items[0].length && valid;j++){
                if(items[i][j]!=null){
                    msg+=" | " +items[i][j].getProd().getId();
                } else {
                    msg+=" | " + "---";
                }
            }
            msg+="\n";
        }
        return msg;
    }

    public int isInLibrary(String id){
        int pos=0;
        boolean valid=true;
        for(int i=0;i<items.length && valid;i++){
            for(int j=0;j<items[0].length && valid;j++){
                if(items[i][j]!=null){
                    if(items[i][j].validateItemId(id)){
                        pos=i*10+j;
                        valid=false;
                    }
                }
            }
        }
        return pos;
    }

    public String readingInfo(int pos){
        String msg="";
        msg=items[(int) Math.floor(pos/10)][pos%10].readingInfo();
        return msg;
    }

    public void modReadingPage(int pos, int nextPrev){
        if (nextPrev==0){
            items[(int) Math.floor(pos/10)][pos%10].previousReadingPage();
        } else if (nextPrev==1){
            items[(int) Math.floor(pos/10)][pos%10].nextReadingPage();
        }
    }
}

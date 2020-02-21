package com.company;

public class Book {
    int score;
    boolean scanned;
    int ID;

    public void setID(int ID){
        this.ID = ID;
    }

    public Book(int score) {
        this.score = score;
        this.scanned = false;
    }
    public void setStatus(boolean status){
        this.scanned = status;
    }

   public boolean getStatus(){
        return this.scanned;
   }

    public void setScanned(boolean scanned){
        this.scanned = scanned;
    }

    public int getScore(){
        return this.score;
    }

    @Override
    public String toString() {
        return "Book{" +
                "score=" + score +
                ", scanned=" + scanned +
                '}';
    }
}

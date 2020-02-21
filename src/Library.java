package com.company;

import java.util.ArrayList;

public class Library {
    int ID;
    int number_of_books;
    int bringup_time;
    int books_per_day;
    ArrayList<Book> books;
    double priority;
    ArrayList<Book> scannedBooks;

    public void setID(int ID){
        this.ID = ID;
    }


    public Library(int number_of_books, int bringup_time, int books_per_day ) {
        this.number_of_books = number_of_books;
        this.bringup_time = bringup_time;
        this.books_per_day = books_per_day;
        this.books = new ArrayList<>();
        this.priority = 0;
        this.scannedBooks = new ArrayList<>();
    }

    public void  addBook(Book book){
        this.books.add(book);
    }

    public int getTotalScore(){
        int totalScore = 0;
        for (Book book: this.books) {
            totalScore += book.getScore();
        }
        return ( totalScore / books_per_day );
    }

    public double getPriority(int totalDays){
        int totalScore = this.getTotalScore();
        return  ( (double) totalScore / books_per_day ) * (totalDays - this.bringup_time);
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }

    public ArrayList<Book> getUnscannedBooks(){
        ArrayList<Book> unscannedBooks = new ArrayList<>();
        for (Book book: this.books) {
            if(book.getStatus() == false) {
                unscannedBooks.add(book);
            }
        }
        return unscannedBooks;
    }


    @Override
    public String toString() {
        return "Library{" +
                " priority=" + priority +
                ", number_of_books=" + number_of_books +
                ", bringup_time=" + bringup_time +
                ", books_per_day=" + books_per_day +
                ", books=" + books +
                '}';
    }

    public ArrayList<Book> getBooks (){
        return this.books;
    }

    public double getPriority()
    {
        return this.priority;
    }

    public void setBooks(ArrayList<Book> books){
       this.books = books;
    }
}

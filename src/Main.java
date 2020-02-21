package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    private static class LibraryComparator implements Comparator<Library> {

        @Override
        public int compare(Library o1, Library o2) {
            //return o1.getPriority() - o2.getPriority();
            if(o1.getPriority() - o2.getPriority() <0)
            {
                return 1;
            }
            else if(o1.getPriority() - o2.getPriority() > 0)
            {
                return -1;
            }
            else return 0;

        }
    }
    private static class BookComparator implements Comparator<Book> {

        @Override
        public int compare(Book o1, Book o2) {
            return o2.getScore() - o1.getScore();
        }
    }
    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\iulia\\OneDrive\\Desktop\\example\\a_example.txt";
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        String line =null;

        int number_of_library_books;
        int number_of_library_days;
        int books_per_day;
        int book_score;
        ArrayList<Book> books = new ArrayList<>();
        ArrayList<Library> libraries = new ArrayList<>();
        Book book;

        line = scanner.nextLine();
        StringTokenizer st = new StringTokenizer(line, " ");

        int number_of_books = Integer.parseInt((String)st.nextElement());
        int number_of_libraries = Integer.parseInt((String)st.nextElement());
        int number_of_days = Integer.parseInt((String)st.nextElement());

        System.out.println(number_of_books);
        System.out.println(number_of_libraries);
        System.out.println(number_of_days);

        line = scanner.nextLine();
        st = new StringTokenizer(line, " ");
        for(int i = 0; i < number_of_books; i++){
            book_score = Integer.parseInt((String) st.nextElement());
            book = new Book(book_score);
            books.add(book);
            book.setID(books.indexOf(book));
        }

        for (Book b: books) {
            System.out.print(b.toString() + " ");
        }
        System.out.println();

        Library newLibrary;
        int id_of_book;
        int libind = 0;
        for(int i = 0; i < number_of_libraries; i ++) {
            line = scanner.nextLine();
            st = new StringTokenizer(line, " ");
            number_of_library_books = Integer.parseInt((String)st.nextElement());
            number_of_library_days = Integer.parseInt((String)st.nextElement());
            books_per_day = Integer.parseInt((String)st.nextElement());
            newLibrary = new Library(number_of_library_books, number_of_library_days, books_per_day);

            line = scanner.nextLine();
            st = new StringTokenizer(line, " ");
            for(int j = 0; j < number_of_library_books; j++) {
                id_of_book = Integer.parseInt((String)st.nextElement());
               newLibrary.addBook(books.get(id_of_book));
            }

            //System.out.println(newLibrary.toString());
            Collections.sort(newLibrary.getBooks(), new BookComparator());
            libraries.add(newLibrary);
            newLibrary.setID(libind);
            libind++;
        }

        ArrayList<Library> days = new ArrayList<>();


        for (Library libr : libraries) {
            System.out.println(libr.toString());
        }

        int counterOfLibraries = 0;
        int days_change = number_of_days;
        ArrayList<Book> unscannedBooks;
        ArrayList<Book> allBooks;

        ArrayList<Library> queuedLibs = new ArrayList<>();
        ArrayList<Book> scannedBooks = new ArrayList<>();
        System.out.println();
        do {
            for(Library libr : libraries) {
                libr.setPriority(libr.getPriority(days_change));
            }

            Collections.sort(libraries, new LibraryComparator());

            /*
             * Do stuff to check if can be queued
             */
            unscannedBooks = libraries.get(0).getUnscannedBooks();
            System.out.println(unscannedBooks.size());
            if(unscannedBooks.size() < days_change * libraries.get(0).books_per_day) {
                for (Book unscb:unscannedBooks) {
                   unscb.setStatus(true);
                   libraries.get(0).scannedBooks.add(unscb);
                    System.out.println(unscb.toString() + " !!!!!");
                    for (Library lib: libraries) {
                        allBooks = lib.getBooks();
                        if(allBooks.contains(unscb)) {
                            allBooks.get(allBooks.indexOf(unscb)).setStatus(true);
                        }
                    }
                }
            }
            else{
                for(int j = 0; j < days_change * libraries.get(0).books_per_day; j++) {
                    Book unscb = unscannedBooks.get(j);
                    unscb.setStatus(true);
                    libraries.get(0).scannedBooks.add(unscb);
                    System.out.println(unscb.toString() + "????");
                    for (Library lib: libraries) {
                        allBooks = lib.getBooks();
                        if(allBooks.contains(unscb)) {
                            allBooks.get(allBooks.indexOf(unscb)).setStatus(true);
                        }
                    }
                }
            }
            days_change -= libraries.get(0).bringup_time;

            System.out.println(libraries.get(0).toString());
            queuedLibs.add(libraries.get(0));
            System.out.println(libraries.get(0).scannedBooks.size());

            libraries.remove(libraries.get(0));
            counterOfLibraries++;

        }while(days_change >= 0 && libraries.size() !=0);

        FileWriter flr = new FileWriter("C:\\Users\\iulia\\OneDrive\\Desktop\\out.txt");
        flr.write(new Integer (queuedLibs.size()).toString() );
        flr.write("\r\n");
        for (Library l : queuedLibs) {
            flr.write(new Integer (l.ID).toString() + " " );
            flr.write(new Integer (l.scannedBooks.size()).toString() );
            flr.write("\r\n");
            for(Book b : l.scannedBooks) {
                flr.write(new Integer (b.ID).toString() + " " );
            }
            flr.write("\r\n");
        }
        flr.close();

    }
}
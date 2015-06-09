import test.Book;

import java.util.ArrayList;

/**
 * Created by João on 11/05/2015.
 */
public class Test {


    public static void main(String[] args) {
        /* Comment */


        ArrayList<Book> books = new ArrayList<Book>();

        books.add(new Book("Scott Fitzgerald","The Great Gatsby",1));
        books.add(new Book("John Steinbeck","The Grapes of Wrath",2));
        books.add(new Book("George Orwell","Nineteen Eighty-Four",3));


        ArrayList<Object> selectTo = new ArrayList<Object>();
        int a[] , b;

        /*@jQ
        in books;

        out selectTo;

        selectTo = $("books[title~='Great']|[author*='beck']&[id$='our']");

        */

        System.out.println(selectTo);
    }
}
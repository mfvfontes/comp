package test;

/**
 * Created by João on 09/06/2015.
 */
public class Book {

    public String author;
    public String title;
    public Integer id;

    public Book(String author, String title) {
        this.author = author;
        this.title = title;
    }

    public Book(String author, String title, Integer id) {
        this.author = author;
        this.title = title;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\tAuthor: " + author;
    }
}

package model;

public class Book {
    private int bookid;
    private String bookname;
    private String author;

    private boolean migrated;

    public int getBookid() {
        return bookid;
    }

    public String getAuthor() {
        return author;
    }

    public String getBookname() {
        return bookname;
    }

    @Override
    public String toString() {
        return "Obtained a book with id " + bookid + " titled " + bookname + " written by " + author;
    }
}

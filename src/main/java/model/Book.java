package model;

public class Book {
    private int bookId;
    private String bookName;
    private String author;

    public int getBookId() {
        return bookId;
    }

    public String getAuthor() {
        return author;
    }

    public String getBookName() {
        return bookName;
    }

    @Override
    public String toString() {
        return "Obtained a book with id " + bookId + " titled " + bookName + " written by " + author;
    }
}

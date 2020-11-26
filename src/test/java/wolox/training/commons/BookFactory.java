package wolox.training.commons;

import wolox.training.models.Book;

public class BookFactory {

    public static Book getBook() {
        Book mockBook = new Book();
        mockBook.setAuthor("Stephen King");
        mockBook.setGenre("Terror");
        mockBook.setImage("image2.pgn");
        mockBook.setIsbn("45788865");
        mockBook.setPages(200);
        mockBook.setPublisher("Viking Press");
        mockBook.setSubtitle("-");
        mockBook.setTitle("It");
        mockBook.setYear("1986");
        return mockBook;
    }
}

package wolox.training.dto;

import java.util.List;

public class BookDTO {

    public BookDTO() {
    }

    private String isbn;
    private String title;
    private String subtitle;
    private List<PublishersDTO> publishers;
    private String publish_date;
    private Integer number_of_pages;
    private List<AuthorDTO> authors;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public List<PublishersDTO> getPublishers() {
        return publishers;
    }

    public void setPublishers(List<PublishersDTO> publishers) {
        this.publishers = publishers;
    }

    public String getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(String publish_date) {
        this.publish_date = publish_date;
    }

    public Integer getNumber_of_pages() {
        return number_of_pages;
    }

    public void setNumber_of_pages(Integer number_of_pages) {
        this.number_of_pages = number_of_pages;
    }

    public List<AuthorDTO> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorDTO> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", publishers='" + publishers + '\'' +
                ", publish_date='" + publish_date + '\'' +
                ", pages=" + number_of_pages +
                ", authors=" + authors +
                '}';
    }
}

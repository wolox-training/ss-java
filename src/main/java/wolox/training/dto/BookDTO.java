package wolox.training.dto;

import java.util.List;

public class BookDTO {

    public BookDTO() {
    }

    private String isbn;
    private String title;
    private String subtitle;
    private List<PublishersDTO> publishers;
    private String publishDate;
    private Integer pages;
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

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
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
                ", publish_date='" + publishDate + '\'' +
                ", pages=" + pages +
                ", authors=" + authors +
                '}';
    }
}


package wolox.training.integration.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Book {

    private List<Publisher> publishers = null;
    private String pagination;
    private Identifiers identifiers;
    private String subtitle;
    private String title;
    private String url;
    @JsonProperty("number_of_pages")
    private Integer numberOfPages;
    private Cover cover;
    private List<Subject> subjects = null;
    @JsonProperty("publish_date")
    private String publishDate;
    private String key;
    private List<Author> authors = null;
    private Classifications classifications;
    @JsonProperty("publish_places")
    private List<PublishPlace> publishPlaces = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<Publisher> getPublishers() {
        return publishers;
    }

    public void setPublishers(List<Publisher> publishers) {
        this.publishers = publishers;
    }

    public String getPagination() {
        return pagination;
    }

    public void setPagination(String pagination) {
        this.pagination = pagination;
    }

    public Identifiers getIdentifiers() {
        return identifiers;
    }

    public void setIdentifiers(Identifiers identifiers) {
        this.identifiers = identifiers;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("number_of_pages")
    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    @JsonProperty("number_of_pages")
    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Cover getCover() {
        return cover;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    @JsonProperty("publish_date")
    public String getPublishDate() {
        return publishDate;
    }

    @JsonProperty("publish_date")
    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public Classifications getClassifications() {
        return classifications;
    }

    public void setClassifications(Classifications classifications) {
        this.classifications = classifications;
    }

    @JsonProperty("publish_places")
    public List<PublishPlace> getPublishPlaces() {
        return publishPlaces;
    }

    @JsonProperty("publish_places")
    public void setPublishPlaces(List<PublishPlace> publishPlaces) {
        this.publishPlaces = publishPlaces;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

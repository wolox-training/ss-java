
package wolox.training.integration.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "publishers",
    "pagination",
    "identifiers",
    "subtitle",
    "title",
    "url",
    "number_of_pages",
    "cover",
    "subjects",
    "publish_date",
    "key",
    "authors",
    "classifications",
    "publish_places"
})
public class Book {

    @JsonProperty("publishers")
    private List<Publisher> publishers = null;
    @JsonProperty("pagination")
    private String pagination;
    @JsonProperty("identifiers")
    private Identifiers identifiers;
    @JsonProperty("subtitle")
    private String subtitle;
    @JsonProperty("title")
    private String title;
    @JsonProperty("url")
    private String url;
    @JsonProperty("number_of_pages")
    private Integer numberOfPages;
    @JsonProperty("cover")
    private Cover cover;
    @JsonProperty("subjects")
    private List<Subject> subjects = null;
    @JsonProperty("publish_date")
    private String publishDate;
    @JsonProperty("key")
    private String key;
    @JsonProperty("authors")
    private List<Author> authors = null;
    @JsonProperty("classifications")
    private Classifications classifications;
    @JsonProperty("publish_places")
    private List<PublishPlace> publishPlaces = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("publishers")
    public List<Publisher> getPublishers() {
        return publishers;
    }

    @JsonProperty("publishers")
    public void setPublishers(List<Publisher> publishers) {
        this.publishers = publishers;
    }

    @JsonProperty("pagination")
    public String getPagination() {
        return pagination;
    }

    @JsonProperty("pagination")
    public void setPagination(String pagination) {
        this.pagination = pagination;
    }

    @JsonProperty("identifiers")
    public Identifiers getIdentifiers() {
        return identifiers;
    }

    @JsonProperty("identifiers")
    public void setIdentifiers(Identifiers identifiers) {
        this.identifiers = identifiers;
    }

    @JsonProperty("subtitle")
    public String getSubtitle() {
        return subtitle;
    }

    @JsonProperty("subtitle")
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
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

    @JsonProperty("cover")
    public Cover getCover() {
        return cover;
    }

    @JsonProperty("cover")
    public void setCover(Cover cover) {
        this.cover = cover;
    }

    @JsonProperty("subjects")
    public List<Subject> getSubjects() {
        return subjects;
    }

    @JsonProperty("subjects")
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

    @JsonProperty("key")
    public String getKey() {
        return key;
    }

    @JsonProperty("key")
    public void setKey(String key) {
        this.key = key;
    }

    @JsonProperty("authors")
    public List<Author> getAuthors() {
        return authors;
    }

    @JsonProperty("authors")
    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @JsonProperty("classifications")
    public Classifications getClassifications() {
        return classifications;
    }

    @JsonProperty("classifications")
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

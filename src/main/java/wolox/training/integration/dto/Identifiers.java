
package wolox.training.integration.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "lccn",
    "openlibrary",
    "isbn_10",
    "librarything",
    "goodreads"
})
public class Identifiers {

    @JsonProperty("lccn")
    private List<String> lccn = null;
    @JsonProperty("openlibrary")
    private List<String> openlibrary = null;
    @JsonProperty("isbn_10")
    private List<String> isbn10 = null;
    @JsonProperty("librarything")
    private List<String> librarything = null;
    @JsonProperty("goodreads")
    private List<String> goodreads = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("lccn")
    public List<String> getLccn() {
        return lccn;
    }

    @JsonProperty("lccn")
    public void setLccn(List<String> lccn) {
        this.lccn = lccn;
    }

    @JsonProperty("openlibrary")
    public List<String> getOpenlibrary() {
        return openlibrary;
    }

    @JsonProperty("openlibrary")
    public void setOpenlibrary(List<String> openlibrary) {
        this.openlibrary = openlibrary;
    }

    @JsonProperty("isbn_10")
    public List<String> getIsbn10() {
        return isbn10;
    }

    @JsonProperty("isbn_10")
    public void setIsbn10(List<String> isbn10) {
        this.isbn10 = isbn10;
    }

    @JsonProperty("librarything")
    public List<String> getLibrarything() {
        return librarything;
    }

    @JsonProperty("librarything")
    public void setLibrarything(List<String> librarything) {
        this.librarything = librarything;
    }

    @JsonProperty("goodreads")
    public List<String> getGoodreads() {
        return goodreads;
    }

    @JsonProperty("goodreads")
    public void setGoodreads(List<String> goodreads) {
        this.goodreads = goodreads;
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

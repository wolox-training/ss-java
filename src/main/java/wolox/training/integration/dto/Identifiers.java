
package wolox.training.integration.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Identifiers {

    private List<String> lccn = null;
    private List<String> openlibrary = null;
    private List<String> isbn10 = null;
    private List<String> librarything = null;
    private List<String> goodreads = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<String> getLccn() {
        return lccn;
    }

    public void setLccn(List<String> lccn) {
        this.lccn = lccn;
    }

    public List<String> getOpenlibrary() {
        return openlibrary;
    }

    public void setOpenlibrary(List<String> openlibrary) {
        this.openlibrary = openlibrary;
    }

    public List<String> getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(List<String> isbn10) {
        this.isbn10 = isbn10;
    }

    public List<String> getLibrarything() {
        return librarything;
    }

    public void setLibrarything(List<String> librarything) {
        this.librarything = librarything;
    }

    public List<String> getGoodreads() {
        return goodreads;
    }

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

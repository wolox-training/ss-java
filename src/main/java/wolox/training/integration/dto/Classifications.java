
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
    "dewey_decimal_class",
    "lc_classifications"
})
public class Classifications {

    @JsonProperty("dewey_decimal_class")
    private List<String> deweyDecimalClass = null;
    @JsonProperty("lc_classifications")
    private List<String> lcClassifications = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("dewey_decimal_class")
    public List<String> getDeweyDecimalClass() {
        return deweyDecimalClass;
    }

    @JsonProperty("dewey_decimal_class")
    public void setDeweyDecimalClass(List<String> deweyDecimalClass) {
        this.deweyDecimalClass = deweyDecimalClass;
    }

    @JsonProperty("lc_classifications")
    public List<String> getLcClassifications() {
        return lcClassifications;
    }

    @JsonProperty("lc_classifications")
    public void setLcClassifications(List<String> lcClassifications) {
        this.lcClassifications = lcClassifications;
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

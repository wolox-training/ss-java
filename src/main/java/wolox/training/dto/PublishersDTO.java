package wolox.training.dto;

public class PublishersDTO {

    public PublishersDTO() {
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PublishersDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}

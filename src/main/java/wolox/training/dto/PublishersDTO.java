package wolox.training.dto;

public class PublishersDTO {

    private String name;

    public PublishersDTO() {
    }

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

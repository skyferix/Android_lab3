package elements;

public class Course {
    private String id;
    private String name;
    private String owner;
    private String startDate;

    public Course(String id, String name, String owner, String startDate) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.startDate = startDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}

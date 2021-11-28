package elements;

public class Course {
    private int id;
    private String name;
    private String owner;
    private String startDate;

    public Course(int id, String name, String owner, String startDate) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.startDate = startDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

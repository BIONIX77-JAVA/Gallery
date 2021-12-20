package alex.falendysh.epam_exhibition.model;

/**
 * ExhibitionHall.java
 * This is a model class represents an ExhibitionHall entity
 * @author Oleksandr Falendysh
 *
 */
public class ExhibitionHall {

    private int id;
    private String name;
    private int capacity;
    private int allowableNumberOfVisitorsPerDay;

    public ExhibitionHall() {
    }

    public ExhibitionHall(String name, int capacity, int allowableNumberOfVisitorsPerDay) {
        this.name = name;
        this.capacity = capacity;
        this.allowableNumberOfVisitorsPerDay = allowableNumberOfVisitorsPerDay;
    }

    public ExhibitionHall(int id, String name) {
        this.id = id;
        this.name = name;

    }
    public ExhibitionHall(int id, String name, int capacity, int allowableNumberOfVisitorsPerDay) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.allowableNumberOfVisitorsPerDay = allowableNumberOfVisitorsPerDay;
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

    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getAllowableNumberOfVisitorsPerDay() {
        return allowableNumberOfVisitorsPerDay;
    }

    public void setAllowableNumberOfVisitorsPerDay(int allowableNumberOfVisitorsPerDay) {
        this.allowableNumberOfVisitorsPerDay = allowableNumberOfVisitorsPerDay;
    }

    @Override
    public String toString() {
        return "ExhibitionHall{" +
                "id=" + id +
                ", capacity='" + capacity + '\'' +
                ", name='" + name + '\'' +
                ", numberOfVisitorsPerDay=" + allowableNumberOfVisitorsPerDay +
                '}';
    }
}

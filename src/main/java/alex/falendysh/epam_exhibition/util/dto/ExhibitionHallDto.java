package alex.falendysh.epam_exhibition.util.dto;

public class ExhibitionHallDto {
    private int id;
    private String name;
    private int allowableNumberOfVisitorsPerDay;

    public ExhibitionHallDto() {
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

    public int getAllowableNumberOfVisitorsPerDay() {
        return allowableNumberOfVisitorsPerDay;
    }

    public void setAllowableNumberOfVisitorsPerDay(int allowableNumberOfVisitorsPerDay) {
        this.allowableNumberOfVisitorsPerDay = allowableNumberOfVisitorsPerDay;
    }

    @Override
    public String toString() {
        return "ExhibitionHallDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numberOfVisitorsPerDay=" + allowableNumberOfVisitorsPerDay +
                '}';
    }
}

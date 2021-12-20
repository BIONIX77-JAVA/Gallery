package alex.falendysh.epam_exhibition.util.dto;

import java.time.LocalDate;

public class ExhibitionDto {
    private int id;
    private String name;
    private String description;
    private LocalDate beginDate;
    private LocalDate endDate;
    private int fullTicketPrice;
    private int exhibitionHallId;

    public ExhibitionDto() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getFullTicketPrice() {
        return fullTicketPrice;
    }

    public void setFullTicketPrice(int fullTicketPrice) {
        this.fullTicketPrice = fullTicketPrice;
    }

    public int getExhibitionHallId() {
        return exhibitionHallId;
    }

    public void setExhibitionHallId(int exhibitionHallId) {
        this.exhibitionHallId = exhibitionHallId;
    }

    @Override
    public String toString() {
        return "ExhibitionDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", fullTicketPrice=" + fullTicketPrice +
                ", exhibitionHallId=" + exhibitionHallId +
                '}';
    }
}

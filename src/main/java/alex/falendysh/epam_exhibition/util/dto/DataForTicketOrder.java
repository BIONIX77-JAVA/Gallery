package alex.falendysh.epam_exhibition.util.dto;

import java.time.LocalDate;

public class DataForTicketOrder {
    private int idInCart;
    private int exhibitionId;
    private String exhibitionName;
    private int exhibitionHallId;
    private LocalDate wantedVisitDate;
    private int ticketPrice;

    public DataForTicketOrder() {
    }

    public int getIdInCart() {
        return idInCart;
    }

    public void setIdInCart(int idInCart) {
        this.idInCart = idInCart;
    }

    public int getExhibitionId() {
        return exhibitionId;
    }

    public void setExhibitionId(int exhibitionId) {
        this.exhibitionId = exhibitionId;
    }

    public String getExhibitionName() {
        return exhibitionName;
    }

    public void setExhibitionName(String exhibitionName) {
        this.exhibitionName = exhibitionName;
    }

    public int getExhibitionHallId() {
        return exhibitionHallId;
    }

    public void setExhibitionHallId(int exhibitionHallId) {
        this.exhibitionHallId = exhibitionHallId;
    }

    public LocalDate getWantedVisitDate() {
        return wantedVisitDate;
    }

    public void setWantedVisitDate(LocalDate wantedVisitDate) {
        this.wantedVisitDate = wantedVisitDate;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}

package alex.falendysh.epam_exhibition.util.dto;

import alex.falendysh.epam_exhibition.util.enums.TicketType;


import java.time.LocalDate;

public class TicketDto {
    private int id;
    private LocalDate visitDate;
    private LocalDate orderDate;
    private TicketType ticketType;
    private int ticketPrice;
    private int userId;
    private int exhibitionId;
    private boolean isPaid;

    public TicketDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getExhibitionId() {
        return exhibitionId;
    }

    public void setExhibitionId(int exhibitionId) {
        this.exhibitionId = exhibitionId;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    @Override
    public String toString() {
        return "TicketDto{" +
                "id=" + id +
                ", visitDate=" + visitDate +
                ", orderDate=" + orderDate +
                ", ticketType=" + ticketType +
                ", ticketPrice=" + ticketPrice +
                ", userId=" + userId +
                ", exhibitionId=" + exhibitionId +
                ", isPaid=" + isPaid +
                '}';
    }
}

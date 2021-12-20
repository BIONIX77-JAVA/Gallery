package alex.falendysh.epam_exhibition.model;


import alex.falendysh.epam_exhibition.util.enums.TicketType;

import java.time.LocalDate;

/**
 * Ticket.java
 * This is a model class represents a Ticket entity
 * @author Oleksandr Falendysh
 *
 */
public class Ticket {

    private int id;
    private LocalDate visitDate;
    private LocalDate orderDate;
    private TicketType ticketType;
    private int ticketPrice;
    private alex.falendysh.epam_exhibition.model.User user;
    private alex.falendysh.epam_exhibition.model.Exhibition exhibition;
    private boolean isPaid;

    public Ticket() {
    }

    public Ticket(LocalDate visitDate, LocalDate orderDate,
                  TicketType ticketType, int ticketPrice,
                  alex.falendysh.epam_exhibition.model.User user, alex.falendysh.epam_exhibition.model.Exhibition exhibition) {
        this.visitDate = visitDate;
        this.orderDate = orderDate;
        this.ticketType = ticketType;
        this.ticketPrice = ticketPrice;
        this.user = user;
        this.exhibition = exhibition;
    }

    public Ticket(int id, LocalDate visitDate, LocalDate orderDate,
                  TicketType ticketType, int ticketPrice, boolean isPaid,
                  alex.falendysh.epam_exhibition.model.User user, alex.falendysh.epam_exhibition.model.Exhibition exhibition) {
        this.id = id;
        this.visitDate = visitDate;
        this.orderDate = orderDate;
        this.ticketType = ticketType;
        this.ticketPrice = ticketPrice;
        this.user = user;
        this.exhibition = exhibition;
        this.isPaid = isPaid;
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

    public alex.falendysh.epam_exhibition.model.Exhibition getExhibition() {
        return exhibition;
    }

    public void setExhibition(alex.falendysh.epam_exhibition.model.Exhibition exhibition) {
        this.exhibition = exhibition;
    }

    public alex.falendysh.epam_exhibition.model.User getUser() {
        return user;
    }

    public void setUser(alex.falendysh.epam_exhibition.model.User user) {
        this.user = user;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", visitDate=" + visitDate +
                ", orderDate=" + orderDate +
                ", ticketType=" + ticketType +
                ", exhibition=" + exhibition +
                ", visitor=" + user +
                ", isPaid=" + isPaid +
                '}';
    }
}

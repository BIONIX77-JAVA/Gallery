package alex.falendysh.epam_exhibition.dao.interfaces;

import alex.falendysh.epam_exhibition.dao.Dao;
import alex.falendysh.epam_exhibition.exceptions.DaoException;
import alex.falendysh.epam_exhibition.model.Ticket;

import java.time.LocalDate;
import java.util.List;

public interface TicketDao<T extends Ticket> extends Dao<T> {

    List<Ticket> getAllTicketsByUserId(int id) throws DaoException;

    boolean saveListOfTickets(List<Ticket> tickets);

    int countTicketsForTheDate(LocalDate date);

    boolean updateListOfTickets(List<Ticket> tickets);
}

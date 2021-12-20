package alex.falendysh.epam_exhibition.service.interfaces;

import alex.falendysh.epam_exhibition.exceptions.InvalidDataException;
import alex.falendysh.epam_exhibition.exceptions.exhibition.NoSuchExhibitionException;
import alex.falendysh.epam_exhibition.exceptions.ticket.NoSuchTicketException;
import alex.falendysh.epam_exhibition.exceptions.ticket.TicketIsAlreadyPaidException;
import alex.falendysh.epam_exhibition.exceptions.ticket.TicketsRunOutForTheDateException;
import alex.falendysh.epam_exhibition.exceptions.user.NoSuchUserException;
import alex.falendysh.epam_exhibition.service.Service;
import alex.falendysh.epam_exhibition.util.enums.TicketType;

import java.util.List;
import java.util.Optional;

public interface TicketService<T> extends Service<T> {

    List<T> getAllTicketsByUserId(int userId);

    boolean save(T t)
            throws InvalidDataException, TicketsRunOutForTheDateException,
            NoSuchExhibitionException, NoSuchUserException;

    boolean saveListOfTickets(List<T> ticketDtos) throws TicketsRunOutForTheDateException, NoSuchUserException, NoSuchExhibitionException, InvalidDataException;

    int calculateTicketPrice(int fullPrice, TicketType type);

    Optional<T> update(int id, boolean isPaid) throws InvalidDataException, NoSuchTicketException;

    boolean updateListOfTickets(List<T> ts) throws NoSuchTicketException;

    boolean delete(int id) throws TicketIsAlreadyPaidException, NoSuchTicketException;
}

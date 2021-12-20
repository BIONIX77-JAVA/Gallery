package alex.falendysh.epam_exhibition.util.dto.mappers;

import alex.falendysh.epam_exhibition.model.Ticket;
import alex.falendysh.epam_exhibition.util.dto.TicketDto;

public class TicketMapper implements DtoMapper<TicketDto, Ticket> {

    @Override
    public TicketDto mapToDto(Ticket ticket) {
        TicketDto dto = new TicketDto();
        dto.setId(ticket.getId());
        dto.setVisitDate(ticket.getVisitDate());
        dto.setOrderDate(ticket.getOrderDate());
        dto.setTicketType(ticket.getTicketType());
        dto.setTicketPrice(ticket.getTicketPrice());
        dto.setUserId(ticket.getUser().getId());
        dto.setExhibitionId(ticket.getExhibition().getId());
        dto.setPaid(ticket.isPaid());
        return dto;
    }

    @Override
    public Ticket mapFromDto(TicketDto ticketDto) {
        Ticket ticket = new Ticket();
        ticket.setId(ticketDto.getId());
        ticket.setVisitDate(ticketDto.getVisitDate());
        ticket.setOrderDate(ticketDto.getOrderDate());
        ticket.setTicketType(ticketDto.getTicketType());
        ticket.setTicketPrice(ticketDto.getTicketPrice());
        ticket.setPaid(ticketDto.isPaid());
        return ticket;
    }
}

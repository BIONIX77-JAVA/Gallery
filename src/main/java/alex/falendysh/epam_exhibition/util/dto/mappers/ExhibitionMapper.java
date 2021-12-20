package alex.falendysh.epam_exhibition.util.dto.mappers;

import alex.falendysh.epam_exhibition.model.Exhibition;
import alex.falendysh.epam_exhibition.util.dto.ExhibitionDto;

public class ExhibitionMapper implements DtoMapper<ExhibitionDto, Exhibition> {

    @Override
    public ExhibitionDto mapToDto(Exhibition exhibition) {
        ExhibitionDto dto = new ExhibitionDto();
        dto.setId(exhibition.getId());
        dto.setName(exhibition.getName());
        dto.setDescription(exhibition.getDescription());
        dto.setBeginDate(exhibition.getBeginDate());
        dto.setEndDate(exhibition.getEndDate());
        dto.setFullTicketPrice(exhibition.getFullTicketPrice());
        dto.setExhibitionHallId(exhibition.getExhibitionHall().getId());
        return dto;
    }

    @Override
    public Exhibition mapFromDto(ExhibitionDto exhibitionDto) {
        Exhibition exhibition = new Exhibition();
        exhibition.setId(exhibitionDto.getId());
        exhibition.setName(exhibitionDto.getName());
        exhibition.setDescription(exhibitionDto.getDescription());
        exhibition.setBeginDate(exhibitionDto.getBeginDate());
        exhibition.setEndDate(exhibitionDto.getEndDate());
        exhibition.setFullTicketPrice(exhibitionDto.getFullTicketPrice());
        return exhibition;
    }
}

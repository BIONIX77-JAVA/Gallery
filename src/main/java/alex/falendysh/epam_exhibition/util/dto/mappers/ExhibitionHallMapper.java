package alex.falendysh.epam_exhibition.util.dto.mappers;

import alex.falendysh.epam_exhibition.model.ExhibitionHall;
import alex.falendysh.epam_exhibition.util.dto.ExhibitionHallDto;

public class ExhibitionHallMapper implements DtoMapper<ExhibitionHallDto, ExhibitionHall> {

    @Override
    public ExhibitionHallDto mapToDto(ExhibitionHall exhibitionHall) {
        ExhibitionHallDto dto = new ExhibitionHallDto();
        dto.setId(exhibitionHall.getId());
        dto.setName(exhibitionHall.getName());
        dto.setAllowableNumberOfVisitorsPerDay(exhibitionHall.getAllowableNumberOfVisitorsPerDay());
        return dto;
    }

    @Override
    public ExhibitionHall mapFromDto(ExhibitionHallDto exhibitionHallDto) {
        ExhibitionHall exhibitionHall = new ExhibitionHall();
        exhibitionHall.setId(exhibitionHallDto.getId());
        exhibitionHall.setName(exhibitionHallDto.getName());
        exhibitionHall.setAllowableNumberOfVisitorsPerDay(exhibitionHallDto.getAllowableNumberOfVisitorsPerDay());
        return exhibitionHall;
    }
}

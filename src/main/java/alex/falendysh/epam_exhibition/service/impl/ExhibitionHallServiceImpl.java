package alex.falendysh.epam_exhibition.service.impl;

import alex.falendysh.epam_exhibition.dao.interfaces.ExhibitionHallDao;
import alex.falendysh.epam_exhibition.exceptions.SuchExhibitionHallIsAlreadyExistsException;
import alex.falendysh.epam_exhibition.exceptions.dao.DuplicateValueException;
import alex.falendysh.epam_exhibition.model.ExhibitionHall;
import alex.falendysh.epam_exhibition.service.interfaces.ExhibitionHallService;
import alex.falendysh.epam_exhibition.util.dto.ExhibitionHallDto;
import alex.falendysh.epam_exhibition.util.dto.mappers.DtoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ExhibitionHallServiceImpl implements ExhibitionHallService<ExhibitionHallDto> {
    private Logger logger =  LoggerFactory.getLogger(ExhibitionHallServiceImpl.class);

    private ExhibitionHallDao<ExhibitionHall> exhibitionHallDao;
    private DtoMapper<ExhibitionHallDto, ExhibitionHall> dtoMapper;

    public ExhibitionHallServiceImpl(ExhibitionHallDao<ExhibitionHall> exhibitionHallDao,
                                     DtoMapper<ExhibitionHallDto, ExhibitionHall> exhibitionHallDtoMapper) {
        this.exhibitionHallDao = exhibitionHallDao;
        this.dtoMapper = exhibitionHallDtoMapper;
    }

    @Override
    public Optional<ExhibitionHallDto> getById(int id) {
        if (id <= 0) {
            return Optional.empty();
        }
        ExhibitionHallDto exhibitionHallDto = null;
        if (exhibitionHallDao.getById(id).isPresent()) {
            exhibitionHallDto = dtoMapper.mapToDto(exhibitionHallDao.getById(id).get());
        }
        return Optional.ofNullable(exhibitionHallDto);
    }

    @Override
    public Optional<ExhibitionHallDto> getByName(String name) {
        if (name == null || name.isEmpty()) {
            return Optional.empty();
        }
        ExhibitionHallDto exhibitionHallDto = null;
        if (exhibitionHallDao.getByName(name).isPresent()) {
            exhibitionHallDto = dtoMapper.mapToDto(exhibitionHallDao.getByName(name).get());
        }
        return Optional.ofNullable(exhibitionHallDto);
    }

    @Override
    public List<ExhibitionHallDto> getAll() {
        return exhibitionHallDao.getAll().stream()
                .map(exhibitionHall -> dtoMapper.mapToDto(exhibitionHall))
                .collect(Collectors.toList());
    }

    @Override
    public boolean save(ExhibitionHallDto exhibitionHallDto) throws SuchExhibitionHallIsAlreadyExistsException {
        if (exhibitionHallDto.getName() == null || exhibitionHallDto.getName().isEmpty() ||
                exhibitionHallDto.getAllowableNumberOfVisitorsPerDay() < 0) {
            return false;
        }
        ExhibitionHall exhibitionHall = dtoMapper.mapFromDto(exhibitionHallDto);

        try {
            return exhibitionHallDao.save(exhibitionHall);
        } catch (DuplicateValueException e) {
            logger.error("Such exhibition hall is already exists", e);
            throw new SuchExhibitionHallIsAlreadyExistsException();
        }
    }

    @Override
    public Optional<ExhibitionHallDto> update(ExhibitionHallDto exhibitionHallDto) {
        if (exhibitionHallDto.getId() <= 0 || exhibitionHallDto.getName() == null ||
                exhibitionHallDto.getName().isEmpty()||
                exhibitionHallDto.getAllowableNumberOfVisitorsPerDay() < 0) {
            return Optional.empty();
        }
        ExhibitionHall exhibitionHall = dtoMapper.mapFromDto(exhibitionHallDto);
        Optional<ExhibitionHall> exhibitionHallToUpdate = exhibitionHallDao.getById(exhibitionHall.getId());
        ExhibitionHallDto exhibitionHallDtoToReturn = null;
        if (exhibitionHallToUpdate.isPresent() &&
                exhibitionHallDao.update(exhibitionHall).isPresent()) {
            exhibitionHallDtoToReturn = dtoMapper.mapToDto(exhibitionHallDao.update(exhibitionHall).get());
        }
        return Optional.ofNullable(exhibitionHallDtoToReturn);
    }

    @Override
    public boolean delete(int id) {
        if (id <= 0) {
            return false;
        }
        return exhibitionHallDao.delete(id);
    }
}

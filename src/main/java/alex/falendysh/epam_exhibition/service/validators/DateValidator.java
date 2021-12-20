package alex.falendysh.epam_exhibition.service.validators;

import alex.falendysh.epam_exhibition.dao.interfaces.ExhibitionDao;
import alex.falendysh.epam_exhibition.model.Exhibition;


import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class DateValidator {

    public static boolean isDatesBusy(int exhibitionHallId, LocalDate beginDate, LocalDate endDate,
                                      ExhibitionDao<Exhibition> exhibitionDao) {
        List<Boolean> result = new CopyOnWriteArrayList<>();

        exhibitionDao.getAllByExhibitionHallId(exhibitionHallId).forEach(exhibition -> {
            if (isBusy(beginDate, endDate, exhibition) ||
            coverExhibition(beginDate, endDate, exhibition)) {
                result.add(true);
            } else {
                result.add(false);
            }
        });
        return result.contains(true);
    }

    private static boolean isBusy(LocalDate beginDate, LocalDate endDate, Exhibition e) {
        boolean beginDateBusy = isSameOrInRangeOfExhibitionDates(beginDate, e);
        boolean endDateBusy = isSameOrInRangeOfExhibitionDates(endDate, e);

        return (beginDateBusy || endDateBusy);
    }

    private static boolean isSameOrInRangeOfExhibitionDates(LocalDate date, Exhibition e) {
        boolean dateSameAsExhibitionBeginDate = date.isEqual(e.getBeginDate());
        boolean dateSameAsExhibitionEndDate = date.isEqual(e.getEndDate());

        boolean dateBetweenExhibitionDates = date.isAfter(e.getBeginDate())
                && date.isBefore(e.getEndDate());

        return dateSameAsExhibitionBeginDate || dateSameAsExhibitionEndDate || dateBetweenExhibitionDates;
    }

    private static boolean coverExhibition(LocalDate beginDate, LocalDate endDate, Exhibition e) {
        return beginDate.isBefore(e.getBeginDate()) &&
                endDate.isAfter(e.getEndDate());
    }

}

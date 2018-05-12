package ua.hcs.model.comparator;

import ua.hcs.model.WorkPlanRecord;

import java.util.Comparator;

/**
 * Надаэ можливість порівнювати записи плану робіт по id.
 */
public class WorkPlanRecordIdComparator implements Comparator<WorkPlanRecord> {

    @Override
    public int compare(WorkPlanRecord o1, WorkPlanRecord o2) {
        return (int) (o1.getId() - o2.getId());
    }
}

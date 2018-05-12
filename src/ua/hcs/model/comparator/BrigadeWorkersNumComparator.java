package ua.hcs.model.comparator;

import ua.hcs.model.Brigade;

import java.util.Comparator;

/**
 * Надаэ можливість порівнювати бригади по кількості працівників.
 */
public class BrigadeWorkersNumComparator implements Comparator<Brigade> {

    @Override
    public int compare(Brigade o1, Brigade o2) {
        return o1.getWorkersNumber() - o2.getWorkersNumber();
    }
}

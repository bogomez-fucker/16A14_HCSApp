package ua.hcs.model.comparator;

import ua.hcs.model.Request;

import java.util.Comparator;

/**
 * Надаэ можливість порівнювати запити по бажаному часу.
 */
public class RequestPrefTimeComparator implements Comparator<Request> {

    @Override
    public int compare(Request o1, Request o2) {
        return (int) (o1.getWorkPrefferedTime() - o2.getWorkPrefferedTime());
    }
}

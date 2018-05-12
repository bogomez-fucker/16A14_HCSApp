package ua.hcs.model.comparator;

import ua.hcs.model.User;

import java.util.Comparator;

/**
 * Надаэ можливість порівнювати користувачів по їх ролі.
 */
public class UserRoleComparator implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        if (o1.getRole().equals("dispatcher") && o2.getRole().equals("tenant"))
            return 1;
        else if (o1.getRole().equals("tenant") && o2.getRole().equals("dispatcher"))
            return -1;
        else
            return 0;
    }
}

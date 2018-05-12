package ua.hcs.util;

import java.util.Date;

/**
 * Надає швидкий доступ до корисних методів.
 */
public class Util {
    public static long generateId() {
        return new Date().getTime();
    }
}

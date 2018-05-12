package ua.hcs.util;

import java.util.Date;

public class Util {
    public static long generateId() {
        return new Date().getTime();
    }
}

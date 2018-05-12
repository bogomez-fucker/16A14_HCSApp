package ua.hcs.util;

/**
 * Сховище констант додатку.
 */
public class Const {
    public static final String BRIGADES_FILEPATH = "storage/brigades.tsv";
    public static final String REQUESTS_FILEPATH = "storage/requests.tsv";
    public static final String USERS_FILEPATH = "storage/users.tsv";
    public static final String WORKPLAN_FILEPATH = "storage/workplan.tsv";
    public static final int DISPATCHER_FRAME_WIDTH = 550;
    public static final int DISPATCHER_FRAME_HEIGHT = 200;
    public static final Object[] REQUESTS_TABLE_HEADER = {
            "Id", "Ім'я орендара", "Тип робіт", "Масштаб робіт", "Бажаний час",
    };
    public static final Object[] WORK_PLAN_TABLE_HEADER = {
            "Id", "Id запиту", "Ім'я орендара", "Id бригади", "Ім'я бригадира", "Кі-сть робочих"
    };
    public static final int UPDATE_DELAY = 3000;
    public static final String ROLE_TENANT = "tenant";
    public static final String ROLE_DISPATCHER = "dispatcher";
}

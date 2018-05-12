package ua.hcs;

import ua.hcs.view.Authorization;

/**
 * Запускає додаток ЖЕК.
 */
public class HousingAndCommunalServicesApp extends Authorization {
    public static void main(String[] args) {
        Authorization authorization = new Authorization();

        authorization.setVisible(true);
    }
}

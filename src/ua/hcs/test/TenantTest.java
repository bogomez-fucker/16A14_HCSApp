package ua.hcs.test;

import ua.hcs.model.User;
import ua.hcs.view.Authorization;
import ua.hcs.view.Tenant;

/**
 * Тест фрейма орендара.
 */
public class TenantTest {

    public static void main(String[] args) {
        Tenant tenant = new Tenant(new User(666, "admin", "admin", "dispatcher"));

        tenant.setVisible(true);
    }
}

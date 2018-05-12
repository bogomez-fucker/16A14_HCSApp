package ua.hcs.test;

import ua.hcs.model.Brigade;
import ua.hcs.model.Request;
import ua.hcs.model.User;
import ua.hcs.model.WorkPlanRecord;
import ua.hcs.view.Tenant;

/**
 * Тест моделей на підтримку клонування.
 */
public class CloneableTest {
    public static void main(String args[]) {
        User u1 = new User(666, "Petro Petrovych", "qwer", "tenant");
        Brigade b1 = new Brigade(1, "Ivan Petrovych", 10);
        Request r1 = new Request(11, u1.getId(), "Hard work", "Little room", 1.5, false);
        WorkPlanRecord wpr1 = new WorkPlanRecord(111, r1.getId(), b1.getId());

        try {
            User u11 = u1.clone();
            Brigade b11 = b1.clone();
            Request r11 = r1.clone();
            WorkPlanRecord wpr11 = wpr1.clone();

            System.out.println("\nФормат: *1 - оригінальні об'єкти, *11 - клоновані об'єкти\n");
            System.out.println("User u1 == u11 -> " + (u1 == u11) + ", u1.equals(u11) -> " + u1.equals(u11));
            System.out.println("Brigade b1 == b11 -> " + (b1 == b11) + ", b1.equals(b11) -> " + b1.equals(b11));
            System.out.println("Request r1 == r11 -> " + (r1 == r11) + ", r1.equals(r11) -> " + r1.equals(r11));
            System.out.println("WorkPlanRecord wpr1 == wpr11 -> " + (wpr1 == wpr11) +
                    ", wpr1.equals(wpr11) -> " + wpr1.equals(wpr11));
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}

package ua.hcs.test;

import ua.hcs.model.Brigade;
import ua.hcs.model.Request;
import ua.hcs.model.User;
import ua.hcs.model.WorkPlanRecord;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Тест моделей на підтримку порівняння за допомогою Comparable.
 */
public class ComparableTest {
    public static void main(String[] args) {
        System.out.println("\nТест масивів моделей на підтримку порівняння" +
                " за допомогою інтерфейсу Comparable(реалізований на порівнянні id)");

        User u1 = new User(123, "Petro Petrovych", "qwer", "tenant");
        User u2 = new User(1, "Ivan Petrovych", "qwer1", "tenant");
        User u3 = new User(12, "Oles Petrovych", "qwer2", "dispatcher");
        List<User> userArray = Arrays.asList(u1, u2, u3);

        testArray(userArray);

        Brigade b1 = new Brigade(123, "Ivan Petrovych", 10);
        Brigade b2 = new Brigade(1, "Petro Petrovych", 11);
        Brigade b3 = new Brigade(12, "Petrovych Petrovych", 1);
        List<Brigade> brigadeArray = Arrays.asList(b1, b2, b3);

        testArray(brigadeArray);

        Request r1 = new Request(123, u1.getId(), "Hard work", "Little room", 1.5, false);
        Request r2 = new Request(1, u1.getId(), "Eqasy work", "Big room", 2.5, false);
        Request r3 = new Request(12, u1.getId(), "Hand work", "Little big room", 1.0, false);
        List<Request> requestArray = Arrays.asList(r1, r2, r3);

        testArray(requestArray);

        WorkPlanRecord wpr1 = new WorkPlanRecord(123, r1.getId(), b1.getId());
        WorkPlanRecord wpr2 = new WorkPlanRecord(1, r2.getId(), b2.getId());
        WorkPlanRecord wpr3 = new WorkPlanRecord(12, r3.getId(), b3.getId());
        List<WorkPlanRecord> wprArray = Arrays.asList(wpr1, wpr2, wpr3);

        testArray(wprArray);
    }

    private static <T extends Comparable<T>> void testArray(List<T> array) {
        System.out.println("\nВхідний массив: " + array);
        System.out.println("Максимум: " + Collections.max(array));
        System.out.println("Мінімум: " + Collections.min(array));
        Collections.sort(array);
        System.out.println("Відсортований: " + array);
        T searchKey = array.get(2);
        System.out.println("Бінарний пошук елемента " + searchKey + ", знайдений індекс: " +
                Collections.binarySearch(array, searchKey));
    }
}

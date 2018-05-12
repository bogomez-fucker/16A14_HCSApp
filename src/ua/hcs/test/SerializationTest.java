package ua.hcs.test;

import ua.hcs.model.Brigade;
import ua.hcs.model.Request;
import ua.hcs.model.User;
import ua.hcs.model.WorkPlanRecord;

import java.io.*;

/**
 * Тест моделей на підтримку серіалізації.
 */
public class SerializationTest {
    public static void main(String[] args) {
        File tempFile = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            tempFile = File.createTempFile("тест-серіалізації", ".bin", new File("."));
            tempFile.deleteOnExit();
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(tempFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        User u1 = new User(666, "Petro Petrovych", "qwer", "tenant");
        Brigade b1 = new Brigade(1, "Ivan Petrovych", 10);
        Request r1 = new Request(11, u1.getId(), "Hard work", "Little room", 1.5);
        WorkPlanRecord wpr1 = new WorkPlanRecord(111, r1.getId(), b1.getId());

        System.out.println("\nСпочатку ми запишемо в файл серіалізовані об'єкти, " +
                "потім прочитаємо і порівняємо з оригінальними\n");

        // Пишемо в файл серіалізовані об'єкти
        try {
            objectOutputStream.writeObject(u1);
            objectOutputStream.writeObject(b1);
            objectOutputStream.writeObject(r1);
            objectOutputStream.writeObject(wpr1);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        User u11 = null;
        Brigade b11 = null;
        Request r11 = null;
        WorkPlanRecord wpr11 = null;

        // Читаємо з файлу серіалізовані об'єкти
        ObjectInputStream objectInputStream = null;

        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(tempFile));

            u11 = (User) objectInputStream.readObject();
            b11 = (Brigade) objectInputStream.readObject();
            r11 = (Request) objectInputStream.readObject();
            wpr11 = (WorkPlanRecord) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                objectInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Формат: *1 - оригінальні об'єкти, *11 - прочитані після серіалізації об'єкти\n");
        System.out.println("User u1.equals(u11) -> " + u1.equals(u11));
        System.out.println("Brigade b1.equals(b11) -> " + b1.equals(b11));
        System.out.println("Request r1.equals(r11) -> " + r1.equals(r11));
        System.out.println("WorkPlanRecord wpr1.equals(wpr11) -> " + wpr1.equals(wpr11));
    }
}

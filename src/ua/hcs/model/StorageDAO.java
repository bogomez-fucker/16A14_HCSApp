package ua.hcs.model;

import ua.hcs.util.Const;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Забезпечує синхронізований доступ до сховища даних.
 * Використано паттерн Singleton.
 * Багато процесів можуть одночасно читати зі сховища,
 * проте коли хтось починає писати в сховище всі інші чекають.
 */
public class StorageDAO {

    private static final StorageDAO _instance = new StorageDAO();

    private ReadWriteLock brigadesLock;
    private ReadWriteLock requestsLock;
    private ReadWriteLock usersLock;
    private ReadWriteLock workPlanLock;

    private StorageDAO() {
        brigadesLock = new ReentrantReadWriteLock();
        requestsLock = new ReentrantReadWriteLock();
        usersLock = new ReentrantReadWriteLock();
        workPlanLock = new ReentrantReadWriteLock();
    }

    /**
     * Читає TSV файл
     * @param filePath шлях до файлу
     * @param columns кількість стовбців
     * @param lock лок на окремий файл
     * @return Лист рядків, що складаються з масиву стовбців
     */
    private List<String[]> readFileSplittedByTab(String filePath,
                                                 int columns, ReadWriteLock lock) {
        lock.readLock().lock();

        File file = new File(filePath);

        // if file is absent or empty return empty list
        if (!file.exists() || file.length() == 0) {
            lock.readLock().unlock();
            return new ArrayList<String[]>();
        }

        List<String> lines = null;

        try {
            lines = Files.readAllLines(Paths.get(file.getAbsolutePath()));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }

        List<String[]> splittedFile = new ArrayList<>();
        String[] parts = null;

        for (String line : lines) {
            parts = line.split("\t");

            if (parts.length != columns)
                continue;

            splittedFile.add(parts);
        }

        return splittedFile;
    }

    /**
     * Повертає об'єкт сховища
     * @return об'єкт сховища
     */
    public static synchronized StorageDAO getInstance() {
        return _instance;
    }

    /**
     * Повертає актуальний список бригад.
     * @return актуальний лист бригад
     */
    public List<Brigade> readBrigades() {
        List<String[]> splittedFile = readFileSplittedByTab(Const.BRIGADES_FILEPATH,
                3, brigadesLock);
        List<Brigade> brigades = new ArrayList<>();

        for (String[] line : splittedFile)
            brigades.add(new Brigade(line));

        return brigades;
    }

    /**
     * Записуэ бригади до сховища.
     * @param brigades бригади, що будуть записані
     * @param appendData чи дописувати дані
     */
    public void writeBrigades(List<Brigade> brigades, boolean appendData) {
        PrintWriter printWriter = null;

        try {
            brigadesLock.writeLock().lock();

            printWriter = new PrintWriter(
                    new BufferedWriter(
                            new FileWriter(Const.BRIGADES_FILEPATH, appendData)));

            for (Brigade b : brigades) {
                printWriter.println(b.getId() + "\t" +
                        b.getBrigadierName() + "\t" +
                        b.getWorkersNumber());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            printWriter.flush();
            printWriter.close();
            brigadesLock.writeLock().unlock();
        }
    }

    /**
     * Повертає актуальний список запитів користувачів.
     * @return актуальний список запитів користувачів
     */
    public List<Request> readRequests() {
        List<String[]> splittedFile = readFileSplittedByTab(Const.REQUESTS_FILEPATH,
                6, requestsLock);
        List<Request> request = new ArrayList<>();

        for (String[] line : splittedFile)
            request.add(new Request(line));

        return request;
    }

    /**
     * Записуэ запити користувачів до сховища.
     * @param requests запити, що будуть записані
     * @param appendData чи дописувати дані
     */
    public void writeRequests(List<Request> requests, boolean appendData) {
        PrintWriter printWriter = null;

        try {
            requestsLock.writeLock().lock();

            printWriter = new PrintWriter(
                    new BufferedWriter(
                            new FileWriter(Const.REQUESTS_FILEPATH, appendData)));

            for (Request r : requests) {
                printWriter.println(r.getId() + "\t" +
                        r.getId_tenant() + "\t" +
                        r.getWorkType() + "\t" +
                        r.getWorkScale() + "\t" +
                        r.getWorkPrefferedTime() + "\t" +
                        r.isAccepted());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            printWriter.flush();
            printWriter.close();
            requestsLock.writeLock().unlock();
        }
    }

    /**
     * Повертає актуальний список користувачів.
     * @return актуальний лист користувачів
     */
    public List<User> readUsers() {
        List<String[]> splittedFile = readFileSplittedByTab(Const.USERS_FILEPATH,
                4, usersLock);
        List<User> users = new ArrayList<>();

        for (String[] line : splittedFile)
            users.add(new User(line));

        return users;
    }

    /**
     * Записуэ користувачів до сховища.
     * @param users користувачі, що будуть записані
     * @param appendData чи дописувати дані
     */
    public void writeUsers(List<User> users, boolean appendData) {
        PrintWriter printWriter = null;

        try {
            usersLock.writeLock().lock();

            printWriter = new PrintWriter(
                    new BufferedWriter(
                            new FileWriter(Const.USERS_FILEPATH, appendData)));

            for (User u : users) {
                printWriter.println(u.getId() + "\t" +
                        u.getName() + "\t" +
                        u.getPassword() + "\t" +
                        u.getRole());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            printWriter.flush();
            printWriter.close();
            usersLock.writeLock().unlock();
        }
    }

    /**
     * Повертає актуальний план робіт.
     * @return актуальний план робіт
     */
    public List<WorkPlanRecord> readWorkPlan() {
        List<String[]> splittedFile = readFileSplittedByTab(Const.WORKPLAN_FILEPATH,
                3, workPlanLock);
        List<WorkPlanRecord> workPlan = new ArrayList<>();

        for (String[] line : splittedFile)
            workPlan.add(new WorkPlanRecord(line));

        return workPlan;
    }

    /**
     * Записуэ план робіт до сховища.
     * @param workPlan план робіт, що буде записано
     * @param appendData чи дописувати дані
     */
    public void writeWorkPlan(List<WorkPlanRecord> workPlan, boolean appendData) {
        PrintWriter printWriter = null;

        try {
            workPlanLock.writeLock().lock();

            printWriter = new PrintWriter(
                    new BufferedWriter(
                            new FileWriter(Const.WORKPLAN_FILEPATH, appendData)));

            for (WorkPlanRecord wpr : workPlan) {
                printWriter.println(wpr.getId() + "\t" +
                        wpr.getIdRequest() + "\t" +
                        wpr.getIdBrigade());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            printWriter.flush();
            printWriter.close();
            workPlanLock.writeLock().unlock();
        }
    }
}

package ua.hcs.model;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Objects;

/**
 * Зображує модель робочої Бригади.
 */
public class Brigade implements Serializable, Cloneable, Comparable<Brigade> {

    private long id;
    private String brigadierName;
    private int workersNumber;

    public Brigade(long id, String brigadierName, int workersNumber) {
        this.id = id;
        this.brigadierName = brigadierName;
        this.workersNumber = workersNumber;
    }

    public Brigade(String[] fields) {
        this.id = Long.valueOf(fields[0]);
        this.brigadierName = fields[1];
        this.workersNumber = Integer.valueOf(fields[2]);
    }

    public long getId() {
        return id;
    }

    public String getBrigadierName() {
        return brigadierName;
    }

    public int getWorkersNumber() {
        return workersNumber;
    }

    @Override
    public Brigade clone() throws CloneNotSupportedException {
        return (Brigade) super.clone();
    }

    @Override
    public int compareTo(@NotNull Brigade o) {
        return Long.valueOf(this.id - o.id).intValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brigade brigade = (Brigade) o;
        return id == brigade.id &&
                workersNumber == brigade.workersNumber &&
                Objects.equals(brigadierName, brigade.brigadierName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, brigadierName, workersNumber);
    }

    @Override
    public String toString() {
        return "Brigade{" +
                "id=" + id +
                ", brigadierName='" + brigadierName + '\'' +
                ", workersNumber=" + workersNumber +
                '}';
    }
}

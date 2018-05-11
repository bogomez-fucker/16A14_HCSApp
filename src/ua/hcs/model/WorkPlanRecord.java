package ua.hcs.model;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Objects;

/**
 * Зображує один запис Робочого плану.
 */
public class WorkPlanRecord implements Serializable, Cloneable, Comparable<WorkPlanRecord> {

    private long id;
    private long idRequest;
    private long idBrigade;

    public WorkPlanRecord(long id, long idRequest, long idBrigade) {
        this.id = id;
        this.idRequest = idRequest;
        this.idBrigade = idBrigade;
    }

    public WorkPlanRecord(String[] fields) {
        this.id = Long.valueOf(fields[0]);
        this.idRequest = Long.valueOf(fields[1]);
        this.idBrigade = Long.valueOf(fields[2]);
    }

    public long getId() {
        return id;
    }

    public long getIdRequest() {
        return idRequest;
    }

    public long getIdBrigade() {
        return idBrigade;
    }

    @Override
    public WorkPlanRecord clone() throws CloneNotSupportedException {
        return (WorkPlanRecord) super.clone();
    }

    @Override
    public int compareTo(@NotNull WorkPlanRecord o) {
        return Long.valueOf(this.id - o.id).intValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkPlanRecord that = (WorkPlanRecord) o;
        return id == that.id &&
                idRequest == that.idRequest &&
                idBrigade == that.idBrigade;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, idRequest, idBrigade);
    }

    @Override
    public String toString() {
        return "WorkPlanRecord{" +
                "id=" + id +
                ", idRequest=" + idRequest +
                ", idBrigade=" + idBrigade +
                '}';
    }
}

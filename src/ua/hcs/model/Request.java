package ua.hcs.model;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Objects;

/**
 * Зображує модель Запиту користувача.
 */
public class Request implements Serializable, Cloneable, Comparable<Request> {

    private long id;
    private long id_tenant;
    private String workType;
    private String workScale;
    private double workPrefferedTime;

    public Request(long id, long id_tenant, String workType, String workScale, double workPrefferedTime) {
        this.id = id;
        this.id_tenant = id_tenant;
        this.workType = workType;
        this.workScale = workScale;
        this.workPrefferedTime = workPrefferedTime;
    }

    public Request(String[] fields) {
        this.id = Long.valueOf(fields[0]);
        this.id_tenant = Long.valueOf(fields[1]);
        this.workType = fields[2];
        this.workScale = fields[3];
        this.workPrefferedTime = Double.valueOf(fields[4]);
    }

    public long getId() {
        return id;
    }

    public long getId_tenant() {
        return id_tenant;
    }

    public String getWorkType() {
        return workType;
    }

    public String getWorkScale() {
        return workScale;
    }

    public double getWorkPrefferedTime() {
        return workPrefferedTime;
    }

    @Override
    public Request clone() throws CloneNotSupportedException {
        return (Request) super.clone();
    }

    @Override
    public int compareTo(@NotNull Request o) {
        return Long.valueOf(this.id - o.id).intValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return id == request.id &&
                id_tenant == request.id_tenant &&
                Double.compare(request.workPrefferedTime, workPrefferedTime) == 0 &&
                Objects.equals(workType, request.workType) &&
                Objects.equals(workScale, request.workScale);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, id_tenant, workType, workScale, workPrefferedTime);
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", id_tenant=" + id_tenant +
                ", workType='" + workType + '\'' +
                ", workScale='" + workScale + '\'' +
                ", workPrefferedTime=" + workPrefferedTime +
                '}';
    }
}

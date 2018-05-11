package ua.hcs.model;

import org.jetbrains.annotations.NotNull;
import ua.hcs.util.Const;

import javax.swing.table.DefaultTableModel;
import java.io.Serializable;
import java.util.Arrays;

/**
 * Модель таблиці плану робіт.
 */
public class WorkPlanTableModel extends DefaultTableModel implements Serializable,
        Cloneable, Comparable<WorkPlanTableModel> {

    private Object[] header;
    private Object[][] content;

    private Class[] types = new Class[]{
            Long.class, Long.class, String.class, Long.class, String.class, Integer.class
    };

    private boolean[] canEdit = new boolean[]{
            false, false, false, false, false, false
    };

    public WorkPlanTableModel() {
        header = Const.WORK_PLAN_TABLE_HEADER;
        content = new Object[][]{{1.0, 2.1, "3", 4.0, "5", 6}, {1.0, 2.1, "3", 4.0, "5", 67}};

        setDataVector(content, header);
    }

    public Object[] getHeader() {
        return header;
    }

    public Object[][] getContent() {
        return content;
    }

    public Class[] getTypes() {
        return types;
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return types[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit[columnIndex];
    }


    @Override
    public WorkPlanTableModel clone() throws CloneNotSupportedException {
        return (WorkPlanTableModel) super.clone();
    }

    @Override
    public int compareTo(@NotNull WorkPlanTableModel o) {
        return Long.valueOf(this.header.length - o.header.length).intValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkPlanTableModel that = (WorkPlanTableModel) o;
        return Arrays.equals(header, that.header) &&
                Arrays.equals(content, that.content) &&
                Arrays.equals(types, that.types) &&
                Arrays.equals(canEdit, that.canEdit);
    }

    @Override
    public int hashCode() {

        int result = Arrays.hashCode(header);
        result = 31 * result + Arrays.hashCode(content);
        result = 31 * result + Arrays.hashCode(types);
        result = 31 * result + Arrays.hashCode(canEdit);
        return result;
    }

    @Override
    public String toString() {
        return "WorkPlanTableModel{" +
                "header=" + (header == null ? null : Arrays.asList(header)) +
                ", content=" + (content == null ? null : Arrays.asList(content)) +
                '}';
    }
}

package ua.hcs.model;

import org.jetbrains.annotations.NotNull;
import ua.hcs.util.Const;

import javax.swing.table.DefaultTableModel;
import java.io.Serializable;
import java.util.Arrays;

/**
 * Модель таблиці запитів орендаторів.
 */
public class RequestsTableModel extends DefaultTableModel implements Serializable,
        Cloneable, Comparable<RequestsTableModel> {

    private Object[] header;
    private Object[][] content;

    private Class[] types = new Class[]{
            Long.class, String.class, String.class, String.class, String.class
    };

    private boolean[] canEdit = new boolean[]{
            false, false, false, false, false
    };

    public RequestsTableModel() {
        header = Const.REQUESTS_TABLE_HEADER;
        content = new Object[][]{{1.0, "2", "3", "4", "5"}, {1.0, "2", "3", "4", "5"}};

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
    public RequestsTableModel clone() throws CloneNotSupportedException {
        return (RequestsTableModel) super.clone();
    }

    @Override
    public int compareTo(@NotNull RequestsTableModel o) {
        return Long.valueOf(this.header.length - o.header.length).intValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestsTableModel that = (RequestsTableModel) o;
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
        return "RequestsTableModel{" +
                "header=" + (header == null ? null : Arrays.asList(header)) +
                ", content=" + (content == null ? null : Arrays.asList(content)) +
                '}';
    }
}

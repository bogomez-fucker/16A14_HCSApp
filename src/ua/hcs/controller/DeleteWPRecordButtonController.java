package ua.hcs.controller;

import ua.hcs.model.StorageDAO;
import ua.hcs.model.WorkPlanRecord;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Обробляє натиснення на кнопку видалення запису з плану робіт.
 */
public class DeleteWPRecordButtonController implements ActionListener {

    private JTable workPlanTable;
    private JFrame frame;

    public DeleteWPRecordButtonController(JTable workPlanTable, JFrame frame) {
        this.workPlanTable = workPlanTable;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRowIndex = workPlanTable.getSelectedRow();

        if (selectedRowIndex < 0) {
            JOptionPane.showMessageDialog(frame, "Будь ласка, виберіть один із записів робочого плану з таблиці!");
            return;
        }

        DefaultTableModel requestsTableModel = (DefaultTableModel) workPlanTable.getModel();
        Object[] record = new Object[6];

        for (int i = 0; i < 5; i++)
            record[i] = requestsTableModel.getValueAt(selectedRowIndex, i);

        long workPlanId = Long.valueOf(record[0].toString());

        List<WorkPlanRecord> filteredWorkPlan = StorageDAO.getInstance()
                .readWorkPlan()
                .stream()
                .filter(x -> x.getId() != workPlanId)
                .collect(Collectors.toList());

        StorageDAO.getInstance().writeWorkPlan(filteredWorkPlan, false);

        JOptionPane.showMessageDialog(frame, "Запис " + workPlanId +
                " успішно видалено з робочого плану!");
    }
}

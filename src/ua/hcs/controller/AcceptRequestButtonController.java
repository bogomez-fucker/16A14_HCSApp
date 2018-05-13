package ua.hcs.controller;

import ua.hcs.model.Request;
import ua.hcs.model.StorageDAO;
import ua.hcs.model.User;
import ua.hcs.view.BrigadeCreation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Обробляє натиснення на кнопку прийняття запиту.
 */
public class AcceptRequestButtonController implements ActionListener {

    private JTable requestsTable;
    private JFrame frame;

    public AcceptRequestButtonController(JTable requestsTable, JFrame frame) {
        this.requestsTable = requestsTable;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRowIndex = requestsTable.getSelectedRow();

        if (selectedRowIndex < 0) {
            JOptionPane.showMessageDialog(frame, "Будь ласка, виберіть з таблиці заяву!");
            return;
        }

        // Отримати дані з таблиці
        DefaultTableModel requestsTableModel = (DefaultTableModel) requestsTable.getModel();
        Object[] record = new Object[5];

        for (int i = 0; i < 5; i++)
            record[i] = requestsTableModel.getValueAt(selectedRowIndex, i);

        // Знайти користувача за ім'ям
        String username = String.valueOf(record[1]);
        User tenant = StorageDAO.getInstance().readUsers()
                .stream()
                .filter(x -> x.getName().equals(username))
                .findFirst()
                .orElse(null);

        if (tenant == null) {
            JOptionPane.showMessageDialog(frame, "Не знайдено користувача з ім'ям \"" + username + "\"." +
                    " Будь ласка, зверніться до адміністратора!");
            return;
        }

        // Позначити запит, як прийнятий
        Request request = new Request(
                Long.valueOf(String.valueOf(record[0])),
                tenant.getId(),
                String.valueOf(record[2]),
                String.valueOf(record[3]),
                Double.valueOf(String.valueOf(record[4])),
                true);

        // Показати фрейм формування бригади під запит
        BrigadeCreation brigadeCreation = new BrigadeCreation(request, frame);

        brigadeCreation.setVisible(true);
    }
}

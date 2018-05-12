package ua.hcs.controller;

import ua.hcs.model.Request;
import ua.hcs.model.StorageDAO;
import ua.hcs.model.User;
import ua.hcs.util.Util;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * Обробляє натиснення на кнопку відправлення запиту.
 */
public class SendRequestButtonController implements ActionListener {

    private JTextField kindOfWorkField;
    private JTextField scaleOfWorkField;
    private JTextField prefTimeOfWorkField;
    private User tenant;
    private JFrame frame;

    public SendRequestButtonController(JTextField kindOfWorkField, JTextField scaleOfWorkField,
                                       JTextField prefTimeOfWorkField, User tenant, JFrame frame) {
        this.kindOfWorkField = kindOfWorkField;
        this.scaleOfWorkField = scaleOfWorkField;
        this.prefTimeOfWorkField = prefTimeOfWorkField;
        this.tenant = tenant;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String kindOfWork = kindOfWorkField.getText();
        String scaleOfWork = scaleOfWorkField.getText();
        String prefTimeOfWorkStr = prefTimeOfWorkField.getText();
        Double prefTimeOfWork = 0.0;

        if (kindOfWork.isEmpty() || scaleOfWork.isEmpty() || prefTimeOfWorkStr.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Будь ласка, заповніть всі поля!");
            return;
        }
        else {
            try {
                prefTimeOfWork = Double.valueOf(prefTimeOfWorkStr);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(frame, "Будь ласка, введіть години цифрами! Приклад: 6, 1.5, 3.0");
                return;
            }
        }

        Request request = new Request(Util.generateId(), tenant.getId(), kindOfWork, scaleOfWork, prefTimeOfWork, false);

        StorageDAO.getInstance()
                .writeRequests(Arrays.asList(request), true);

        JOptionPane.showMessageDialog(frame, "Ваш запит успішно відправлено!");
    }
}

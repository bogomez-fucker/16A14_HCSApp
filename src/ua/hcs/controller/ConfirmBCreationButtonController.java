package ua.hcs.controller;

import ua.hcs.model.Brigade;
import ua.hcs.model.Request;
import ua.hcs.model.StorageDAO;
import ua.hcs.model.WorkPlanRecord;
import ua.hcs.util.Util;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Обробляє натиснення на кнопку формування бригади на запит.
 */
public class ConfirmBCreationButtonController implements ActionListener {

    private Request request;
    private JTextField brigadierName;
    private JTextField workersNumber;
    private JFrame frame;

    public ConfirmBCreationButtonController(Request request, JTextField brigadierName,
                                            JTextField workersNumber, JFrame frame) {
        this.request = request;
        this.brigadierName = brigadierName;
        this.workersNumber = workersNumber;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Сформувати бригаду і занесена до робочого плану
        String enteredBrigadierName = brigadierName.getText();
        String enteredWorkersNumberStr = workersNumber.getText();
        int enteredWorkersNumber = 0;

        if (enteredBrigadierName.isEmpty() || enteredWorkersNumberStr.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Будь ласка, введіть ім'я бригадира та кількість працівників!");
            return;
        } else {
            try {
                enteredWorkersNumber = Integer.valueOf(enteredWorkersNumberStr);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(frame,
                        "Будь ласка, введіть кількість працівників цілими цифрами! Приклад: 6, 5, 12");
                return;
            }
        }

        // Сперше, позначимо запит як прийнятий і запишемо зміни
        List<Request> filteredRequests = StorageDAO.getInstance()
                .readRequests()
                .stream()
                .filter(x -> x.getId() != request.getId())
                .collect(Collectors.toList());

        filteredRequests.add(request); // додати змінений
        // Записати зміни
        StorageDAO.getInstance().writeRequests(filteredRequests, false);

        Brigade brigade = new Brigade(Util.generateId(), enteredBrigadierName, enteredWorkersNumber);

        StorageDAO.getInstance().writeBrigades(Arrays.asList(brigade), true);

        WorkPlanRecord workPlanRecord = new WorkPlanRecord(Util.generateId(), request.getId(), brigade.getId());

        StorageDAO.getInstance().writeWorkPlan(Arrays.asList(workPlanRecord), true);

        JOptionPane.showMessageDialog(frame, "Бригада сформована та занесена до робочого плану!");
        frame.dispose();
    }
}

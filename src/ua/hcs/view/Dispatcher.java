package ua.hcs.view;

import ua.hcs.controller.AcceptRequestButtonController;
import ua.hcs.model.*;
import ua.hcs.util.Const;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Фрейм диспетчера.
 */
public class Dispatcher extends JFrame {

    private JLabel headerLabel;
    private JScrollPane requestsTableScrollPane;
    private JTable requestsTable;
    private JButton acceptRequestButton;
    private JScrollPane workPlanTableScrollPane;
    private JTable workPlanTable;
    private JButton deleteWorkPlanRecordButton;
    private List<Request> lastRequests = new ArrayList<>();
    private List<WorkPlanRecord> lastWorkPlan = new ArrayList<>();

    public Dispatcher() {
        headerLabel = new JLabel();
        requestsTableScrollPane = new JScrollPane();
        requestsTable = new JTable();
        acceptRequestButton = new JButton();
        workPlanTableScrollPane = new JScrollPane();
        workPlanTable = new JTable();
        deleteWorkPlanRecordButton = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Додаток ЖЕК ~ Диспетчер");

        headerLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        headerLabel.setHorizontalAlignment(SwingConstants.LEFT);
        headerLabel.setText("Панель диспетчера");

        requestsTable.setModel(new RequestsTableModel());
        requestsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ((DefaultTableCellRenderer)requestsTable.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.LEFT);

        requestsTableScrollPane.setBorder(BorderFactory.createTitledBorder("Заяви орендарів"));
        requestsTableScrollPane.setPreferredSize(new Dimension(Const.DISPATCHER_FRAME_WIDTH,
                Const.DISPATCHER_FRAME_HEIGHT));
        requestsTableScrollPane.setViewportView(requestsTable);

        acceptRequestButton.setText("Прийняти заяву");
        acceptRequestButton.addActionListener(new AcceptRequestButtonController(requestsTable, this));

        workPlanTable.setModel(new WorkPlanTableModel());
        workPlanTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ((DefaultTableCellRenderer)workPlanTable.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.LEFT);

        workPlanTableScrollPane.setBorder(BorderFactory.createTitledBorder("План робіт"));
        workPlanTableScrollPane.setPreferredSize(new Dimension(Const.DISPATCHER_FRAME_WIDTH,
                Const.DISPATCHER_FRAME_HEIGHT));
        workPlanTableScrollPane.setViewportView(workPlanTable);

        deleteWorkPlanRecordButton.setText("Видалити з плану робіт");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(headerLabel)
                                        .addComponent(requestsTableScrollPane)
                                        .addComponent(acceptRequestButton)
                                        .addComponent(workPlanTableScrollPane)
                                        .addComponent(deleteWorkPlanRecordButton))
                                .addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(headerLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(requestsTableScrollPane)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(acceptRequestButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(workPlanTableScrollPane)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteWorkPlanRecordButton)
                        .addContainerGap()));

        pack();
        setLocationRelativeTo(null);

        // Запланувати оновлення таблиць
        Timer requestsTableTimer = new Timer(0, (ActionEvent e) -> {
            List<Request> requests = StorageDAO.getInstance().readRequests();

            if (!requests.equals(lastRequests)) {
                lastRequests = requests;
                fillRequestTable(requests);
            }
        });

        requestsTableTimer.setDelay(Const.UPDATE_DELAY);
        requestsTableTimer.start();

        Timer workPlanTableTimer = new Timer(0, (ActionEvent e) -> {
            List<WorkPlanRecord> workPlan = StorageDAO.getInstance().readWorkPlan();

            if (!workPlan.equals(lastWorkPlan)) {
                lastWorkPlan = workPlan;
                fillWorkPlanTable(workPlan);
            }
        });

        workPlanTableTimer.setDelay(Const.UPDATE_DELAY);
        workPlanTableTimer.start();
    }

    /**
     * Заповнює таблицю запитів.
     * @param requests нові реквести що будуть відображені
     */
    private void fillRequestTable(List<Request> requests) {
        DefaultTableModel requestsTableModel = (DefaultTableModel) requestsTable.getModel();

        // Спочатку затерти старі дані
        for (int i = requestsTableModel.getRowCount() - 1; i >= 0; i--)
            requestsTableModel.removeRow(i);

        // Один рядок даних
        Object[] rowData = new Object[5];

        // Заповнення таблиці
        for (Request request : requests) {
            rowData[0] = request.getId();
            rowData[1] = StorageDAO.getInstance() // Знайти ім'я користувача по Id запиту
                    .readUsers()
                    .stream()
                    .filter(x -> x.getId() == request.getId_tenant())
                    .findFirst()
                    .map(User::getName)
                    .orElse("Немає такого користувача")
            ;
            rowData[2] = request.getWorkType();
            rowData[3] = request.getWorkScale();
            rowData[4] = request.getWorkPrefferedTime();

            requestsTableModel.addRow(rowData);
        }
    }

    /**
     * Заповнює таблицю плану робіт.
     * @param workPlan новий план що буде відображено
     */
    private void fillWorkPlanTable(List<WorkPlanRecord> workPlan) {
        DefaultTableModel workPlanTableModel = (DefaultTableModel) workPlanTable.getModel();

        // Спочатку затерти старі дані
        for (int i = workPlanTableModel.getRowCount() - 1; i >= 0; i--)
            workPlanTableModel.removeRow(i);

        // Один рядок даних
        Object[] rowData = new Object[6];

        // Заповнення таблиці
        for (WorkPlanRecord workPlanRecord : workPlan) {
            rowData[0] = workPlanRecord.getId();
            rowData[1] = workPlanRecord.getIdRequest();
            rowData[2] = StorageDAO.getInstance() // Знайти реквест по запису в плані роботи
                    .readRequests()
                    .stream()
                    .filter(x -> x.getId() == workPlanRecord.getIdRequest())
                    .flatMap(req -> StorageDAO.getInstance() // Знайти користувача по реквесту
                            .readUsers()
                            .stream()
                            .filter(usr -> usr.getId() == req.getId_tenant()))
                    .findFirst()
                    .map(User::getName)
                    .orElse("Немає такого користувача");
            rowData[3] = workPlanRecord.getIdBrigade();
            rowData[4] = StorageDAO.getInstance()
                    .readBrigades()
                    .stream()
                    .filter(brigade -> brigade.getId() == workPlanRecord.getIdBrigade())
                    .findFirst()
                    .map(Brigade::getBrigadierName)
                    .orElse("Немає такого бригадира");
            rowData[5] = StorageDAO.getInstance()
                    .readBrigades()
                    .stream()
                    .filter(brigade -> brigade.getId() == workPlanRecord.getIdBrigade())
                    .findFirst()
                    .map(Brigade::getWorkersNumber)
                    .orElse(0);

            workPlanTableModel.addRow(rowData);
        }
    }
}

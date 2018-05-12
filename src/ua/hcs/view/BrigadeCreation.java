package ua.hcs.view;

import ua.hcs.model.Request;

import javax.swing.*;
import java.awt.*;

/**
 * Фрейм формування бригади на запит.
 */
public class BrigadeCreation extends JFrame {

    private JLabel headerLabel;
    private JLabel workTypeLabel;
    private JLabel workScaleLabel;
    private JLabel workPrefTimeLabel;
    private JLabel brigadierNameLabel;
    private JTextField brigadierNameTextField;
    private JLabel workerNumberLabel;
    private JTextField workerNumberTextField;
    private JButton confirmButton;
    private Request request;

    public BrigadeCreation(Request request) {
        headerLabel = new JLabel();
        workScaleLabel = new JLabel();
        workPrefTimeLabel = new JLabel();
        workTypeLabel = new JLabel();
        brigadierNameLabel = new JLabel();
        brigadierNameTextField = new JTextField();
        workerNumberLabel = new JLabel();
        workerNumberTextField = new JTextField();
        confirmButton = new JButton();
        request = request;

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Додаток ЖЕК ~ Формування бригади");

        headerLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        headerLabel.setHorizontalAlignment(SwingConstants.LEFT);
        headerLabel.setText("Формування бригади для запиту " + request.getId() + ":\n");

        workTypeLabel.setText("Тип робіт: \"" + request.getWorkType() + "\"");
        workScaleLabel.setText("Масштаб робіт: \"" + request.getWorkScale() + "\"");
        workPrefTimeLabel.setText("Бажаний час виконання: " + request.getWorkPrefferedTime() + " години");

        brigadierNameLabel.setText("Введіть ім'я бригадира:");
        workerNumberLabel.setText("Введіть необхідну кількість працівників:");

        confirmButton.setText("Запланувати роботу");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(headerLabel)
                                        .addComponent(workTypeLabel)
                                        .addComponent(workScaleLabel)
                                        .addComponent(workPrefTimeLabel)
                                        .addComponent(brigadierNameLabel)
                                        .addComponent(brigadierNameTextField)
                                        .addComponent(workerNumberLabel)
                                        .addComponent(workerNumberTextField)
                                        .addComponent(confirmButton))
                                .addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(headerLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(workTypeLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(workScaleLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(workPrefTimeLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(brigadierNameLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(brigadierNameTextField)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(workerNumberLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(workerNumberTextField)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(confirmButton)
                        .addContainerGap()));

        pack();
        setLocationRelativeTo(null);
    }
}

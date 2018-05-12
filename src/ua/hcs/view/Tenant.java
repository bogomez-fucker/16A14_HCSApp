package ua.hcs.view;

import ua.hcs.controller.SendRequestButtonController;
import ua.hcs.model.User;

import javax.swing.*;
import java.awt.*;

/**
 * Фрейм орендара.
 */
public class Tenant extends JFrame {

    private JLabel headerLabel;
    private JLabel kindOfWorkLabel;
    private JTextField kindOfWorkField;
    private JLabel scaleOfWorkLabel;
    private JTextField scaleOfWorkField;
    private JLabel prefTimeOfWorkLabel;
    private JTextField prefTimeOfWorkField;
    private JButton sendRequestButton;
    private User tenant;

    public Tenant(User tenant) {
        headerLabel = new JLabel();
        kindOfWorkLabel = new JLabel();
        kindOfWorkField = new JTextField();
        scaleOfWorkLabel = new JLabel();
        scaleOfWorkField = new JTextField();
        prefTimeOfWorkLabel = new JLabel();
        prefTimeOfWorkField = new JTextField();
        sendRequestButton = new JButton();
        this.tenant = tenant;

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Додаток ЖЕК ~ Заява");

        headerLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        headerLabel.setHorizontalAlignment(SwingConstants.LEFT);
        headerLabel.setText("Сформувати заяву");

        kindOfWorkLabel.setText("Введіть рід робіт:");
        scaleOfWorkLabel.setText("Введіть масштаб робіт:");
        prefTimeOfWorkLabel.setText("Введіть бажаний час в годинах:");

        sendRequestButton.setText("Відправити заяву");
        sendRequestButton.addActionListener(new SendRequestButtonController(kindOfWorkField,
                scaleOfWorkField, prefTimeOfWorkField, tenant, this));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(headerLabel)
                                        .addComponent(kindOfWorkLabel)
                                        .addComponent(kindOfWorkField)
                                        .addComponent(scaleOfWorkLabel)
                                        .addComponent(scaleOfWorkField)
                                        .addComponent(prefTimeOfWorkLabel)
                                        .addComponent(prefTimeOfWorkField)
                                        .addComponent(sendRequestButton))
                                .addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(headerLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(kindOfWorkLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(kindOfWorkField)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scaleOfWorkLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scaleOfWorkField)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(prefTimeOfWorkLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(prefTimeOfWorkField)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sendRequestButton)
                        .addContainerGap()));

        pack();
        setLocationRelativeTo(null);
    }
}

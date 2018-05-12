package ua.hcs.view;

import ua.hcs.controller.LoginButtonController;

import javax.swing.*;
import java.awt.*;

/**
 * Фрейм авторизації.
 */
public class Authorization extends JFrame {

    private JLabel headerLabel;
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;

    public Authorization() {
        headerLabel = new JLabel();
        nameLabel = new JLabel();
        nameField = new JTextField();
        passwordLabel = new JLabel();
        passwordField = new JPasswordField();
        loginButton = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Додаток ЖЕК ~ Авторизація");

        headerLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        headerLabel.setHorizontalAlignment(SwingConstants.LEFT);
        headerLabel.setText("Додаток ЖЕК");

        nameLabel.setText("Введіть ім'я: ");
        passwordLabel.setText("Введіть пароль: ");
        loginButton.setText("Ввійти");
        loginButton.addActionListener(new LoginButtonController(nameField, passwordField, this));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(headerLabel)
                                        .addComponent(nameLabel)
                                        .addComponent(nameField)
                                        .addComponent(passwordLabel)
                                        .addComponent(passwordField)
                                        .addComponent(loginButton))
                                .addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(headerLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameField)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passwordLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passwordField)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(loginButton)
                        .addContainerGap()));

        pack();
        setLocationRelativeTo(null);
    }
}

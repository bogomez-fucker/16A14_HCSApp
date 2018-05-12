package ua.hcs.controller;

import ua.hcs.model.StorageDAO;
import ua.hcs.model.User;
import ua.hcs.util.Const;
import ua.hcs.view.Dispatcher;
import ua.hcs.view.Tenant;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import static java.awt.event.WindowEvent.WINDOW_CLOSING;

public class LoginButtonController implements ActionListener {

    private JTextField nameField;
    private JPasswordField passwordField;
    private JFrame frame;

    public LoginButtonController(JTextField nameField, JPasswordField passwordField, JFrame frame) {
        this.nameField = nameField;
        this.passwordField = passwordField;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String enteredName = nameField.getText();
        String enteredPass = String.valueOf(passwordField.getPassword());

        if (enteredName.isEmpty() || enteredPass.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Будь ласка, введіть ваше ім'я та пароль!");
            return;
        }

        User foundUser = StorageDAO.getInstance().readUsers()
                .stream()
                .filter(x -> x.getName().equals(enteredName))
                .findFirst()
                .orElse(null);

        if (foundUser == null)
            JOptionPane.showMessageDialog(frame, "Користувача \"" + enteredName + "\" не знайдено!");
        else if (!foundUser.getPassword().equals(enteredPass))
            JOptionPane.showMessageDialog(frame, "Пароль введено не вірно!");
        else if (foundUser.getRole().equals(Const.ROLE_TENANT)) {
            Tenant tenantGUI = new Tenant(foundUser);

            frame.setVisible(false);
            tenantGUI.setVisible(true);
            frame.dispose();
        } else if (foundUser.getRole().equals(Const.ROLE_DISPATCHER)) {
            Dispatcher dispatcher = new Dispatcher();

            frame.setVisible(false);
            dispatcher.setVisible(true);
            frame.dispose();
        } else {
            JOptionPane.showMessageDialog(frame,
                    "В вашого користувача помилкова роль \"" + foundUser.getRole() +
                            "\"! Будь ласка, зверніться до адміністратора!");
            frame.dispatchEvent(new WindowEvent(frame, WINDOW_CLOSING));
        }
    }
}

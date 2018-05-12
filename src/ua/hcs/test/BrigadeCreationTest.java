package ua.hcs.test;

import ua.hcs.model.Request;
import ua.hcs.view.BrigadeCreation;

/**
 * Тест фрейма формування бригади.
 */
public class BrigadeCreationTest {

    public static void main(String[] args) {
        BrigadeCreation brigadeCreation = new BrigadeCreation(
                new Request(2001, 3001, "Спил сухих дерев",
                        "Ділянка 2 сотки", 6.5, false));

        brigadeCreation.setVisible(true);
    }
}

import controller.Controller;
import javax.swing.SwingUtilities;
import model.UserTableModel;
import view.View;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserTableModel userTableModel = new UserTableModel();
            View view = new View();
            Controller controller = new Controller(userTableModel, view);
            controller.initializeData();
            view.setController(controller);
            view.setModel(userTableModel);
            view.createAndShowGUI();
        });
    }
}
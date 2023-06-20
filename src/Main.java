import controller.Controller;
import javax.swing.SwingUtilities;

import repository.PhoneNumberRepository;
import repository.UserRepository;
import view.View;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserRepository userRepository = new UserRepository();
            PhoneNumberRepository phoneNumberRepository = new PhoneNumberRepository();
            View view = new View();
            Controller controller = new Controller(userRepository, phoneNumberRepository, view);
            view.setController(controller);
            controller.initializeData();
            view.createAndShowGUI();
        });
    }
}
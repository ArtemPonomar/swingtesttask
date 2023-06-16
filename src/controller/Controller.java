package controller;

import entity.User;
import java.util.List;
import java.util.Optional;
import repository.UserTableModel;
import view.View;

public class Controller {
    private final UserTableModel userTableModel;
    private final View view;

    public Controller(UserTableModel userTableModel, View view) {
        this.userTableModel = userTableModel;
        this.view = view;
    }

    public void searchButtonClicked(String name) {
        Optional<User> user = userTableModel.getUserByUsername(name);
        if (user.isEmpty()) {
            view.displayErrorMessageUserNotFound();
            return;
        }
        if (user.get().phoneNumbers() == null) {
            view.displayErrorMessageNoMobileNumbers();
            return;
        }
        view.displayPhoneNumbers(user.get().phoneNumbers());
        view.hideTriangle();
    }

    public void initializeData() {
        userTableModel.addUser(new User("Ivan", List.of("00000000", "00000001")));
        userTableModel.addUser(new User("Petro", List.of("00000002", "00000003")));
        userTableModel.addUser(new User("Daniil", List.of("00000004")));
        userTableModel.addUser(new User("Olha", null));
    }
}
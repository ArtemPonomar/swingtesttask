package view.teblemodel;

import entity.PhoneNumber;
import entity.User;
import repository.UserRepository;

import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Optional;

public class UserTableModel extends DefaultTableModel {

    private static final String USER_ID = "user_id";
    private static final String USER_NAME = "user_name";

    public UserTableModel(List<User> initialData) {
        super(new String[]{USER_ID, USER_NAME}, 0);
        addAll(initialData);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public void add(User user) {
        addRow(new Object[]{
                user.getId(),
                user.getUserName()
        });
    }

    public void addAll(Iterable<User> users) {
        users.forEach(this::add);
    }
}
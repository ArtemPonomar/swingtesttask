package model;

import entity.User;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Optional;

public class UserTableModel extends DefaultTableModel {

    private static final int NAME_COLUMN = 0;
    private static final int PHONE_NUMBERS_COLUMN = 1;
    private static final String USER_NAME = "user_name";
    private static final String PHONE_NUMBERS = "phone_numbers";

    public UserTableModel() {
        super(new String[]{USER_NAME, PHONE_NUMBERS}, 0);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public void addUser(User user) {
        addRow(new Object[]{user.username(), user.phoneNumbers()});
    }

    public User getUser(int rowIndex) {
        return new User(
                getValueAt(rowIndex, NAME_COLUMN).toString(),
                (List<String>) getValueAt(rowIndex, PHONE_NUMBERS_COLUMN)
        );
    }

    public Optional<User> getUserByUsername(String username) {
        for (int i = 0; i < getRowCount(); i++) {
            if (getValueAt(i, NAME_COLUMN).equals(username)) {
                return Optional.of(getUser(i));
            }
        }
        return Optional.empty();
    }
}
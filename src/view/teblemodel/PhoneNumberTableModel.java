package view.teblemodel;

import entity.PhoneNumber;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class PhoneNumberTableModel extends DefaultTableModel {

    private static final String PHONE_NUMBER_ID = "phone_number_id";
    private static final String USER_ID = "owner_user_id";
    private static final String PHONE_NUMBER = "phone_number";

    public PhoneNumberTableModel(List<PhoneNumber> initialData) {
        super(new String[]{PHONE_NUMBER_ID, PHONE_NUMBER, USER_ID}, 0);
        addAll(initialData);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public void add(PhoneNumber phoneNumber) {
        addRow(new Object[]{
                phoneNumber.getId(),
                phoneNumber.getPhoneNumber(),
                phoneNumber.getUserId()
        });
    }

    public void addAll(Iterable<PhoneNumber> phoneNumbers) {
        phoneNumbers.forEach(this::add);
    }
}
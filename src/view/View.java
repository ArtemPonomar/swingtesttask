package view;

import controller.Controller;
import entity.PhoneNumber;
import entity.User;
import view.teblemodel.PhoneNumberTableModel;
import view.teblemodel.UserTableModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

public class View {
    private static final String PHONE_NUMBERS_HEADER = "Phone Numbers:\n";
    private static final String EMPLOYEE_NOT_FOUND = "Employee not found!";
    private static final String NO_REGISTERED_MOBILE_NUMBERS = "Employee does not have registered mobile numbers!";
    private static final String WINDOW_TITLE = "Swing Database";
    private static final Color TRIANGLE_COLOR = Color.RED;
    private static final String SEARCH_PANEL_LABEL = "Name:";
    private static final String SEARCH_BUTTON_LABEL = "Search";
    private static final int SEARCH_PANEL_COLUMNS = 20;
    private static final Rectangle CONTENT_BOUNDS = new Rectangle(60, 60, 680, 380);
    private static final Rectangle BACKGROUND_BOUNDS = new Rectangle(0, 0, 800, 500);
    private static final Dimension WINDOW_SIZE = new Dimension(800, 500);
    private static final int TABLES_DIVIDER_LOCATION = 335;
    private Controller controller;
    private JFrame frame;
    private JPanel backgroundPanel;
    private JPanel searchPanel;
    private JScrollPane userTable;
    private JScrollPane phoneNumberTable;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setUserTableModel(List<User> users) {
        this.userTable = new JScrollPane(new JTable(new UserTableModel(users)));
    }

    public void setPhoneNumberTableModel(List<PhoneNumber> phoneNumbers) {
        this.phoneNumberTable = new JScrollPane(new JTable(new PhoneNumberTableModel(phoneNumbers)));
    }

    public void createAndShowGUI() {
        drawFrame();
        drawTrianglePanel();
        drawSearchPanel();
        composeFrame();
    }

    public void displayPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        StringBuilder message = new StringBuilder(PHONE_NUMBERS_HEADER);
        for (PhoneNumber phoneNumber : phoneNumbers) {
            message.append(phoneNumber.getPhoneNumber()).append("\n");
        }
        JOptionPane.showMessageDialog(frame, message.toString());
    }

    public void displayErrorMessageUserNotFound() {
        JOptionPane.showMessageDialog(frame, EMPLOYEE_NOT_FOUND);
    }

    public void displayErrorMessageNoMobileNumbers() {
        JOptionPane.showMessageDialog(frame, NO_REGISTERED_MOBILE_NUMBERS);
    }

    public void hideTriangle() {
        backgroundPanel.setVisible(false);
    }

    private void drawFrame(){
        frame = new JFrame(WINDOW_TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
    }

    private void drawTrianglePanel() {
        backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int[] xPoints = {0, getWidth() / 2, getWidth()};
                int[] yPoints = {getHeight(), 0, getHeight()};
                g.setColor(TRIANGLE_COLOR);
                g.fillPolygon(xPoints, yPoints, 3);
            }
        };
    }

    private void drawSearchPanel() {
        searchPanel = new JPanel();
        searchPanel.add(new JLabel(SEARCH_PANEL_LABEL));
        JTextField nameField = new JTextField(SEARCH_PANEL_COLUMNS);
        searchPanel.add(nameField);
        JButton searchButton = new JButton(SEARCH_BUTTON_LABEL);
        searchButton.addActionListener(e -> controller.searchButtonClicked(nameField.getText()));
        searchPanel.add(searchButton);
    }

    private void composeFrame(){
        JSplitPane tablesPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, userTable, phoneNumberTable);
        tablesPanel.setDividerLocation(TABLES_DIVIDER_LOCATION);
        JSplitPane foregroundPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, searchPanel, tablesPanel);
        foregroundPanel.setBounds(CONTENT_BOUNDS);
        backgroundPanel.setBounds(BACKGROUND_BOUNDS);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(WINDOW_SIZE);
        layeredPane.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(foregroundPanel, JLayeredPane.POPUP_LAYER);

        frame.add(layeredPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
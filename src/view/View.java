package view;

import controller.Controller;
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
import javax.swing.table.TableModel;
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
    private static final Rectangle CONTENT_BOUNDS = new Rectangle(60, 60, 480, 180);
    private static final Rectangle BACKGROUND_BOUNDS = new Rectangle(0, 0, 600, 300);
    private static final Dimension WINDOW_SIZE = new Dimension(600, 300);
    private Controller controller;
    private JFrame frame;
    private JPanel trianglePanel;
    private JPanel searchPanel;
    private JScrollPane userTable;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setModel(TableModel userTableModel) {
        this.userTable = new JScrollPane(new JTable(userTableModel));
    }

    public void createAndShowGUI() {
        drawFrame();
        drawTrianglePanel();
        drawSearchPanel();
        composeFrame();
    }

    public void displayPhoneNumbers(List<String> phoneNumbers) {
        StringBuilder message = new StringBuilder(PHONE_NUMBERS_HEADER);
        for (String phoneNumber : phoneNumbers) {
            message.append(phoneNumber).append("\n");
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
        trianglePanel.setVisible(false);
    }

    private void drawFrame(){
        frame = new JFrame(WINDOW_TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
    }

    private void drawTrianglePanel() {
        trianglePanel = new JPanel() {
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
        JSplitPane mainContent = new JSplitPane(JSplitPane.VERTICAL_SPLIT, searchPanel, userTable);
        mainContent.setBounds(CONTENT_BOUNDS);
        trianglePanel.setBounds(BACKGROUND_BOUNDS);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(WINDOW_SIZE);
        layeredPane.add(trianglePanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(mainContent, JLayeredPane.POPUP_LAYER);

        frame.add(layeredPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
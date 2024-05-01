package src.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import src.model.*;

public class WardView {
    private JFrame frame;
    private JList<String> wardList;
    private DefaultListModel<String> listModel;
    private JButton refreshButton;

    public WardView() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Ward Management System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        listModel = new DefaultListModel<>();
        wardList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(wardList);

        refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> {
            // Call method to refresh ward list
            // For example: refreshWardList();
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(refreshButton);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
    }

    public void displayWards(List<Ward> wards) {
        listModel.clear();
        for (Ward ward : wards) {
            listModel.addElement(ward.toString());
        }
        frame.setVisible(true);
    }
}


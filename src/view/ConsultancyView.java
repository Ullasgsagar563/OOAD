package src.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import src.model.Consultancy;
public class ConsultancyView {
    private JFrame frame;
    private JList<String> consultancyList;
    private DefaultListModel<String> listModel;
    private JButton refreshButton;

    public ConsultancyView() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Consultancy Management System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        listModel = new DefaultListModel<>();
        consultancyList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(consultancyList);

        refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call method to refresh consultancy list
                // For example: refreshConsultancyList();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(refreshButton);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
    }

    public void displayConsultancies(List<Consultancy> consultancies) {
        listModel.clear();
        for (Consultancy consultancy : consultancies) {
            listModel.addElement(consultancy.toString());
        }
        frame.setVisible(true);
    }
}

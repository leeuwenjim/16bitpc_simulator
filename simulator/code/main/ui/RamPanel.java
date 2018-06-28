package main.ui;

import main.Converter;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RamPanel extends JPanel {

    public JTable table;
    public JButton btnLoad;

    public RamPanel() {
        setLayout(null);
        setBorder(new LineBorder(new Color(0, 0, 0)));

        JLabel lblRandomAccessMemory = new JLabel("Random Access Memory");
        lblRandomAccessMemory.setBounds(10, 11, 174, 14);
        lblRandomAccessMemory.setFont(Window.boldFont);
        add(lblRandomAccessMemory);

        btnLoad = new JButton("Load program");
        btnLoad.setBounds(192, 11, 140, 23);
        add(btnLoad);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 43, 322, 245);
        add(scrollPane);

        String[][] data = new String[1024][2];
        for (int i = 0; i < data.length; i++) {
            data[i][0] = Integer.toString(i);
            data[i][1] = Converter.getBinary16String(0);
        }

        table = new JTable();
        table.setEnabled(false);
        table.setModel(new DefaultTableModel(
                data,
                new String[] {
                        "Index", "Data"
                }
        ));

        scrollPane.setColumnHeaderView(table.getTableHeader());
        scrollPane.setViewportView(table);

    }
}

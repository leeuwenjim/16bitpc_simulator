package main.ui;

import oracle.jrockit.jfr.JFR;

import javax.swing.*;
import java.awt.*;

public class Window {

    private JFrame frame;
    private JPanel contentPane;
    public OverviewPanel overView;

    public static Font boldFont = new Font("Tahoma", Font.BOLD, 11);

    public Window() {
        frame = new JFrame();
        frame.setTitle("16 bit pc simulator");

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 915, 635);
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());

        overView = new OverviewPanel();
        contentPane.add(overView, BorderLayout.CENTER);

        frame.setContentPane(contentPane);

        frame.setVisible(true);
    }

}

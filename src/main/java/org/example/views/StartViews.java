package org.example.views;

import javax.swing.*;

public class StartViews {

    private static JFrame appFrame = new JFrame("Productos");

    public static void startViews() {

        appFrame.setContentPane(new ProductsViews().getBodyPanel());
        appFrame.pack();
        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appFrame.setVisible(true);
        appFrame.setResizable(false);
        appFrame.setSize(750, 500);
        appFrame.setLocationRelativeTo(null);
    }

    public static JFrame getAppFrame() {
        return appFrame;
    }
}

package org.example.views;

import org.example.controllers.ProductController;
import org.example.models.Product;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FiltersProduct extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextField textField2;
    private JLabel priceLabel2;
    private JLabel priceLabel3;

    private static ArrayList<Product> products;

    public FiltersProduct() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        if (!ProductController.checkBlank(textField1.getText(),textField2.getText())){
            try {
                String textCheck = "asw";
                String textCheck2 = "asw";
                if (textField2.getText().contains(".")) {
                    textCheck = textField2.getText().substring(textField2.getText().indexOf("."));
                }
                if (textField1.getText().contains(".")) {
                    textCheck2 = textField1.getText().substring(textField2.getText().indexOf("."));
                }
                if (textCheck.length() <=3 && textCheck2.length() <=3) {
                    float price1 = Float.parseFloat(textField2.getText());
                    float price2 = Float.parseFloat(textField2.getText());
                    if (price1 > 0.0 && price2 > 0.0) {
                        products = ProductController.filter(price1,price2);
                        JOptionPane.showMessageDialog(this, "Filtro aplicado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        ProductsViews.endDialogResults = true;
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Introduzca un precio válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Introduzca un precio válido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                System.out.println("Ha ocurrido el siguiente error: " + e);
                JOptionPane.showMessageDialog(this, "Introduzca un precio válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static ArrayList<Product> start() {
        FiltersProduct dialog = new FiltersProduct();
        dialog.pack();
        dialog.setResizable(false);
        dialog.setLocation(StartViews.getAppFrame().getLocation());
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        return products;
    }
}

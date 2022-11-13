package org.example.views;

import org.example.controllers.ProductController;

import javax.swing.*;
import java.awt.event.*;

public class AddProduct extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JLabel nameLabel;
    private JLabel priceLabel;
    private JLabel categoryLabel;

    public AddProduct() {
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

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        if (!ProductController.checkBlank(textField1.getText(),textField2.getText(),textField3.getText())) {
            if (textField1.getText().length() >= 3) {
                if (ProductController.checkName(textField1.getText())) {
                    if (!ProductController.checkCategory(textField3.getText())){
                        try {
                            String textCheck = "asw";
                            if (textField2.getText().contains(".")) {
                                textCheck = textField2.getText().substring(textField2.getText().indexOf("."));
                            }
                            if (textCheck.length() <=3) {
                                float price = Float.parseFloat(textField2.getText());
                                if (price > 0.0) {
                                    ProductController.insertProducts(textField1.getText(),price,textField3.getText());
                                    JOptionPane.showMessageDialog(this, "Producto añadido correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
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
                        JOptionPane.showMessageDialog(this, "No existe esa categoría.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Ya existe ese producto.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "El producto debe tener tres o más letras.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onCancel() {
        dispose();
    }

    public static void start() {
        AddProduct dialog = new AddProduct();
        dialog.pack();
        dialog.setResizable(false);
        dialog.setLocation(StartViews.getAppFrame().getLocation());
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}

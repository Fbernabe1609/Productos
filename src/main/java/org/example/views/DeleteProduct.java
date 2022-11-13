package org.example.views;

import org.example.controllers.ProductController;

import javax.swing.*;
import java.awt.event.*;

public class DeleteProduct extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JLabel nameLabel;

    public DeleteProduct() {
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
        if (!ProductController.checkBlank(textField1.getText())){
            if (!ProductController.checkName(textField1.getText())) {
                ProductController.deleteProducts(textField1.getText());
                JOptionPane.showMessageDialog(this, "Producto borrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                ProductsViews.endDialogResults = true;
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "El producto no existe.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onCancel() {
        dispose();
    }

    public static void start() {
        DeleteProduct dialog = new DeleteProduct();
        dialog.pack();
        dialog.setResizable(false);
        dialog.setLocation(StartViews.getAppFrame().getLocation());
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}

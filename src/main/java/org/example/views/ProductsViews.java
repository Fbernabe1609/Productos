package org.example.views;

import org.example.controllers.ProductController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

public class ProductsViews {
    private JPanel bodyPanel;
    private JPanel optionsPanel;
    private JPanel contentPanel;
    private JPanel showHideInformationPanel;
    private JPanel tablePanel;
    private JLabel productsLabel;
    private JButton searchButton;
    private JButton editButton;
    private JButton addButton;
    private JButton deleteButton;
    private JPanel buttonsPanel;
    private JLabel logoLabel;

    private TableRowSorter<TableModel> orderModel;

    public ProductsViews() {
        DefaultTableModel defaultTableModel =new DefaultTableModel() {
            @Override
            public Class getColumnClass(int columna) {
                if (columna == 0 || columna == 3) {
                    return Integer.class;
                }
                else if (columna == 2) {
                    return Float.class;
                } else {
                    return String.class;
                }
            }
        };
        defaultTableModel.addColumn("ID Producto");
        defaultTableModel.addColumn("Nombre");
        defaultTableModel.addColumn("Precio");
        defaultTableModel.addColumn("ID Categoría");
        defaultTableModel.addColumn("Categoría");
        for (int i = 0; i < ProductController.createProducts().size(); i++) {
            defaultTableModel.addRow(new Object[] {ProductController.createProducts().get(i).getProductID(),
                    ProductController.createProducts().get(i).getName(),
                    ProductController.createProducts().get(i).getPrice(),
                    ProductController.createProducts().get(i).getCategoryID(),
                    ProductController.createProducts().get(i).getCategory()
            });
        }

        tablePanel.add(createTable(defaultTableModel));
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public JPanel getBodyPanel() {
        return bodyPanel;
    }

    public JScrollPane createTable(DefaultTableModel defaultTableModel) {
        JTable jt = new JTable(defaultTableModel) {
            @Override
            public boolean editCellAt(int row, int column, EventObject e) {
                return false;
            }
        };
        jt.setModel(defaultTableModel);
        jt.setRowSelectionAllowed(false);
        jt.getTableHeader().setBackground(productsLabel.getBackground());
        jt.getTableHeader().setForeground(Color.white);
        orderModel = new TableRowSorter<>(defaultTableModel);
        jt.setRowSorter(orderModel);
        return new JScrollPane(jt);
    }
}
package org.example.views;

import org.example.controllers.ProductController;
import org.example.models.ModelProduct;
import org.example.models.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.EventObject;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;

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
    private JButton filterButton;
    private JButton resetFiltersButton;

    static Boolean endDialogResults = false;

    private TableRowSorter<TableModel> orderModel;

    private ArrayList<Product> products;

    public ProductsViews() {

        ModelProduct.startConnection();

        tablePanel.add(createTable(ProductController.createProducts()));
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteProduct.start();
                if (endDialogResults){
                    tablePanel.removeAll();
                    tablePanel.updateUI();
                    tablePanel.add(createTable(ProductController.createProducts()));
                    endDialogResults = false;
                }
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddProduct.start();
                if (endDialogResults){
                    tablePanel.removeAll();
                    tablePanel.updateUI();
                    tablePanel.add(createTable(ProductController.createProducts()));
                    endDialogResults = false;
                }
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditProduct.start();
                if (endDialogResults){
                    tablePanel.removeAll();
                    tablePanel.updateUI();
                    tablePanel.add(createTable(ProductController.createProducts()));
                    endDialogResults = false;
                }
            }
        });

        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                products = FiltersProduct.start();
                if (endDialogResults){
                    tablePanel.removeAll();
                    tablePanel.updateUI();
                    tablePanel.add(createTable(products));
                    endDialogResults = false;
                }
            }
        });

        resetFiltersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tablePanel.removeAll();
                tablePanel.updateUI();
                tablePanel.add(createTable(ProductController.createProducts()));
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                ModelProduct.stopConnection();
            }
        });
    }

    public JPanel getBodyPanel() {
        return bodyPanel;
    }

    public JScrollPane createTable(ArrayList<Product> products2) {

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

        for (int i = 0; i <products2.size(); i++) {
            defaultTableModel.addRow(new Object[] {products2.get(i).getProductID(),
                    products2.get(i).getName().toUpperCase(),
                    products2.get(i).getPrice(),
                    products2.get(i).getCategoryID(),
                    products2.get(i).getCategory()
            });
        }
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
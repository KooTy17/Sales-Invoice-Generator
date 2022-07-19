/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package salesinvoicegenerator.view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author KooTy
 */
public class CreateNewInvoiceDialog extends JDialog{
    private JTextField invoiceCustomerName;
    private JTextField invoiceDate;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JButton createInvoiceButton;
    private JButton cancelInvoiceButton;
    
    public CreateNewInvoiceDialog(SalesFrame frame)
    {
        jLabel1 = new JLabel("Invoice Date :");
        invoiceCustomerName = new JTextField(20);
        jLabel2 = new JLabel("Customer Name :");
        invoiceDate = new JTextField(20);
        createInvoiceButton = new JButton("Create");
        cancelInvoiceButton = new JButton("Cancel");
        createInvoiceButton.setActionCommand("Create Invoice Button");
        cancelInvoiceButton.setActionCommand("Cancel Create Invoice");
        createInvoiceButton.addActionListener(frame.getController());
        cancelInvoiceButton.addActionListener(frame.getController());
        setLayout(new GridLayout(3, 2));
        add(jLabel1);
        add(invoiceDate);
        add(jLabel2);
        add(invoiceCustomerName);
        add(createInvoiceButton);
        add(cancelInvoiceButton);
        pack();
        
    }

    public JTextField getInvoiceCustomerName() {
        return invoiceCustomerName;
    }

    public JTextField getInvoiceDate() {
        return invoiceDate;
    
    }
}

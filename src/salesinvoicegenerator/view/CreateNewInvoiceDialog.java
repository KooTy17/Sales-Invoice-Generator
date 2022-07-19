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
    private JTextField invDateField;
    private JLabel custNameLbl;
    private JLabel invDateLbl;
    private JButton okBtn;
    private JButton cancelBtn;
    
    public CreateNewInvoiceDialog(SalesFrame frame)
    {
        custNameLbl = new JLabel("Customer Name:");
        invoiceCustomerName = new JTextField(20);
        invDateLbl = new JLabel("Invoice Date:");
        invDateField = new JTextField(20);
        okBtn = new JButton("OK");
        cancelBtn = new JButton("Cancel");
        
        okBtn.setActionCommand("Create Invoice Button");
        cancelBtn.setActionCommand("Cancel Create Invoice");
        
        okBtn.addActionListener(frame.getController());
        cancelBtn.addActionListener(frame.getController());
        setLayout(new GridLayout(3, 2));
        
        add(invDateLbl);
        add(invDateField);
        add(custNameLbl);
        add(invoiceCustomerName);
        add(okBtn);
        add(cancelBtn);
        
        pack();
        
    }

    public JTextField getInvoiceCustomerName() {
        return invoiceCustomerName;
    }

    public JTextField getInvoiceDate() {
        return invDateField;
    
    }
}

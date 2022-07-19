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
public class CreateNewItemDialog extends JDialog{
    
    private JTextField itemName;
    private JTextField itemCount;
    private JTextField itemPrice;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JButton createItemButton;
    private JButton cancelItemButton;
  
    public CreateNewItemDialog(SalesFrame frame)
    {
        itemName = new JTextField(20);
        jLabel1 = new JLabel("Item Name");
        itemCount = new JTextField(20);
        jLabel2 = new JLabel("Item Count");
        itemPrice = new JTextField(20);
        jLabel3 = new JLabel("Item Price");
        createItemButton = new JButton("Create");
        cancelItemButton = new JButton("Cancel");
        createItemButton.setActionCommand("Create Item Button");
        cancelItemButton.setActionCommand("Cancel Create Item");
        createItemButton.addActionListener(frame.getController());
        cancelItemButton.addActionListener(frame.getController());
        setLayout(new GridLayout(4, 2));
        add(jLabel1);
        add(itemName);
        add(jLabel2);
        add(itemCount);
        add(jLabel3);
        add(itemPrice);
        add(createItemButton);
        add(cancelItemButton);
        pack();
    }

    public JTextField getItemNameField() {
        return itemName;
    }

    public JTextField getItemCountField() {
        return itemCount;
    }

    public JTextField getItemPriceField() {
        return itemPrice;
    }
    
}

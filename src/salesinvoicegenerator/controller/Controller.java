/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package salesinvoicegenerator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import salesinvoicegenerator.model.InvoiceHeader;
import salesinvoicegenerator.model.InvoiceLine;
import salesinvoicegenerator.model.InvoiceLineTableModel;
import salesinvoicegenerator.view.CreateNewInvoiceDialog;
import salesinvoicegenerator.view.CreateNewItemDialog;

import salesinvoicegenerator.view.SalesFrame;

/**
 *
 * @author KooTy
 */
public class Controller implements ActionListener, ListSelectionListener {
   // InvoiceLine lin = new InvoiceLine();
    private DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    private SalesFrame frame;
    private CreateNewInvoiceDialog createNewInvoiceDialog;
    private CreateNewItemDialog createNewItemDialog;
    
    public Controller(SalesFrame frame)
    {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        switch (actionCommand)
        {
            case "Load File":
                loadFile();
                break;
            case "Save File":
                saveFile();
                break;
            case "Create New Invoice":
                createNewInvoice();
                break;
            case "Create Invoice Button":
                createInvoiceButton();
                break;
            case "Cancel Create Invoice":
                cancelInoivceButton();
                break;
            case "Delete Invoice":
                deleteInvoice();
                break;
            case "Create New Item":
                createNewItem();
                break;
            case "Create Item Button":
                createItemButton();
                break;
            case "Cancel Create Item":
                cancelCreateItem();
                break;
            case "Delete Item":
                deleteItem();
                break;
            
            
        }
        
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
       // System.out.println("Row Seleceted");
        int selectedRow = frame.getInvoiceTable().getSelectedRow();
       // System.out.println(selectedRow);
        if (selectedRow >= 0)
        {
            
            ArrayList<InvoiceLine> lines = frame.getInvoiceHeadersList().get(selectedRow).getLines();
            frame.getInvoiceItem().setModel(new InvoiceLineTableModel(lines));
            frame.setLineTableModel(new InvoiceLineTableModel(lines));
            frame.getInvoiceItem().setModel(frame.getLineTableModel());
            InvoiceHeader header = frame.getHeaderTableModel().getHeaderData().get(selectedRow);
            frame.getInvoiceNumber().setText("" + header.getInvoiceNum());
            frame.getInvoiceDate().setText(df.format(header.getInvoiceDate()));
            frame.getCustomerName().setText(header.getCustomerName());
            frame.getInvoiceTotal().setText("" + header.getInvoiceTotal());
            
            
        }
    }

    private void loadFile() {
        try
        {
        JFileChooser fc = new JFileChooser();
        int result = fc.showOpenDialog(frame);
        if ( result == JFileChooser.APPROVE_OPTION)
        {
            File headerFile = fc.getSelectedFile();
            String headerStrPath = headerFile.getAbsolutePath();
            Path headerPath = Paths.get(headerStrPath);
            List<String> headerLines = Files.lines(headerPath).collect(Collectors.toList());
            ArrayList<InvoiceHeader> invoiceHeadersList = new ArrayList<>();
            for ( String headerLine : headerLines)
            {
                String[] parts = headerLine.split(",");
                int id = Integer.parseInt(parts[0]);
                
                try
                {
                    Date invDate = df.parse(parts[1]);
                    InvoiceHeader invHeader = new InvoiceHeader(id, invDate, parts[2]);
                    invoiceHeadersList.add(invHeader);
                }
                catch (ParseException ex) {
                    System.out.println("Date Format Error");
            
        }
                
            }
            System.out.println("check");
            result = fc.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION)
            {
                String lineStrpath = fc.getSelectedFile().getAbsolutePath();
                Path linePath = Paths.get(lineStrpath);
                List<String> lineLines = Files.lines(linePath).collect(Collectors.toList());
                for (String lineLine : lineLines)
                {
                    
                    String[] parts = lineLine.split(",");
                    int invId = Integer.parseInt(parts[0]);
                    double price = Double.parseDouble(parts[2]);
                    int count = Integer.parseInt(parts[3]);
                    InvoiceHeader header = getInvoiceHeaderById(invoiceHeadersList, invId);
                    InvoiceLine invLine = new InvoiceLine(parts[1], price, count, header);
                    header.getLines().add(invLine);
                }
                frame.setInvoiceHeadersList(invoiceHeadersList);
            }
        }
        }
        catch(IOException ex)
        {
            System.out.println("File Error");
        }
    }
    private InvoiceHeader getInvoiceHeaderById(ArrayList<InvoiceHeader> invoices, int id)
    {
        for (InvoiceHeader invoice : invoices)
        {
            if (invoice.getInvoiceNum() == id)
            { 
                return invoice;
            }
        }
        return null;
    }

    private void saveFile() {
        try
        {
        JFileChooser fc = new JFileChooser();
        String headers = "";
        String lines = "";
        int result = fc.showSaveDialog(frame);
        for (InvoiceHeader header : frame.getInvoiceHeadersList() )
            {
                headers += header.toString();
                headers += "\n";
                for (InvoiceLine line : header.getLines())
                {
                    lines += line.toString();
                    lines += "\n";
                }
            }
        if ( result == JFileChooser.APPROVE_OPTION)
        {
            File headerFile = fc.getSelectedFile();
            FileWriter hedearfw = new FileWriter(headerFile);
            hedearfw.write(headers);
            hedearfw.flush();
            hedearfw.close();
            result = fc.showSaveDialog(frame);
            if ( result == JFileChooser.APPROVE_OPTION)
            {
                File lineFile = fc.getSelectedFile();
                FileWriter linefw = new FileWriter(lineFile);
                linefw.write(lines);
                linefw.flush();
                linefw.close();
            }
            
            
        }
       

        }
        catch (IOException ex) {
            System.out.println("File Error");
            
        }
    }

    private void createNewInvoice() {
        createNewInvoiceDialog = new CreateNewInvoiceDialog(frame);
        createNewInvoiceDialog.setVisible(true);
      //  frame.setCreateNewInvoiceDialog(new CreateNewInvoiceDialog(frame));
        //frame.getCreateNewInvoiceDialog().setVisible(true);

    }
    
    
    private void createInvoiceButton() {
        createNewInvoiceDialog.setVisible(false);
        String invName = createNewInvoiceDialog.getInvoiceCustomerName().getText();
        String inveDate = createNewInvoiceDialog.getInvoiceDate().getText();
        createNewInvoiceDialog.dispose();
        createNewInvoiceDialog = null;
        try
        {
        Date invDate = df.parse(inveDate);
        int id = 0;
        for (InvoiceHeader header : frame.getInvoiceHeadersList())
        {
            if (header.getInvoiceNum() > id)
            {
                id = header.getInvoiceNum();
            }
        }
        id++;
        InvoiceHeader header = new InvoiceHeader(id, invDate, invName);
        frame.getInvoiceHeadersList().add(header);
        frame.getHeaderTableModel().fireTableDataChanged();
        
        } catch (ParseException ex) {
            System.out.println("Date Format Error");
            
        }
                
        
       
    }
    
    private void cancelInoivceButton() {
        createNewInvoiceDialog.setVisible(false);
        createNewInvoiceDialog.dispose();
        createNewInvoiceDialog = null;
    }

    private void deleteInvoice() {
        int invoiceData = frame.getInvoiceTable().getSelectedRow();
        InvoiceHeader header = frame.getHeaderTableModel().getHeaderData().get(invoiceData);
        frame.getHeaderTableModel().getHeaderData().remove(invoiceData);
        frame.getHeaderTableModel().fireTableDataChanged();
        frame.setLineTableModel(new InvoiceLineTableModel(new ArrayList<InvoiceLine>()));
        frame.getInvoiceItem().setModel(frame.getLineTableModel());
        frame.getLineTableModel().fireTableDataChanged();
    }

    private void createNewItem() {
        createNewItemDialog = new CreateNewItemDialog(frame);
        createNewItemDialog.setVisible(true);
        
        
    }
    
    private void createItemButton() {
        createNewItemDialog.setVisible(false);
        String itemName = createNewItemDialog.getItemNameField().getText();
        String itemCount = createNewItemDialog.getItemCountField().getText();
        String itemPrice = createNewItemDialog.getItemPriceField().getText();
        int count = Integer.parseInt(itemCount);
        double price = Integer.parseInt(itemPrice);
        int selectedHeader = frame.getInvoiceTable().getSelectedRow();
        InvoiceHeader header = frame.getHeaderTableModel().getHeaderData().get(selectedHeader);
        InvoiceLine line = new InvoiceLine(itemName, price, count, header);
        header.addInvoiceLine(line);
        frame.getHeaderTableModel().fireTableDataChanged();
        frame.getLineTableModel().fireTableDataChanged();
    }
    
    
    private void cancelCreateItem() {
        
        createNewItemDialog.setVisible(false);
        createNewItemDialog.dispose();
        createNewItemDialog = null;
    }

    private void deleteItem() {
        int invoiceData = frame.getInvoiceItem().getSelectedRow();
        //ArrayList<InvoiceLine> invoiceLinesList = new ArrayList<>();
        InvoiceLine line = frame.getLineTableModel().getLineData().get(invoiceData);
        frame.getLineTableModel().getLineData().remove(invoiceData);
        frame.getHeaderTableModel().fireTableDataChanged();
        frame.getLineTableModel().fireTableDataChanged();
        frame.getInvoiceNumber().setText("" + line.getHeader().getInvoiceTotal());
        
    }

    


    

    

    

    
    
}

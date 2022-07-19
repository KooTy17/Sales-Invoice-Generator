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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import salesinvoicegenerator.model.InvoiceHeader;
import salesinvoicegenerator.model.InvoiceLine;
import salesinvoicegenerator.model.InvoiceLineTableModel;
import salesinvoicegenerator.view.SalesFrame;

/**
 *
 * @author KooTy
 */
public class Controller implements ActionListener, ListSelectionListener {
   // InvoiceLine lin = new InvoiceLine();
    private SalesFrame frame;
    
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
            case "Delete Invoice":
                deleteInvoice();
                break;
            case "Create New Item":
                createNewItem();
                break;
            case "Delete Item":
                deleteItem();
                break;
            
            
        }
        
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        System.out.println("Row Seleceted");
        int selectedRow = frame.getInvoiceTable().getSelectedRow();
        System.out.println(selectedRow);
        if (selectedRow >= 0)
        {
            InvoiceHeader header = frame.getHeaderTableModel().getData().get(selectedRow);
            ArrayList<InvoiceLine> lines = frame.getInvoiceHeadersList().get(selectedRow).getLines();
            frame.getInvoiceItem().setModel(new InvoiceLineTableModel(lines));
            frame.getInvoiceNumber().setText("" + header.getInvoiceNum());
            frame.getInvoiceDate().setText(header.getInvoiceDate());
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
                InvoiceHeader invHeader = new InvoiceHeader(id, parts[1], parts[2]);
                invoiceHeadersList.add(invHeader);
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
            ex.printStackTrace();
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
        JFileChooser fc = new JFileChooser();
        int result = fc.showSaveDialog(frame);
       
            
        
    }

    private void createNewInvoice() {
    }

    private void deleteInvoice() {
        int invoiceData = frame.getInvoiceTable().getSelectedRow();
        InvoiceHeader header = frame.getHeaderTableModel().getData().get(invoiceData);
        frame.getHeaderTableModel().getData().remove(invoiceData);
        frame.getHeaderTableModel().fireTableDataChanged();
        frame.setLineTableModel(new InvoiceLineTableModel(new ArrayList<InvoiceLine>()));
        frame.getInvoiceItem().setModel(frame.getLineTableModel());
        frame.getLineTableModel().fireTableDataChanged();
    }

    private void createNewItem() {
        
    }

    private void deleteItem() {
        int invoiceData = frame.getInvoiceItem().getSelectedRow();
        //ArrayList<InvoiceLine> invoiceLinesList = new ArrayList<>();
        InvoiceLine lin = frame.getLineTableModel().getData().get(invoiceData);
        frame.getHeaderTableModel().getData().remove(invoiceData);
        frame.getHeaderTableModel().fireTableDataChanged();
        frame.getLineTableModel().fireTableDataChanged();
        frame.getInvoiceNumber().setText("" + lin.getHeader().getInvoiceTotal());
        //displayInvoices();
    }
    //private void displayInvoices(){
         //for (InvoiceHeader header :frame.getInvoiceHeadersList()) {
          //   System.out.println(header);
       //  }
}

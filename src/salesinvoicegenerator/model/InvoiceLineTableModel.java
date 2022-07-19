/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package salesinvoicegenerator.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author KooTy
 */
public class InvoiceLineTableModel extends AbstractTableModel{
    
    private ArrayList<InvoiceLine> lineData;
    
    private String[] cols = {"Item Name", "Unit Price", "Count", "Item Total"};

    public InvoiceLineTableModel(ArrayList<InvoiceLine> lineData) {
        this.lineData = lineData;
    }

    

    @Override
    public int getRowCount() {
        return lineData.size();
    }

    @Override
    public int getColumnCount() {
        return cols.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceLine line = lineData.get(rowIndex);
        switch(columnIndex)
        {
            case 0:
                return line.getItemName();
            case 1:
                return line.getItemPrice();
            case 2:
                return line.getCount();
            case 3:
                return line.getLineTotal();
                
        }
            
            return "";
             
    }

    public ArrayList<InvoiceLine> getLineData() {
        return lineData;
    }

    public void setData(ArrayList<InvoiceLine> data) {
        this.lineData = data;
    }

    @Override
    public String getColumnName(int column) {
        return cols[column];
    }
    
}

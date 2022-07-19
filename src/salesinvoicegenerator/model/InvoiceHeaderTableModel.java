/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package salesinvoicegenerator.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author KooTy
 */
public class InvoiceHeaderTableModel extends AbstractTableModel{
    private ArrayList<InvoiceHeader> headerData;
    private String[] cols = {"Id", "Invoice Date", "Customer Name", "Invoice Total"};
    private DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

    public InvoiceHeaderTableModel(ArrayList<InvoiceHeader> headerData) {
        this.headerData = headerData;
    }

    public ArrayList<InvoiceHeader> getHeaderData() {
        return headerData;
    }

    @Override
    public int getRowCount() {
        return headerData.size();
    }

    @Override
    public int getColumnCount() {
        return cols.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceHeader header = headerData.get(rowIndex);
        switch (columnIndex)
        {
            case 0:
                return header.getInvoiceNum();
            case 1:
                return df.format(header.getInvoiceDate());
            case 2:
                return header.getCustomerName();
            case 3:
                return header.getInvoiceTotal();
        }
        return null;
    }
    @Override
    public String getColumnName(int column)
{

        return cols[column];

}
}


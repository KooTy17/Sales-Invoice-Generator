/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package salesinvoicegenerator.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author KooTy
 */
public class InvoiceHeader {
    private int invoiceNum;
    private Date invoiceDate;
    private String customerName;
    private ArrayList<InvoiceLine> lines;

    public InvoiceHeader(int invoiceNum, Date invoiceDate, String customerName) {
        this.invoiceNum = invoiceNum;
        this.invoiceDate = invoiceDate;
        this.customerName = customerName;
    }

    public int getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(int invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getInvoiceTotal() {
        double total = 0;
        for (InvoiceLine line : getLines())
        {
            total += line.getLineTotal();
        }
        return total;
    }

    public void setLines(ArrayList<InvoiceLine> lines) {
        this.lines = lines;
    }

    public ArrayList<InvoiceLine> getLines() {
        if (lines == null)
        {
            lines = new ArrayList<>();
        }
        return lines;
    }
    
    @Override
    public String toString() {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return "" + getInvoiceNum() + "," + df.format(getInvoiceDate()) + "," + getCustomerName();
    }

    
    
}

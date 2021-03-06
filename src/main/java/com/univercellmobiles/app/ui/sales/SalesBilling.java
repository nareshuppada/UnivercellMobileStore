package com.univercellmobiles.app.ui.sales;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.univercellmobiles.app.beans.AccessoryStock;
import com.univercellmobiles.app.beans.PhoneStock;
import com.univercellmobiles.app.beans.Sales;
import com.univercellmobiles.app.service.AccessoryStockService;
import com.univercellmobiles.app.service.PhoneModelService;
import com.univercellmobiles.app.service.PhoneStockService;
import com.univercellmobiles.app.service.SalesService;
import com.univercellmobiles.app.ui.common.custom.AutocompleteJComboBox;
import com.univercellmobiles.app.ui.common.custom.StringSearchable;
import com.univercellmobiles.app.util.ConfigBuilder;

import javax.swing.JLabel;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.jfree.ui.RefineryUtilities;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Dimension;

import javax.swing.JSeparator;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import java.awt.Window.Type;

public class SalesBilling extends JFrame {
	private JTextField txtPrice;
	private JTable table;
	  private JTable tableStock;
	  static SalesBilling frame;
	  private TableRowSorter<StockTableModel> sorter;
	private boolean DEBUG = false;
	InvoiceTableModel im;
	
	private Float totalCost = (float) 0.0;
	AutocompleteJComboBox comboModelSearch;
	 ConfigurableApplicationContext context = ConfigBuilder.getAppContext();
		
		AccessoryStockService as = (AccessoryStockService) context.getBean("accessoryStockService");
		PhoneStockService pss = (PhoneStockService) context.getBean("phoneStockService");
		
		private JTextField txtGrandTotal;
		private JTextField txtCustName;
		private JTextField txtCustContact;
		private JTextField txtInvoiceId;
		private JTextField txtVat;
		private JTextField txtDiscount;
		JTextArea txtAreaOffer;
		JTextArea txtAreaDesc;
		private JTextField txtScanIMEI;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new SalesBilling();
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SalesBilling() {
		frame = this;
		setTitle("Phone Billing");
		//setAlwaysOnTop(true);
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 855, 733);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setMaximumSize(new Dimension(50000, 50000));
		getContentPane().add(panel);
		panel.setLayout(null);
		//filterText = new JTextField();
        //Whenever filterText changes, invoke newFilter.
       /* filterText.getDocument().addDocumentListener(
                new DocumentListener() {
                    public void changedUpdate(DocumentEvent e) {
                        newFilter();
                    }
                    public void insertUpdate(DocumentEvent e) {
                        newFilter();
                    }
                    public void removeUpdate(DocumentEvent e) {
                        newFilter();
                    }
                });
        filterText.setBounds(279, 10, 415, 22);
        panel.add(filterText);*/
		JLabel lblModel = new JLabel("Phone Model");
		lblModel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblModel.setBounds(67, 53, 153, 22);
		panel.add(lblModel);
		
		List<String> modelsList = new ArrayList<String>();
		
		PhoneModelService pms = (PhoneModelService) context.getBean("phoneModelService");

		modelsList = pms.getAllModelNames();


		StringSearchable searchable = new StringSearchable(modelsList);

		comboModelSearch = new AutocompleteJComboBox(searchable);
		 final StockTableModel  stockModel = new StockTableModel();
	        sorter = new TableRowSorter<StockTableModel>(stockModel);


	        //Create the scroll pane and add the table to it.
	        JScrollPane stockScrollPane = new JScrollPane();
	        stockScrollPane.setBounds(67, 86, 625, 125);
	        //Add the scroll pane to this panel.
	        panel.add(stockScrollPane);
	        tableStock = new JTable(stockModel);
	        stockScrollPane.setViewportView(tableStock);
	        tableStock.setRowSorter(sorter);
	        tableStock.setPreferredScrollableViewportSize(new Dimension(500, 500));
	        tableStock.setFillsViewportHeight(true);
	        
	        	        //For the purposes of this example, better to have a single
	        	        //selection.
	        	        tableStock.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        	        
	        	        	        //When selection changes, provide user with row numbers for
	        	        	        //both view and model.
	        	        	        tableStock.getSelectionModel().addListSelectionListener(
	        	        	                new ListSelectionListener() {
	        	        	                    public void valueChanged(ListSelectionEvent event) {
	        	        	                        int viewRow = tableStock.getSelectedRow();
	        	        	                        if (viewRow < 0) {
	        	        	                            //Selection got filtered away.
	        	        	                            //statusText.setText("");
	        	        	                        } else {
	        	        	                            int modelRow = 
	        	        	                            		tableStock.convertRowIndexToModel(viewRow);
	        	        	                            PhoneStock ps = stockModel.getRow(modelRow);
	        	        	                            txtPrice.setText(ps.getSp().toString());
	        	        	                            txtAreaOffer.setText(ps.getOffer());
	        	        	                            txtAreaDesc.setText(ps.getDescription());	        	        	                           /* statusText.setText(
	        	        	                                String.format("Selected Row in view: %d. " +
	        	        	                                    "Selected Row in model: %d.", 
	        	        	                                    viewRow, modelRow));*/
	        	        	                        }
	        	        	                    }
	        	        	                }
	        	        	        );

		
		/*txtModel = new JTextField();
		txtModel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				
				
			}
		});
		txtModel.setBounds(279, 38, 415, 22);
		panel.add(txtModel);
		txtModel.setColumns(10);*/
	        comboModelSearch.addItemListener(new MyItemListener());
	        
	       
		comboModelSearch.setBounds(279, 53, 415, 22);
		panel.add(comboModelSearch);
		
		JLabel lblPrice = new JLabel("Selling Price");
		lblPrice.setBounds(67, 263, 128, 22);
		panel.add(lblPrice);
		
		txtPrice = new JTextField();
		txtPrice.setBounds(177, 264, 171, 20);
		panel.add(txtPrice);
		txtPrice.setColumns(10);
		
		JLabel lblOffer = new JLabel("Offer Details");
		lblOffer.setBounds(68, 359, 153, 22);
		panel.add(lblOffer);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(70, 405, 153, 22);
		panel.add(lblDescription);
		
		im = new InvoiceTableModel();
		table = new JTable();
		table.setModel(im);
		table.setBounds(67, 461, 627, -116);
		
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(67, 505, 625, 95);
		panel.add(scrollPane);
		
		JButton btnAddStock = new JButton("Add to Sale");
		btnAddStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*PhoneStock stock = new PhoneStock(); 
				//stock.setImeiNo(txtIMEI.getText());
				stock.setPhModel(comboModelSearch.getSelectedItem().toString());
				//stock.setMargin(Float.parseFloat(txtMargin.getText()));
				stock.setSp(Float.parseFloat(txtPrice.getText()));
				totalCost+=stock.getSp();
				pss.add(stock);*/
				
				int selectedRow = tableStock.getSelectedRow();
				if(selectedRow==-1){
					 JOptionPane.showMessageDialog(null, "Please select atleast one Stock to Add", 
                             "No Stock Selected",
                             JOptionPane.WARNING_MESSAGE);
					 return;
				}
				
				 int modelRow = 
                 		tableStock.convertRowIndexToModel(selectedRow);
				PhoneStock selectedStock =null;
				if(modelRow>=0){
					selectedStock= stockModel.getRow(modelRow);
				}
				if(selectedStock!=null){
					
					Sales sale = new Sales();
					String discString = txtDiscount.getText();
					String spString = txtPrice.getText();
					float sp = 0;
					float disc = 0;
					if(!spString.equals("")){
						sp = Float.parseFloat(spString);
					}
					if(!discString.equals("")){
						disc = Float.parseFloat(discString);
					}
					Float finalSP = sp-disc;
					sale.setSalePrice(finalSP);
					sale.setStockId(selectedStock.getId());
					sale.setImeiNo(selectedStock.getImeiNo());
					sale.setPhoneModel(selectedStock.getPhModel());
					sale.setSalesDate(new Date());
					sale.setQty(1);
					float vat = txtVat.getText().equals("")?0:Float.parseFloat(txtVat.getText());
					sale.setVat(vat);
					sale.setCustContact(txtCustContact.getText());
					sale.setCustName(txtCustName.getText());
					float margin = finalSP - selectedStock.getDp()+ selectedStock.getMargin()*(selectedStock.getDp()/100);
					sale.setMargin(margin);
					sale.setDiscount(disc);
					sale.setDistributor(selectedStock.getDistributor());
					sale.setDp(selectedStock.getDp());
					sale.setInvoiceId(txtInvoiceId.getText());
					sale.setOffer(selectedStock.getOffer());
					sale.setDescription(selectedStock.getDescription());
					totalCost+=finalSP;
					txtGrandTotal.setText(totalCost.toString());
					//sm.refreshTableData();
					//ss.add(sale);
					//pss.sellStock(selectedStock.getId());
					
					stockModel.removeRow(selectedStock);
					stockModel.fireTableDataChanged();
					im.addRow(sale);
					im.fireTableDataChanged();
				}
				
				
				
			}
		});
		btnAddStock.setBounds(559, 471, 133, 23);
		panel.add(btnAddStock);
		
		/*JLabel lblTotalCost = new JLabel("Total Stock Added Cost : ");
		lblTotalCost.setBounds(332, 623, 187, 22);
		panel.add(lblTotalCost);
		
		lblCost = new JLabel("0");
		lblCost.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCost.setForeground(Color.GREEN);
		lblCost.setBounds(561, 625, 133, 18);
		panel.add(lblCost);
				lblCostCost+=stock.getSp();
				pss.add(stock);
				lblCost.setText(totalCost.toString());
				//sm.refreshTableData();
				im.addRow(stock);
				im.fireTableDataChanged();
				*/
		
		
		
		JLabel lblTotalCost = new JLabel("Grand Total : ");
		lblTotalCost.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTotalCost.setBounds(394, 621, 128, 36);
		panel.add(lblTotalCost);
		
		JButton btnConfirmSale = new JButton("Confirm Sale");
		btnConfirmSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(im.data.size()==0){
					JOptionPane.showMessageDialog (null, "Add atleast one Stock for Selling", "No Stock Added for Sales", JOptionPane.WARNING_MESSAGE);
					return;
				}
				ConfirmSale confirmBox = new ConfirmSale(im.data,frame);
				RefineryUtilities.centerFrameOnScreen(confirmBox);
				confirmBox.setVisible(true);
				
				
				
			}
		});
		btnConfirmSale.setBounds(67, 630, 128, 23);
		panel.add(btnConfirmSale);
		
		txtGrandTotal = new JTextField();
		txtGrandTotal.setForeground(Color.BLUE);
		txtGrandTotal.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtGrandTotal.setText("0");
		txtGrandTotal.setEditable(false);
		txtGrandTotal.setBounds(530, 620, 162, 36);
		panel.add(txtGrandTotal);
		txtGrandTotal.setColumns(10);
		
		JLabel lblCustomerName = new JLabel("Customer Name");
		lblCustomerName.setBounds(67, 297, 107, 20);
		panel.add(lblCustomerName);
		
		txtCustName = new JTextField();
		txtCustName.setBounds(177, 296, 171, 22);
		panel.add(txtCustName);
		txtCustName.setColumns(10);
		
		JLabel lblCustomerContact = new JLabel("Customer Contact");
		lblCustomerContact.setBounds(379, 296, 107, 22);
		panel.add(lblCustomerContact);
		
		txtCustContact = new JTextField();
		txtCustContact.setBounds(521, 296, 171, 21);
		panel.add(txtCustContact);
		txtCustContact.setColumns(10);
		
		JLabel lblInvoiceNo = new JLabel("Invoice No");
		lblInvoiceNo.setBounds(67, 236, 101, 22);
		panel.add(lblInvoiceNo);
		
		txtInvoiceId = new JTextField();
		txtInvoiceId.setBounds(177, 237, 171, 20);
		panel.add(txtInvoiceId);
		txtInvoiceId.setColumns(10);
		
		JLabel lblVatt = new JLabel("VAT");
		lblVatt.setBounds(67, 329, 82, 18);
		panel.add(lblVatt);
		
		txtVat = new JTextField();
		txtVat.setText("5.0");
		txtVat.setBounds(177, 328, 171, 20);
		panel.add(txtVat);
		txtVat.setColumns(10);
		
		JLabel lblDiscount = new JLabel("Discount %");
		lblDiscount.setBounds(383, 263, 89, 18);
		panel.add(lblDiscount);
		
		txtDiscount = new JTextField();
		txtDiscount.setText("0.0");
		txtDiscount.setBounds(521, 264, 171, 20);
		panel.add(txtDiscount);
		txtDiscount.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(67, 222, 625, 3);
		panel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(67, 462, 625, 2);
		panel.add(separator_1);
		
		txtAreaOffer = new JTextArea();
		txtAreaOffer.setBounds(178, 358, 514, 36);
		panel.add(txtAreaOffer);
		
		txtAreaDesc = new JTextArea();
		txtAreaDesc.setBounds(178, 404, 514, 43);
		panel.add(txtAreaDesc);
		
		txtScanIMEI = new JTextField();
		txtScanIMEI.setToolTipText("Place cursor here and Scan");
		txtScanIMEI.setBounds(279, 18, 413, 20);
		panel.add(txtScanIMEI);
		txtScanIMEI.setColumns(10);
		
		JLabel lblImeiNo = new JLabel("IMEI No.");
		lblImeiNo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblImeiNo.setBounds(67, 21, 46, 14);
		panel.add(lblImeiNo);
		
		txtScanIMEI.addKeyListener(new KeyAdapter() {
		      public void keyReleased(KeyEvent e) {
		        JTextField textField = (JTextField) e.getSource();
		        String text = textField.getText();
		        if(text.length()==10){
		        	newBarCodeIMEFilter();
		        }
		        textField.setText(text.toUpperCase());
		      }
		});
		
		
	}
	
	 private void newBarCodeIMEFilter() {
	        RowFilter<StockTableModel, Object> rf = null;
	        //If current expression doesn't parse, don't update.
	        try {
	        	 rf = RowFilter.regexFilter(txtScanIMEI.getText(), 2);
	        } catch (java.util.regex.PatternSyntaxException e) {
	            return;
	        }
	        sorter.setRowFilter(rf);
	    }
	
	 /** 
     * Update the row filter regular expression from the expression in
     * the text box.
     */
    private void newFilter() {
        RowFilter<StockTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
        	 rf = RowFilter.regexFilter("(?i)"+comboModelSearch.getSelectedItem().toString().replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)"), 1);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }

    class MyItemListener implements ItemListener {
    	  // This method is called only if a new item has been selected.
    	  public void itemStateChanged(ItemEvent evt) {
    	    JComboBox cb = (JComboBox) evt.getSource();

    	    Object item = evt.getItem();

    	    if (evt.getStateChange() == ItemEvent.SELECTED) {
    	      // Item was just selected
    	    	newFilter();
    	    } else if (evt.getStateChange() == ItemEvent.DESELECTED) {
    	      // Item is no longer selected
    	    	//newFilter();
    	    }
    	  }
    }
	
    class  StockTableModel extends AbstractTableModel {
        private String[] columnNames = {"Stock Id",
                                        "Model",
                                        "IMEI",
                                        "Selling Price"
                                        };
        
       
		
		
        private List<PhoneStock> data = new ArrayList<PhoneStock>();
        StockTableModel(){
        	data = pss.getAllAvailable();
        }
        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.size();
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
        	PhoneStock ps = data.get(row);
        	/*private String accStockId;
//        	private Timestamp arrivalDate;
        	private String accModel;
        	private String phmodelName;
        	//private Timestamp soldDate;
        	private String desription;
        	private String margin;
        	private Float dp;
        	private Float sp;*/
        	 switch (col) {
             case 0:
                    return ps.getId();
             case 1:
                    return ps.getPhModel();
             case 2:
                    return ps.getImeiNo();
             case 3:
            	 	return ps.getSp();
             default:
                    throw new IndexOutOfBoundsException();
             }
        	
        	
        }
        
        public PhoneStock getRow(int row){
        	return data.get(row);
        }
        
        public boolean removeRow(PhoneStock stock){
        	return data.remove(stock);
        }

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
      /*  public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }*/

        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (col < 2) {
                return false;
            } else {
                return true;
            }
        }

        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
      /*  public void setValueAt(Object value, int row, int col) {
            if (DEBUG) {
                System.out.println("Setting value at " + row + "," + col
                                   + " to " + value
                                   + " (an instance of "
                                   + value.getClass() + ")");
            }
            
            data[row][col] = value;
            fireTableCellUpdated(row, col);

            if (DEBUG) {
                System.out.println("New value of data:");
                printDebugData();
            }
        }*/

      /*  private void printDebugData() {
            int numRows = getRowCount();
            int numCols = getColumnCount();

            for (int i=0; i < numRows; i++) {
                System.out.print("    row " + i + ":");
                for (int j=0; j < numCols; j++) {
                    System.out.print("  " + data[i][j]);
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }*/
    }
	
	 class  InvoiceTableModel extends AbstractTableModel {
	        private String[] columnNames = {"Sale Id",
	                                        "Phone Model",
	                                        "IMEI",
	                                        "Quantity",
	                                        "Price",
	                                        "VAT",
	                                        "Discount",
	                                        "Sale Price"};
	        
	       
			
			
			
	        private List<Sales> data = new ArrayList<Sales>();
	        InvoiceTableModel(){
	        	//data = pss.getAllDetails();
	        }

	        public int getColumnCount() {
	            return columnNames.length;
	        }

	        public int getRowCount() {
	            return data.size();
	        }

	        public String getColumnName(int col) {
	            return columnNames[col];
	        }

	        public Object getValueAt(int row, int col) {
	        	Sales invoice = data.get(row);
	        	/*private String saleId;
	        	private Float salePrice;
	        	private Float dp;
	        	private Float margin;
	        	private Date salesDate;
	        	private String invoiceId;
	        	private Float discount;
	        	private String phoneModel;
	        	private int stockId;
	        	private int qty;
	        	private int custId;
	        	private String imeiNo;
	        	private String custName;
	        	private String custContact;*/
	        	
	        	/*{"Sale Id",
                    "Phone Model",
                    "IMEI",
                    "Quantity",
                    "Price",
                    "VAT",
                    "Discount",
                    "Sale Price"};*/
	        	 switch (col) {
	             case 0:
	                    return invoice.getSaleId();
	             case 1:
	                    return invoice.getPhoneModel();
	             case 2:
	                    return invoice.getImeiNo();
	             case 3:
	            	 	return invoice.getQty();
	             case 4:
	            	 	String discString = txtDiscount.getText();
						String spString = txtPrice.getText();
						float sp = 0;
						float disc = 0;
						if(!spString.equals("")){
							sp = Float.parseFloat(spString);
						}
						if(!discString.equals("")){
							disc = Float.parseFloat(discString);
						}
						Float finalSP = sp-disc;
	            	 	return ((finalSP*100)/(invoice.getVat()+100));
	             case 5:
	            	 	return (invoice.getVat());
	             case 6 : 
	            	 	return invoice.getDiscount();
	             case 7 : 
	         	 		return invoice.getSalePrice();
	           
	             default:
	                    throw new IndexOutOfBoundsException();
	             }
	        	
	        	
	        }

	        /*
	         * JTable uses this method to determine the default renderer/
	         * editor for each cell.  If we didn't implement this method,
	         * then the last column would contain text ("true"/"false"),
	         * rather than a check box.
	         */
	       /* public Class getColumnClass(int c) {
	            return getValueAt(0, c).getClass();
	        }*/

	        /*
	         * Don't need to implement this method unless your table's
	         * editable.
	         */
	        public boolean isCellEditable(int row, int col) {
	            //Note that the data/cell address is constant,
	            //no matter where the cell appears onscreen.
	            if (col < 2) {
	                return false;
	            } else {
	                return true;
	            }
	        }

	        /*
	         * Don't need to implement this method unless your table's
	         * data can change.
	         */
	        public void setValueAt(Object value, int row, int col) {
	            if (DEBUG) {
	                System.out.println("Setting value at " + row + "," + col
	                                   + " to " + value
	                                   + " (an instance of "
	                                   + value.getClass() + ")");
	            }

	          //  data[row][col] = value;
	            fireTableCellUpdated(row, col);

	            if (DEBUG) {
	                System.out.println("New value of data:");
	                printDebugData();
	            }
	        }
	        
	        public void refreshTableData(){
	        	//data = pss.getAllDetails();
	        }
	        
	        public void addRow(Object record){
	        	Sales sale = (Sales)record;
	        	data.add(sale);
	        }

	        private void printDebugData() {
	            int numRows = getRowCount();
	            int numCols = getColumnCount();

	            for (int i=0; i < numRows; i++) {
	                System.out.print("    row " + i + ":");
	                for (int j=0; j < numCols; j++) {
	                   // System.out.print("  " + data[i][j]);
	                }
	                System.out.println();
	            }
	            System.out.println("--------------------------");
	        }
	    }
}
    



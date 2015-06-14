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
import com.univercellmobiles.app.beans.ReturnStock;
import com.univercellmobiles.app.service.AccessoryStockService;
import com.univercellmobiles.app.service.PhoneModelService;
import com.univercellmobiles.app.service.PhoneStockService;
import com.univercellmobiles.app.service.ReturnsService;
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

public class StockReturns extends JFrame {
	private JTable table;
	  private JTable tableStock;
	  static StockReturns frame;
	  private TableRowSorter<StockTableModel> sorter;
	private boolean DEBUG = false;
	ReturnsTable im;
	
	private Float totalCost = (float) 0.0;
	AutocompleteJComboBox comboModelSearch;
	 ConfigurableApplicationContext context = ConfigBuilder.getAppContext();
		ReturnsService rs = (ReturnsService) context.getBean("returnService");
		PhoneStockService pss = (PhoneStockService) context.getBean("phoneStockService");
		private JTextField txtGrandTotal;
		JTextArea txtAreaDesc;
		private JTextField txtScanIMEI;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new StockReturns();
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
	public StockReturns() {
		frame = this;
		setTitle("Returns Management");
		//setAlwaysOnTop(true);
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 855, 733);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setMaximumSize(new Dimension(50000, 50000));
		getContentPane().add(panel);
		panel.setLayout(null);
		
		txtGrandTotal = new JTextField();
		txtGrandTotal.setForeground(Color.BLUE);
		txtGrandTotal.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtGrandTotal.setText("0");
		txtGrandTotal.setEditable(false);
		txtGrandTotal.setBounds(530, 620, 162, 36);
		panel.add(txtGrandTotal);
		txtGrandTotal.setColumns(10);
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
		lblModel.setFont(new Font("Dialog", Font.BOLD, 12));
		lblModel.setBounds(67, 38, 153, 22);
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
	        stockScrollPane.setBounds(67, 71, 625, 174);
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
	        	        	                            txtAreaDesc.setText("");	        	        	                    
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
	        
	       
		comboModelSearch.setBounds(279, 38, 415, 22);
		panel.add(comboModelSearch);
		
		JLabel lblDescription = new JLabel("Return Reason");
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDescription.setBounds(67, 282, 153, 22);
		panel.add(lblDescription);
		
		im = new ReturnsTable();
		table = new JTable();
		table.setModel(im);
		table.setBounds(67, 461, 627, -116);
		
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(67, 415, 625, 185);
		panel.add(scrollPane);
		
		JButton btnAddStock = new JButton("Add to Returns");
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
					 JOptionPane.showMessageDialog(null, "Please select atleast one Stock to Returns", 
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
					
					ReturnStock sale = new ReturnStock();
					sale.setImeiNo(selectedStock.getImeiNo());
					sale.setPhModel(selectedStock.getPhModel());
					float margin = selectedStock.getSp() - selectedStock.getDp()+ selectedStock.getMargin()*(selectedStock.getDp()/100);
					sale.setMarginAmount(selectedStock.getMarginAmount());
					sale.setDistributor(selectedStock.getDistributor());
					sale.setDp(selectedStock.getDp());
					sale.setSp(selectedStock.getSp());
					sale.setInvoiceNo(selectedStock.getInvoiceNo());
					sale.setOffer(selectedStock.getOffer());
					sale.setDescription(selectedStock.getDescription());
					sale.setArrivalDate(selectedStock.getArrivalDate());
					sale.setReason(txtAreaDesc.getText());
					sale.setReturnDate(new Date());
					sale.setAvailable(1);
					/*totalCost+=selectedStock.getDp();
					txtGrandTotal.setText(totalCost.toString());*/
					
					//sm.refreshTableData();
					//ss.add(sale);
					//pss.sellStock(selectedStock.getId());
					
					
					stockModel.removeRow(selectedStock);
					pss.sellStock(selectedStock.getId());
					stockModel.fireTableDataChanged();
					rs.add(sale);
					im.refreshTableData();
					im.fireTableDataChanged();
					txtGrandTotal.setText(Float.toString(rs.getAllReturnsAmount()));
				}
				
				
				
			}
		});
		btnAddStock.setBounds(559, 381, 133, 23);
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
		
		
		
		JLabel lblTotalCost = new JLabel("Total Returns : ");
		lblTotalCost.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTotalCost.setBounds(394, 621, 128, 36);
		panel.add(lblTotalCost);
		
		JButton btnConfirmSale = new JButton("Resolve Return");
		btnConfirmSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if(selectedRow==-1){
					 JOptionPane.showMessageDialog(null, "Please select atleast one Stock to Resolve", 
                             "No Stock Selected",
                             JOptionPane.WARNING_MESSAGE);
					 return;
				}
				
				 int modelRow = 
                 		table.convertRowIndexToModel(selectedRow);
				ReturnStock selectedStock =null;
				if(modelRow>=0){
					selectedStock= im.getRow(modelRow);
				}
				if(selectedStock!=null){
					rs.resolveStock(selectedStock.getId());
				}
				im.refreshTableData();
				im.fireTableDataChanged();
				
				
				
			}
		});
		btnConfirmSale.setBounds(67, 628, 128, 23);
		panel.add(btnConfirmSale);
		
		
		
		JSeparator separator = new JSeparator();
		separator.setBounds(67, 256, 625, 3);
		panel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(67, 334, 625, 2);
		panel.add(separator_1);
		
		txtAreaDesc = new JTextArea();
		txtAreaDesc.setBounds(175, 281, 514, 43);
		panel.add(txtAreaDesc);
		
		JLabel lblImeiNo = new JLabel("IMEI No");
		lblImeiNo.setFont(new Font("Dialog", Font.BOLD, 12));
		lblImeiNo.setBounds(67, 13, 153, 14);
		panel.add(lblImeiNo);
		
		txtScanIMEI = new JTextField();
		txtScanIMEI.setToolTipText("Place cursor here and Scan");
		txtScanIMEI.setBounds(279, 7, 413, 20);
		panel.add(txtScanIMEI);
		txtScanIMEI.setColumns(10);
		
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
                                        "DP","SP","Invoice No","Arrival Date"
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
//        {"Stock Id",
                                        "Model",
                                        "IMEI",
                                        "DP","SP","Invoice No","Arrival Date"
        	private Float sp;*/
        	 switch (col) {
             case 0:
                    return ps.getId();
             case 1:
                    return ps.getPhModel();
             case 2:
                    return ps.getImeiNo();
             case 3:
            	 	return ps.getDp();
             case 4:
         	 	return ps.getSp();
             case 5:
         	 	return ps.getInvoiceNo();
             case 6:
          	 	return ps.getArrivalDate();
         	 	
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
	
	 class  ReturnsTable extends AbstractTableModel {
	        private String[] columnNames = {"Return Id",
	                                        "Phone Model",
	                                        "IMEI",
	                                        "DP",
	                                        "Sale Price","Arrival Date","Return Date","Reason"};
	        
	       
			
			
			
	        private List<ReturnStock> data = new ArrayList<ReturnStock>();
	        ReturnsTable(){
	        	
	        	data = rs.getAllAvailable();
	        	txtGrandTotal.setText(Float.toString(rs.getAllReturnsAmount()));
	        }

	        public int getColumnCount() {
	            return columnNames.length;
	        }

	        public int getRowCount() {
	            return data.size();
	        }
	        
	        public ReturnStock getRow(int row){
	        	return data.get(row);
	        }

	        public String getColumnName(int col) {
	            return columnNames[col];
	        }

	        public Object getValueAt(int row, int col) {
	        	ReturnStock invoice = data.get(row);
	        	/*private String saleId;
	        	{"Return Id",
	                                        "Phone Model",
	                                        "IMEI",
	                                        "Quantity",
	                                        "DP",
	                                        "Sale Price","Arrival Date","Return Date","Reason"};
	        	private String custContact;*/
	        	
	        	
	        	 switch (col) {
	             case 0:
	                    return invoice.getId();
	             case 1:
	                    return invoice.getPhModel();
	             case 2:
	                    return invoice.getImeiNo();
	             case 3:
	            	 	return invoice.getDp();
	      
	             case 4 : 
	         	 		return invoice.getSp();
	             case 5 : 
	         	 		return invoice.getArrivalDate();
	             case 6 : 
	         	 		return invoice.getReturnDate();
	             case 7 : 
	         	 		return invoice.getReason();
	           
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
	   /*     public boolean isCellEditable(int row, int col) {
	            //Note that the data/cell address is constant,
	            //no matter where the cell appears onscreen.
	            if (col < 2) {
	                return false;
	            } else {
	                return true;
	            }
	        }*/

	        /*
	         * Don't need to implement this method unless your table's
	         * data can change.
	         */
	       /* public void setValueAt(Object value, int row, int col) {
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
	        }*/
	        
	        public void refreshTableData(){
	        	data = rs.getAllAvailable();
	        	txtGrandTotal.setText(Float.toString(rs.getAllReturnsAmount()));
	        }
	        
	        public void addRow(Object record){
	        	ReturnStock sale = (ReturnStock)record;
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
    



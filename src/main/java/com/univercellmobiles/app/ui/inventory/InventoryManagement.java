package com.univercellmobiles.app.ui.inventory;

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
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

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

import javax.swing.JTextArea;

import java.awt.Window.Type;

public class InventoryManagement extends JFrame {
	private JTextField txtPrice;
	private JTable table;
	  private JTable tableStock;
	  private TableRowSorter<StockTableModel> sorter;
	  JTextArea txtOffer;
	  JTextArea txtDesc;
	  PhoneStock currentSelection=null;
	private boolean DEBUG = false;
	private Float totalCost = (float) 0.0;
	AutocompleteJComboBox comboModelSearch;
	 ConfigurableApplicationContext context = ConfigBuilder.getAppContext();
		
		AccessoryStockService as = (AccessoryStockService) context.getBean("accessoryStockService");
		PhoneStockService pss = (PhoneStockService) context.getBean("phoneStockService");
		SalesService ss =  (SalesService) context.getBean("salesService");
		private JTextField txtModel;
		private JTextField txtDP;
		private JTextField txtIEMI;
		private JTextField txtScanIMEI;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InventoryManagement frame = new InventoryManagement();
				
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
	public InventoryManagement() {
		setAlwaysOnTop(true);
		setType(Type.POPUP);
		setTitle("Inventory Management");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 771, 707);
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
	        stockScrollPane.setBounds(67, 71, 625, 214);
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
	        	        	                            currentSelection = stockModel.getRow(modelRow);
	        	        	                            PhoneStock ps = stockModel.getRow(modelRow);
	        	        	                            txtPrice.setText(ps.getSp().toString());
	        	        	                            txtDP.setText(ps.getDp().toString());
	        	        	                            txtIEMI.setText(ps.getImeiNo());
	        	        	                            txtDesc.setText(ps.getDescription());
	        	        	                            txtOffer.setText(ps.getOffer());
	        	        	                            txtModel.setText(ps.getPhModel());
	        	        	                           /* statusText.setText(
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
	        
	       
		comboModelSearch.setBounds(279, 38, 415, 22);
		panel.add(comboModelSearch);
		
		JLabel lblPrice = new JLabel("Selling Price");
		lblPrice.setBounds(67, 374, 128, 22);
		panel.add(lblPrice);
		
		txtPrice = new JTextField();
		txtPrice.setBounds(265, 375, 128, 20);
		panel.add(txtPrice);
		txtPrice.setColumns(10);
		
		JLabel lblOffer = new JLabel("Offer Details");
		lblOffer.setBounds(67, 407, 153, 22);
		panel.add(lblOffer);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(69, 452, 153, 22);
		panel.add(lblDescription);
		
		txtOffer = new JTextArea();
		txtOffer.setEditable(false);
		txtOffer.setBounds(265, 406, 427, 35);
		panel.add(txtOffer);
		
		txtDesc = new JTextArea();
		txtDesc.setEditable(false);
		txtDesc.setBounds(265, 451, 427, 35);
		panel.add(txtDesc);
		
		JLabel lblModel_1 = new JLabel("Model");
		lblModel_1.setBounds(67, 341, 128, 22);
		panel.add(lblModel_1);
		
		txtModel = new JTextField();
		txtModel.setEditable(false);
		txtModel.setBounds(265, 342, 427, 20);
		panel.add(txtModel);
		txtModel.setColumns(10);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentSelection!=null){
					currentSelection.setSp(Float.valueOf(txtPrice.getText()));
					currentSelection.setDp(Float.valueOf(txtDP.getText()));
					currentSelection.setImeiNo(txtIEMI.getText());
					currentSelection.setOffer(txtOffer.getText());
					currentSelection.setDescription(txtDesc.getText());
					pss.update(currentSelection);
					currentSelection=null;
					stockModel.refreshTableData();
					stockModel.fireTableDataChanged();
					
					//currentSelection.set(Float.valueOf(txtPrice.getText()));
					
				}
			}
		});
		btnUpdate.setBounds(564, 526, 128, 23);
		panel.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentSelection!=null){
				pss.delete(currentSelection.getId());
				stockModel.refreshTableData();
				stockModel.fireTableDataChanged();
				}
			}
		});
		btnDelete.setBounds(564, 296, 128, 23);
		panel.add(btnDelete);
		
		JLabel lblDp = new JLabel("DP");
		lblDp.setBounds(523, 378, 42, 14);
		panel.add(lblDp);
		
		txtDP = new JTextField();
		txtDP.setBounds(575, 375, 117, 20);
		panel.add(txtDP);
		txtDP.setColumns(10);
		
		JLabel lblIemiNo = new JLabel("IEMI No.");
		lblIemiNo.setBounds(67, 316, 128, 14);
		panel.add(lblIemiNo);
		
		txtIEMI = new JTextField();
		txtIEMI.setBounds(265, 313, 289, 20);
		panel.add(txtIEMI);
		txtIEMI.setColumns(10);
		
		txtScanIMEI = new JTextField();
		txtScanIMEI.setToolTipText("Place cursor here and Scan");
		txtScanIMEI.setBounds(279, 7, 413, 20);
		panel.add(txtScanIMEI);
		txtScanIMEI.setColumns(10);
		
		JLabel lblImeiNo = new JLabel("IMEI No.");
		lblImeiNo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblImeiNo.setBounds(67, 13, 46, 14);
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
                                        "Selling Price",
                                        "DP"
                                        };
        
       
		
		
        private List<PhoneStock> data = new ArrayList<PhoneStock>();
        StockTableModel(){
        	data = pss.getAllAvailable();
        }
        public void refreshTableData() {
			// TODO Auto-generated method stub
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
           
             case 4:
         	 	return ps.getDp();
           
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
       /* public boolean isCellEditable(int row, int col) {
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
}
    



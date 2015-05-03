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

import com.univercellmobiles.app.beans.AccessorySales;
import com.univercellmobiles.app.beans.AccessoryStock;
import com.univercellmobiles.app.service.AccessoryStockService;
import com.univercellmobiles.app.service.PhoneModelService;
import com.univercellmobiles.app.service.PhoneStockService;
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
import java.awt.Dimension;

import javax.swing.JSeparator;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import java.awt.Window.Type;

public class AccessoryBilling extends JFrame {
	private JTextField txtPrice;
	private JTable table;
	private JTable tableStock;
	static AccessoryBilling frame;
	private TableRowSorter<StockTableModel> sorter;
	private boolean DEBUG = false;
	InvoiceTableModel im;

	private Float totalCost = (float) 0.0;
	AutocompleteJComboBox comboModelSearch;
	AutocompleteJComboBox accNameSearchCombo;
	JComboBox comboAccType;
	ConfigurableApplicationContext context = ConfigBuilder.getAppContext();

	AccessoryStockService as = (AccessoryStockService) context
			.getBean("accessoryStockService");
	PhoneStockService pss = (PhoneStockService) context
			.getBean("phoneStockService");

	private JTextField txtGrandTotal;
	private JTextField txtCustName;
	private JTextField txtCustContact;
	private JTextField txtInvoiceId;
	private JTextField txtVat;
	private JTextField txtDiscount;
	JTextArea txtAreaOffer;
	JTextArea txtAreaDesc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new AccessoryBilling();
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
	public AccessoryBilling() {
		frame = this;
		setTitle("Accessory Billing");
		// setAlwaysOnTop(true);
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 855, 730);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel = new JPanel();
		panel.setMaximumSize(new Dimension(50000, 50000));
		getContentPane().add(panel);
		panel.setLayout(null);
		// filterText = new JTextField();
		// Whenever filterText changes, invoke newFilter.
		/*
		 * filterText.getDocument().addDocumentListener( new DocumentListener()
		 * { public void changedUpdate(DocumentEvent e) { newFilter(); } public
		 * void insertUpdate(DocumentEvent e) { newFilter(); } public void
		 * removeUpdate(DocumentEvent e) { newFilter(); } });
		 * filterText.setBounds(279, 10, 415, 22); panel.add(filterText);
		 */
		List<String> accType = new ArrayList<String>();
		
		/*
		 * Power Bank
Ear Phones
Data cable
Bluetooth & Headset
Bluetooth
FlipCover
Tab Covers
Adaptor
Multi Data Cable
Back Pouch (Photo Case)
Back Pouch(Rajasthani Print)
Back Pouch(Slicion)
Back Pouch (Silicon)
Back Pouch(Silicon)
Tampered Glass
Screen Guard(Clear)
Screen Guard(Matt)
Screen Guard(Ultra Clear)
Spike
Mirco Card Reader
SD Card

		 */

		accType.add("Ear Phones");
		accType.add("Data cable");
		accType.add("Bluetooth & Headset");
	accType.add("Bluetooth Speakers");
	accType.add("FlipCover");
	accType.add("Tab Covers");
	accType.add("Adaptor & Chargers");
	accType.add("Multi Data Cable");
	accType.add("Back Pouch (Photo Case)");
	accType.add("Back Pouch(Rajasthani Print)");
	accType.add("Back Pouch (Silicon)");
	accType.add("Tampered Glass");
	accType.add("Screen Guard(Clear)");
	accType.add("Screen Guard(Matt)");
	accType.add("Screen Guard(Ultra Clear)");
	accType.add("Spike");
	accType.add("Mirco Card Reader");

		comboAccType = new JComboBox();
		comboAccType.setBounds(279, 11, 415, 20);
		for (String type : accType) {
			comboAccType.addItem(type);
		
		}
		
		comboAccType.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				newTypeFilter();
			}
			
		});
		panel.add(comboAccType);
		
		JLabel lblModel = new JLabel("Phone Model");
		lblModel.setBounds(70, 42, 153, 22);
		panel.add(lblModel);

		List<String> modelsList = new ArrayList<String>();

		PhoneModelService pms = (PhoneModelService) context
				.getBean("phoneModelService");// this needs to be changed to AccessroyStock Service 
		// Need to filter by Accessory Stock first
		// Next by Accessory Name

		
		List<String> accNameList = new ArrayList<String>();

		
		modelsList = pms.getAllModelNames();
		accNameList = as.getAllAccNames();
		StringSearchable searchable = new StringSearchable(modelsList);

		comboModelSearch = new AutocompleteJComboBox(searchable);
		final StockTableModel stockModel = new StockTableModel();
		sorter = new TableRowSorter<StockTableModel>(stockModel);
		
		
		
		StringSearchable searchableAccModel = new StringSearchable(accNameList);


		

		// Create the scroll pane and add the table to it.
		JScrollPane stockScrollPane = new JScrollPane();
		stockScrollPane.setBounds(69, 108, 625, 87);
		// Add the scroll pane to this panel.
		panel.add(stockScrollPane);
		tableStock = new JTable(stockModel);
		stockScrollPane.setViewportView(tableStock);
		tableStock.setRowSorter(sorter);
		tableStock.setPreferredScrollableViewportSize(new Dimension(500, 500));
		tableStock.setFillsViewportHeight(true);

		// For the purposes of this example, better to have a single
		// selection.
		tableStock.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// When selection changes, provide user with row numbers for
		// both view and model.
		tableStock.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
						int viewRow = tableStock.getSelectedRow();
						if (viewRow < 0) {
							// Selection got filtered away.
							// statusText.setText("");
						} else {
							int modelRow = tableStock
									.convertRowIndexToModel(viewRow);
							AccessoryStock as = stockModel.getRow(modelRow);
							txtPrice.setText(as.getSp().toString());
							txtAreaDesc.setText(as.getDesription());

						}
					}
				});

		comboModelSearch.addItemListener(new MyItemListener());
		accNameSearchCombo = new AutocompleteJComboBox(searchableAccModel);
		accNameSearchCombo.addItemListener(new MyItemAccNameListener());
		accNameSearchCombo.setBounds(279, 75, 415, 22);
		panel.add(accNameSearchCombo);

		comboModelSearch.setBounds(279, 42, 415, 22);
		panel.add(comboModelSearch);

		JLabel lblPrice = new JLabel("Selling Price");
		lblPrice.setBounds(69, 259, 128, 22);
		panel.add(lblPrice);

		txtPrice = new JTextField();
		txtPrice.setBounds(179, 260, 171, 20);
		panel.add(txtPrice);
		txtPrice.setColumns(10);

		JLabel lblOffer = new JLabel("Offer Details");
		lblOffer.setBounds(70, 355, 153, 22);
		panel.add(lblOffer);

		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(72, 401, 153, 22);
		panel.add(lblDescription);

		im = new InvoiceTableModel();
		table = new JTable();
		table.setModel(im);
		table.setBounds(67, 461, 627, -116);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(67, 499, 625, 101);
		panel.add(scrollPane);

		JButton btnAddStock = new JButton("Add to Sale");
		btnAddStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int selectedRow = tableStock.getSelectedRow();
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null,
							"Please select atleast one Stock to Add",
							"No Stock Selected", JOptionPane.WARNING_MESSAGE);
					return;
				}

				int modelRow = tableStock.convertRowIndexToModel(selectedRow);
				AccessoryStock selectedStock = null;
				if (modelRow >= 0) {
					selectedStock = stockModel.getRow(modelRow);
				}
				if (selectedStock != null) {

					AccessorySales sale = new AccessorySales();
					Float finalSP = 0.0f;
					if (txtPrice.getText() != null
							&& txtDiscount.getText() != null)
						finalSP = Float.parseFloat(txtPrice.getText())
								- Float.parseFloat(txtDiscount.getText());
					sale.setSalePrice(finalSP);
					sale.setDp(selectedStock.getDp());
					sale.setAccModel(selectedStock.getAccModel());
					sale.setStockId(selectedStock.getAccStockId());
					sale.setPhoneModel(selectedStock.getPhmodelName());
					sale.setSaleType(selectedStock.getAccType());
					sale.setSalesDate(new Date());
					sale.setQty(1);
					sale.setVat(Float.parseFloat(txtVat.getText()));
					sale.setCustContact(txtCustContact.getText());
					sale.setInvoiceId(txtInvoiceId.getText());
					sale.setOffer(txtAreaOffer.getText());
					sale.setCustName(txtCustName.getText());
					sale.setDescription(txtAreaDesc.getText());
					float margin = finalSP - selectedStock.getDp()
							+ selectedStock.getMargin()
							* (selectedStock.getDp() / 100);
					sale.setMargin(margin);
					sale.setDiscount(Float.parseFloat(txtDiscount.getText()));
					totalCost += finalSP;
					txtGrandTotal.setText(totalCost.toString());
					// sm.refreshTableData();
					// ss.add(sale);
					// pss.sellStock(selectedStock.getId());

					//stockModel.removeRow(selectedStock);
					stockModel.updateRow(selectedStock);// updating the quanity in selected row
					stockModel.fireTableDataChanged();
					im.addRow(sale);
					im.fireTableDataChanged();
				}

			}
		});
		btnAddStock.setBounds(561, 465, 133, 23);
		panel.add(btnAddStock);

		/*
		 * JLabel lblTotalCost = new JLabel("Total Stock Added Cost : ");
		 * lblTotalCost.setBounds(332, 623, 187, 22); panel.add(lblTotalCost);
		 * 
		 * lblCost = new JLabel("0"); lblCost.setFont(new Font("Tahoma",
		 * Font.BOLD, 11)); lblCost.setForeground(Color.GREEN);
		 * lblCost.setBounds(561, 625, 133, 18); panel.add(lblCost);
		 * lblCostCost+=stock.getSp(); pss.add(stock);
		 * lblCost.setText(totalCost.toString()); //sm.refreshTableData();
		 * im.addRow(stock); im.fireTableDataChanged();
		 */

		JLabel lblTotalCost = new JLabel("Grand Total : ");
		lblTotalCost.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTotalCost.setBounds(390, 620, 128, 37);
		panel.add(lblTotalCost);

		JButton btnConfirmSale = new JButton("Confirm Sale");
		btnConfirmSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (im.data.size() == 0) {
					JOptionPane.showMessageDialog(null,
							"Add atleast one Stock for Selling",
							"No Stock Added for Sales",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				ConfirmSale confirmBox = new ConfirmSale(im.data, frame);
				RefineryUtilities.centerFrameOnScreen(confirmBox);
				confirmBox.setVisible(true);

			}
		});
		btnConfirmSale.setBounds(67, 629, 128, 23);
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
		lblCustomerName.setBounds(69, 293, 107, 20);
		panel.add(lblCustomerName);

		txtCustName = new JTextField();
		txtCustName.setBounds(179, 292, 171, 22);
		panel.add(txtCustName);
		txtCustName.setColumns(10);

		JLabel lblCustomerContact = new JLabel("Customer Contact");
		lblCustomerContact.setBounds(381, 292, 107, 22);
		panel.add(lblCustomerContact);

		txtCustContact = new JTextField();
		txtCustContact.setBounds(523, 292, 171, 21);
		panel.add(txtCustContact);
		txtCustContact.setColumns(10);

		JLabel lblInvoiceNo = new JLabel("Invoice No");
		lblInvoiceNo.setBounds(70, 220, 153, 22);
		panel.add(lblInvoiceNo);

		txtInvoiceId = new JTextField();
		txtInvoiceId.setBounds(179, 221, 171, 20);
		panel.add(txtInvoiceId);
		txtInvoiceId.setColumns(10);

		JLabel lblVatt = new JLabel("VAT");
		lblVatt.setBounds(69, 325, 82, 18);
		panel.add(lblVatt);

		txtVat = new JTextField();
		txtVat.setText("5.0");
		txtVat.setBounds(179, 324, 171, 20);
		panel.add(txtVat);
		txtVat.setColumns(10);

		JLabel lblDiscount = new JLabel("Discount %");
		lblDiscount.setBounds(385, 259, 89, 18);
		panel.add(lblDiscount);

		txtDiscount = new JTextField();
		txtDiscount.setText("0.0");
		txtDiscount.setBounds(523, 260, 171, 20);
		panel.add(txtDiscount);
		txtDiscount.setColumns(10);

		JSeparator separator = new JSeparator();
		separator.setBounds(69, 206, 625, 3);
		panel.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(69, 454, 625, 2);
		panel.add(separator_1);

		txtAreaOffer = new JTextArea();
		txtAreaOffer.setBounds(180, 354, 514, 36);
		panel.add(txtAreaOffer);

		txtAreaDesc = new JTextArea();
		txtAreaDesc.setBounds(180, 400, 514, 43);
		panel.add(txtAreaDesc);
		
		JLabel lblNewLabel = new JLabel("Accessory Name");
		lblNewLabel.setBounds(70, 79, 153, 14);
		panel.add(lblNewLabel);
		
		JLabel lblAccessoryType = new JLabel("Accessory Type");
		lblAccessoryType.setBounds(69, 14, 153, 14);
		panel.add(lblAccessoryType);

	}
	private void newTypeFilter() {
		RowFilter<StockTableModel, Object> rf = null;
		// If current expression doesn't parse, don't update.
		try {
			 rf = RowFilter.regexFilter("(?i)"+comboAccType.getSelectedItem().toString().replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)"), 3);
		} catch (java.util.regex.PatternSyntaxException e) {
			return;
		}
		sorter.setRowFilter(rf);
	}

	/**
	 * Update the row filter regular expression from the expression in the text
	 * box.
	 */
	private void newFilter() {
		RowFilter<StockTableModel, Object> rf = null;
		// If current expression doesn't parse, don't update.
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
				// newFilter();
			}
		}
	}
	
	/**
	 * Update the row filter regular expression from the expression in the text
	 * box.
	 */
	private void accNameFilter() {
		RowFilter<StockTableModel, Object> rf = null;
		// If current expression doesn't parse, don't update.
		try {
			 rf = RowFilter.regexFilter("(?i)"+accNameSearchCombo.getSelectedItem().toString().replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)"), 2);
		} catch (java.util.regex.PatternSyntaxException e) {
			return;
		}
		sorter.setRowFilter(rf);
	}

	class MyItemAccNameListener implements ItemListener {
		// This method is called only if a new item has been selected.
		public void itemStateChanged(ItemEvent evt) {
			JComboBox cb = (JComboBox) evt.getSource();

			Object item = evt.getItem();

			if (evt.getStateChange() == ItemEvent.SELECTED) {
				// Item was just selected
				accNameFilter();
			} else if (evt.getStateChange() == ItemEvent.DESELECTED) {
				// Item is no longer selected
				// newFilter();
			}
		}
	}
	class StockTableModel extends AbstractTableModel {
		private String[] columnNames = { "Id", "Model", "Acc Name" ,"Type",
				"Quantity", "Margin", "Selling Price", "Deal Price",
				"Description" };

		private List<AccessoryStock> data = new ArrayList<AccessoryStock>();

		StockTableModel() {
			data = as.getAllAvailable();
		}

		public void updateRow(AccessoryStock selectedStock) {
			// TODO Auto-generated method stub
			int qt = selectedStock.getQuantity();
			selectedStock.setQuantity(qt-1);
				
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
			AccessoryStock as = data.get(row);
			switch (col) {
			case 0:
				return as.getAccStockId();
			case 1:
				return as.getPhmodelName();
			case 2:
				 return as.getAccModel();
			case 3: 
				return as.getAccType();
			case 4:
				return as.getQuantity();
			case 5:
				return as.getMargin();
			case 6:
				return as.getSp();
			case 7:
				return as.getDp();
			case 8:
				return as.getDesription();
			default:
				throw new IndexOutOfBoundsException();
			}

		}

		public AccessoryStock getRow(int row) {
			return data.get(row);
		}

		public boolean removeRow(AccessoryStock stock ) {
		//	data.set(row, stock.setAvailable(available);)
			return data.remove(stock);
			
			
		}

		/*
		 * JTable uses this method to determine the default renderer/ editor for
		 * each cell. If we didn't implement this method, then the last column
		 * would contain text ("true"/"false"), rather than a check box.
		 */
		/*
		 * public Class getColumnClass(int c) { return getValueAt(0,
		 * c).getClass(); }
		 */

		/*
		 * Don't need to implement this method unless your table's editable.
		 */
		public boolean isCellEditable(int row, int col) {
			// Note that the data/cell address is constant,
			// no matter where the cell appears onscreen.
			if (col < 2) {
				return false;
			} else {
				return true;
			}
		}

		/*
		 * Don't need to implement this method unless your table's data can
		 * change.
		 */
		/*
		 * public void setValueAt(Object value, int row, int col) { if (DEBUG) {
		 * System.out.println("Setting value at " + row + "," + col + " to " +
		 * value + " (an instance of " + value.getClass() + ")"); }
		 * 
		 * data[row][col] = value; fireTableCellUpdated(row, col);
		 * 
		 * if (DEBUG) { System.out.println("New value of data:");
		 * printDebugData(); } }
		 */

		/*
		 * private void printDebugData() { int numRows = getRowCount(); int
		 * numCols = getColumnCount();
		 * 
		 * for (int i=0; i < numRows; i++) { System.out.print("    row " + i +
		 * ":"); for (int j=0; j < numCols; j++) { System.out.print("  " +
		 * data[i][j]); } System.out.println(); }
		 * System.out.println("--------------------------"); }
		 */
	}

	class InvoiceTableModel extends AbstractTableModel {
		private String[] columnNames = {"Id",
                "Phone",
                "Type",
                "Quantity",
                "Price",
                "VAT",
                "Discount",
                "Sale Price"};

		private List<AccessorySales> data = new ArrayList<AccessorySales>();

		InvoiceTableModel() {
			// data = pss.getAllDetails();
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
        	AccessorySales invoice = data.get(row);
       
        	 switch (col) {
             case 0:
                    return invoice.getSaleId();
             case 1:
                    return invoice.getPhoneModel(); // accessory name?here phone model is not mandatory. even search should not be on phone model also. it should be on accessory name.
             case 2:
                    return invoice.getSaleType();
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
		 * JTable uses this method to determine the default renderer/ editor for
		 * each cell. If we didn't implement this method, then the last column
		 * would contain text ("true"/"false"), rather than a check box.
		 */
		/*
		 * public Class getColumnClass(int c) { return getValueAt(0,
		 * c).getClass(); }
		 */

		/*
		 * Don't need to implement this method unless your table's editable.
		 */
		public boolean isCellEditable(int row, int col) {
			// Note that the data/cell address is constant,
			// no matter where the cell appears onscreen.
			if (col < 2) {
				return false;
			} else {
				return true;
			}
		}

		/*
		 * Don't need to implement this method unless your table's data can
		 * change.
		 */
		public void setValueAt(Object value, int row, int col) {
			if (DEBUG) {
				System.out.println("Setting value at " + row + "," + col
						+ " to " + value + " (an instance of "
						+ value.getClass() + ")");
			}

			// data[row][col] = value;
			fireTableCellUpdated(row, col);

			if (DEBUG) {
				System.out.println("New value of data:");
				printDebugData();
			}
		}

		public void refreshTableData() {
			// data = pss.getAllDetails();
		}

		public void addRow(Object record) {
			AccessorySales sale = (AccessorySales) record;
			data.add(sale);
		}

		private void printDebugData() {
			int numRows = getRowCount();
			int numCols = getColumnCount();

			for (int i = 0; i < numRows; i++) {
				System.out.print("    row " + i + ":");
				for (int j = 0; j < numCols; j++) {
					// System.out.print("  " + data[i][j]);
				}
				System.out.println();
			}
			System.out.println("--------------------------");
		}
	}
}

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
		setTitle("Phone Billing");
		// setAlwaysOnTop(true);
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 855, 707);
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
		JLabel lblModel = new JLabel("Accessory Name");
		lblModel.setBounds(67, 38, 153, 22);
		panel.add(lblModel);

		List<String> modelsList = new ArrayList<String>();

		PhoneModelService pms = (PhoneModelService) context
				.getBean("phoneModelService");

		modelsList = pms.getAllModelNames();

		StringSearchable searchable = new StringSearchable(modelsList);

		comboModelSearch = new AutocompleteJComboBox(searchable);
		final StockTableModel stockModel = new StockTableModel();
		sorter = new TableRowSorter<StockTableModel>(stockModel);

		// Create the scroll pane and add the table to it.
		JScrollPane stockScrollPane = new JScrollPane();
		stockScrollPane.setBounds(67, 71, 625, 87);
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

		comboModelSearch.setBounds(279, 38, 415, 22);
		panel.add(comboModelSearch);

		JLabel lblPrice = new JLabel("Selling Price");
		lblPrice.setBounds(67, 183, 128, 22);
		panel.add(lblPrice);

		txtPrice = new JTextField();
		txtPrice.setBounds(177, 184, 171, 20);
		panel.add(txtPrice);
		txtPrice.setColumns(10);

		JLabel lblOffer = new JLabel("Offer Details");
		lblOffer.setBounds(67, 282, 153, 22);
		panel.add(lblOffer);

		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(69, 328, 153, 22);
		panel.add(lblDescription);

		im = new InvoiceTableModel();
		table = new JTable();
		table.setModel(im);
		table.setBounds(67, 461, 627, -116);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(67, 415, 625, 185);
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
					sale.setStockId(selectedStock.getAccStockId());
					sale.setPhoneModel(selectedStock.getAccModel());
					sale.setSaleType(selectedStock.getAccType());
					sale.setSalesDate(new Date());
					sale.setQty(1);
					sale.setVat(Float.parseFloat(txtVat.getText()));
					sale.setCustContact(txtCustContact.getText());
					sale.setCustName(txtCustName.getText());
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
		btnAddStock.setBounds(415, 381, 133, 23);
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
		lblTotalCost.setBounds(389, 611, 128, 22);
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
		btnConfirmSale.setBounds(67, 611, 128, 23);
		panel.add(btnConfirmSale);

		txtGrandTotal = new JTextField();
		txtGrandTotal.setText("0");
		txtGrandTotal.setEditable(false);
		txtGrandTotal.setBounds(530, 612, 162, 20);
		panel.add(txtGrandTotal);
		txtGrandTotal.setColumns(10);

		JButton btnDeleteSale = new JButton("Delete Selected");
		btnDeleteSale.setBounds(559, 381, 133, 23);
		panel.add(btnDeleteSale);

		JLabel lblCustomerName = new JLabel("Customer Name");
		lblCustomerName.setBounds(67, 217, 107, 20);
		panel.add(lblCustomerName);

		txtCustName = new JTextField();
		txtCustName.setBounds(177, 216, 171, 22);
		panel.add(txtCustName);
		txtCustName.setColumns(10);

		JLabel lblCustomerContact = new JLabel("Customer Contact");
		lblCustomerContact.setBounds(379, 216, 107, 22);
		panel.add(lblCustomerContact);

		txtCustContact = new JTextField();
		txtCustContact.setBounds(521, 216, 171, 21);
		panel.add(txtCustContact);
		txtCustContact.setColumns(10);

		JLabel lblInvoiceNo = new JLabel("Invoice No");
		lblInvoiceNo.setBounds(67, 11, 153, 22);
		panel.add(lblInvoiceNo);

		txtInvoiceId = new JTextField();
		txtInvoiceId.setBounds(279, 12, 413, 20);
		panel.add(txtInvoiceId);
		txtInvoiceId.setColumns(10);

		JLabel lblVatt = new JLabel("VAT");
		lblVatt.setBounds(67, 249, 82, 18);
		panel.add(lblVatt);

		txtVat = new JTextField();
		txtVat.setText("5.0");
		txtVat.setBounds(177, 248, 171, 20);
		panel.add(txtVat);
		txtVat.setColumns(10);

		JLabel lblDiscount = new JLabel("Discount %");
		lblDiscount.setBounds(383, 183, 89, 18);
		panel.add(lblDiscount);

		txtDiscount = new JTextField();
		txtDiscount.setText("0.0");
		txtDiscount.setBounds(521, 184, 171, 20);
		panel.add(txtDiscount);
		txtDiscount.setColumns(10);

		JSeparator separator = new JSeparator();
		separator.setBounds(67, 169, 625, 3);
		panel.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(67, 378, 625, 2);
		panel.add(separator_1);

		txtAreaOffer = new JTextArea();
		txtAreaOffer.setBounds(177, 281, 514, 36);
		panel.add(txtAreaOffer);

		txtAreaDesc = new JTextArea();
		txtAreaDesc.setBounds(177, 327, 514, 43);
		panel.add(txtAreaDesc);

	}

	/**
	 * Update the row filter regular expression from the expression in the text
	 * box.
	 */
	private void newFilter() {
		RowFilter<StockTableModel, Object> rf = null;
		// If current expression doesn't parse, don't update.
		try {
			rf = RowFilter.regexFilter(comboModelSearch.getSelectedItem()
					.toString(), 1);
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
            	 	return ((invoice.getSalePrice()*100)/(invoice.getVat()+100));
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

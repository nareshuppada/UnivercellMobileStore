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
import java.awt.Dimension;

import javax.swing.JTextArea;

import java.awt.Window.Type;

@SuppressWarnings("serial")
public class AccessorySearch extends JFrame {
	private JTextField txtPrice;
	private JTable table;
	private JTable tableStock;
	private TableRowSorter<StockTableModel> sorter;
	JTextArea txtOffer;
	JTextArea txtDesc;
	private boolean DEBUG = false;
	private Float totalCost = (float) 0.0;
	AutocompleteJComboBox comboModelSearch;
	JComboBox comboAccType;
	ConfigurableApplicationContext context = ConfigBuilder.getAppContext();

	AccessoryStockService as = (AccessoryStockService) context
			.getBean("accessoryStockService");
	// PhoneStockService pss = (PhoneStockService)
	// context.getBean("phoneStockService");
	SalesService ss = (SalesService) context.getBean("salesService");
	private JTextField txtModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccessorySearch frame = new AccessorySearch();

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
	public AccessorySearch() {
		setAlwaysOnTop(true);
		setType(Type.POPUP);
		setTitle("Accessroy Stock Search");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 771, 707);
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

		JLabel lblAccessoryType = new JLabel("Accessory Type");
		lblAccessoryType.setBounds(67, 17, 153, 14);
		panel.add(lblAccessoryType);

		List<String> accType = new ArrayList<String>();
		accType.add("Tamper Glass");
		accType.add("Ear Phones");
		accType.add("Head Phones");
		accType.add("Flip Cover");
		accType.add("Back Pouch");
		accType.add("Tab Cover");

		comboAccType = new JComboBox();
		comboAccType.setBounds(279, 14, 415, 20);

		panel.add(comboAccType);
		for (String type : accType) {
			comboAccType.addItem(type);
		}

		JLabel lblModel = new JLabel("Phone Model");
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
		stockScrollPane.setBounds(67, 71, 625, 214);
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
							AccessoryStock ps = stockModel.getRow(modelRow);
							txtPrice.setText(ps.getSp().toString());
							txtDesc.setText(ps.getDesription());
							// txtOffer.setText(ps.getOffer());
							txtModel.setText(ps.getAccModel());
							/*
							 * statusText.setText(
							 * String.format("Selected Row in view: %d. " +
							 * "Selected Row in model: %d.", viewRow,
							 * modelRow));
							 */
						}
					}
				});

		/*
		 * txtModel = new JTextField(); txtModel.addKeyListener(new KeyAdapter()
		 * {
		 * 
		 * @Override public void keyTyped(KeyEvent arg0) {
		 * 
		 * 
		 * } }); txtModel.setBounds(279, 38, 415, 22); panel.add(txtModel);
		 * txtModel.setColumns(10);
		 */
		comboModelSearch.addItemListener(new MyItemListener());

		comboModelSearch.setBounds(279, 38, 415, 22);
		panel.add(comboModelSearch);

		JLabel lblPrice = new JLabel("Selling Price");
		lblPrice.setBounds(67, 374, 128, 22);
		panel.add(lblPrice);

		txtPrice = new JTextField();
		txtPrice.setEditable(false);
		txtPrice.setBounds(265, 375, 187, 20);
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
		private String[] columnNames = { "SId", "Model", "Name", "Type",
				"Selling Price" ,"Quantity"};

		private List<AccessoryStock> data = new ArrayList<AccessoryStock>();

		StockTableModel() {
			data = as.getAllAvailable();
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
				return as.getSp();
			case 5:
				return as.getQuantity();
			case 6:
				return as.getMargin();
			case 7:
				return as.getSp();
			case 8:
				return as.getDp();
			case 9:
				return as.getDesription();
			default:
				throw new IndexOutOfBoundsException();
			}

		}

		public AccessoryStock getRow(int row) {
			return data.get(row);
		}

		public boolean removeRow(AccessoryStock stock) {
			return data.remove(stock);
		}

		/*
		 * JTable uses this method to determine the default renderer/ editor for
		 * each cell. If we didn't implement this method, then the last column
		 * would contain text ("true"/"false"), rather than a check box.
		 */
		/*public Class getColumnClass(int c) {
			return getValueAt(0, c).getClass();
		}
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
}

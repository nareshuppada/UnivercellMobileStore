package com.univercellmobiles.app.ui.inventory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import com.univercellmobiles.app.beans.AccessoryStock;
import com.univercellmobiles.app.service.AccessoryStockService;
import com.univercellmobiles.app.service.PhoneModelService;
import com.univercellmobiles.app.ui.common.custom.AutocompleteJComboBox;
import com.univercellmobiles.app.ui.common.custom.StringSearchable;
import com.univercellmobiles.app.ui.common.custom.Searchable;

public class AddMobileAccessory extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtQuantiy, txtPlace, txtInvoice;
	private boolean DEBUG = false;
	StockModel sm;
	private Float totalCost = (float) 0.0;
	JLabel lblCost;
	JLabel lblMarginValue;
	private NumberFormat moneyFormat, percentFormat;
	AutocompleteJComboBox comboModelSearch;
	AutocompleteJComboBox accNameSearchCombo;
	JFormattedTextField ftfDP;
	JFormattedTextField ftfSP;
	JFormattedTextField ftfMargin;
	JDatePickerImpl datePicker;
	JTextArea textAreaDesc;
	@SuppressWarnings("rawtypes")
	JComboBox comboAccType;

	ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
			"applicationContext.xml");

	AccessoryStockService accessoryService = (AccessoryStockService) context
			.getBean("accessoryStockService");
	// AccModelService modelService = (AccModelService) context
	// .getBean("accModelService");
	private JTable accessoryTable;
	private JTextField textArrivalDate;

	PhoneModelService pms = (PhoneModelService) context
			.getBean("phoneModelService");
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMobileAccessory frame = new AddMobileAccessory();

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
	@SuppressWarnings("unchecked")
	public AddMobileAccessory() {
		setType(Type.POPUP);
		setTitle("Add Accessroy Stock ");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 771, 707);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		percentFormat = NumberFormat.getInstance();
		percentFormat.setMaximumIntegerDigits(2);
		percentFormat.setMaximumFractionDigits(2);
		moneyFormat = NumberFormat.getInstance();

		setBounds(100, 100, 771, 707);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		sm = new StockModel();

		JPanel panel = new JPanel();
		panel.setMaximumSize(new Dimension(50000, 50000));
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblModel = new JLabel("Phone Model");
		lblModel.setBounds(67, 49, 153, 22);
		panel.add(lblModel);

		List<String> modelsList = new ArrayList<String>();
		List<String> accNameList = new ArrayList<String>();

		// PhoneModelService pms = (PhoneModelService)
		// context.getBean("phoneModelService");

		modelsList = pms.getAllModelNames();
		accNameList = accessoryService.getAllAccNames();
	//	System.out.println("modelsList" + modelsList.toString());

		StringSearchable searchablePhoneModel = new StringSearchable(modelsList);

		comboModelSearch = new AutocompleteJComboBox(searchablePhoneModel);

		comboModelSearch.setBounds(279, 49, 415, 22);
		panel.add(comboModelSearch);
		
		StringSearchable searchableAccModel = new StringSearchable(accNameList);
		accNameSearchCombo = new AutocompleteJComboBox(searchableAccModel);
		accNameSearchCombo.setBounds(279, 107, 415, 22);
		panel.add(accNameSearchCombo);


		JLabel lblQuantiy = new JLabel("Quantiy");
		lblQuantiy.setBounds(67, 140, 138, 22);
		panel.add(lblQuantiy);

		txtQuantiy = new JTextField();
		txtQuantiy.setBounds(279, 140, 79, 23);
		panel.add(txtQuantiy);
		txtQuantiy.setColumns(10);

		JLabel lblDealerPricedp = new JLabel("Dealer Price (DP)");
		lblDealerPricedp.setBounds(67, 173, 163, 22);
		panel.add(lblDealerPricedp);

		JLabel lblSP = new JLabel("Selling Price (SP)");
		lblSP.setBounds(67, 206, 153, 22);
		panel.add(lblSP);

		JLabel lblMargin = new JLabel("Margin %");
		lblMargin.setBounds(67, 239, 153, 22);
		panel.add(lblMargin);

		JButton btnAddStock = new JButton("Add Accessory");
		btnAddStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(accNameSearchCombo.getSelectedItem().toString().equals("")){
					JOptionPane.showMessageDialog(null, "Please input Accessory Name", 
                            "Invalid Accessory Name",
                            JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				
				if(txtQuantiy.getText().equals("")||txtQuantiy.getText().equals("0")){
					JOptionPane.showMessageDialog(null, "Please input valid Quantity", 
                            "Invalid Accessory Quantity",
                            JOptionPane.WARNING_MESSAGE);
					return;
				}
				AccessoryStock stock = new AccessoryStock();

				stock.setPhmodelName(comboModelSearch.getSelectedItem()
						.toString());
				stock.setAccModel(accNameSearchCombo.getSelectedItem().toString());
				stock.setAccType(comboAccType.getSelectedItem().toString());
				stock.setQuantity(Integer.parseInt(txtQuantiy.getText()));
				
				String marginString = ftfMargin.getText();
				String dpString = ftfDP.getText();
				String spString = ftfSP.getText();
				float marginper =0;
				float dp=0;
				float sp =0;
				if(!marginString.equals("")){
					marginper = Float.parseFloat(marginString.replace(",", ""));
				}
				if(!dpString.equals("")){
					dp =Float.parseFloat(dpString.replace(",", "")) ;
				}
				if(!spString.equals("")){
					sp = Float.parseFloat(spString.replace(",", ""));
				}
				float profit = sp-dp;
				stock.setMargin(marginper);
				stock.setSp(sp);
				stock.setDp(dp);
				Date arvDate = (Date) datePicker.getModel().getValue();
				stock.setArrivalDate(arvDate);
				int qty = 0;
				try {
					qty = Integer.parseInt(txtQuantiy.getText());
				} catch (NumberFormatException ex) {

				}

				stock.setAvailable(qty);
				stock.setDesription(textAreaDesc.getText());
				stock.setSp(Float.parseFloat(ftfSP.getText().replace(",", "")));
				float margin =profit+ marginper*dp/100;
				stock.setMarginAmount(margin);
				totalCost += stock.getDp()*stock.getQuantity();
				accessoryService.add(stock);
				lblCost.setText(totalCost.toString());
				sm.addRow(stock);
				sm.fireTableDataChanged();
				accNameSearchCombo.setSelectedIndex(0);
				txtQuantiy.setText("0");
			}
		});
		btnAddStock.setBounds(490, 432, 204, 23);
		panel.add(btnAddStock);

		JLabel lblTotalCost = new JLabel("Total Stock Added Cost : ");
		lblTotalCost.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTotalCost.setBounds(320, 636, 199, 22);
		panel.add(lblTotalCost);

		lblCost = new JLabel("0");
		lblCost.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCost.setForeground(Color.GREEN);
		lblCost.setBounds(559, 631, 133, 28);
		panel.add(lblCost);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 466, 656, 146);
		panel.add(scrollPane);

		accessoryTable = new JTable(sm);
		scrollPane.setViewportView(accessoryTable);

		JLabel lblArrivalDate = new JLabel("Arrival Date");
		lblArrivalDate.setBounds(67, 11, 79, 22);
		panel.add(lblArrivalDate);

		UtilDateModel model = new UtilDateModel();
		model.setSelected(true);

		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		datePicker = new JDatePickerImpl(datePanel);
		datePicker.setBounds(279, 11, 144, 28);
		panel.add(datePicker);

		textArrivalDate = new JTextField();
		// textArrivalDate.setBounds(279, 178, 86, 20);
		panel.add(textArrivalDate);
		textArrivalDate.setColumns(10);

		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(67, 272, 79, 14);
		panel.add(lblDescription);

		textAreaDesc = new JTextArea();
		textAreaDesc.setBounds(279, 272, 415, 69);
		panel.add(textAreaDesc);

		JLabel label = new JLabel("Margin");
		label.setBounds(413, 239, 55, 22);
		panel.add(label);

		lblMarginValue = new JLabel("0");
		lblMarginValue.setBounds(503, 239, 107, 22);
		panel.add(lblMarginValue);

		ftfDP = new JFormattedTextField(moneyFormat);
		ftfDP.setText("0");
		ftfDP.setBounds(279, 174, 79, 20);
		panel.add(ftfDP);

		ftfSP = new JFormattedTextField(moneyFormat);
		ftfSP.setText("0");
		ftfSP.setBounds(279, 207, 79, 20);
		panel.add(ftfSP);

		ftfMargin = new JFormattedTextField(percentFormat);
		ftfMargin.setText("0");
		ftfMargin.setBounds(279, 240, 79, 20);
		panel.add(ftfMargin);

		JLabel lblAccessoryType = new JLabel("Accessory Type");
		lblAccessoryType.setBounds(67, 82, 89, 14);
		panel.add(lblAccessoryType);

		List<String> accType = new ArrayList<String>();
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
		comboAccType.setBounds(279, 79, 415, 20);

		panel.add(comboAccType);
		for (String type : accType) {
			comboAccType.addItem(type);
		}

		JLabel lblAccessoryName = new JLabel("Accessory Name");
		lblAccessoryName.setBounds(67, 107, 138, 22);
		panel.add(lblAccessoryName);
		
		
		DocumentListener documentListener = new DocumentListener() {
			public void changedUpdate(DocumentEvent documentEvent) {
			}

			public void insertUpdate(DocumentEvent documentEvent) {
				calculateMargin();
			}

			public void removeUpdate(DocumentEvent documentEvent) {
				// calculateMargin();
			}

			public void calculateMargin() {
				if (ftfMargin.getText() != null && ftfDP.getText() != null) {
					Float margin = Float.parseFloat(ftfMargin.getText()
							.replace(",", ""));
					Float dp = Float.parseFloat(ftfDP.getText()
							.replace(",", ""));
					System.out.println("" + margin + dp);

					Float marginAmount = (float) 0;
					if (margin == null || dp == null) {
						marginAmount = (float) 0;
					} else {
						marginAmount = margin * dp / 100;
					}
					lblMarginValue.setText("Rs. " + marginAmount);

				}
			}

		};

		ftfMargin.getDocument().addDocumentListener(documentListener);
		ftfDP.getDocument().addDocumentListener(documentListener);

	}

	class StockModel extends AbstractTableModel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 6567154131372853697L;

		private String[] columnNames = { "Stock Id", "Phone Model", "Acc Name",
				"Acc Type", "Arrival Date", "Quantity", "Margin",
				"Selling Price", "Deal Price", "Description" };

		AccessoryStock accessoryStock = new AccessoryStock();

		private List<AccessoryStock> data = new ArrayList<AccessoryStock>();

		StockModel() {
			// data = accessoryService.getAllDetails();
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
				return as.getArrivalDate();
			case 5:
				return as.getQuantity();
			case 6:
				return as.getMarginAmount();
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
			// data = accessoryService.getAllDetails();
		}

		public void addRow(Object record) {
			AccessoryStock as = (AccessoryStock) record;
			data.add(as);
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

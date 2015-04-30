package com.univercellmobiles.app.ui.inventory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
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

import net.sourceforge.jdatepicker.JDatePicker;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.univercellmobiles.app.beans.AccessoryStock;
import com.univercellmobiles.app.beans.PhoneStock;
import com.univercellmobiles.app.service.AccessoryStockService;
import com.univercellmobiles.app.service.PhoneModelService;
import com.univercellmobiles.app.service.PhoneModelServiceImpl;
import com.univercellmobiles.app.service.PhoneStockService;
import com.univercellmobiles.app.ui.common.custom.AutocompleteJComboBox;
import com.univercellmobiles.app.ui.common.custom.StringSearchable;
import com.univercellmobiles.app.util.ConfigBuilder;

import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;

import java.awt.Window.Type;



public class AddStock extends JFrame {
	private JTextField txtIMEI;
	private JTable table;
	private JTextField txtPlace;
	private JTextField txtInvoice;
	private boolean DEBUG = false;
	StockModel sm;
	private Float totalCost = (float) 0.0;
	private Float totalSPCost = (float) 0.0;
	JLabel lblCost;
	JLabel lblMarginValue;
	private NumberFormat moneyFormat;
	private NumberFormat percentFormat;
	AutocompleteJComboBox comboModelSearch;
	JFormattedTextField ftfDP;
	JFormattedTextField ftfSP;
	JFormattedTextField ftfMargin;
	JDatePickerImpl datePicker;
	JTextArea textAreaOffer;
	JTextArea textAreaDesc;
	JComboBox comboBox;
	JLabel lblSPCost;
	ConfigurableApplicationContext context ;
	PhoneStockService pss ;
	PhoneModelService pms;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddStock frame = new AddStock();
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
	public AddStock() {
		//setAlwaysOnTop(true);
		setType(Type.POPUP);
		setTitle("Add Phone Inventory");
	        percentFormat = NumberFormat.getInstance();
	        percentFormat.setMaximumIntegerDigits(2);
	        percentFormat.setMaximumFractionDigits(2);
	        moneyFormat = NumberFormat.getInstance();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(10, 10, 999, 780);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel = new JPanel();
		panel.setMaximumSize(new Dimension(50000, 50000));
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblModel = new JLabel("Phone Model");
		lblModel.setBounds(67, 99, 153, 22);
		panel.add(lblModel);

		List<String> modelsList = new ArrayList<String>();

		context = ConfigBuilder.getAppContext();
		pms = (PhoneModelService) context
				.getBean("phoneModelService");
		pss = (PhoneStockService) context
				.getBean("phoneStockService");

		modelsList = pms.getAllModelNames();

		StringSearchable searchable = new StringSearchable(modelsList);

		comboModelSearch = new AutocompleteJComboBox(searchable);

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

		comboModelSearch.setBounds(279, 99, 415, 22);
		panel.add(comboModelSearch);

		JLabel lblImeiNumber = new JLabel("IMEI Number");
		lblImeiNumber.setBounds(67, 132, 138, 22);
		panel.add(lblImeiNumber);

		txtIMEI = new JTextField();
		txtIMEI.setBounds(279, 131, 415, 23);
		panel.add(txtIMEI);
		txtIMEI.setColumns(10);

		JLabel lblSP = new JLabel("Selling Price (SP)");
		lblSP.setBounds(67, 198, 153, 22);
		panel.add(lblSP);
		

		JLabel lblMargin = new JLabel("Margin %");
		lblMargin.setBounds(67, 231, 153, 22);
		panel.add(lblMargin);
		
		

		JLabel lblOffer = new JLabel("Offer Details");
		lblOffer.setBounds(67, 312, 153, 22);
		panel.add(lblOffer);

		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(67, 374, 153, 22);
		panel.add(lblDescription);
		sm = new StockModel();
		table = new JTable();
		table.setModel(sm);
		table.setBounds(67, 461, 627, -116);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(67, 473, 625, 202);
		panel.add(scrollPane);

		JLabel lblPlace = new JLabel("Place");
		lblPlace.setBounds(67, 66, 163, 22);
		panel.add(lblPlace);

		txtPlace = new JTextField();
		txtPlace.setBounds(279, 66, 415, 22);
		panel.add(txtPlace);
		txtPlace.setColumns(10);

		JLabel lblInvoiceNo = new JLabel("Invoice No.");
		lblInvoiceNo.setBounds(67, 32, 184, 22);
		panel.add(lblInvoiceNo);

		txtInvoice = new JTextField();
		txtInvoice.setBounds(279, 33, 207, 22);
		panel.add(txtInvoice);
		txtInvoice.setColumns(10);
		
		   lblSPCost = new JLabel("0");
		    lblSPCost.setForeground(Color.GREEN);
		    lblSPCost.setFont(new Font("Tahoma", Font.BOLD, 11));
		    lblSPCost.setBounds(873, 633, 79, 18);
		    panel.add(lblSPCost);

		JButton btnAddStock = new JButton("Add Stock");
		btnAddStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtIMEI.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Please Input the IEMI Number.", 
                            "InCorrect Input",
                            JOptionPane.WARNING_MESSAGE);
					return;
				}
				PhoneStock stock = new PhoneStock();
				stock.setImeiNo(txtIMEI.getText());
				if(comboModelSearch.getSelectedItem()==null){
					JOptionPane.showMessageDialog(null, "Please Select a Phone Model.", 
                            "InCorrect Phone Model",
                            JOptionPane.WARNING_MESSAGE);
					return;
				}
				stock.setPhModel(comboModelSearch.getSelectedItem().toString());
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
				stock.setAvailable(1);
				stock.setInvoiceNo(txtInvoice.getText());
				stock.setDistributor(null);
				stock.setDescription(textAreaDesc.getText());
				stock.setOffer(textAreaOffer.getText());
				stock.setPlace(txtPlace.getText());
				stock.setBp(sp);
				stock.setDistributor(comboBox.getSelectedItem().toString());
				float margin =profit+ marginper*dp/100;
				stock.setMarginAmount(margin);
				totalCost += stock.getDp();
				totalSPCost+=stock.getSp();
				pss.add(stock);
				lblCost.setText(totalCost.toString());
				lblSPCost.setText(totalSPCost.toString());
				sm.addRow(stock);
				sm.fireTableDataChanged();
				
				txtIMEI.setText("");
				ftfDP.setText("0");
				ftfSP.setText("0");
				ftfMargin.setText("0");
				

			}
		});
		btnAddStock.setBounds(577, 439, 115, 23);
		panel.add(btnAddStock);

		JLabel lblTotalCost = new JLabel("Total Purchases : ");
		lblTotalCost.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotalCost.setBounds(721, 598, 128, 22);
		panel.add(lblTotalCost);

		lblCost = new JLabel("0");
		lblCost.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCost.setForeground(Color.GREEN);
		lblCost.setBounds(873, 600, 79, 18);
		panel.add(lblCost);

		JLabel lblDealerPricedp = new JLabel("Dealer Price (DP)");
		lblDealerPricedp.setBounds(67, 165, 163, 22);
		panel.add(lblDealerPricedp);
		

		JLabel lblMargin_1 = new JLabel("Margin");
		lblMargin_1.setBounds(417, 231, 55, 22);
		panel.add(lblMargin_1);

		lblMarginValue = new JLabel("0");
		lblMarginValue.setBounds(482, 231, 174, 22);
		panel.add(lblMarginValue);

		textAreaOffer = new JTextArea();
		textAreaOffer.setBounds(279, 311, 415, 39);
		panel.add(textAreaOffer);

		textAreaDesc = new JTextArea();
		textAreaDesc.setBounds(279, 361, 415, 51);
		panel.add(textAreaDesc);

		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(497, 36, 30, 19);
		panel.add(lblDate);
		
			UtilDateModel model = new UtilDateModel();  
			model.setSelected(true);
			Date today = new Date();
			//model.setDate(today.getYear(),today.getDate(),today.getDate());
		    JDatePanelImpl datePanel = new JDatePanelImpl(model);  
		    datePicker = new JDatePickerImpl(datePanel);
		    datePicker.setBounds(528, 32,160,28);
		    panel.add(datePicker);
		    
		    ftfDP = new JFormattedTextField(moneyFormat);
		    ftfDP.setText("0");
		    ftfDP.setBounds(279, 166, 79, 20);
		    panel.add(ftfDP);
		    
		    ftfSP = new JFormattedTextField(moneyFormat);
		    ftfSP.setText("0");
		    ftfSP.setBounds(279, 199, 79, 20);
		    panel.add(ftfSP);
		    
		    ftfMargin = new JFormattedTextField(percentFormat);
		    ftfMargin.setText("0");
		    ftfMargin.setBounds(279, 232, 79, 20);
		    panel.add(ftfMargin);
		    
		    JLabel lblDistributor = new JLabel("Distributor");
		    lblDistributor.setBounds(67, 264, 115, 22);
		    panel.add(lblDistributor);
		    
		    comboBox = new JComboBox();
		    comboBox.setBounds(279, 265, 193, 22);
		    comboBox.addItem("UNIVERCELL");
		    comboBox.addItem("OTHERS");
		    panel.add(comboBox);
		    
		    JLabel lblTotalPurchaseSp = new JLabel("Total Purchase SP :");
		    lblTotalPurchaseSp.setFont(new Font("Tahoma", Font.BOLD, 11));
		    lblTotalPurchaseSp.setBounds(721, 631, 128, 22);
		    panel.add(lblTotalPurchaseSp);
		    
		 
		    
		
		    
		    DocumentListener documentListener = new DocumentListener() {
			      public void changedUpdate(DocumentEvent documentEvent) {
			      }
			      public void insertUpdate(DocumentEvent documentEvent) {
			    	  calculateMargin();
			      }
			      public void removeUpdate(DocumentEvent documentEvent) {
			    	 // calculateMargin();
			      }
			      
			      public void calculateMargin(){
			    	  if(ftfMargin.getText()!=null &&ftfDP.getText()!=null&&ftfSP.getText()!=null ){
			    		  String marginString = ftfMargin.getText();
							String dpString = ftfDP.getText();
							String spString =ftfSP.getText();
							float margin =0;
							float dp=0;
							float sp =0;
							if(!marginString.equals("")){
								margin = Float.parseFloat(marginString.replace(",", ""));
							}
							if(!dpString.equals("")){
								dp =Float.parseFloat(dpString.replace(",", "")) ;
							}
							
							if(!spString.equals("")){
								sp = Float.parseFloat(spString.replace(",", ""));
							}
							
						float profit = sp-dp;
			    	
			    	 System.out.println(""+margin +dp);
			    	 
			    	 Float marginAmount = (float) 0;
			    	 if(margin==0 || dp ==0){
			    		 marginAmount = (float) 0;
			    	 }
			    	 else{
			    	 marginAmount = (margin*dp/100)+profit;
			    	 }
			    	 lblMarginValue.setText("Rs. " +marginAmount);
			    	 
			      }
			      }
			      
			     
			    };
			    
			    ftfMargin.getDocument().addDocumentListener(documentListener);
			    ftfDP.getDocument().addDocumentListener(documentListener);
			    

	}

		


	class StockModel extends AbstractTableModel {
		private String[] columnNames = { "Stock Id", "Model", "IMEI", "Margin %","Margin Amount",
				"Billing Price","DP" };

		AccessoryStock accessoryStock = new AccessoryStock();

		private List<PhoneStock> data = new ArrayList<PhoneStock>();

		StockModel() {
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
			PhoneStock ps = data.get(row);
			/*
			 * private String accStockId; // private Timestamp arrivalDate;
			 * private String accModel; private String phmodelName; //private
			 * Timestamp soldDate; private String desription; private String
			 * margin; private Float dp; private Float sp;
			 */
			switch (col) {
			case 0:
				return ps.getId();
			case 1:
				return ps.getPhModel();
			case 2:
				return ps.getImeiNo();
			case 3:
				return ps.getMargin();
			case 4:
				return ps.getMarginAmount();
			case 5:
				return ps.getSp();
			case 6:
				return ps.getDp();
			
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
			PhoneStock ps = (PhoneStock) record;
			data.add(ps);
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

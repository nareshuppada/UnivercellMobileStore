package com.univercellmobiles.app.ui.funds;

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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import net.sourceforge.jdatepicker.JDatePicker;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.univercellmobiles.app.beans.AccessoryStock;
import com.univercellmobiles.app.beans.Brand;
import com.univercellmobiles.app.beans.FundStatus;
import com.univercellmobiles.app.beans.PhoneModel;
import com.univercellmobiles.app.beans.PhoneStock;
import com.univercellmobiles.app.beans.Transactions;
import com.univercellmobiles.app.service.AccessorySalesService;
import com.univercellmobiles.app.service.AccessoryStockService;
import com.univercellmobiles.app.service.BrandService;
import com.univercellmobiles.app.service.FundStatusService;
import com.univercellmobiles.app.service.PhoneModelService;
import com.univercellmobiles.app.service.PhoneStockService;
import com.univercellmobiles.app.service.SalesService;
import com.univercellmobiles.app.service.TransactionService;
import com.univercellmobiles.app.ui.common.custom.AutocompleteJComboBox;
import com.univercellmobiles.app.ui.common.custom.StringSearchable;
import com.univercellmobiles.app.util.ConfigBuilder;

import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;

import java.awt.Window.Type;



public class BalanceSheet extends JFrame {
	private JTable table;
	private boolean DEBUG = false;
	FundsModel fm;
	ConfigurableApplicationContext context = ConfigBuilder.getAppContext();
	FundStatus currentSelection = null;
	JTextArea textAreaDesc;
	FundStatusService fs;
	PhoneStockService pss;
	TransactionService txs;
	AccessoryStockService ass;
	AccessorySalesService asaless;
	SalesService ss;
	private JTextField txtCash;
	private JTextField txtUniFunds;
	private JTextField txtReturns;
	private JTextField txtDeposits;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BalanceSheet frame = new BalanceSheet();

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
	public BalanceSheet() {
		setAlwaysOnTop(true);
		setType(Type.POPUP);
		setTitle("End of Day Funds");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(10, 10, 1150, 780);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel = new JPanel();
		panel.setMaximumSize(new Dimension(50000, 50000));
		getContentPane().add(panel);
		panel.setLayout(null);
		
		new ArrayList<Transactions>();

		
		fs = (FundStatusService) context.getBean("fundStatusService");
		txs = (TransactionService) context.getBean("transactionService");
		pss = (PhoneStockService) context.getBean("phoneStockService");
		ss =  (SalesService) context.getBean("salesService");
		ass= (AccessoryStockService)context.getBean("accessoryStockService");
		asaless =(AccessorySalesService)context.getBean("accessorySalesService");

		fm = new FundsModel();
		table = new JTable();
		table.setModel(fm);
		table.setBounds(67, 461, 627, -116);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(67, 350, 1022, 331);
		panel.add(scrollPane);

		JButton btnAddBalance = new JButton("Add Balance");
		btnAddBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FundStatus f = new FundStatus();
				f.setAssets(txs.getAssetsBalance());
				f.setExpense(txs.getExpenseBalance());
				f.setFundsout(txs.getInvestmentOut());
				f.setInvestment(txs.getInvestmentBalance()+txs.getInvestmentOut());
				f.setReturns(Float.parseFloat(txtReturns.getText().equals("")?"0":txtReturns.getText()));
				f.setStockValue(pss.getCurrentStockValue());
				f.setUnivercellfunds(Float.parseFloat(txtUniFunds.getText().equals("")?"0":txtUniFunds.getText()));
				f.setProfit(ss.getTodaysProfit());
			    f.setDeposits(Float.parseFloat(txtDeposits.getText().equals("")?"0":txtDeposits.getText()));
			    f.setCash(Float.parseFloat(txtCash.getText().equals("")?"0":txtCash.getText()));
			    f.setAccStockValue(ass.getCurrentStockValue());
			    f.setAccProfit(asaless.getTodaysProfit());
			    f.setToday(new Date());
				fs.add(f);
				fm.addRow(f);
				fm.refreshTableData();
				fm.fireTableDataChanged();

			}
		});
		btnAddBalance.setBounds(552, 301, 140, 23);
		panel.add(btnAddBalance);
		
		
		JButton btnDeleteBrand = new JButton("Delete Balance");
		btnDeleteBrand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentSelection!=null){
					fs.delete(currentSelection.getStatusId());
					fm.refreshTableData();
					fm.fireTableDataChanged();
					
				}
			}
		});
		btnDeleteBrand.setBounds(552, 707, 140, 23);
		panel.add(btnDeleteBrand);
		
			//model.setDate(today.getYear(),today.getDate(),today.getDate());
		
		 table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		 
		 JLabel lblDesc = new JLabel("Description");
		 lblDesc.setBounds(64, 179, 187, 23);
		 panel.add(lblDesc);
		 
		 textAreaDesc = new JTextArea();
		 textAreaDesc.setBounds(291, 178, 401, 83);
		 panel.add(textAreaDesc);
		 
		 JLabel lblCashInCounter = new JLabel("Cash in Counter");
		 lblCashInCounter.setBounds(67, 29, 140, 23);
		 panel.add(lblCashInCounter);
		 
		 txtCash = new JTextField();
		 txtCash.setBounds(291, 30, 180, 20);
		 panel.add(txtCash);
		 txtCash.setColumns(10);
		 
		 JLabel lblFundsWithUnivercell = new JLabel("Funds with Univercell");
		 lblFundsWithUnivercell.setBounds(67, 59, 140, 20);
		 panel.add(lblFundsWithUnivercell);
		 
		 txtUniFunds = new JTextField();
		 txtUniFunds.setBounds(291, 59, 180, 20);
		 panel.add(txtUniFunds);
		 txtUniFunds.setColumns(10);
		 
		 JLabel lblReturns = new JLabel("Returns");
		 lblReturns.setBounds(67, 91, 140, 21);
		 panel.add(lblReturns);
		 
		 txtReturns = new JTextField();
		 txtReturns.setBounds(291, 91, 180, 20);
		 panel.add(txtReturns);
		 txtReturns.setColumns(10);
		 
		 JLabel lblBankDeposits = new JLabel("Bank Deposits");
		 lblBankDeposits.setBounds(67, 123, 140, 23);
		 panel.add(lblBankDeposits);
		 
		 txtDeposits = new JTextField();
		 txtDeposits.setBounds(291, 122, 180, 20);
		 panel.add(txtDeposits);
		 txtDeposits.setColumns(10);
		 

	        
	        //When selection changes, provide user with row numbers for
	        //both view and model.
	        table.getSelectionModel().addListSelectionListener(
	                new ListSelectionListener() {
	                    public void valueChanged(ListSelectionEvent event) {
	                        int viewRow = table.getSelectedRow();
	                        if (viewRow < 0) {
	                            //Selection got filtered away.
	                            //statusText.setText("");
	                        } else {
	                            int modelRow = 
	                            table.convertRowIndexToModel(viewRow);
	                            currentSelection = fm.getRow(modelRow);
	                           /* txtAmount.setText(Float.toString(currentSelection.getAmount()));
	                            textAreaDesc.setText(currentSelection.getDescription());*/
	                         
	                        }
	                    }
	                }
	        );

		   
			    

	}

		


	class FundsModel extends AbstractTableModel {
		private String[] columnNames = { "Date", "Investment", "Expense", "Phone Stock Value", "Phones Sale Profit","Assets","Cash","Univercell Funds","Returns","Investment WidthDrawn","Deposits","Acc Stock Value","Acc Sale Profit"};
		FundStatus fund = new FundStatus();

		private List<FundStatus> data = new ArrayList<FundStatus>();

		FundsModel() {
			 data = fs.getAllDetails();
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
			FundStatus f = data.get(row);
			/*
			 * private String accStockId; // private Timestamp arrivalDate;
			 * private String accModel; private String phmodelName; //private
			 * Timestamp soldDate; private String desription; private String
			 * margin; private Float dp; private Float sp;
			 */
			switch (col) {
			case 0:
				return f.getToday();
			case 1:
				return f.getInvestment();
			case 2:
				return f.getExpense();
			case 3:
				return f.getStockValue();
			case 4:
				return f.getProfit();
			case 5:
				return f.getAssets();
			case 6:
				return f.getCash();
			case 7:
				return f.getUnivercellfunds();
			case 8:
				return f.getReturns();
			case 9:
				return f.getFundsout();
			case 10:
				return f.getDeposits();
			case 11:
				return f.getAccStockValue();
			case 12:
				return f.getAccProfit();
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
			 data = fs.getAllDetails();
		}

		public void addRow(Object record) {
			FundStatus fs = (FundStatus) record;
			data.add(fs);
		}

		  public FundStatus getRow(int row){
	        	return data.get(row);
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

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
import javax.swing.JOptionPane;
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
import com.univercellmobiles.app.service.ReturnsService;
import com.univercellmobiles.app.service.SalesService;
import com.univercellmobiles.app.service.TransactionService;
import com.univercellmobiles.app.ui.common.custom.AutocompleteJComboBox;
import com.univercellmobiles.app.ui.common.custom.StringSearchable;
import com.univercellmobiles.app.ui.reports.SendEmail;
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
	ReturnsService rs;
	SalesService ss;
	private JTextField txtCash;
	private JTextField txtUniFunds;
	private JTextField txtReturns;
	private JTextField txtDeposits;
	private JTextField txtRecharges;

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
		setType(Type.POPUP);
		setTitle("End of Day Funds");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(10, 10, 1334, 780);
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
		rs = (ReturnsService)context.getBean("returnService");
		

		fm = new FundsModel();
		table = new JTable();
		table.setModel(fm);
		table.setBounds(67, 461, 627, -116);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(67, 350, 1198, 331);
		panel.add(scrollPane);

		JButton btnAddBalance = new JButton("Add Balance");
		btnAddBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtCash.getText().equals("")||txtUniFunds.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Fields can not be blank.", 
                            "Blank Input",
                            JOptionPane.WARNING_MESSAGE);
					return;
					
				}
				String htmlString = "<h1>PALASA UNIVERCELL FRANCHISEE Firm Value as on :"+new Date()+"</h1>";
				FundStatus f = new FundStatus();
				f.setAssets(txs.getAssetsBalance());
				f.setExpense(txs.getTodaysExpense());
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
			    f.setPhoneSale(ss.getTodaySale());
			    f.setAccSale(asaless.getTodaySale());
			    f.setRecharges(Float.parseFloat(txtRecharges.getText().equals("")?"0":txtRecharges.getText()));
			    htmlString += "<br/>";
			    htmlString += "<table>";
			    htmlString += "<tr><td><b>Investment :</b></td><td>"+f.getInvestment()+"</td></tr>";
			    htmlString += "<tr><td><b>Phone Stock Value :</b></td><td>"+f.getStockValue()+"</td></tr>";
			    htmlString += "<tr><td><b>Acc. Stock Value :</b></td><td>"+f.getAccStockValue()+"</td></tr>";
			    htmlString += "<tr><td><b>Cash :</b></td><td>"+f.getCash()+"</td></tr>";
			    htmlString += "<tr><td><b>Cash in Bank :</b></td><td>"+f.getDeposits()+"</td></tr>";
			    htmlString += "<tr><td><b>Univercell Funds :</b></td><td>"+f.getUnivercellfunds()+"</td></tr>";
			    htmlString += "<tr><td><b>Stock Returns :</b></td><td>"+f.getReturns()+"</td></tr>";
			    htmlString += "<tr><td><b>Fixed Assets :</b></td><td>"+f.getAssets()+"</td></tr>";
			    htmlString += "<tr><td><b>Recharges :</b></td><td>"+f.getRecharges()+"</td></tr>";
			    htmlString += "<tr><td><b>Today's Phone Sale :</b></td><td>"+f.getPhoneSale()+"</td></tr>";
			    htmlString += "<tr><td><b>Today's Phone Sale Profit :</b></td><td>"+f.getProfit()+"</td></tr>";
			    htmlString += "<tr><td><b>Today's Acc. Sale :</b></td><td>"+f.getAccSale()+"</td></tr>";
			    htmlString += "<tr><td><b>Today's Acc. Sale Profit :</b></td><td>"+f.getAccProfit()+"</td></tr>";
			    float todaysProfit = (float) (f.getAccProfit()+f.getProfit()+((f.getRecharges()*2.8)/100));
			    float todaysSale = f.getAccSale()+f.getPhoneSale();
			    float todaysExpense = f.getExpense();
			    float effProfit = todaysProfit-todaysExpense;
			    htmlString += "<tr><td><b>Today's Total Sale(Phone + Acc) :</b></td><td>"+todaysSale+"</td></tr>";
			    htmlString += "<tr><td><b>Today's Total Profit(Phone + Acc + Recharges) :</b></td><td>"+todaysProfit+"</td></tr>";
			    htmlString += "<tr><td><b>Today's Expense :</b></td><td>"+todaysExpense+"</td></tr>";
			    htmlString += "<tr><td><b>Avg Daily Expense :</b></td><td>"+txs.getAvgExpense()+"</td></tr>";
			    htmlString += "<tr><td><b>Today's Effective Profit :</b></td><td>"+effProfit+"</td></tr>";
			    htmlString += "<tr><td><b>Last 30 Days Summary :</b></td></tr>";
			    htmlString += "<tr><td><b>Last 30 Days Purchase :</b></td><td>"+pss.get30DaysPurchase()+"</td></tr>";
			    htmlString += "<tr><td><b>Last 30 Days Sales :</b></td><td>"+ss.get30DaysSales()+"</td></tr>";
			    float profit30 = ss.get30DayProfit()+asaless.get30DayProfit();
			    htmlString += "<tr><td><b>Last 30 Days Profit :</b></td><td>"+profit30+"</td></tr>";
			    float expense30 =txs.get30DaysExpense();
			    htmlString += "<tr><td><b>Last 30 Days Expense :</b></td><td>"+expense30+"</td></tr>";
			    float balance30 = profit30-expense30;
			    htmlString += "<tr><td><b>Last 30 Days (Profit-Expense) :</b></td><td>"+balance30+"</td></tr>";
			    
			    
			    
			   
			    float currValue=f.getCash()+f.getStockValue()+f.getAccStockValue()+f.getUnivercellfunds()+f.getReturns()+f.getDeposits()+f.getAssets();
				
				float growth = currValue-f.getInvestment();
				
				float ROI = (growth/f.getInvestment())*100;
				
				 htmlString += "<tr><td><b>Firm Current Value :</b></td><td>"+currValue+"</td></tr>";
				 htmlString += "<tr><td><b>Growth :</b></td><td>"+growth+"</td></tr>";
				 htmlString += "<tr><td><b>ROI :</b></td><td>"+ROI+"</td></tr>";
				 htmlString += "</table>";
				    
				
				fs.add(f);
				fm.addRow(f);
				fm.refreshTableData();
				fm.fireTableDataChanged();
				
				SendEmail.sendHTMLasMail(htmlString);
				txtUniFunds.setText("");
				txtDeposits.setText("");
				txtCash.setText("");
				txtRecharges.setText("");

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
		btnDeleteBrand.setBounds(1125, 707, 140, 23);
		panel.add(btnDeleteBrand);
		
			//model.setDate(today.getYear(),today.getDate(),today.getDate());
		
		 table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		 
		 JLabel lblDesc = new JLabel("Description");
		 lblDesc.setBounds(67, 201, 187, 23);
		 panel.add(lblDesc);
		 
		 textAreaDesc = new JTextArea();
		 textAreaDesc.setBounds(291, 200, 401, 83);
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
		 txtReturns.setText("0");
		 txtReturns.setForeground(Color.RED);
		 txtReturns.setEditable(false);
		 txtReturns.setBounds(291, 91, 180, 20);
		 panel.add(txtReturns);
		 txtReturns.setColumns(10);
		 txtReturns.setText(Float.toString(rs.getAllReturnsAmount()));
		 
		 JLabel lblBankDeposits = new JLabel("Bank Deposits");
		 lblBankDeposits.setBounds(67, 123, 140, 23);
		 panel.add(lblBankDeposits);
		 
		 txtDeposits = new JTextField();
		 txtDeposits.setBounds(291, 122, 180, 20);
		 panel.add(txtDeposits);
		 txtDeposits.setColumns(10);
		 
		 JLabel lblRecharges = new JLabel("Recharges Amount");
		 lblRecharges.setBounds(67, 157, 140, 14);
		 panel.add(lblRecharges);
		 
		 txtRecharges = new JTextField();
		 txtRecharges.setBounds(291, 154, 180, 20);
		 panel.add(txtRecharges);
		 txtRecharges.setColumns(10);
		 txtRecharges.setText("0");
		 

	        
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
		private String[] columnNames = { "Date", "Investment", "Expense", "Phone Stock Value", "Phones Sale Profit","Assets","Cash","Univercell Funds","Returns","Investment WidthDrawn","Deposits","Acc Stock Value","Acc Sale Profit","Phone Sale","Acc. Sale","Recharges","Total Profit"};
		FundStatus fund = new FundStatus();

		private List<FundStatus> data = new ArrayList<FundStatus>();

		FundsModel() {
			 data = fs.getLastMonthDetails();
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
			case 13:
				return f.getPhoneSale();
			case 14:
				return f.getAccSale();
			case 15:
				return f.getRecharges();
			case 16:
				return f.getAccProfit()+f.getProfit()+f.getRecharges()*3/100;
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
			 data = fs.getLastMonthDetails();
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

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
import com.univercellmobiles.app.beans.PhoneModel;
import com.univercellmobiles.app.beans.PhoneStock;
import com.univercellmobiles.app.beans.Transactions;
import com.univercellmobiles.app.service.AccessoryStockService;
import com.univercellmobiles.app.service.BrandService;
import com.univercellmobiles.app.service.PhoneModelService;
import com.univercellmobiles.app.service.PhoneStockService;
import com.univercellmobiles.app.service.TransactionService;
import com.univercellmobiles.app.ui.common.custom.AutocompleteJComboBox;
import com.univercellmobiles.app.ui.common.custom.StringSearchable;
import com.univercellmobiles.app.util.ConfigBuilder;

import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;



public class ExpenseManagement extends JFrame {
	private JTable table;
	private boolean DEBUG = false;
	TransactionModel txm;
	private Float totalCost = (float) 0.0;
	private NumberFormat moneyFormat;
	private NumberFormat percentFormat;
	ConfigurableApplicationContext context =ConfigBuilder.getAppContext();

	Transactions currentSelection = null;
	private JTextField txtAmount;
	JTextArea textAreaDesc;
	JComboBox comboExpenseType;
	TransactionService txs;
	 JLabel expensetoday;
	 JLabel expense30;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExpenseManagement frame = new ExpenseManagement();
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
	public ExpenseManagement() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(10, 10, 771, 825);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel = new JPanel();
		panel.setMaximumSize(new Dimension(50000, 50000));
		getContentPane().add(panel);
		panel.setLayout(null);
		
		List<Transactions> txList = new ArrayList<Transactions>();

		List<String> expenseType = new ArrayList<String>();
		expenseType.add("Shop Rent");
		expenseType.add("Staff Salary");
		expenseType.add("Shop Utility Bills");
		expenseType.add("EMI Payment");
		expenseType.add("Transport Expense");
		expenseType.add("Misc");
		txs = (TransactionService) context.getBean("transactionService");

		
		 comboExpenseType = new JComboBox();
		 comboExpenseType.setBounds(291, 30, 401, 20);
		 panel.add(comboExpenseType);
		 for(String expense : expenseType){
			 comboExpenseType.addItem(expense);
		 }



		txm = new TransactionModel();
		table = new JTable();
		table.setModel(txm);
		table.setBounds(67, 461, 627, -116);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(67, 270, 625, 258);
		panel.add(scrollPane);

		JButton btnAddExpense = new JButton("Add Expense");
		btnAddExpense.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Transactions tx = new Transactions();
				tx.setAmount(Float.parseFloat(txtAmount.getText()));
				tx.setDescription(textAreaDesc.getText());
				tx.setTypeDetails(comboExpenseType.getSelectedItem().toString());
				tx.setExpenseDate(new Date());
				tx.setType(-1);
				txs.add(tx);
				txm.addRow(tx);
				txm.refreshTableData();
				txm.fireTableDataChanged();

			}
		});
		btnAddExpense.setBounds(552, 222, 140, 23);
		panel.add(btnAddExpense);
		
		
		JButton btnDeleteBrand = new JButton("Delete Expense");
		btnDeleteBrand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentSelection!=null){
					txs.delete(currentSelection.getTransId());
					txm.refreshTableData();
					txm.fireTableDataChanged();
					
				}
			}
		});
		btnDeleteBrand.setBounds(552, 554, 140, 23);
		panel.add(btnDeleteBrand);
		
			//model.setDate(today.getYear(),today.getDate(),today.getDate());
		
		 table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		 
		 JLabel lblExpenseType = new JLabel("Expense Type");
		 lblExpenseType.setBounds(67, 29, 187, 23);
		 panel.add(lblExpenseType);
		 
		 JLabel lblAmount = new JLabel("Amount");
		 lblAmount.setBounds(67, 63, 187, 23);
		 panel.add(lblAmount);
		 
		 txtAmount = new JTextField();
		 txtAmount.setBounds(291, 61, 180, 20);
		 panel.add(txtAmount);
		 txtAmount.setColumns(10);
		 
		 JLabel lblDesc = new JLabel("Description");
		 lblDesc.setBounds(67, 97, 187, 23);
		 panel.add(lblDesc);
		 
		 textAreaDesc = new JTextArea();
		 textAreaDesc.setBounds(291, 96, 401, 83);
		 panel.add(textAreaDesc);
		 
		 JLabel lblLastDays = new JLabel("Last 30 days Expense :");
		 lblLastDays.setFont(new Font("Tahoma", Font.BOLD, 11));
		 lblLastDays.setBounds(67, 554, 145, 23);
		 panel.add(lblLastDays);
		 
		 JLabel lblAvgExpensePer = new JLabel("Avg Expense per Day :");
		 lblAvgExpensePer.setFont(new Font("Tahoma", Font.BOLD, 11));
		 lblAvgExpensePer.setBounds(67, 588, 145, 20);
		 panel.add(lblAvgExpensePer);
		 
		 expense30 = new JLabel("0.0");
		 expense30.setForeground(Color.RED);
		 expense30.setFont(new Font("Tahoma", Font.BOLD, 11));
		 expense30.setBounds(229, 556, 96, 19);
		 expense30.setText(Float.toString(txs.get30DaysExpense()));
		 panel.add(expense30);
		 
		 expensetoday = new JLabel("0.0");
		 expensetoday.setForeground(Color.RED);
		 expensetoday.setFont(new Font("Tahoma", Font.BOLD, 11));
		 expensetoday.setBounds(229, 590, 96, 17);
		 expensetoday.setText(Float.toString(txs.getAvgExpense()));
		 panel.add(expensetoday);
		 
		 
		 

	        
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
	                            currentSelection = txm.getRow(modelRow);
	                            txtAmount.setText(Float.toString(currentSelection.getAmount()));
	                            textAreaDesc.setText(currentSelection.getDescription());
	                            comboExpenseType.setSelectedItem(currentSelection.getTypeDetails());
	                         
	                        }
	                    }
	                }
	        );

		   
			    

	}

		


	class TransactionModel extends AbstractTableModel {
		private String[] columnNames = { "Expense Id", "Type", "Desc", "Amount",
				"Date" };

		Transactions trans = new Transactions();

		private List<Transactions> data = new ArrayList<Transactions>();

		TransactionModel() {
			 data = txs.getLastMonthExpenseDetails();
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
			Transactions tx = data.get(row);
			/*
			 * private String accStockId; // private Timestamp arrivalDate;
			 * private String accModel; private String phmodelName; //private
			 * Timestamp soldDate; private String desription; private String
			 * margin; private Float dp; private Float sp;
			 */
			switch (col) {
			case 0:
				return tx.getTransId();
			case 1:
				return tx.getTypeDetails();
			case 2:
				return tx.getDescription();
			case 3:
				return tx.getAmount();
			case 4:
				return tx.getExpenseDate();
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
			 data = txs.getLastMonthExpenseDetails();
			 expensetoday.setText(Float.toString(txs.getAvgExpense()));
			 expense30.setText(Float.toString(txs.get30DaysExpense()));
			 
		}

		public void addRow(Object record) {
			Transactions tx = (Transactions) record;
			data.add(tx);
		}

		  public Transactions getRow(int row){
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

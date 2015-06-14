package com.univercellmobiles.app.ui.reports;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSeparator;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.univercellmobiles.app.beans.FundStatus;
import com.univercellmobiles.app.service.AccessorySalesService;
import com.univercellmobiles.app.service.AccessoryStockService;
import com.univercellmobiles.app.service.FundStatusService;
import com.univercellmobiles.app.service.PhoneStockService;
import com.univercellmobiles.app.service.ReturnsService;
import com.univercellmobiles.app.service.SalesService;
import com.univercellmobiles.app.service.TransactionService;
import com.univercellmobiles.app.util.ConfigBuilder;

import java.awt.Font;
import java.awt.Color;
import java.util.List;
import java.awt.Window.Type;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class FirmValue extends JFrame {

	private JPanel contentPane;
	private JTextField txtInvestment;
	private JTextField txtCash;
	private JTextField txtStock;
	private JTextField txtUniFunds;
	private JTextField txtBank;
	private JTextField txtAssets;
	private JTextField txtTotal;
	private JTextField txtROI;
	private JTextField txtGrowth;
	PhoneStockService pss;
	AccessoryStockService ass;
	AccessorySalesService asaless;
	ReturnsService rs;
	SalesService ss;
	FundStatusService fs;
	TransactionService txs;
	JLabel lblUpdatedDate;
	
	
	private JTextField txtAccStockValue;
	private JTextField txtReturns;
	private JTextField txtPhSale;
	private JTextField txtPhProfit;
	private JTextField txtAccSale;
	private JTextField txtAccProfit;
	private JTextField txtRecharges;
	private JTextField txtRechargeProfit;
	private JTextField txtTotalSale;
	private JTextField txtProfit;
	private JTextField txtExpense;
	private JTextField txtEffProfit;
	private JTextField txtEffCash;
	private JTextField txt30Expense;
	private JTextField txt30Profit;
	private JTextField txt30Balance;
	private JTextField txt30Purchase;
	private JTextField txt30Sales;
	JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirmValue frame = new FirmValue();
					//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					frame.setVisible(true);
					frame.setAlwaysOnTop(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FirmValue() {
		setType(Type.POPUP);
		setTitle("Firm Current Value");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 910, 740);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		ConfigurableApplicationContext context = ConfigBuilder.getAppContext();
		fs = (FundStatusService) context.getBean("fundStatusService");
		txs = (TransactionService) context.getBean("transactionService");
		pss = (PhoneStockService) context.getBean("phoneStockService");
		ss =  (SalesService) context.getBean("salesService");
		ass= (AccessoryStockService)context.getBean("accessoryStockService");
		asaless =(AccessorySalesService)context.getBean("accessorySalesService");
		rs = (ReturnsService)context.getBean("returnService");
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblInvestmentAmount = new JLabel("Investment Amount");
		lblInvestmentAmount.setBounds(85, 33, 147, 24);
		panel.add(lblInvestmentAmount);
		
		txtInvestment = new JTextField();
		txtInvestment.setForeground(Color.BLUE);
		txtInvestment.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtInvestment.setEditable(false);
		txtInvestment.setBounds(257, 35, 172, 20);
		panel.add(txtInvestment);
		txtInvestment.setColumns(10);
		
		JLabel lblCashAtStore = new JLabel("Cash At Store");
		lblCashAtStore.setBounds(85, 73, 147, 24);
		panel.add(lblCashAtStore);
		
		txtCash = new JTextField();
		txtCash.setEditable(false);
		txtCash.setBounds(257, 75, 172, 20);
		panel.add(txtCash);
		txtCash.setColumns(10);
		
		JLabel lblStockValue = new JLabel("Stock Value");
		lblStockValue.setBounds(85, 108, 147, 24);
		panel.add(lblStockValue);
		
		txtStock = new JTextField();
		txtStock.setEditable(false);
		txtStock.setBounds(257, 110, 172, 20);
		panel.add(txtStock);
		txtStock.setColumns(10);
		
		JLabel lblFundsWithUnivercell = new JLabel("Funds with Univercell");
		lblFundsWithUnivercell.setBounds(85, 212, 147, 24);
		panel.add(lblFundsWithUnivercell);
		
		txtUniFunds = new JTextField();
		txtUniFunds.setEditable(false);
		txtUniFunds.setBounds(257, 214, 172, 20);
		panel.add(txtUniFunds);
		txtUniFunds.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(33, 67, 782, 2);
		panel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(33, 346, 421, 7);
		panel.add(separator_1);
		
		JLabel lblBankDeposit = new JLabel("Bank Deposit");
		lblBankDeposit.setBounds(85, 245, 147, 20);
		panel.add(lblBankDeposit);
		
		txtBank = new JTextField();
		txtBank.setEditable(false);
		txtBank.setBounds(257, 245, 172, 20);
		panel.add(txtBank);
		txtBank.setColumns(10);
		
		JLabel lblTotal = new JLabel("Current Value");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTotal.setBounds(85, 364, 109, 24);
		panel.add(lblTotal);
		
		JLabel lblFixedAssets = new JLabel("Fixed Assets");
		lblFixedAssets.setBounds(85, 278, 147, 24);
		panel.add(lblFixedAssets);
		
		txtAssets = new JTextField();
		txtAssets.setEditable(false);
		txtAssets.setBounds(257, 282, 172, 20);
		panel.add(txtAssets);
		txtAssets.setColumns(10);
		
		txtTotal = new JTextField();
		txtTotal.setForeground(Color.RED);
		txtTotal.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtTotal.setEditable(false);
		txtTotal.setBounds(257, 362, 172, 31);
		panel.add(txtTotal);
		txtTotal.setColumns(10);
		
		JLabel lblRoi = new JLabel("ROI");
		lblRoi.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRoi.setBounds(85, 467, 69, 24);
		panel.add(lblRoi);
		
		txtROI = new JTextField();
		txtROI.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtROI.setEditable(false);
		txtROI.setBounds(257, 462, 172, 31);
		panel.add(txtROI);
		txtROI.setColumns(10);
		
		JLabel lblInvestment = new JLabel("Growth");
		lblInvestment.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblInvestment.setBounds(86, 416, 86, 28);
		panel.add(lblInvestment);
		
		txtGrowth = new JTextField();
		txtGrowth.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtGrowth.setEditable(false);
		txtGrowth.setBounds(257, 413, 172, 30);
		panel.add(txtGrowth);
		txtGrowth.setColumns(10);
		
		lblUpdatedDate = new JLabel("");
		lblUpdatedDate.setForeground(new Color(255, 0, 255));
		lblUpdatedDate.setBounds(489, 101, 126, 20);
		panel.add(lblUpdatedDate);
		
		txtAccStockValue = new JTextField();
		txtAccStockValue.setText("0.0");
		txtAccStockValue.setEditable(false);
		txtAccStockValue.setColumns(10);
		txtAccStockValue.setBounds(257, 141, 172, 20);
		panel.add(txtAccStockValue);
		
		
		JLabel lblDetailsUpdatedAs = new JLabel("Details Updated as on ");
		lblDetailsUpdatedAs.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDetailsUpdatedAs.setBounds(489, 75, 126, 20);
		panel.add(lblDetailsUpdatedAs);
		
		JLabel lblAccStockValue = new JLabel("Acc. Stock Value");
		lblAccStockValue.setBounds(85, 143, 120, 14);
		panel.add(lblAccStockValue);
		
		JLabel lblReturns = new JLabel("Returns");
		lblReturns.setBounds(489, 217, 92, 14);
		panel.add(lblReturns);
		
		txtReturns = new JTextField();
		txtReturns.setText("0");
		txtReturns.setEditable(false);
		txtReturns.setBounds(686, 214, 102, 20);
		panel.add(txtReturns);
		txtReturns.setColumns(10);
		
		JLabel lblTodaysSaleDetails = new JLabel("Todays Sale Details :");
		lblTodaysSaleDetails.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTodaysSaleDetails.setBounds(489, 310, 299, 24);
		panel.add(lblTodaysSaleDetails);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(489, 346, 299, 7);
		panel.add(separator_2);
		
		JLabel lblPhoneSale = new JLabel("Phone");
		lblPhoneSale.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPhoneSale.setBounds(489, 383, 56, 14);
		panel.add(lblPhoneSale);
		
		txtPhSale = new JTextField();
		txtPhSale.setText("0");
		txtPhSale.setEditable(false);
		txtPhSale.setBounds(574, 380, 102, 20);
		panel.add(txtPhSale);
		txtPhSale.setColumns(10);
		
		JLabel lblSale = new JLabel("Sale");
		lblSale.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSale.setBounds(601, 355, 46, 14);
		panel.add(lblSale);
		
		JLabel lblProfit = new JLabel("Profit");
		lblProfit.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblProfit.setBounds(710, 355, 46, 14);
		panel.add(lblProfit);
		
		txtPhProfit = new JTextField();
		txtPhProfit.setText("0");
		txtPhProfit.setEditable(false);
		txtPhProfit.setBounds(686, 380, 102, 20);
		panel.add(txtPhProfit);
		txtPhProfit.setColumns(10);
		
		JLabel lblAccessoriers = new JLabel("Accessoriers");
		lblAccessoriers.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAccessoriers.setBounds(489, 411, 79, 14);
		panel.add(lblAccessoriers);
		
		txtAccSale = new JTextField();
		txtAccSale.setText("0");
		txtAccSale.setEditable(false);
		txtAccSale.setBounds(574, 408, 102, 20);
		panel.add(txtAccSale);
		txtAccSale.setColumns(10);
		
		txtAccProfit = new JTextField();
		txtAccProfit.setText("0");
		txtAccProfit.setEditable(false);
		txtAccProfit.setBounds(686, 408, 102, 20);
		panel.add(txtAccProfit);
		txtAccProfit.setColumns(10);
		
		JLabel lblRecharges = new JLabel("Recharges");
		lblRecharges.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRecharges.setBounds(489, 442, 79, 14);
		panel.add(lblRecharges);
		
		txtRecharges = new JTextField();
		txtRecharges.setText("0");
		txtRecharges.setEditable(false);
		txtRecharges.setBounds(574, 439, 102, 20);
		panel.add(txtRecharges);
		txtRecharges.setColumns(10);
		
		txtRechargeProfit = new JTextField();
		txtRechargeProfit.setText("0");
		txtRechargeProfit.setEditable(false);
		txtRechargeProfit.setBounds(686, 439, 102, 20);
		panel.add(txtRechargeProfit);
		txtRechargeProfit.setColumns(10);
		
		JLabel lblTotal_1 = new JLabel("Total");
		lblTotal_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTotal_1.setBounds(489, 484, 79, 20);
		panel.add(lblTotal_1);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(489, 471, 299, 7);
		panel.add(separator_3);
		
		txtTotalSale = new JTextField();
		txtTotalSale.setText("0");
		txtTotalSale.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtTotalSale.setForeground(Color.BLUE);
		txtTotalSale.setEditable(false);
		txtTotalSale.setBounds(574, 486, 102, 20);
		panel.add(txtTotalSale);
		txtTotalSale.setColumns(10);
		
		txtProfit = new JTextField();
		txtProfit.setText("0");
		txtProfit.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtProfit.setForeground(Color.GREEN);
		txtProfit.setEditable(false);
		txtProfit.setBounds(686, 486, 102, 20);
		panel.add(txtProfit);
		txtProfit.setColumns(10);
		
		JLabel lblExpenses = new JLabel("Expenses");
		lblExpenses.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblExpenses.setBounds(489, 529, 79, 24);
		panel.add(lblExpenses);
		
		txtExpense = new JTextField();
		txtExpense.setText("0");
		txtExpense.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtExpense.setForeground(Color.RED);
		txtExpense.setEditable(false);
		txtExpense.setBounds(574, 533, 216, 20);
		panel.add(txtExpense);
		txtExpense.setColumns(10);
		
		JLabel lblEffectiveTodaysProfit = new JLabel("Effective Today's Profit");
		lblEffectiveTodaysProfit.setBounds(489, 582, 172, 14);
		panel.add(lblEffectiveTodaysProfit);
		
		txtEffProfit = new JTextField();
		txtEffProfit.setEditable(false);
		txtEffProfit.setBounds(686, 579, 102, 20);
		panel.add(txtEffProfit);
		txtEffProfit.setColumns(10);
		
		JLabel lblEffectiveCashAt = new JLabel("Effective Cash At Desk");
		lblEffectiveCashAt.setBounds(489, 619, 172, 14);
		panel.add(lblEffectiveCashAt);
		
		txtEffCash = new JTextField();
		txtEffCash.setEditable(false);
		txtEffCash.setBounds(686, 616, 102, 20);
		panel.add(txtEffCash);
		txtEffCash.setColumns(10);
		
		List<FundStatus> fundStatus = fs.getCurrentTxnDetails();
		FundStatus currFundStatus = fundStatus.get(0);
		System.out.println(currFundStatus.toString());
		float investment =currFundStatus.getInvestment();
		float cash =currFundStatus.getCash() ;
		float stock=currFundStatus.getStockValue();
		float unifunds=currFundStatus.getUnivercellfunds();
		float bank =currFundStatus.getDeposits();
		float assets =currFundStatus.getAssets();
		float returns = currFundStatus.getReturns();
		

		float todaysProfit = currFundStatus.getAccProfit()+currFundStatus.getProfit();
		float todayRechargProfit = (float) ((currFundStatus.getRecharges()*2.8)/100);
	    float todaysSale = currFundStatus.getAccSale()+currFundStatus.getPhoneSale()+currFundStatus.getRecharges();
	    float todaysExpense = currFundStatus.getExpense();
	    float effProfit = (todaysProfit+todayRechargProfit)-todaysExpense;
	    float appxtodaysCash = currFundStatus.getRecharges()+todayRechargProfit+currFundStatus.getAccSale()+currFundStatus.getPhoneSale()+currFundStatus.getAccProfit()+currFundStatus.getProfit()+currFundStatus.getAccProfit()-todaysExpense;	    
	    float accstock = currFundStatus.getAccStockValue();
		
		
	
	    float currValue=cash+stock+accstock+unifunds+bank+assets+returns;
		
		
		txtInvestment.setText(Float.toString(investment));
		txtCash.setText(Float.toString(cash));
		txtStock.setText(Float.toString(stock));
		txtUniFunds.setText(Float.toString(unifunds));
		txtBank.setText(Float.toString(bank));
		txtAssets.setText(Float.toString(assets));
		txtReturns.setText(Float.toString(returns));
		txtPhSale.setText(Float.toString(currFundStatus.getPhoneSale()));
		txtAccSale.setText(Float.toString(currFundStatus.getAccSale()));
		txtPhProfit.setText(Float.toString(currFundStatus.getProfit()));
		txtAccProfit.setText(Float.toString(currFundStatus.getAccProfit()));
		txtRecharges.setText(Float.toString(currFundStatus.getRecharges()));
		txtRechargeProfit.setText(Float.toString(todayRechargProfit));
		txtTotalSale.setText(Float.toString(todaysSale));
		txtEffCash.setText(Float.toString(appxtodaysCash));
		txtEffProfit.setText(Float.toString(effProfit));
		txtExpense.setText(Float.toString(todaysExpense));
		txtProfit.setText(Float.toString(todaysProfit+todayRechargProfit));
		
		txtAccStockValue.setText(Float.toString(accstock));
		if(currFundStatus.getToday()!=null){
		lblUpdatedDate.setText(currFundStatus.getToday().toString());
		}
		
		txtTotal.setText(Float.toString(currValue));
		
		float growth = currValue-investment;
		
		txtGrowth.setText(Float.toString(growth));
		
		float ROI = (growth/investment)*100;
		
		txtROI.setText(Float.toString(ROI));
		
		JLabel lblLastDays = new JLabel("Last 30 Days Expense :");
		lblLastDays.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLastDays.setBounds(85, 621, 161, 24);
		panel.add(lblLastDays);
		
		JLabel lblLastDays_1 = new JLabel("Last 30 Days Profit :");
		lblLastDays_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLastDays_1.setBounds(85, 656, 161, 24);
		panel.add(lblLastDays_1);
		float expense30 = txs.get30DaysExpense();
		txt30Expense = new JTextField();
		txt30Expense.setForeground(Color.RED);
		txt30Expense.setEditable(false);
		txt30Expense.setBounds(282, 623, 102, 20);
		panel.add(txt30Expense);
		txt30Expense.setText(Float.toString(expense30));
		txt30Expense.setColumns(10);
		
		txt30Profit = new JTextField();
		txt30Profit.setForeground(Color.BLUE);
		txt30Profit.setEditable(false);
		txt30Profit.setBounds(282, 658, 102, 20);
		panel.add(txt30Profit);
		float profit30 = ss.get30DayProfit()+asaless.get30DayProfit();
		txt30Profit.setText(Float.toString(profit30));
		txt30Profit.setColumns(10);
		
		JLabel lblEffectiveLast = new JLabel("Last 30 Days (Profit-Expense) :");
		lblEffectiveLast.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEffectiveLast.setBounds(470, 26, 214, 31);
		panel.add(lblEffectiveLast);
		
		txt30Balance = new JTextField();
		txt30Balance.setText("0.0");
		txt30Balance.setBackground(Color.DARK_GRAY);
		txt30Balance.setFont(new Font("Tahoma", Font.BOLD, 15));
		txt30Balance.setForeground(Color.GREEN);
		txt30Balance.setEditable(false);
		txt30Balance.setBounds(694, 26, 120, 31);
		panel.add(txt30Balance);
		float balance30 = profit30-expense30;
		txt30Balance.setText(Float.toString(balance30));
		txt30Balance.setColumns(10);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(33, 529, 1, 2);
		panel.add(separator_4);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(33, 551, 421, 2);
		panel.add(separator_5);
		
		JLabel lblLastDays_2 = new JLabel("Last 30 Days Purchase :");
		lblLastDays_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLastDays_2.setBounds(85, 562, 161, 24);
		panel.add(lblLastDays_2);
		
		JLabel lblLastDays_3 = new JLabel("Last 30 Days Sales");
		lblLastDays_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLastDays_3.setBounds(85, 590, 161, 24);
		panel.add(lblLastDays_3);
		
		txt30Purchase = new JTextField();
		txt30Purchase.setText("0.0");
		txt30Purchase.setEditable(false);
		txt30Purchase.setBounds(282, 564, 102, 20);
		panel.add(txt30Purchase);
		txt30Purchase.setText(Float.toString(pss.get30DaysPurchase()));
		txt30Purchase.setColumns(10);
		
		txt30Sales = new JTextField();
		txt30Sales.setText("0.0");
		txt30Sales.setEditable(false);
		txt30Sales.setBounds(282, 592, 102, 20);
		panel.add(txt30Sales);
		txt30Sales.setText(Float.toString(ss.get30DaysSales()));
		txt30Sales.setColumns(10);
		
		JLabel lblLastDays_4 = new JLabel("Last 30 Days Summary :");
		lblLastDays_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLastDays_4.setBounds(44, 518, 385, 24);
		panel.add(lblLastDays_4);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    PrinterJob job = PrinterJob.getPrinterJob();
			    
			    // set the Printable to the PrinterJob
			    job.setPrintable(new Printable() {
			      public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) {
			        if (pageIndex == 0) {
			          panel.print(graphics);
			          return Printable.PAGE_EXISTS;
			        }
			        return Printable.NO_SUCH_PAGE;
			      }
			    });
			    
			    // show the dialog
			    if (job.printDialog()) {
			      try {
			        job.print();
			      }
			      catch (PrinterException ex) {
			        // handle exception
			      }
			}
			}
		});
		btnPrint.setBounds(699, 657, 89, 23);
		panel.add(btnPrint);
		
		
		

		
		
	
		
		
		
		
		
		
		
		
		
	}
}

package com.univercellmobiles.app.ui.reports;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSeparator;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.univercellmobiles.app.beans.FundStatus;
import com.univercellmobiles.app.service.FundStatusService;
import com.univercellmobiles.app.util.ConfigBuilder;

import java.awt.Font;
import java.awt.Color;
import java.util.List;
import java.awt.Window.Type;

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
	JLabel lblUpdatedDate;
	ConfigurableApplicationContext context = ConfigBuilder.getAppContext();
	FundStatusService fs = (FundStatusService) context.getBean("fundStatusService");
	private JTextField txtAccStockValue;

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
		setAlwaysOnTop(true);
		setType(Type.POPUP);
		setTitle("Firm Current Value");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 770, 596);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblInvestmentAmount = new JLabel("Investment Amount");
		lblInvestmentAmount.setBounds(85, 33, 147, 24);
		panel.add(lblInvestmentAmount);
		
		txtInvestment = new JTextField();
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
		separator.setBounds(33, 67, 582, 7);
		panel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(33, 351, 594, 2);
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
		
		List<FundStatus> fundStatus = fs.getCurrentTxnDetails();
		FundStatus currFundStatus = fundStatus.get(0);
		System.out.println(currFundStatus.toString());
		float investment =currFundStatus.getInvestment();
		float cash =currFundStatus.getCash() ;
		float stock=currFundStatus.getStockValue();
		float unifunds=currFundStatus.getUnivercellfunds();
		float bank =currFundStatus.getDeposits();
		float assets =currFundStatus.getAssets();
		
		
		txtInvestment.setText(Float.toString(investment));
		txtCash.setText(Float.toString(cash));
		txtStock.setText(Float.toString(stock));
		txtUniFunds.setText(Float.toString(unifunds));
		txtBank.setText(Float.toString(bank));
		txtAssets.setText(Float.toString(assets));
		
		float accstock = currFundStatus.getAccStockValue();
		txtAccStockValue.setText(Float.toString(accstock));
		if(currFundStatus.getToday()!=null){
		lblUpdatedDate.setText(currFundStatus.getToday().toString());
		}
		
		
		float currValue=cash+stock+accstock+unifunds+bank+assets;
		txtTotal.setText(Float.toString(currValue));
		
		float growth = currValue-investment;
		
		txtGrowth.setText(Float.toString(growth));
		
		float ROI = (growth/investment)*100;
		
		txtROI.setText(Float.toString(ROI));
		
		JLabel lblDetailsUpdatedAs = new JLabel("Details Updated as on ");
		lblDetailsUpdatedAs.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDetailsUpdatedAs.setBounds(489, 75, 126, 20);
		panel.add(lblDetailsUpdatedAs);
		
		JLabel lblAccStockValue = new JLabel("Acc. Stock Value");
		lblAccStockValue.setBounds(85, 143, 120, 14);
		panel.add(lblAccStockValue);
		
		
	
		
		
		
		
		
		
		
		
		
	}
}

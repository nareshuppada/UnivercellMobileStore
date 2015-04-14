package com.univercellmobiles.app.ui.sales;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JPanel;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.univercellmobiles.app.beans.Sales;
import com.univercellmobiles.app.service.AccessoryStockService;
import com.univercellmobiles.app.service.PhoneStockService;
import com.univercellmobiles.app.service.SalesService;

public class ConfirmSale extends JFrame implements ActionListener {
	private JTextField txtBillingAmount;
	private JTextField txtCash;
	private JTextField txtCardNumber;
	private JTextField txtCardName;
	JRadioButton rdbtnCash;
	JRadioButton rdbtnCard;
	JLabel lblCardNumber;
	JLabel lblCashRecieved;
	JLabel lblReturnAmount ;
	JLabel lblNameOnCard;
	JButton btnConfirmPayment ;
	JLabel lblBalance;
	float billAmount = 0;
	List<Sales> sales = null;
	 ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	 SalesService ss = (SalesService) context.getBean("salesService");
	 PhoneStockService pss = (PhoneStockService) context.getBean("phoneStockService");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					/*ConfirmSale frame = new ConfirmSale();
					frame.setVisible(true);*/
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ConfirmSale(final List<Sales> sales) {
		this.sales =sales;
		setBounds(100, 100, 390, 300);
		getContentPane().setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 354, 239);
		getContentPane().add(panel);
		panel.setLayout(null);
		final SalesService ss =  (SalesService) context.getBean("salesService");
		
		
		JLabel lblModeOfPayment = new JLabel("Mode Of Payment");
		lblModeOfPayment.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblModeOfPayment.setBounds(21, 59, 100, 14);
		panel.add(lblModeOfPayment);
		
		rdbtnCash = new JRadioButton("Cash");
		rdbtnCash.setSelected(true);
		rdbtnCash.setBounds(138, 55, 85, 23);
		panel.add(rdbtnCash);
		rdbtnCash.addActionListener(this);
		
		rdbtnCard = new JRadioButton("Card");
		rdbtnCard.setBounds(225, 55, 85, 23);
		panel.add(rdbtnCard);
		rdbtnCard.addActionListener(this);
		 ButtonGroup paymentGroup = new ButtonGroup();
		 paymentGroup.add(rdbtnCard);
		 paymentGroup.add(rdbtnCash);
		 
		 for(Sales sale: sales){
			 billAmount+=sale.getSalePrice();
		 }
		
		txtBillingAmount = new JTextField();
		txtBillingAmount.setForeground(Color.GREEN);
		txtBillingAmount.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtBillingAmount.setEditable(false);
		txtBillingAmount.setBounds(138, 9, 116, 22);
		txtBillingAmount.setText(Float.toString(billAmount));
		panel.add(txtBillingAmount);
		txtBillingAmount.setColumns(10);
		
		JLabel lblBillingAmount = new JLabel("Billing Amount");
		lblBillingAmount.setBounds(41, 14, 66, 14);
		panel.add(lblBillingAmount);
		
		lblCashRecieved = new JLabel("Cash Recieved");
		lblCashRecieved.setBounds(42, 100, 85, 25);
		panel.add(lblCashRecieved);
		
		txtCash = new JTextField();
		txtCash.setBounds(138, 102, 85, 20);
		panel.add(txtCash);
		txtCash.setColumns(10);
		
		lblReturnAmount = new JLabel("Return Amount");
		lblReturnAmount.setBounds(41, 164, 73, 14);
		panel.add(lblReturnAmount);
		
		lblBalance = new JLabel("Rs. 0.0");
		lblBalance.setForeground(Color.RED);
		lblBalance.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblBalance.setBounds(138, 159, 126, 23);
		panel.add(lblBalance);
		
		txtCardNumber = new JTextField();
		txtCardNumber.setVisible(false);
		txtCardNumber.setBounds(138, 119, 176, 20);
		panel.add(txtCardNumber);
		txtCardNumber.setColumns(10);
		
		lblCardNumber = new JLabel("Card Number");
		lblCardNumber.setVisible(false);
		lblCardNumber.setBounds(41, 118, 80, 23);
		panel.add(lblCardNumber);
		
		lblNameOnCard = new JLabel("Name on Card");
		lblNameOnCard.setVisible(false);
		lblNameOnCard.setBounds(41, 84, 90, 20);
		panel.add(lblNameOnCard);
		
		txtCardName = new JTextField();
		txtCardName.setVisible(false);
		txtCardName.setBounds(138, 84, 176, 20);
		panel.add(txtCardName);
		txtCardName.setColumns(10);
		
		btnConfirmPayment = new JButton("Confirm Payment");
		btnConfirmPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(sales!=null && sales.size()>0){
					btnConfirmPayment.setEnabled(false);
				for(Sales sale : sales){
					ss.add(sale);
					pss.sellStock(sale.getStockId());
				}
				closePayment();
				
				
				}
			}
		});
		btnConfirmPayment.setBounds(226, 205, 115, 23);
		panel.add(btnConfirmPayment);
		
		
	}
	
	public void closePayment(){
		this.dispose();
	}

	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == rdbtnCard || e.getSource() == rdbtnCash){
			if(rdbtnCash.isSelected()){
				txtCardName.setVisible(false);
				lblNameOnCard.setVisible(false);
				txtCardNumber.setVisible(false);
				lblCardNumber.setVisible(false);
				
				lblCashRecieved.setVisible(true);
				txtCash.setVisible(true);
				lblReturnAmount.setVisible(true);
				lblBalance.setVisible(true);
				
			}
			else{
				txtCardName.setVisible(true);
				lblNameOnCard.setVisible(true);
				txtCardNumber.setVisible(true);
				lblCardNumber.setVisible(true);
				
				lblCashRecieved.setVisible(false);
				txtCash.setVisible(false);
				lblReturnAmount.setVisible(false);
				lblBalance.setVisible(false);
				
				
				
			}
		}
		// TODO Auto-generated method stub
		
	}

}

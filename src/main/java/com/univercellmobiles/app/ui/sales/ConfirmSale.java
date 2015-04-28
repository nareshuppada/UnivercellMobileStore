package com.univercellmobiles.app.ui.sales;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.univercellmobiles.app.beans.AccessorySales;
import com.univercellmobiles.app.beans.Sales;
import com.univercellmobiles.app.service.AccessorySalesService;
import com.univercellmobiles.app.service.AccessoryStockService;
import com.univercellmobiles.app.service.PhoneStockService;
import com.univercellmobiles.app.service.SalesService;

import java.awt.Window.Type;

public class ConfirmSale extends JFrame implements ActionListener {
	private JTextField txtBillingAmount;
	private JFormattedTextField txtCash;
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
	SalesBilling salesRef;
	AccessoryBilling accBillingRef;
	List<Sales> phoneSales = null;
	 ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	 SalesService ss = (SalesService) context.getBean("salesService");
	 PhoneStockService pss = (PhoneStockService) context.getBean("phoneStockService");
	 AccessorySalesService ass = (AccessorySalesService) context.getBean("accessorySalesService");
	 AccessoryStockService acc_stock_service = (AccessoryStockService) context.getBean("accessoryStockService");
	private List<AccessorySales> accSales = null;
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
	 * @param frame 
	 */
	public ConfirmSale(final List<?> sales,  final Object frame) {
		setType(Type.POPUP);
		if(frame instanceof SalesBilling){
			salesRef = (SalesBilling)frame;
			this.phoneSales =(List<Sales>) sales;
		}
		else{
			accBillingRef =(AccessoryBilling)frame;
			this.accSales = (List<AccessorySales>) sales;
		}
		
		//setAlwaysOnTop(true);
		setTitle("Confirm Payment");
		
		setBounds(100, 100, 390, 300);
		getContentPane().setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 354, 239);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		
		
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
		 
		 if(frame instanceof SalesBilling){
			 for(Sales sale: phoneSales){
				 billAmount+=sale.getSalePrice();
			 }
			}
			else{
				 for(AccessorySales sale: accSales){
					 billAmount+=sale.getSalePrice();
				 }
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
		NumberFormat moneyFormat = NumberFormat.getInstance();
		txtCash = new JFormattedTextField(moneyFormat);
		txtCash.setBounds(138, 102, 85, 20);
		panel.add(txtCash);
		txtCash.setColumns(10);
		txtCash.setText("0");
		 DocumentListener documentListener = new DocumentListener() {
		      public void changedUpdate(DocumentEvent documentEvent) {
		      }
		      public void insertUpdate(DocumentEvent documentEvent) {
		    	  confirmBilling();
		      }
		      public void removeUpdate(DocumentEvent documentEvent) {
		    	 // calculateMargin();
		      }
		      
		      public void confirmBilling(){
		    	  if(txtCash.getText()!=null&&!txtCash.getText().equals("")){
						String cash = txtCash.getText().replace(",", "");
					Float returnAmt = Float.parseFloat(cash)-billAmount;
					lblBalance.setText(returnAmt.toString());
		    	  
		      }
		      }
		      
		     
		    };
		 
		txtCash.getDocument().addDocumentListener(documentListener);
		
		
		lblReturnAmount = new JLabel("Return Amount");
		lblReturnAmount.setBounds(41, 164, 86, 14);
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
				String cash = txtCash.getText().replace(",", "");
				if(Float.parseFloat(cash)<billAmount && rdbtnCash.isSelected()){
					JOptionPane.showMessageDialog(null, "Cash Paid can not be less then billing Amount.", 
                             "Payment Invalid",
                             JOptionPane.WARNING_MESSAGE);
					return;
					
				}
				if(sales!=null && sales.size()>0){
					btnConfirmPayment.setEnabled(false);
					
					if(frame instanceof SalesBilling){
						 for(Sales sale: phoneSales){
							 ss.add(sale);
								pss.sellStock(sale.getStockId());
						 }
						}
						else{
							 for(AccessorySales sale: accSales){
								 ass.add(sale);
								  acc_stock_service.sellStock(sale.getStockId());
							 }
						}
					
					
				
				
				closePayment();
				
				
				}
			}
		});
		btnConfirmPayment.setBounds(208, 205, 133, 23);
		panel.add(btnConfirmPayment);
		
		
	}
	


	public void closePayment(){
		this.dispose();
		if(salesRef!=null){
			salesRef.dispose();
			
		
		}
		if(accBillingRef!=null){
			accBillingRef.dispose();
		}
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

package com.univercellmobiles.app.ui.main;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.Timer;

import org.jfree.ui.RefineryUtilities;

import com.univercellmobiles.app.ui.funds.BalanceSheet;
import com.univercellmobiles.app.ui.funds.ExpenseManagement;
import com.univercellmobiles.app.ui.funds.FixedAssetManagment;
import com.univercellmobiles.app.ui.funds.InvestmentManagement;
import com.univercellmobiles.app.ui.inventory.AccInventoryManagement;
import com.univercellmobiles.app.ui.inventory.AccessorySearch;
import com.univercellmobiles.app.ui.inventory.AddMobileAccessory;
import com.univercellmobiles.app.ui.inventory.AddStock;
import com.univercellmobiles.app.ui.inventory.BrandManager;
import com.univercellmobiles.app.ui.inventory.InventoryManagement;
import com.univercellmobiles.app.ui.inventory.ModelManager;
import com.univercellmobiles.app.ui.inventory.StockSearch;
import com.univercellmobiles.app.ui.reports.AccPurchaseHistory;
import com.univercellmobiles.app.ui.reports.AccSalesHistory;
import com.univercellmobiles.app.ui.reports.FirmValue;
import com.univercellmobiles.app.ui.reports.FirmValueChart;
import com.univercellmobiles.app.ui.reports.PurchaseHistory;
import com.univercellmobiles.app.ui.reports.SalesHistory;
import com.univercellmobiles.app.ui.sales.AccessoryBilling;
import com.univercellmobiles.app.ui.sales.SalesBilling;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdminMainFrontEnd {

	private JFrame frmUnivercellMobilesStore;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMainFrontEnd window = new AdminMainFrontEnd();
					window.frmUnivercellMobilesStore.setExtendedState(JFrame.MAXIMIZED_BOTH);
					window.frmUnivercellMobilesStore.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdminMainFrontEnd() {
		initialize();
		this.frmUnivercellMobilesStore.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.frmUnivercellMobilesStore.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmUnivercellMobilesStore = new JFrame();
		frmUnivercellMobilesStore.setTitle("Univercell Mobiles Store Management");
		frmUnivercellMobilesStore.setBounds(100, 100, 717, 462);
		frmUnivercellMobilesStore.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUnivercellMobilesStore.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frmUnivercellMobilesStore.getContentPane().add(panel, BorderLayout.WEST);
		
		JButton btnAddPhoneStock = new JButton("Add Phone Stock");
		btnAddPhoneStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddStock frame = new AddStock();
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				frame.setVisible(true);
			}
		});
		
		JButton btnAddAccessories = new JButton("Add Accessories");
		btnAddAccessories.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddMobileAccessory frame = new AddMobileAccessory();
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				RefineryUtilities.centerFrameOnScreen(frame);
				frame.setVisible(true);
			}
		});
		btnAddAccessories.setRolloverEnabled(false);
		
		JLabel lblInventory = new JLabel("Inventory Management");
		lblInventory.setBackground(SystemColor.textHighlight);
		lblInventory.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblInventory.setForeground(Color.BLUE);
		
		JButton btnManageBrands = new JButton("Manage Brands");
		btnManageBrands.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BrandManager frame = new BrandManager();
				RefineryUtilities.centerFrameOnScreen(frame);
				frame.setVisible(true);
			}
		});
		
		JButton btnManageModels = new JButton("Manage Models");
		btnManageModels.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModelManager frame = new ModelManager();
				RefineryUtilities.centerFrameOnScreen(frame);
				frame.setVisible(true);
			}
		});
		
		JLabel lblSales = new JLabel("Sales Billing Management");
		lblSales.setForeground(Color.BLUE);
		lblSales.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSales.setBackground(SystemColor.textHighlight);
		
		JButton btnPhoneSales = new JButton("Phone Sales");
		btnPhoneSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SalesBilling frame = new SalesBilling();
				RefineryUtilities.centerFrameOnScreen(frame);
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				frame.setVisible(true);
			}
		});
		
		JButton btnAccessoriesSales = new JButton("Accessories Sales");
		btnAccessoriesSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccessoryBilling frame = new AccessoryBilling();
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				RefineryUtilities.centerFrameOnScreen(frame);
				frame.setVisible(true);
			}
		});
		
		JButton btnSearchPhones = new JButton("Search Phones");
		btnSearchPhones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StockSearch frame = new StockSearch();
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				RefineryUtilities.centerFrameOnScreen(frame);
				frame.setVisible(true);
			}
		});
		
		JButton btnSearchAccessories = new JButton("Search Accessories");
		btnSearchAccessories.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccessorySearch frame = new AccessorySearch();
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				RefineryUtilities.centerFrameOnScreen(frame);
				frame.setVisible(true);
			}
		});
		
		JButton btnInventoryManagement = new JButton("Inventory Management");
		btnInventoryManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InventoryManagement frame = new InventoryManagement();
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				RefineryUtilities.centerFrameOnScreen(frame);
				frame.setVisible(true);
			}
		});
		
		JButton btnAccInventoryManagement = new JButton("Acc. Inventory Management");
		btnAccInventoryManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccInventoryManagement frame = new AccInventoryManagement();
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				RefineryUtilities.centerFrameOnScreen(frame);
				frame.setVisible(true);
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(53)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnPhoneSales, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnAccessoriesSales, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblSales, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(51)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnAddPhoneStock, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnAddAccessories, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnManageBrands, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnManageModels, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnSearchPhones, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnSearchAccessories, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnAccInventoryManagement, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnInventoryManagement, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblInventory, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(8)
					.addComponent(lblInventory, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAddPhoneStock)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAddAccessories)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnManageBrands)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnManageModels)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSearchPhones)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSearchAccessories)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnInventoryManagement)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAccInventoryManagement)
					.addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
					.addComponent(lblSales, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnPhoneSales)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAccessoriesSales)
					.addGap(29))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		frmUnivercellMobilesStore.getContentPane().add(panel_1, BorderLayout.NORTH);
		
		JPanel panel_2 = new JPanel();
		frmUnivercellMobilesStore.getContentPane().add(panel_2, BorderLayout.EAST);
		
		ImagePanel ip = new ImagePanel();
		frmUnivercellMobilesStore.getContentPane().add(ip,BorderLayout.CENTER);
		
		JLabel lblFundsManagement = new JLabel("Funds Management");
		lblFundsManagement.setForeground(Color.BLUE);
		lblFundsManagement.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFundsManagement.setBackground(SystemColor.textHighlight);
		
		JButton btnInvestment = new JButton("Manage Investment");
		btnInvestment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InvestmentManagement frame = new InvestmentManagement();
				RefineryUtilities.centerFrameOnScreen(frame);
				frame.setVisible(true);
			}
		});
		btnInvestment.setAutoscrolls(true);
		
		JButton btnExpense = new JButton("Manage Expenses");
		btnExpense.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExpenseManagement frame = new ExpenseManagement();
				frame.setVisible(true);
			}
		});
		btnExpense.setAutoscrolls(true);
		
		JButton btnAssets = new JButton("Manage Assets");
		btnAssets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FixedAssetManagment frame = new FixedAssetManagment();
				RefineryUtilities.centerFrameOnScreen(frame);
				frame.setVisible(true);
			}
		});
		
		JButton btnManageEodCash = new JButton("Manage EOD Cash");
		btnManageEodCash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BalanceSheet frame = new BalanceSheet();
				RefineryUtilities.centerFrameOnScreen(frame);

				frame.setVisible(true);
				
			}
		});
		
		JLabel lblReports = new JLabel("Reports");
		lblReports.setForeground(Color.BLUE);
		lblReports.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblReports.setBackground(SystemColor.textHighlight);
		
		JButton btnPurchaseHistory = new JButton("Purchase History");
		btnPurchaseHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PurchaseHistory frame = new PurchaseHistory();
				RefineryUtilities.centerFrameOnScreen(frame);
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				frame.setVisible(true);
			}
		});
		
		JButton btnSalesHistory = new JButton("Sales History");
		btnSalesHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SalesHistory frame = new SalesHistory();
				RefineryUtilities.centerFrameOnScreen(frame);
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				frame.setVisible(true);
			}
		});
		
		JButton btnFirmValue = new JButton("Firm Value");
		btnFirmValue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FirmValue frame = new FirmValue();
				//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				RefineryUtilities.centerFrameOnScreen(frame);
				frame.setVisible(true);
				frame.setAlwaysOnTop(true);
			}
		});
		
		final JLabel lblTime = new JLabel("Time :");
		
		final JLabel lblDate = new JLabel("Date :");
		
		  final DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		  final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	        ActionListener timerListener = new ActionListener()
	        {
	            public void actionPerformed(ActionEvent e)
	            {
	                Date date = new Date();
	                String time = timeFormat.format(date);
	                String d = dateFormat.format(date);
	                lblTime.setText("Time : " + time);
	                lblDate.setText("Date : " + d );
	            }
	        };
	        Timer timer = new Timer(1000, timerListener);
	        // to make sure it doesn't wait one second at the start
	        timer.setInitialDelay(0);
	        timer.start();
		
		JButton btnFirmValueGraph = new JButton("Firm Value Graph");
		btnFirmValueGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 final FirmValueChart demo = new FirmValueChart("Bar Chart Demo");
			        demo.pack();
			        RefineryUtilities.centerFrameOnScreen(demo);
			        demo.setVisible(true);
			}
		});
		
		JButton btnAccPurchaseHistory = new JButton("Acc. Purchase History");
		btnAccPurchaseHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccPurchaseHistory frame = new AccPurchaseHistory();
				RefineryUtilities.centerFrameOnScreen(frame);
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				frame.setVisible(true);
			}
		});
		
		JButton btnAccSalesHistory = new JButton("Acc. Sales History");
		btnAccSalesHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccSalesHistory frame = new AccSalesHistory();
				RefineryUtilities.centerFrameOnScreen(frame);
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				frame.setVisible(true);
			}
		});
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(33)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnExpense, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnInvestment, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
								.addComponent(btnAssets, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnManageEodCash, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblReports, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnPurchaseHistory, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
								.addComponent(btnSalesHistory, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnFirmValue, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblTime, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblDate, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnFirmValueGraph, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnAccPurchaseHistory, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnAccSalesHistory, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(32)
							.addComponent(lblFundsManagement, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(31, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblFundsManagement, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnInvestment)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnExpense)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAssets)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnManageEodCash)
					.addGap(49)
					.addComponent(lblReports, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnPurchaseHistory)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSalesHistory)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAccPurchaseHistory)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAccSalesHistory)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnFirmValue)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnFirmValueGraph)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblTime)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblDate)
					.addGap(23))
		);
		panel_2.setLayout(gl_panel_2);
		
		JPanel panel_3 = new JPanel();
		frmUnivercellMobilesStore.getContentPane().add(panel_3, BorderLayout.SOUTH);
		
		
	}
	
	class ImagePanel extends JPanel{

	    private ImageIcon imageIcon;
	    Image image;

	    public ImagePanel() {
	    	URL url = ImagePanel.class.getResource("/images/univercellstore.png");
	    	imageIcon =new ImageIcon(url);
	    	System.out.println(url.getPath());
	    	image = imageIcon.getImage();
	    }

	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        //g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters          
	        g.drawImage(image, 150, 100, 600, 500, null);
	    }

	}
}



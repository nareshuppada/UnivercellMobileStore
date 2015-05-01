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

public class SalesMainFrontEnd {

	private JFrame frmUnivercellMobilesStore;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalesMainFrontEnd window = new SalesMainFrontEnd();
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
	public SalesMainFrontEnd() {
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
		
		JLabel lblInventory = new JLabel("Inventory Management");
		lblInventory.setBackground(SystemColor.textHighlight);
		lblInventory.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblInventory.setForeground(Color.BLUE);
		
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
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(51)
					.addComponent(lblInventory, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addGap(50)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSearchPhones, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
						.addComponent(btnSearchAccessories, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(26))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(8)
					.addComponent(lblInventory, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSearchPhones)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSearchAccessories)
					.addContainerGap(598, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		frmUnivercellMobilesStore.getContentPane().add(panel_1, BorderLayout.NORTH);
		
		JPanel panel_2 = new JPanel();
		frmUnivercellMobilesStore.getContentPane().add(panel_2, BorderLayout.EAST);
		
		ImagePanel ip = new ImagePanel();
		frmUnivercellMobilesStore.getContentPane().add(ip,BorderLayout.CENTER);
		
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
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(33)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblTime, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblDate, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(178, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
					.addContainerGap(166, Short.MAX_VALUE)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAccessoriesSales, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPhoneSales, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSales))
					.addGap(22))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(13)
					.addComponent(lblSales, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnPhoneSales)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAccessoriesSales)
					.addPreferredGap(ComponentPlacement.RELATED, 536, Short.MAX_VALUE)
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



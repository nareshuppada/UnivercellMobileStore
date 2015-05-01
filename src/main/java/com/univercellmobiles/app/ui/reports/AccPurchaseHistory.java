package com.univercellmobiles.app.ui.reports;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.univercellmobiles.app.beans.AccessoryStock;
import com.univercellmobiles.app.beans.PhoneStock;
import com.univercellmobiles.app.beans.Sales;
import com.univercellmobiles.app.service.AccessoryStockService;
import com.univercellmobiles.app.service.PhoneModelService;
import com.univercellmobiles.app.service.PhoneStockService;
import com.univercellmobiles.app.service.SalesService;
import com.univercellmobiles.app.ui.common.custom.AutocompleteJComboBox;
import com.univercellmobiles.app.ui.common.custom.StringSearchable;
import com.univercellmobiles.app.util.ConfigBuilder;

import javax.swing.JLabel;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Dimension;

import javax.swing.JTextArea;

import java.awt.Window.Type;

public class AccPurchaseHistory extends JFrame {
	private JTextField txtPrice;
	private JTable table;
	  private JTable tableStock;
	  private TableRowSorter<StockTableModel> sorter;
	  JTextArea txtOffer;
	  JTextArea txtDesc;
	private boolean DEBUG = false;
	private Float totalCost = (float) 0.0;
	JDatePickerImpl fromDatePicker,toDatePicker;
	 ConfigurableApplicationContext context = ConfigBuilder.getAppContext();
		
		AccessoryStockService as = (AccessoryStockService) context.getBean("accessoryStockService");
		private JTextField txtModel;
		private JLabel lblFromDate;
		private JLabel lblToDate;
		private JLabel lblTotalPurchaseMade;
		private JTextField txtPurchase;
		private JLabel lblIncentivesForThe;
		private JTextField txtIncentives;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccPurchaseHistory frame = new AccPurchaseHistory();
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
	public AccPurchaseHistory() {
		setType(Type.POPUP);
		setTitle("Purchase History");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 707);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setMaximumSize(new Dimension(50000, 50000));
		getContentPane().add(panel);
		panel.setLayout(null);
		UtilDateModel fromModel = new UtilDateModel();  
		 Calendar cal = Calendar.getInstance();
         cal.setTimeZone(TimeZone.getTimeZone("IST"));
         cal.add(Calendar.MONTH, -1);
         fromModel.setValue(cal.getTime());
		fromModel.setSelected(true);
		UtilDateModel toModel = new UtilDateModel();  
		toModel.setSelected(true);
		JDatePanelImpl fromDatePanel = new JDatePanelImpl(fromModel);  
		JDatePanelImpl toDatePanel = new JDatePanelImpl(toModel);  
	    fromDatePicker = new JDatePickerImpl(fromDatePanel);
	    fromDatePicker.setBounds(135, 32,160,28);
	    panel.add(fromDatePicker);
	    
	    toDatePicker = new JDatePickerImpl(toDatePanel);
	    toDatePicker.setBounds(379, 32,160,28);
	    panel.add(toDatePicker);
	    
	    lblTotalPurchaseMade = new JLabel("Total Purchases");
		lblTotalPurchaseMade.setBounds(647, 301, 122, 22);
		panel.add(lblTotalPurchaseMade);
		
		txtPurchase = new JTextField();
		txtPurchase.setEditable(false);
		txtPurchase.setBounds(773, 302, 187, 20);
		panel.add(txtPurchase);
		txtPurchase.setColumns(10);
		
		lblIncentivesForThe = new JLabel("Incentives");
		lblIncentivesForThe.setBounds(647, 335, 94, 22);
		panel.add(lblIncentivesForThe);
		
		txtIncentives = new JTextField();
		txtIncentives.setForeground(Color.GREEN);
		txtIncentives.setEditable(false);
		txtIncentives.setBounds(773, 336, 187, 20);
		panel.add(txtIncentives);
		txtIncentives.setColumns(10);
		 final StockTableModel  stockModel = new StockTableModel();
	        sorter = new TableRowSorter<StockTableModel>(stockModel);
	        

	        //Create the scroll pane and add the table to it.
	        JScrollPane stockScrollPane = new JScrollPane();
	        stockScrollPane.setBounds(67, 71, 893, 214);
	        //Add the scroll pane to this panel.
	        panel.add(stockScrollPane);
	        tableStock = new JTable(stockModel);
	        stockScrollPane.setViewportView(tableStock);
	        tableStock.setRowSorter(sorter);
	        tableStock.setPreferredScrollableViewportSize(new Dimension(500, 500));
	        tableStock.setFillsViewportHeight(true);
	        
	        	        //For the purposes of this example, better to have a single
	        	        //selection.
	        	        tableStock.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	        	        
	        	        	        //When selection changes, provide user with row numbers for
	        	        	        //both view and model.
	        	        	        tableStock.getSelectionModel().addListSelectionListener(
	        	        	                new ListSelectionListener() {
	        	        	                    public void valueChanged(ListSelectionEvent event) {
	        	        	                        int viewRow = tableStock.getSelectedRow();
	        	        	                        if (viewRow < 0) {
	        	        	                            //Selection got filtered away.
	        	        	                            //statusText.setText("");
	        	        	                        } else {
	        	        	                            int modelRow = 
	        	        	                            		tableStock.convertRowIndexToModel(viewRow);
	        	        	                            AccessoryStock ps = stockModel.getRow(modelRow);
	        	        	                            txtPrice.setText(ps.getSp().toString());
	        	        	                            txtDesc.setText(ps.getDesription());
	        	        	                            txtModel.setText(ps.getAccModel());
	        	        	                           /* statusText.setText(
	        	        	                                String.format("Selected Row in view: %d. " +
	        	        	                                    "Selected Row in model: %d.", 
	        	        	                                    viewRow, modelRow));*/
	        	        	                        }
	        	        	                    }
	        	        	                }
	        	        	        );

		
		/*txtModel = new JTextField();
		txtModel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				
				
			}
		});
		txtModel.setBounds(279, 38, 415, 22);
		panel.add(txtModel);
		txtModel.setColumns(10);*/
	        
	       
		
		JLabel lblPrice = new JLabel("Selling Price");
		lblPrice.setBounds(67, 451, 128, 22);
		panel.add(lblPrice);
		
		txtPrice = new JTextField();
		txtPrice.setEditable(false);
		txtPrice.setBounds(265, 452, 187, 20);
		panel.add(txtPrice);
		txtPrice.setColumns(10);
		
		JLabel lblOffer = new JLabel("Offer Details");
		lblOffer.setBounds(67, 484, 153, 22);
		panel.add(lblOffer);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(69, 529, 153, 22);
		panel.add(lblDescription);
		
		txtOffer = new JTextArea();
		txtOffer.setEditable(false);
		txtOffer.setBounds(265, 483, 427, 35);
		panel.add(txtOffer);
		
		txtDesc = new JTextArea();
		txtDesc.setEditable(false);
		txtDesc.setBounds(265, 528, 427, 35);
		panel.add(txtDesc);
		
		JLabel lblModel_1 = new JLabel("Model");
		lblModel_1.setBounds(67, 418, 128, 22);
		panel.add(lblModel_1);
		
		txtModel = new JTextField();
		txtModel.setEditable(false);
		txtModel.setBounds(265, 419, 427, 20);
		panel.add(txtModel);
		txtModel.setColumns(10);
		
		lblFromDate = new JLabel("From Date");
		lblFromDate.setBounds(67, 32, 70, 28);
		panel.add(lblFromDate);
		
		lblToDate = new JLabel("To Date");
		lblToDate.setBounds(323, 32, 64, 28);
		panel.add(lblToDate);
		
		JButton btnFetch = new JButton("Fetch");
		btnFetch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stockModel.reFetchGrid();
				
			}
		});
		btnFetch.setBounds(561, 32, 89, 28);
		panel.add(btnFetch);
		
		
		
		
	}
	
	

	
    class  StockTableModel extends AbstractTableModel {
        private String[] columnNames = {"Stock Id",
                                        "Ph Model",
                                        "Acc Model",
                                        "Selling Price","DP","Acc Type","Margin","Purchase Date","Desc"
                                        };
        

		
        private List<AccessoryStock> data = new ArrayList<AccessoryStock>();
        StockTableModel(){
        	Date fromDate = (Date) fromDatePicker.getModel().getValue();
        	Date toDate = (Date) toDatePicker.getModel().getValue();
        	
        	data = as.getPurchaseByRange(fromDate,toDate);
        	setIncentives();
        }
        public int getColumnCount() {
            return columnNames.length;
        }
        
        public void reFetchGrid(){
        	Date fromDate = (Date) fromDatePicker.getModel().getValue();
        	Date toDate = (Date) toDatePicker.getModel().getValue();
        	
        	data = as.getPurchaseByRange(fromDate,toDate);
        	this.fireTableDataChanged();
        	setIncentives();
        	
        }
        
        public void setIncentives(){
        	float purchase = 0;
        	float incentives =0;
        	for(AccessoryStock pstock : data){
        		if(pstock.getDp()!=null){
        		purchase+=pstock.getDp();
        		}
        		if(pstock.getMarginAmount()!=null){
        		incentives+=pstock.getMarginAmount();
        		}
        		txtPurchase.setText(Float.toString(purchase));
        		txtIncentives.setText(Float.toString(incentives));
        	}
        }

        public int getRowCount() {
            return data.size();
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
        	AccessoryStock ps = data.get(row);
        	/*private String accStockId;
//        	private Timestamp arrivalDate;
        	private String accModel;
        	private String phmodelName;
        	//private Timestamp soldDate;
        	private String desription;
        	private String margin;
        	private Float dp;
        	private Float sp;*/ 
        	 switch (col) {
             case 0:
                    return ps.getAccStockId();
             case 1:
                    return ps.getPhmodelName();
             case 2:
                    return ps.getAccModel();
             case 3:
            	 	return ps.getSp();
             case 4:
            	 	return ps.getDp();
             case 5:
         	 	return ps.getAccType();
            
          case 6:
                 return ps.getMarginAmount();
          case 7:
         	 	return ps.getArrivalDate();
         
          case 8:
      	 	return ps.getDesription();
           
             default:
                    throw new IndexOutOfBoundsException();
             }
        	
        	
        }
        
        public AccessoryStock getRow(int row){
        	return data.get(row);
        }
        
        public boolean removeRow(PhoneStock stock){
        	return data.remove(stock);
        }

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
      /*  public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }*/

        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (col < 2) {
                return false;
            } else {
                return true;
            }
        }

        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
      /*  public void setValueAt(Object value, int row, int col) {
            if (DEBUG) {
                System.out.println("Setting value at " + row + "," + col
                                   + " to " + value
                                   + " (an instance of "
                                   + value.getClass() + ")");
            }
            
            data[row][col] = value;
            fireTableCellUpdated(row, col);

            if (DEBUG) {
                System.out.println("New value of data:");
                printDebugData();
            }
        }*/

      /*  private void printDebugData() {
            int numRows = getRowCount();
            int numCols = getColumnCount();

            for (int i=0; i < numRows; i++) {
                System.out.print("    row " + i + ":");
                for (int j=0; j < numCols; j++) {
                    System.out.print("  " + data[i][j]);
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }*/
    }
}
    



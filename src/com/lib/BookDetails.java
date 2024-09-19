package com.lib;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JScrollPane;

public class BookDetails {

	 JFrame bdetails;
	 JTextField bt1;
	 private JTable table;
	 JComboBox<String> c1 = new JComboBox<String>();
	 JComboBox<String> c2 = new JComboBox<String>();
	 JRadioButton bnsearch = new JRadioButton("Search by Book Name");
	 JRadioButton branchns = new JRadioButton("Select by Branch");
	 private javax.swing.ButtonGroup b1;
	 Connection con;
	 Statement stmt;
	 ResultSet rs;
	 String query;
	 private final JScrollPane scrollPane = new JScrollPane();
	 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookDetails window = new BookDetails();
					window.bdetails.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	 public void connect() throws SQLException
	    {
	        try
	        {  
	            Class.forName("com.mysql.cj.jdbc.Driver");  
	            con=DriverManager.getConnection(  
	            "jdbc:mysql://localhost:3306/library1","root","mysql1212#");  
	            stmt=con.createStatement(); 
	            System.out.println("DB CONNECTED!");
	        }
	        catch(Exception e)
	        { 
	            JOptionPane.showMessageDialog(bdetails,"connection error");
	        }
	    }
	   
	 public void disconnect() {
	    	try
	        {
	           con.close(); 
	        }
	        catch(Exception e)
	        {}
	    }

	public BookDetails() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	@SuppressWarnings("serial")
	private void initialize() {
		b1 = new javax.swing.ButtonGroup();
		bdetails = new JFrame();
		bdetails.setBounds(100, 100, 645, 376);
		bdetails.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bdetails.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Details");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(272, 22, 173, 14);
		bdetails.getContentPane().add(lblNewLabel);
		
		b1.add(bnsearch);
		bnsearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bnsearchActionPerformed(e);
				
			}
		});
		bnsearch.setBounds(53, 49, 136, 23);
		bdetails.getContentPane().add(bnsearch);
		
		b1.add(branchns);
		branchns.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				branchnsActionPerformed(e);
				
			}
		});
		branchns.setBounds(53, 91, 109, 23);
		bdetails.getContentPane().add(branchns);
		
		bt1 = new JTextField();
		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bt1ActionPerformed(e);
			}
		});
		bt1.addKeyListener(new KeyAdapter() {
			 public void keyReleased(java.awt.event.KeyEvent evt) {
	                txt1KeyReleased(evt);
	            }
		});
		bt1.setBounds(221, 49, 212, 23);
		bdetails.getContentPane().add(bt1);
		bt1.setColumns(10);
		
		c1.setModel(new DefaultComboBoxModel<String>(new String[] {"Select Branch", "B.Tech", "CE", "EE"}));
		c1.setBounds(221, 91, 102, 22);
		bdetails.getContentPane().add(c1);
		
		
		c2.setModel(new DefaultComboBoxModel<String>(new String[] {"Select Sem", "1", "2", "3", "4"}));
		c2.setBounds(345, 91, 88, 22);
		bdetails.getContentPane().add(c2);
		c2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c2ActionPerformed(e);
			}
		});
		
		scrollPane.setBounds(23, 137, 583, 160);
		
		bdetails.getContentPane().add(scrollPane);

		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					 "Book ID","Book Name", "Author", "Branch", "Semester", "Available"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				true, true, true, true, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		JButton btnNewButton = new JButton("Cancle");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home h1 = new Home();
				h1.home.setVisible(true);
				bdetails.setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(517, 308, 89, 23);
		bdetails.getContentPane().add(btnNewButton);
		
		
	}
	
	@SuppressWarnings("deprecation")
	private void bnsearchActionPerformed(ActionEvent e1) {
		
		c1.disable();
		c2.disable();
		bt1.enable();
	}
	
	@SuppressWarnings("deprecation")
	private void branchnsActionPerformed(ActionEvent e) {
		bt1.disable();
		c1.enable();
		c2.enable();
	}
	 private void bt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt1ActionPerformed
	        
	    }
	
	private void txt1KeyReleased(KeyEvent k) {
		DefaultTableModel model=(DefaultTableModel)table.getModel();
        model.setRowCount(0);
        String s1 = bt1.getText();
        if(s1.equals("")) {
        	System.out.println("");
        }
        else {
        	try {
        		connect();
        		query="select * from books where bookname LIKE \"%"+bt1.getText()+"%\";";
        		rs=stmt.executeQuery(query);
        		while(rs.next()) {
        			String bid = rs.getString("bookid");
        			String bname = rs.getString("bookname");
        			String bauthor = rs.getString("author");
        			String bbranch = rs.getString("branch");
        			String bsem = rs.getString("semester");
        			String bavail = rs.getString("avilable");
        			Object[] row = {bid,bname,bauthor,bbranch,bsem,bavail};
        			model.addRow(row);
        		}
        		disconnect();
        	}
        	catch(Exception e) {
        		
        	}
        }
	}
	private void c2ActionPerformed(ActionEvent evt) {

		try {
			connect();
			query = "select * from books where branch= '"+c1.getSelectedItem()+"' and semester = '"+c2.getSelectedItem()+"';";
			rs = stmt.executeQuery(query);
			DefaultTableModel model=(DefaultTableModel)table.getModel();
	        model.setRowCount(0);
	 
			while(rs.next()) {
				String bid = rs.getString("bookid");
    			String bname = rs.getString("bookname");
    			String bauthor = rs.getString("author");
    			String bbranch = rs.getString("branch");
    			String bsem = rs.getString("semester");
    			String bavail = rs.getString("avilable");
    			Object[] row = {bid,bname,bauthor,bbranch,bsem,bavail};
    			model.addRow(row);
			}
			disconnect();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
       
	 }
	
	
}

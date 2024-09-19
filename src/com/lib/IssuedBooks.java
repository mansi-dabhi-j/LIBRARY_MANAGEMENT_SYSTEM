package com.lib;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;


public class IssuedBooks {

	 JFrame IssuedBooks;
	 private JTable table;
	 Connection con;
	 Statement stmt;
	 ResultSet rs;
	 String query;
	 JComboBox<String> c1,c2;
	 String sid,sname,bid,bname,issuedate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IssuedBooks window = new IssuedBooks();
					window.IssuedBooks.setVisible(true);
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
	            JOptionPane.showMessageDialog(IssuedBooks,"connection error");
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
	public IssuedBooks() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		IssuedBooks = new JFrame();
		IssuedBooks.setBounds(100, 100, 660, 436);
		IssuedBooks.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		IssuedBooks.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Issued Books Details");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(239, 31, 140, 14);
		IssuedBooks.getContentPane().add(lblNewLabel);
		
		JLabel branch = new JLabel("Branch");
		branch.setBounds(88, 64, 46, 14);
		IssuedBooks.getContentPane().add(branch);
		
		c1 = new JComboBox<String>();
		c1.setModel(new DefaultComboBoxModel(new String[] {"Select Branch", "B.Tech", "CE", "EE"}));
		c1.setBounds(132, 60, 98, 22);
		IssuedBooks.getContentPane().add(c1);
		
		JLabel semester = new JLabel("Semester");
		semester.setBounds(373, 64, 46, 14);
		IssuedBooks.getContentPane().add(semester);
		
		 c2 = new JComboBox<String>();
		c2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c2ActionPerformed(e);
			}
		});
		c2.setModel(new DefaultComboBoxModel(new String[] {"Select Sem", "1", "2", "3", "4"}));
		c2.setBounds(440, 60, 98, 22);
		IssuedBooks.getContentPane().add(c2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 122, 584, 232);
		IssuedBooks.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Student ID", "Sudent Name","Book ID","Book Name","Issue Date" 
			}
		));
		
		JButton btnNewButton = new JButton("Cancle");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home h1 = new Home();
				h1.home.setVisible(true);
				IssuedBooks.setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(532, 363, 89, 23);
		IssuedBooks.getContentPane().add(btnNewButton);
	}
	private void c2ActionPerformed(ActionEvent e) {
		try {
			connect();
			DefaultTableModel model=(DefaultTableModel)table.getModel();
	        model.setRowCount(0);
	        query="select * from issue where branch = '"+c1.getSelectedItem()+"' and sem = '"+c2.getSelectedItem()+"';";
	       rs= stmt.executeQuery(query);
	        while(rs.next()) {
	        	sid = rs.getString("sid");
	        	sname=rs.getString("sname");
	        	bid=rs.getString("bookid");
	        	bname=rs.getString("bookname");
	        	issuedate = rs.getString("date");
	        	Object[] o1 = {sid,sname,bid,bname,issuedate};
	        	model.addRow(o1);
	        }
	        disconnect();
		}
		catch(Exception c) {
			c.printStackTrace();
		}
	}
}

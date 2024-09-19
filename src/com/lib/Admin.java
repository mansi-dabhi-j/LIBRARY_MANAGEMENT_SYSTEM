package com.lib;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Admin {

	 JFrame Admin;
	private JTextField admint1;
	private JPasswordField adminpasst1;
	Connection con;
	Statement stmt;
	ResultSet rs ;
	String query;

	/**
	 * Launch the application.
	 */
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
	            JOptionPane.showMessageDialog(Admin,"connection error");
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
	    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin window = new Admin();
					window.Admin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Admin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Admin = new JFrame();
		Admin.setBounds(100, 100, 450, 300);
		Admin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Admin.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Admin Login");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(163, 27, 83, 14);
		Admin.getContentPane().add(lblNewLabel);
		
		JLabel uname = new JLabel("UserName");
		uname.setBounds(110, 82, 68, 14);
		Admin.getContentPane().add(uname);
		
		JLabel password = new JLabel("PassWord");
		password.setBounds(110, 134, 68, 14);
		Admin.getContentPane().add(password);
		
		admint1 = new JTextField();
		admint1.setBounds(214, 79, 113, 20);
		Admin.getContentPane().add(admint1);
		admint1.setColumns(10);
		
		adminpasst1 = new JPasswordField();
		adminpasst1.setBounds(214, 131, 113, 20);
		Admin.getContentPane().add(adminpasst1);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					try {
						connect();
						String un = admint1.getText();
						String up = adminpasst1.getText();
						query = "select * from admin where uname='"+un+"' and password='"+up+"';";
						stmt = con.createStatement();
						rs=stmt.executeQuery(query);
						
						if(rs.next()) {
							AdminHome ad = new AdminHome();
							ad.AdHome.setVisible(true);
							Admin.setVisible(false);
						}
						else {
							JOptionPane.showMessageDialog(Admin, "wrong username and password..!");
							admint1.setText("");
							adminpasst1.setText("");
						}
						
				     	disconnect();
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				
				}
			
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(124, 194, 89, 23);
		Admin.getContentPane().add(btnNewButton);
		
		JButton btncan = new JButton("Cancle");
		btncan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home h1 = new Home();
				h1.home.setVisible(true);
				Admin.setVisible(false);
			}
		});
		btncan.setBounds(223, 195, 89, 23);
		Admin.getContentPane().add(btncan);
	}
}

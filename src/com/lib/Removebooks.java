package com.lib;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Removebooks {
	

	 JFrame Removeframe;
	private JTextField t1;
	private JTextField t2;
	Connection con;
	Statement stmt;
	ResultSet rs;
	String query;
	String bname;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Removebooks window = new Removebooks();
					window.Removeframe.setVisible(true);
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
	            JOptionPane.showMessageDialog(Removeframe,"connection error");
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
	 
	public Removebooks() {
		initialize();
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Removeframe = new JFrame();
		Removeframe.setBounds(100, 100, 450, 300);
		Removeframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Removeframe.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Remove Books from Library");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(128, 37, 180, 14);
		Removeframe.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Book ID");
		lblNewLabel_1.setBounds(122, 83, 46, 14);
		Removeframe.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Book Name");
		lblNewLabel_1_1.setBounds(122, 127, 69, 14);
		Removeframe.getContentPane().add(lblNewLabel_1_1);
		
		t1 = new JTextField();
		t1.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				t1keyReleased(e);
			}
		});
		t1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t1ActionPerformed(e);
			}
		});
		t1.setBounds(200, 80, 108, 20);
		Removeframe.getContentPane().add(t1);
		t1.setColumns(10);
		
		t2 = new JTextField();
		t2.setColumns(10);
		t2.setBounds(200, 124, 108, 20);
		Removeframe.getContentPane().add(t2);
		
		JButton btnNewButton = new JButton("Remove");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				r1ActionPerformed(e);
			}
		});
		btnNewButton.setBounds(110, 181, 89, 23);
		Removeframe.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancle");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminHome ad = new AdminHome();
				ad.AdHome.setVisible(true);
				Removeframe.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(219, 181, 89, 23);
		Removeframe.getContentPane().add(btnNewButton_1);
	}
	
	private void t1ActionPerformed(ActionEvent a) {
		
	}
	
	private void t1keyReleased(KeyEvent k) {
		try {
			connect();
			query="select * from books where bookid='"+t1.getText()+"';";
			rs=stmt.executeQuery(query);
			if(rs.next()) {
				bname = rs.getString("bookname");
				t2.setText(bname);
			}
			else {
				t2.setText("");
				disconnect();
			}
		}
	
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void r1ActionPerformed(ActionEvent e) {
		try {
			connect();
			query="delete from books where bookid ='"+t1.getText()+"';";
			stmt.executeUpdate(query);
			
			if(t1.getText().equals("")) {
				JOptionPane.showInternalMessageDialog(Removeframe, "No Record Found..!!");
			}
			else {
			disconnect();
			JOptionPane.showMessageDialog(Removeframe, "Deleted Successfully..!!");
			t1.setText("");
			t2.setText("");
			}
		}
		catch(Exception a) {
			a.printStackTrace();
		}
	}
}

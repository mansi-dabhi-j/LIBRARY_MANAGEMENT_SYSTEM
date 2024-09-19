package com.lib;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
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
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReturnBook {

	 JFrame ReturnBook;
	private JTextField t1id;
	private JTextField t2name;
	private JTextField t3bid;
	private JTextField t4bname;
	Connection con;
	Statement stmt;
	ResultSet rs;
	String query;
	String sname,bid,bname,isdate;
	private JTextField t5isd;
	private JTextField t6rd;
	 Date date=new Date();
     SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReturnBook window = new ReturnBook();
					window.ReturnBook.setVisible(true);
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
	            JOptionPane.showMessageDialog(ReturnBook,"connection error");
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
	public ReturnBook() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ReturnBook = new JFrame();
		ReturnBook.setBounds(100, 100, 450, 421);
		ReturnBook.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ReturnBook.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Return Book");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(160, 23, 99, 14);
		ReturnBook.getContentPane().add(lblNewLabel);
		
		JLabel sid = new JLabel("StudentID");
		sid.setBounds(104, 65, 59, 14);
		ReturnBook.getContentPane().add(sid);
		
		JLabel sname = new JLabel("Student Name");
		sname.setBounds(104, 111, 68, 14);
		ReturnBook.getContentPane().add(sname);
		
		JLabel bid = new JLabel("BookID");
		bid.setBounds(104, 153, 46, 14);
		ReturnBook.getContentPane().add(bid);
		
		JLabel bname = new JLabel("Book Name");
		bname.setBounds(104, 194, 59, 14);
		ReturnBook.getContentPane().add(bname);
		
		JLabel isd = new JLabel("Issued on");
		isd.setBounds(104, 237, 59, 14);
		ReturnBook.getContentPane().add(isd);
		
		JLabel rdate = new JLabel("Return Date");
		rdate.setBounds(104, 279, 59, 14);
		ReturnBook.getContentPane().add(rdate);
		
		t1id = new JTextField();
		t1id.addKeyListener(new KeyAdapter(){
			 public void keyReleased(java.awt.event.KeyEvent evt) {
	               t1idkeyReleased(evt);
	            }
		});
		t1id.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t1ActionPerformed(e);
			}
		});
		t1id.setBounds(214, 62, 122, 20);
		ReturnBook.getContentPane().add(t1id);
		t1id.setColumns(10);
		
		t2name = new JTextField();
		t2name.setColumns(10);
		t2name.setBounds(214, 108, 122, 20);
		ReturnBook.getContentPane().add(t2name);
		
		t3bid = new JTextField();
		t3bid.setColumns(10);
		t3bid.setBounds(214, 150, 122, 20);
		ReturnBook.getContentPane().add(t3bid);
		
		t4bname = new JTextField();
		t4bname.setColumns(10);
		t4bname.setBounds(214, 191, 122, 20);
		ReturnBook.getContentPane().add(t4bname);
		
		JButton btnreturn = new JButton("Return");
		btnreturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdActionPerformed(e);
			}
		});
		btnreturn.setBounds(125, 330, 89, 23);
		ReturnBook.getContentPane().add(btnreturn);
		
		JButton cancle = new JButton("Cancle");
		cancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home h1 = new Home();
				h1.home.setVisible(true);
				ReturnBook.setVisible(false);
			}
		});
		cancle.setBounds(225, 330, 89, 23);
		ReturnBook.getContentPane().add(cancle);
		
		t5isd = new JTextField();
		t5isd.setColumns(10);
		t5isd.setBounds(214, 234, 122, 20);
		ReturnBook.getContentPane().add(t5isd);
		
		t6rd = new JTextField();
		t6rd.setColumns(10);
		t6rd.setBounds(214, 276, 122, 20);
		ReturnBook.getContentPane().add(t6rd);
	}
	private void t1ActionPerformed(ActionEvent e) {
		
	}
	private void t1idkeyReleased(KeyEvent k) {
		try {
			connect();
			query="select * from issue where sid = '"+t1id.getText()+"';";
			rs=stmt.executeQuery(query);
			if(t1id.getText().equals("")) {
				
				JOptionPane.showMessageDialog(ReturnBook, "no record found..!!");
			}
			if(rs.next()) {
				sname=rs.getString("sname");
				bid = rs.getString("bookid");
				bname = rs.getString("bookname");
				isdate = rs.getString("date");
				t2name.setText(sname);
				t3bid.setText(bid);
				t4bname.setText(bname);
				t5isd.setText(isdate);
				
			}
			else
            {
                JOptionPane.showMessageDialog(ReturnBook,"No book is issued on this id currently");
                
            }
			t6rd.setText(format.format(date));
			disconnect();
		}
		catch(Exception r) {
			r.printStackTrace();
		}
	}
	private void rdActionPerformed(ActionEvent e) {
		try {
			connect();
			if(t1id.getText().equals("")) {
				
				JOptionPane.showMessageDialog(ReturnBook, "no record found..!!");
			}
			else {
			query="delete from issue where sid ='" + t1id.getText()+"';";
			stmt.executeUpdate(query);
			query="update books set avilable='YES' where BookId='"+t3bid.getText()+"';";
            stmt.executeUpdate(query);
            JOptionPane.showMessageDialog(ReturnBook,"Thank you! visit again");
            disconnect();
            t1id.setText("");
            t2name.setText("");
            t3bid.setText("");
            t4bname.setText("");
            t5isd.setText("");
            t6rd.setText("");
			}
		}
		catch(Exception t) {
			t.printStackTrace();
		}
	}
}

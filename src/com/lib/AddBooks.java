package com.lib;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class AddBooks {

	 JFrame AddBooks;
	private JTextField t1;
	private JTextField t2;
	private JTextField t3;
	Connection con;
	Statement stmt;
	ResultSet rs;
	String query;

	/**
	 * Launch the application.
	 */
	
	public void connect() {
		 try
	        {  
	            Class.forName("com.mysql.cj.jdbc.Driver");  
	            con=DriverManager.getConnection(  
	            "jdbc:mysql://localhost:3306/library1","root","mysql1212#");  
	            stmt=con.createStatement(); 
	        }
	        catch(Exception e)
	        { 
	            System.out.println(e);
	            JOptionPane.showMessageDialog(AddBooks,"connection error");
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
					AddBooks window = new AddBooks();
					window.AddBooks.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddBooks() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		AddBooks = new JFrame();
		AddBooks.setBounds(100, 100, 482, 382);
		AddBooks.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		AddBooks.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add a New Book");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(166, 28, 106, 14);
		AddBooks.getContentPane().add(lblNewLabel);
		
		JLabel bookid = new JLabel("BookID");
		bookid.setBounds(129, 68, 46, 14);
		AddBooks.getContentPane().add(bookid);
		
		JLabel bookname = new JLabel("Book Name");
		bookname.setBounds(129, 111, 53, 14);
		AddBooks.getContentPane().add(bookname);
		
		JLabel author = new JLabel("Author");
		author.setBounds(129, 154, 46, 14);
		AddBooks.getContentPane().add(author);
		
		JLabel branch = new JLabel("Branch");
		branch.setBounds(129, 194, 46, 14);
		AddBooks.getContentPane().add(branch);
		
		JLabel semester = new JLabel("Semester");
		semester.setBounds(129, 233, 46, 14);
		AddBooks.getContentPane().add(semester);
		
		t1 = new JTextField();
		t1.setBounds(229, 65, 132, 20);
		AddBooks.getContentPane().add(t1);
		t1.setColumns(10);
		
		t2 = new JTextField();
		t2.setColumns(10);
		t2.setBounds(229, 108, 132, 20);
		AddBooks.getContentPane().add(t2);
		
		t3 = new JTextField();
		t3.setColumns(10);
		t3.setBounds(229, 151, 132, 20);
		AddBooks.getContentPane().add(t3);
		
		JComboBox<String> c1 = new JComboBox<String>();
		c1.setModel(new DefaultComboBoxModel<String>(new String[] {"Select Branch", "B.Tech", "CE", "EE"}));
		c1.setBounds(229, 190, 133, 22);
		AddBooks.getContentPane().add(c1);
		
		JComboBox <String>c2 = new JComboBox<String>();
		c2.setModel(new DefaultComboBoxModel<String>(new String[] {"Select Sem", "1", "2", "3", "4"}));
		c2.setBounds(229, 229, 132, 22);
		AddBooks.getContentPane().add(c2);
		
		JButton addbook = new JButton("Add");
		addbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					connect();
					query="insert into books values('"+t1.getText()+"','"+t2.getText()+"','"+t3.getText()+
							"','"+c1.getSelectedItem()+"','"+c2.getSelectedItem()+"','YES');";
					stmt.executeUpdate(query);
					disconnect();
					JOptionPane.showMessageDialog(AddBooks,"New book added to Library");
					t1.setText("");
					t2.setText("");
					t3.setText("");
					c1.setSelectedItem("Select Branch");
					c2.setSelectedItem("Select Sem");
					
				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		addbook.setBounds(154, 283, 89, 23);
		AddBooks.getContentPane().add(addbook);
		
		JButton btnNewButton_1 = new JButton("Cancle");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminHome an = new AdminHome();
				an.AdHome.setVisible(true);
				AddBooks.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(253, 283, 89, 23);
		AddBooks.getContentPane().add(btnNewButton_1);
	}

}

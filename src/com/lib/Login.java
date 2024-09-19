package com.lib;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Login {

	 JFrame Login;
	private JTextField t1;
	private JTextField t2;
	
	Connection con;
	Statement stmt;
	ResultSet rs;
	String query;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.Login.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


    public void connect()
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
            JOptionPane.showMessageDialog(Login,"connection error");
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
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Login = new JFrame();
		Login.setBounds(100, 100, 415, 386);
		Login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Login.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Gabriola", Font.BOLD, 22));
		lblNewLabel.setBounds(159, 33, 60, 34);
		Login.getContentPane().add(lblNewLabel);
		
		JLabel sid = new JLabel("StudentID");
		sid.setFont(new Font("Georgia", Font.BOLD, 11));
		sid.setBounds(71, 78, 93, 14);
		Login.getContentPane().add(sid);
		
		JLabel sname = new JLabel("Student Name");
		sname.setFont(new Font("Georgia", Font.BOLD, 11));
		sname.setBounds(71, 125, 109, 14);
		Login.getContentPane().add(sname);
		
		t1 = new JTextField();
		t1.setBounds(190, 75, 138, 20);
		Login.getContentPane().add(t1);
		t1.setColumns(10);
		
		JLabel sbranch = new JLabel("Branch");
		sbranch.setFont(new Font("Georgia", Font.BOLD, 11));
		sbranch.setBounds(71, 173, 54, 14);
		Login.getContentPane().add(sbranch);
		
		JComboBox<String> cb1 = new JComboBox<>(new String[] {"Select Branch","B.Tech","CE","EE"});
		cb1.setFont(new Font("Garamond", Font.PLAIN, 13));
		cb1.setBounds(190, 169, 138, 22);
		Login.getContentPane().add(cb1);
		
		
		JLabel ssem = new JLabel("Semester");
		ssem.setFont(new Font("Georgia", Font.BOLD, 11));
		ssem.setBounds(71, 215, 73, 14);
		Login.getContentPane().add(ssem);
		
		JComboBox cs1 = new JComboBox<Object>();
		cs1.setFont(new Font("Garamond", Font.PLAIN, 13));
		cs1.setModel(new DefaultComboBoxModel(new String[] {"Select Semester", "1", "2", "3", "4"}));
		cs1.setBounds(190, 211, 138, 22);
		Login.getContentPane().add(cs1);
		
		JButton btnCancle = new JButton("Cancle");
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home hp = new Home();
				hp.home.setVisible(true);
				Login.setVisible(false);
			}
		});
		
		
		JButton btnsubmit = new JButton("Submit");
		btnsubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(t1.getText().equals("")) {
					JOptionPane.showMessageDialog(Login, "Please enter your ID");
				}
				else if(t2.getText().equals("")) {
					JOptionPane.showMessageDialog(Login, "Please enter your name");
				}
				else if(cb1.getSelectedItem()=="Select Branch") {
					JOptionPane.showMessageDialog(Login, "Please select Branch");
				}
				else if(cs1.getSelectedItem()=="Select Semester") {
					JOptionPane.showMessageDialog(Login, "Please select semester");
				}
				else {
					connect();
					try {
						
						query="insert into studs values ('"+ t1.getText()+"','"+t2.getText()+"','"+cb1.getSelectedItem()+"','"+cs1.getSelectedItem()+"');";
						stmt.executeUpdate(query);
						JOptionPane.showMessageDialog(Login, "Thank you "+ t2.getText()+ "\nyou are Registered now..!!");
						disconnect();
					}
					 catch(SQLException se1) {
						if(se1.getErrorCode()==1062) {
							JOptionPane.showMessageDialog(Login, "StudentId is a primary key, duplicate entry is not allowed\\nIt should be unique");
						}
						else {
							JOptionPane.showMessageDialog(Login, "Connection Error..!!" + se1);
						}
					 }
					
				}
				
			}
		});
		btnsubmit.setFont(new Font("Georgia", Font.BOLD, 11));
		btnsubmit.setBounds(100, 272, 89, 23);
		Login.getContentPane().add(btnsubmit);
		
		
		btnCancle.setFont(new Font("Georgia", Font.BOLD, 11));
		btnCancle.setBounds(212, 272, 89, 23);
		Login.getContentPane().add(btnCancle);
		
		t2 = new JTextField();
		t2.setBounds(190, 119, 138, 20);
		Login.getContentPane().add(t2);
		t2.setColumns(10);
		
		
	}
}

package com.lib;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class Home {

	 JFrame home;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
					window.home.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Home() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		home = new JFrame();
		home.setBounds(100, 100, 450, 317);
		home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		home.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Library Management System");
		lblNewLabel.setFont(new Font("Gabriola", Font.BOLD, 20));
		lblNewLabel.setBounds(138, 41, 200, 31);
		home.getContentPane().add(lblNewLabel);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login l1 = new Login();
				l1.Login.setVisible(true);
				home.setVisible(false);
			}
		});
		btnLogin.setFont(new Font("Georgia", Font.BOLD, 11));
		btnLogin.setBounds(50, 104, 115, 23);
		home.getContentPane().add(btnLogin);
		
		JButton btnIssuebook = new JButton("Issue Book");
		btnIssuebook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IssueBooks ib = new IssueBooks();
				ib.IssueBooks.setVisible(true);
				home.setVisible(false);
			}
		});
		btnIssuebook.setFont(new Font("Georgia", Font.BOLD, 11));
		btnIssuebook.setBounds(254, 104, 115, 23);
		home.getContentPane().add(btnIssuebook);
		
		JButton btnreturnbook = new JButton("Return Book");
		btnreturnbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReturnBook rb = new ReturnBook();
				rb.ReturnBook.setVisible(true);
				home.setVisible(false);
			}
		});
		btnreturnbook.setFont(new Font("Georgia", Font.BOLD, 11));
		btnreturnbook.setBounds(50, 205, 115, 23);
		home.getContentPane().add(btnreturnbook);
		
		JButton btnNewButton = new JButton("Admin");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Admin ad = new Admin();
				ad.Admin.setVisible(true);
				home.setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("Georgia", Font.BOLD, 11));
		btnNewButton.setBounds(50, 156, 115, 23);
		home.getContentPane().add(btnNewButton);
		
		JButton btnBookDetails = new JButton("Book Details");
		btnBookDetails.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				BookDetails bd = new BookDetails();
				bd.bdetails.setVisible(true);
				home.setVisible(false);
			}
		});
		btnBookDetails.setFont(new Font("Georgia", Font.BOLD, 11));
		btnBookDetails.setBounds(254, 205, 115, 23);
		home.getContentPane().add(btnBookDetails);
		
		JButton btnIssuedBookDetails = new JButton("Issued Books");
		btnIssuedBookDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IssuedBooks ids = new IssuedBooks();
				ids.IssuedBooks.setVisible(true);
				home.setVisible(false);
			}
		});
		btnIssuedBookDetails.setFont(new Font("Georgia", Font.BOLD, 11));
		btnIssuedBookDetails.setBounds(254, 156, 115, 23);
		home.getContentPane().add(btnIssuedBookDetails);
	}


	
	
}

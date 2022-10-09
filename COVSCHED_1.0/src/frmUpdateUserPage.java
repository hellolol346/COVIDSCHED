import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class frmUpdateUserPage {

	private JFrame frmUpdateUserPage;
	private JPasswordField txtPassword;
	private JTextField txtName;
	private JTextField txtUsername;

	/**
	 * Launch the application.
	 */
	public void show() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmUpdateUserPage window = new frmUpdateUserPage();
					window.frmUpdateUserPage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public frmUpdateUserPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmUpdateUserPage = new JFrame();
		frmUpdateUserPage.setResizable(false);
		frmUpdateUserPage.setTitle("Update User Page");
		frmUpdateUserPage.setBounds(100, 100, 487, 329);
		frmUpdateUserPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUpdateUserPage.getContentPane().setLayout(null);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblPassword.setBounds(10, 95, 132, 32);
		frmUpdateUserPage.getContentPane().add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setEnabled(false);
		txtPassword.setBounds(152, 99, 284, 20);
		frmUpdateUserPage.getContentPane().add(txtPassword);
		
		JLabel lblName = new JLabel("Name of the User:");
		lblName.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblName.setBounds(10, 149, 132, 32);
		frmUpdateUserPage.getContentPane().add(lblName);
		
		txtName = new JTextField();
		txtName.setEnabled(false);
		txtName.setColumns(10);
		txtName.setBounds(152, 153, 284, 20);
		frmUpdateUserPage.getContentPane().add(txtName);
		
		JLabel lblRole = new JLabel("Role:");
		lblRole.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblRole.setBounds(10, 202, 132, 32);
		frmUpdateUserPage.getContentPane().add(lblRole);
		
		JComboBox cmbRole = new JComboBox();
		cmbRole.setEnabled(false);
		cmbRole.setModel(new DefaultComboBoxModel(new String[] {"-select-", "Limited User", "Administrator"}));
		cmbRole.setBounds(152, 205, 284, 22);
		frmUpdateUserPage.getContentPane().add(cmbRole);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( String.valueOf(txtPassword.getPassword()).equals("") || txtName.getText().equals("") ||cmbRole.getSelectedItem().equals("-select-")) {
					JOptionPane.showMessageDialog(frmUpdateUserPage, "All fields have not been filled", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String username = txtUsername.getText();
					String password = String.valueOf(txtPassword.getPassword());
					String name = txtName.getText();
					String role = String.valueOf(cmbRole.getSelectedItem());
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection conn = null;
						conn = DriverManager.getConnection("jdbc:mysql://localhost/covsched", "root", "");
						Statement st = conn.createStatement();
						st.executeUpdate("UPDATE login SET password = '"+password+"',uname = '"+name+"', role = '"+role+"' WHERE username = '"+username+"' ");
						JOptionPane.showMessageDialog(frmUpdateUserPage, "User has successfully been updated.", "Success", JOptionPane.INFORMATION_MESSAGE);
						frmUpdateUserPage.dispose();
					}
					catch(Exception ex) {
						System.out.println(ex);
					}
				}
			}
			}
		);
		btnSubmit.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnSubmit.setBounds(162, 252, 132, 29);
		frmUpdateUserPage.getContentPane().add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnCancel.setBounds(304, 252, 132, 29);
		frmUpdateUserPage.getContentPane().add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtPassword.setText("");
				txtName.setText("");
				cmbRole.setSelectedItem("-select-");
			}
		});
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(10, 25, 101, 14);
		frmUpdateUserPage.getContentPane().add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		txtUsername.setBounds(152, 11, 284, 20);
		frmUpdateUserPage.getContentPane().add(txtUsername);
		
		JButton btnViewCurrentDetails = new JButton("View Current Details");
		btnViewCurrentDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtUsername.getText();
				try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = null;
				conn = DriverManager.getConnection("jdbc:mysql://localhost/covsched", "root", "");
				Statement st = conn.createStatement();
				ResultSet rs=st.executeQuery("SELECT * FROM login WHERE username = '" + username + "'");
				if(rs.next())
				{
					txtName.setText(rs.getString(3));
					txtName.setEnabled(true);
					cmbRole.setToolTipText(rs.getString(4));
					cmbRole.setEnabled(true);
					txtPassword.setEnabled(true);
				}
				else
				{
					JOptionPane.showMessageDialog(frmUpdateUserPage, "Invalid username", "Error", JOptionPane.ERROR_MESSAGE);
					
				}
				}
				catch(Exception ex) {
					System.out.println(ex);
				}
			}
		});
		btnViewCurrentDetails.setBounds(219, 42, 153, 36);
		frmUpdateUserPage.getContentPane().add(btnViewCurrentDetails);
	}
}

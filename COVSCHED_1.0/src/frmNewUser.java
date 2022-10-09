import java.awt.EventQueue;



import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;

public class frmNewUser {

	private JFrame frmNewUserRegistration;
	private JTextField txtUsername;
	private JTextField txtName;
	private JPasswordField txtRetype;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public void show() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmNewUser window = new frmNewUser();
					window.frmNewUserRegistration.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public frmNewUser() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNewUserRegistration = new JFrame();
		frmNewUserRegistration.setResizable(false);
		frmNewUserRegistration.setTitle("New User Registration");
		frmNewUserRegistration.setBounds(100, 100, 483, 338);
		frmNewUserRegistration.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmNewUserRegistration.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Desired Username:");
		lblUsername.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblUsername.setBounds(10, 27, 132, 32);
		frmNewUserRegistration.getContentPane().add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(152, 31, 284, 20);
		frmNewUserRegistration.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(152, 167, 284, 20);
		frmNewUserRegistration.getContentPane().add(txtName);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblPassword.setBounds(10, 67, 132, 32);
		frmNewUserRegistration.getContentPane().add(lblPassword);
		
		JLabel lblReType = new JLabel("Re-type the Password:");
		lblReType.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblReType.setBounds(10, 110, 132, 32);
		frmNewUserRegistration.getContentPane().add(lblReType);
		
		JLabel lblName = new JLabel("Name of the User:");
		lblName.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblName.setBounds(10, 163, 132, 32);
		frmNewUserRegistration.getContentPane().add(lblName);
		
		JLabel lblRole = new JLabel("Role:");
		lblRole.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblRole.setBounds(10, 218, 132, 32);
		frmNewUserRegistration.getContentPane().add(lblRole);
		
		txtRetype = new JPasswordField();
		txtRetype.setBounds(152, 114, 284, 20);
		frmNewUserRegistration.getContentPane().add(txtRetype);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(152, 71, 284, 20);
		frmNewUserRegistration.getContentPane().add(txtPassword);
		
		JComboBox cmbRole = new JComboBox();
		cmbRole.setModel(new DefaultComboBoxModel(new String[] {"-select-", "Administrator", "Limited User"}));
		cmbRole.setBounds(152, 221, 284, 22);
		frmNewUserRegistration.getContentPane().add(cmbRole);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtUsername.getText().equals("") || String.valueOf(txtPassword.getPassword()).equals("") ||String.valueOf(txtRetype.getPassword()).equals("") || txtName.getText().equals("") ||cmbRole.getSelectedItem().equals("-select-")) {
					JOptionPane.showMessageDialog(frmNewUserRegistration, "All fields have not been filled", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(String.valueOf(txtPassword.getPassword()).equals(String.valueOf(txtRetype.getPassword())) == false) {
					JOptionPane.showMessageDialog(frmNewUserRegistration, "Password fields do not match.", "Error", JOptionPane.ERROR_MESSAGE);
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
						st.execute("INSERT INTO login VALUES('" +username+ "','" +password+ "','" +name+ "','" +role+ "')");
						JOptionPane.showMessageDialog(frmNewUserRegistration, "New user has successfully been created.", "Success", JOptionPane.INFORMATION_MESSAGE);
						frmNewUserRegistration.dispose();
					}
					catch(SQLIntegrityConstraintViolationException ex) {
						JOptionPane.showMessageDialog(frmNewUserRegistration, "Username already exists. Plase pick another one.", "Username Error", JOptionPane.ERROR_MESSAGE);
					}
					catch(Exception ex) {
						System.out.println(ex);
					}
				}
			}
		});
		btnSubmit.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnSubmit.setBounds(152, 262, 132, 29);
		frmNewUserRegistration.getContentPane().add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ans = JOptionPane.showConfirmDialog(frmNewUserRegistration, "Are you sure you want to clear the contents?", "Confirmation", JOptionPane.OK_CANCEL_OPTION);
				if(ans == 0) {
					txtUsername.setText("");
					txtPassword.setText("");
					txtRetype.setText("");
					txtName.setText("");
					cmbRole.setSelectedItem("-select-");
				}
			}
		});
		btnCancel.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnCancel.setBounds(304, 262, 132, 29);
		frmNewUserRegistration.getContentPane().add(btnCancel);
	}
}

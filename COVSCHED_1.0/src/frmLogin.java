import java.awt.EventQueue;
import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;

public class frmLogin {

	private JFrame frmLogin;
	private JTextField txtUsername;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmLogin window = new frmLogin();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public frmLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setResizable(false);
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 588, 245);
		frmLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Calibri", Font.PLAIN, 12));
		txtUsername.setBounds(101, 25, 275, 34);
		frmLogin.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblUsername.setBounds(10, 33, 93, 34);
		frmLogin.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblPassword.setBounds(10, 78, 82, 34);
		frmLogin.getContentPane().add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Calibri", Font.PLAIN, 12));
		txtPassword.setBounds(101, 77, 275, 35);
		frmLogin.getContentPane().add(txtPassword);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username=txtUsername.getText();
				String password = String.valueOf(txtPassword.getPassword());
				if(username.equals("") || password.equals("")) {
					JOptionPane.showMessageDialog(frmLogin, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = null;
					conn = DriverManager.getConnection("jdbc:mysql://localhost/covsched", "root", "");
					Statement st = conn.createStatement();
					ResultSet rs=st.executeQuery("SELECT * FROM login WHERE username = '" + username + "'");
					if(rs.next())
					{
						if(rs.getString(2).equals(password)) {
							JOptionPane.showMessageDialog(frmLogin, "Successfully logged in to account.", "Success", JOptionPane.INFORMATION_MESSAGE);
							frmMain fr = new frmMain();
							fr.show();
							frmLogin.dispose();
						}
						else {
							JOptionPane.showMessageDialog(frmLogin, "Invalid credentials have been entered.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(frmLogin, "Username does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
					}
						
				}
				catch(Exception ex) {
					System.out.println(ex);
				}
				}
			}
		});
		btnSubmit.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnSubmit.setBounds(101, 123, 121, 34);
		frmLogin.getContentPane().add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ans = JOptionPane.showConfirmDialog(frmLogin, "Are you sure you want to clear the contents?", "Confirmation", JOptionPane.OK_CANCEL_OPTION);
				if(ans == 0) {
					txtUsername.setText("");
					txtPassword.setText("");
				}
			}
		});
		btnCancel.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnCancel.setBounds(243, 123, 133, 34);
		frmLogin.getContentPane().add(btnCancel);
		
		JLabel lblPic = new JLabel("");
		lblPic.setIcon(new ImageIcon("c:\\Login3.jpg"));
		lblPic.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblPic.setBounds(410,0, 168, 200);
		frmLogin.getContentPane().add(lblPic);
		
		JButton btnNewAccount = new JButton("Click here to Create a New Account");
		btnNewAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmNewUser f = new frmNewUser();
				f.show();
			}
		});
		btnNewAccount.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnNewAccount.setBounds(101, 168, 275, 32);
		frmLogin.getContentPane().add(btnNewAccount);
	}
}

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class frmSearchUser {

	private JFrame frmSearchUser;
	private JTextField txtUsername;
	private JTextField txtName;
	private JLabel lblRole;
	private JTextField txtRole;
	private JButton btnButton;

	/**
	 * Launch the application.
	 */
	public void show() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmSearchUser window = new frmSearchUser();
					window.frmSearchUser.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public frmSearchUser() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		frmSearchUser = new JFrame();
		frmSearchUser.setResizable(false);
		frmSearchUser.setTitle("Search User");
		frmSearchUser.setBounds(100, 100, 642, 243);
		frmSearchUser.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmSearchUser.getContentPane().setLayout(null);
		
		JLabel lblUsername_1 = new JLabel("Username:");
		lblUsername_1.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblUsername_1.setBounds(20, 34, 132, 32);
		frmSearchUser.getContentPane().add(lblUsername_1);
		
		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		txtUsername.setBounds(162, 38, 284, 20);
		frmSearchUser.getContentPane().add(txtUsername);
		
		JLabel lblName = new JLabel("Name of the User:");
		lblName.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblName.setBounds(20, 97, 132, 32);
		frmSearchUser.getContentPane().add(lblName);
		
		txtName = new JTextField();
		txtName.setEnabled(false);
		txtName.setColumns(10);
		txtName.setBounds(162, 101, 284, 20);
		frmSearchUser.getContentPane().add(txtName);
		
		lblRole = new JLabel("Role:");
		lblRole.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblRole.setBounds(20, 159, 132, 32);
		frmSearchUser.getContentPane().add(lblRole);
		
		txtRole = new JTextField();
		txtRole.setEnabled(false);
		txtRole.setColumns(10);
		txtRole.setBounds(162, 163, 284, 20);
		frmSearchUser.getContentPane().add(txtRole);
		
		btnButton = new JButton("Click to View Details");
		btnButton.addActionListener(new ActionListener() {
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
					txtRole.setText(rs.getString(4));
				}
				else
				{
					JOptionPane.showMessageDialog(frmSearchUser, "Invalid username", "Error", JOptionPane.ERROR_MESSAGE);
					
				}
				}
				catch(Exception ex) {
					System.out.println(ex);
				}
			}
		});
		btnButton.setBounds(456, 37, 162, 23);
		frmSearchUser.getContentPane().add(btnButton);
	}
}

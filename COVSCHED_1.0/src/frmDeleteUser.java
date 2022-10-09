import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class frmDeleteUser {

	private JFrame frmDeleteUser;
	private JTextField txtUsername;
	private JTextField txtName;
	int flag=1;
	int ans = 1;
	/**
	 * Launch the application.
	 */
	public void show() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmDeleteUser window = new frmDeleteUser();
					window.frmDeleteUser.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public frmDeleteUser() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDeleteUser = new JFrame();
		frmDeleteUser.setResizable(false);
		frmDeleteUser.setTitle("Delete Registration");
		frmDeleteUser.setBounds(100, 100, 470, 248);
		frmDeleteUser.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmDeleteUser.getContentPane().setLayout(null);
	
		JButton btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ans = JOptionPane.showConfirmDialog(frmDeleteUser, "Are you sure you want to delete the registration", "Delete Confirmation", JOptionPane.OK_CANCEL_OPTION);
				String username = txtUsername.getText();
				if(ans == 0) {
					try {
						if(flag==1)
						{
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection conn = null;
						conn = DriverManager.getConnection("jdbc:mysql://localhost/covsched", "root", "");
						Statement st = conn.createStatement();
						st.execute("DELETE FROM login WHERE username = '" +username+ "'");
						}
						
					}
					catch(Exception ex) {
						System.out.println(ex);
					}
					JOptionPane.showMessageDialog(frmDeleteUser, "The registration has successfully been deleted.", "Success", JOptionPane.INFORMATION_MESSAGE);
					frmDeleteUser.dispose();
				}
			}
		});
		btnDelete.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnDelete.setBounds(134, 137, 96, 23);
		frmDeleteUser.getContentPane().add(btnDelete);
		JLabel lblUsername = new JLabel("Enter Username:");
		lblUsername.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblUsername.setBounds(10, 23, 113, 24);
		frmDeleteUser.getContentPane().add(lblUsername);
		
		txtUsername = new JTextField();
		
		txtUsername.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String username = txtUsername.getText();
				try {
				btnDelete.setEnabled(true);	
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = null;
				conn = DriverManager.getConnection("jdbc:mysql://localhost/covsched", "root", "");
				Statement st = conn.createStatement();
				ResultSet rs=st.executeQuery("SELECT uname FROM login WHERE username = '" + username + "'");
				if(rs.next())
				{
					txtName.setText(rs.getString(1));
				}
				else
				{
					JOptionPane.showMessageDialog(frmDeleteUser, "Invalid username", "Error", JOptionPane.ERROR_MESSAGE);
					btnDelete.setEnabled(false);
				}
				}
				catch(Exception ex) {
					System.out.println(ex);
				}
			}
		});
		txtUsername.setFont(new Font("Calibri", Font.PLAIN, 12));
		txtUsername.setBounds(133, 23, 220, 20);
		frmDeleteUser.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ans = JOptionPane.showConfirmDialog(frmDeleteUser, "Are you sure you want to exit?", "Confirmation", JOptionPane.OK_CANCEL_OPTION);
				if(ans == 0) {
					frmDeleteUser.dispose();
				}
			}
		});
		btnCancel.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnCancel.setBounds(254, 137, 96, 23);
		frmDeleteUser.getContentPane().add(btnCancel);
		
		JLabel lblName = new JLabel("Name of User:");
		lblName.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblName.setBounds(10, 78, 113, 24);
		frmDeleteUser.getContentPane().add(lblName);
		
		txtName = new JTextField();
		txtName.setEnabled(false);
		txtName.setFont(new Font("Calibri", Font.PLAIN, 12));
		txtName.setColumns(10);
		txtName.setBounds(133, 80, 220, 20);
		frmDeleteUser.getContentPane().add(txtName);
		
		JLabel lblRemind = new JLabel("*Press tab button after entering username*");
		lblRemind.setBounds(131, 54, 222, 14);
		frmDeleteUser.getContentPane().add(lblRemind);
	}
}

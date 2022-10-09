import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frmUpdateUser {

	private JFrame frmUpdateUser;
	private JTextField txtUsername;

	/**
	 * Launch the application.
	 */
	public void show() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmUpdateUser window = new frmUpdateUser();
					window.frmUpdateUser.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public frmUpdateUser() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmUpdateUser = new JFrame();
		frmUpdateUser.setResizable(false);
		frmUpdateUser.setTitle("Update Registration");
		frmUpdateUser.setBounds(100, 100, 515, 232);
		frmUpdateUser.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmUpdateUser.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Enter your Username:");
		lblUsername.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblUsername.setBounds(10, 49, 132, 35);
		frmUpdateUser.getContentPane().add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Calibri", Font.PLAIN, 12));
		txtUsername.setColumns(10);
		txtUsername.setBounds(156, 54, 335, 20);
		frmUpdateUser.getContentPane().add(txtUsername);
		
		JButton btnUpdate = new JButton("Go to Update Details");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frmUpdateUser, "Click Update when done.", "Update", JOptionPane.INFORMATION_MESSAGE );
				frmUpdateUserPage f = new frmUpdateUserPage();
				f.show();
			}
		});
		btnUpdate.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnUpdate.setBounds(156, 111, 155, 29);
		frmUpdateUser.getContentPane().add(btnUpdate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtUsername.setText("");
			}
		});
		btnCancel.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnCancel.setBounds(359, 111, 132, 29);
		frmUpdateUser.getContentPane().add(btnCancel);
	}

}

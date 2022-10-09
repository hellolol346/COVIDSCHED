import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class ViewDetails {

	private JFrame frmViewDetails;
	private JTextField txtMobile;
	private JTextField textField_1;
	private JTextField txtSecond;
	private JTextField txtEmail;
	private JTextField txtGender;
	private JTextField txtDate;
	String photo = "";

	/**
	 * Launch the application.
	 */
	public void show() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewDetails window = new ViewDetails();
					window.frmViewDetails.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the application.
	 */
	public ViewDetails() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmViewDetails = new JFrame();
		frmViewDetails.setTitle("Vaccination Details/Update");
		frmViewDetails.setResizable(false);
		frmViewDetails.setBounds(100, 100, 785, 523);
		frmViewDetails.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmViewDetails.getContentPane().setLayout(null);
		
		JLabel lblMobile = new JLabel("Mobile Number:");
		lblMobile.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblMobile.setBounds(10, 37, 107, 21);
		frmViewDetails.getContentPane().add(lblMobile);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Vaccinated");
		chckbxNewCheckBox.setEnabled(false);
		
		chckbxNewCheckBox.setForeground(Color.MAGENTA);
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.BOLD, 13));
		chckbxNewCheckBox.setBounds(496, 361, 168, 23);
		frmViewDetails.getContentPane().add(chckbxNewCheckBox);
		
		txtMobile = new JTextField();
		txtMobile.setColumns(10);
		txtMobile.setBounds(127, 35, 160, 20);
		frmViewDetails.getContentPane().add(txtMobile);
		
		JLabel lblName = new JLabel("Name of Applicant:");
		lblName.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblName.setBounds(10, 81, 107, 21);
		frmViewDetails.getContentPane().add(lblName);
		
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setColumns(10);
		textField_1.setBounds(127, 79, 276, 20);
		frmViewDetails.getContentPane().add(textField_1);
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblGender.setBounds(10, 140, 79, 21);
		frmViewDetails.getContentPane().add(lblGender);
		
		JLabel lblDate = new JLabel("Date of Birth:");
		lblDate.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblDate.setBounds(10, 181, 107, 21);
		frmViewDetails.getContentPane().add(lblDate);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblAddress.setBounds(15, 222, 107, 21);
		frmViewDetails.getContentPane().add(lblAddress);
		
		JTextArea txtAddress = new JTextArea();
		txtAddress.setEnabled(false);
		txtAddress.setBounds(127, 218, 290, 113);
		frmViewDetails.getContentPane().add(txtAddress);
		
		JLabel lblMobile2 = new JLabel("Second Mobile:");
		lblMobile2.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblMobile2.setBounds(10, 344, 112, 21);
		frmViewDetails.getContentPane().add(lblMobile2);
		
		txtSecond = new JTextField();
		txtSecond.setEnabled(false);
		txtSecond.setColumns(10);
		txtSecond.setBounds(129, 342, 187, 20);
		frmViewDetails.getContentPane().add(txtSecond);
		
		JLabel lblEmail = new JLabel("Email Address:");
		lblEmail.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblEmail.setBounds(10, 389, 107, 21);
		frmViewDetails.getContentPane().add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setEnabled(false);
		txtEmail.setColumns(10);
		txtEmail.setBounds(129, 387, 276, 20);
		frmViewDetails.getContentPane().add(txtEmail);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setEnabled(false);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String vaccinated = "Not vaccinated";
				if(chckbxNewCheckBox.isSelected()) {
					vaccinated = "Vaccinated";
				}
				try {
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = null;
					conn = DriverManager.getConnection("jdbc:mysql://localhost/covsched", "root", "");
					Statement st = conn.createStatement();
					st.execute("UPDATE registration set status = '"+vaccinated+"' WHERE mobileNumber ='" +txtMobile.getText()+ "'");
					JOptionPane.showMessageDialog(frmViewDetails, "Successfully Updated", "Success", JOptionPane.INFORMATION_MESSAGE);
				} 
				catch (Exception e1) {
					System.out.println(e1);
				}
			}
		});
		btnUpdate.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnUpdate.setBounds(129, 439, 122, 35);
		frmViewDetails.getContentPane().add(btnUpdate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmViewDetails.dispose();
			}
		});
		btnCancel.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnCancel.setBounds(283, 439, 122, 35);
		frmViewDetails.getContentPane().add(btnCancel);
		
		JLabel lblPicture = new JLabel("");
		lblPicture.setBounds(555, 52, 187, 191);
		frmViewDetails.getContentPane().add(lblPicture);
		
		
		txtGender = new JTextField();
		txtGender.setEnabled(false);
		txtGender.setBounds(127, 138, 96, 20);
		frmViewDetails.getContentPane().add(txtGender);
		txtGender.setColumns(10);
		
		txtDate = new JTextField();
		txtDate.setEnabled(false);
		txtDate.setBounds(127, 179, 210, 20);
		frmViewDetails.getContentPane().add(txtDate);
		txtDate.setColumns(10);
		
		JButton btnView = new JButton("View Details");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mobile = txtMobile.getText();
				try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = null;
				conn = DriverManager.getConnection("jdbc:mysql://localhost/covsched", "root", "");
				Statement st = conn.createStatement();
				ResultSet rs=st.executeQuery("SELECT * FROM registration WHERE mobileNumber = '" + mobile + "'");
				if(rs.next())
				{
					textField_1.setText(rs.getString(2));
					txtGender.setText(rs.getString(3));
					txtDate.setText(rs.getString(4));
					txtAddress.setText(rs.getString(5));
					txtSecond.setText(rs.getString(6));
					txtEmail.setText(rs.getString(7));
					photo = rs.getString(8);
					ImageIcon icon=new ImageIcon(rs.getString(8));
					if(rs.getString(9).equals("Vaccinated")) {
						chckbxNewCheckBox.setSelected(true);
					}
					chckbxNewCheckBox.setEnabled(true);
					lblPicture.setIcon(icon);
				}
				else
				{
					JOptionPane.showMessageDialog(frmViewDetails, "Invalid mobile number", "Error", JOptionPane.ERROR_MESSAGE);
				}
				}
				catch(Exception ex) {
					System.out.println(ex);
				}
				btnUpdate.setEnabled(true);
			}
		});
		btnView.setBounds(331, 34, 122, 23);
		frmViewDetails.getContentPane().add(btnView);
		
		JLabel lblPic = new JLabel("Picture");
		lblPic.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblPic.setBounds(626, 281, 49, 14);
		frmViewDetails.getContentPane().add(lblPic);
	}
}





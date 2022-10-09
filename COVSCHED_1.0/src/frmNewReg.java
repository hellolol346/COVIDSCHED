import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JRadioButton;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;

public class frmNewReg {

	private JFrame frmNewRegistration;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_4;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	String fname = "";

	/**
	 * Launch the application.
	 */
	public void show() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmNewReg window = new frmNewReg();
					window.frmNewRegistration.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public frmNewReg() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNewRegistration = new JFrame();
		frmNewRegistration.setTitle("New Registration");
		frmNewRegistration.setResizable(false);
		frmNewRegistration.setBounds(100, 100, 838, 544);
		frmNewRegistration.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmNewRegistration.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(160, 39, 160, 20);
		frmNewRegistration.getContentPane().add(textField);
		
		JLabel lblMobile = new JLabel("Mobile Number:");
		lblMobile.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblMobile.setBounds(43, 41, 107, 21);
		frmNewRegistration.getContentPane().add(lblMobile);
		
		JLabel lblName = new JLabel("Name of Applicant:");
		lblName.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblName.setBounds(43, 85, 107, 21);
		frmNewRegistration.getContentPane().add(lblName);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(160, 83, 276, 20);
		frmNewRegistration.getContentPane().add(textField_1);
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblGender.setBounds(43, 144, 79, 21);
		frmNewRegistration.getContentPane().add(lblGender);
		
		JRadioButton rdbtnMale = new JRadioButton("Male");
		buttonGroup.add(rdbtnMale);
		rdbtnMale.setFont(new Font("Calibri", Font.PLAIN, 12));
		rdbtnMale.setBounds(160, 143, 79, 23);
		frmNewRegistration.getContentPane().add(rdbtnMale);
		
		JRadioButton rdbtnFemale = new JRadioButton("Female");
		buttonGroup.add(rdbtnFemale);
		rdbtnFemale.setToolTipText("");
		rdbtnFemale.setFont(new Font("Calibri", Font.PLAIN, 12));
		rdbtnFemale.setBounds(268, 143, 79, 23);
		frmNewRegistration.getContentPane().add(rdbtnFemale);
		
		JLabel lblDate = new JLabel("Date of Birth:");
		lblDate.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblDate.setBounds(43, 185, 107, 21);
		frmNewRegistration.getContentPane().add(lblDate);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(160, 185, 192, 20);
		frmNewRegistration.getContentPane().add(dateChooser);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblAddress.setBounds(48, 226, 107, 21);
		frmNewRegistration.getContentPane().add(lblAddress);
		
		JLabel lblMobile2 = new JLabel("Second Mobile:");
		lblMobile2.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblMobile2.setBounds(43, 348, 112, 21);
		frmNewRegistration.getContentPane().add(lblMobile2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(162, 346, 187, 20);
		frmNewRegistration.getContentPane().add(textField_3);
		
		JLabel lblEmail = new JLabel("Email Address:");
		lblEmail.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblEmail.setBounds(43, 393, 107, 21);
		frmNewRegistration.getContentPane().add(lblEmail);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(162, 391, 276, 20);
		frmNewRegistration.getContentPane().add(textField_4);
		
		JTextArea txtAddress = new JTextArea();
		txtAddress.setBounds(160, 222, 290, 113);
		frmNewRegistration.getContentPane().add(txtAddress);
		
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mobile = textField.getText();
				String name = textField_1.getText();
				String gender="";
				if(rdbtnMale.isSelected()) {
					gender="Male";
				}
				else {
					gender = "Female";
				}
				String dateBirth = dateChooser.getDateFormatString();
				String address = txtAddress.getText();
				String mobileSecond = textField_3.getText();
				String email = textField_4.getText();
				String picture = fname.replace("\\","\\\\");
				System.out.println("Picture : "+picture);
				if(mobile.equals("") || name.equals("") || gender.equals("") || dateBirth.equals("") || address.equals("") || mobileSecond.equals("") || email.equals("") || picture.equals("")) {
					JOptionPane.showMessageDialog(frmNewRegistration, "Some field are not filled in.", "Blank Fields", JOptionPane.ERROR_MESSAGE);
					}
				else {
				
				try {
					Date d=dateChooser.getDate();
					SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
					String strDate = df.format(d);
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = null;
					conn = DriverManager.getConnection("jdbc:mysql://localhost/covsched", "root", "");
					Statement st = conn.createStatement();
					st.execute("INSERT INTO registration VALUES('" +mobile+ "','" +name+ "','" +gender+ "','" +strDate+ "','" +address+ "','" +mobileSecond+ "','" +email+ "','" +picture+"','Not vaccinated')");
				} 
				catch (Exception e1) {
					System.out.println(e1);
				}
				JOptionPane.showMessageDialog(frmNewRegistration, "You have successfully registered.", "Success", JOptionPane.INFORMATION_MESSAGE);
				}
				}
			}
		);
		btnUpdate.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnUpdate.setBounds(162, 443, 122, 35);
		frmNewRegistration.getContentPane().add(btnUpdate);
		
		JLabel lblPicture = new JLabel("");
		lblPicture.setBounds(590, 15, 187, 191);
		frmNewRegistration.getContentPane().add(lblPicture);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ans = JOptionPane.showConfirmDialog(frmNewRegistration, "Are you sure you want to clear", "Confirm", JOptionPane.OK_CANCEL_OPTION);
				if(ans == 0) {
				textField.setText("");
				textField_1.setText("");
				textField_3.setText("");
				textField_4.setText("");
				rdbtnMale.setSelected(false);
				rdbtnFemale.setSelected(false);
				dateChooser.setDate(null);
				lblPicture.setIcon(null);
				txtAddress.setText("");
				}
			}
		});
		btnCancel.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnCancel.setBounds(316, 443, 122, 35);
		frmNewRegistration.getContentPane().add(btnCancel);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnBrowse.setBounds(636, 256, 122, 35);
		frmNewRegistration.getContentPane().add(btnBrowse);
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser=new JFileChooser();
				chooser.showOpenDialog(null);
				File f = chooser.getSelectedFile();
				fname=f.getAbsolutePath();
				ImageIcon icon=new ImageIcon(fname);
				lblPicture.setIcon(icon);
			}
		});
		
	}
}

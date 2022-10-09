import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frmMain {

	private JFrame frmCovidManagement;

	/**
	 * Launch the application.
	 */
	public void show() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmMain window = new frmMain();
					window.frmCovidManagement.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public frmMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCovidManagement = new JFrame();
		frmCovidManagement.setResizable(false);
		frmCovidManagement.setTitle("COVID Scheduele Management");
		frmCovidManagement.setBounds(100, 100, 992, 497);
		frmCovidManagement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCovidManagement.getContentPane().setLayout(null);
		
		JLabel lblPicture = new JLabel("");
		lblPicture.setIcon(new ImageIcon("c:\\Vaccine.jpg"));
		lblPicture.setBounds(20, 36, 952, 411);
		frmCovidManagement.getContentPane().add(lblPicture);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(10, 3, 962, 22);
		frmCovidManagement.getContentPane().add(menuBar);
		
		JMenu mnNewMenu = new JMenu("User Management");
		menuBar.add(mnNewMenu);
		
		JMenuItem AddUser = new JMenuItem("Add new user");
		AddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmNewUser fr=new frmNewUser();
				fr.show();
			}
		});
		mnNewMenu.add(AddUser);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Delete User");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmDeleteUser f = new frmDeleteUser();
				f.show();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Search User");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmSearchUser fr = new frmSearchUser();
				fr.show();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Update User");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmUpdateUser f = new frmUpdateUser();
				f.show();
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_2 = new JMenu("Vaccine Allotment");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Allote Vaccine");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmNewReg f=new frmNewReg();
				f.show();
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_6);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("View Vaccination Details");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewDetails v = new ViewDetails();
				v.show();
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_7);
	}
}

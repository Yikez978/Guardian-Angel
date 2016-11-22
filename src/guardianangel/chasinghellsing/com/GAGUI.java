package guardianangel.chasinghellsing.com;

import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Label;
import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GAGUI {
	

	private JFrame frmGuardianAngel;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			
			
			public void run() {
				try {
					GAGUI window = new GAGUI();
					window.frmGuardianAngel.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GAGUI()throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()throws IOException {
		frmGuardianAngel = new JFrame();
		frmGuardianAngel.getContentPane().setBackground(Color.DARK_GRAY);
		frmGuardianAngel.setBackground(Color.DARK_GRAY);
		frmGuardianAngel.setTitle("Guardian Angel v1.0");
		frmGuardianAngel.setBounds(100, 100, 786, 565);
		frmGuardianAngel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGuardianAngel.getContentPane().setLayout(null);
		
		//Defining models to populate Jlists.
		DefaultListModel<String> ulist = new DefaultListModel<String>();
		DefaultListModel<String> dulist = new DefaultListModel<String>();		
		DefaultListModel<String> lulist = new DefaultListModel<String>();
		DefaultListModel<String> glist = new DefaultListModel<String>();
		DefaultListModel<String> gmlist = new DefaultListModel<String>();
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.LIGHT_GRAY);
		tabbedPane.setToolTipText("Unlock, Enable, and Disable User accounts.");
		tabbedPane.setBounds(0, 25, 770, 502);
		frmGuardianAngel.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("User Accounts", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(0, 35, 276, 132);
		panel.add(scrollPane);
		JList list = new JList();
		list.setBackground(Color.LIGHT_GRAY);
		scrollPane.setViewportView(list);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(615, 35, 135, 218);
		panel.add(scrollPane_1);
		
		JList list_2 = new JList();
		list_2.setBackground(Color.LIGHT_GRAY);
		scrollPane_1.setViewportView(list_2);
		Button button_1 = new Button("Enabled Users");
		button_1.setBounds(10, 173, 91, 22);
		panel.add(button_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(Color.LIGHT_GRAY);
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setToolTipText("AD group Management.");
		tabbedPane.addTab("Groups", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(354, 43, 150, 267);
		panel_1.add(scrollPane_3);
		
		JList list_3 = new JList();
		list_3.setBackground(Color.LIGHT_GRAY);
		scrollPane_3.setViewportView(list_3);
		
		
		
		Button button_3 = new Button("Unlock");
		button_3.setBounds(655, 260, 46, 22);
		panel.add(button_3);
		
		Button btnEnableAccount = new Button("Enable Account");
		btnEnableAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEnableAccount.setBounds(20, 201, 99, 23);
		btnEnableAccount.setFont(new Font("Dialog", Font.PLAIN, 9));
		panel.add(btnEnableAccount);
		JButton button_2 = new JButton("Lockedout Users");
		button_2.setBounds(0, 226, -113, -23);
		panel.add(button_2);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//for (int i = 0; i <300; i++){
					
				powerShell pshell = new powerShell();
				ArrayList<String> lusers = new ArrayList<String>();
				lulist.clear();
				try{
					pshell.lockedUsers();
				} catch(IOException ex){					
				}
				File in = new File("C:/ProgramData/Guardian Angel/lockedusers.txt");
				try{
					Scanner lins = new Scanner(in);
					BufferedReader br = new BufferedReader(new FileReader(in));     
					if (br.readLine() == null) {
					    lulist.addElement("No Locks");
					    br.close(); }
					
					
					while (lins.hasNext()){
						lusers.add(lins.nextLine());
						
				} lins.close();
								
				} catch(IOException ex){ 					
				}
				
				for(String s : lusers){
					lulist.addElement(s);										
				}
				list_2.setModel(lulist);
				
				/*try{
					Thread.sleep(3000);
				} catch(InterruptedException ex){
					
				}*/
			}
			
		});
		
		Button btnDisableAcccount = new Button("Disable Acccount");
		btnDisableAcccount.setBounds(142, 201, 98, 23);
		btnDisableAcccount.setFont(new Font("Dialog", Font.PLAIN, 9));
		panel.add(btnDisableAcccount);
		
		Button but = new Button("Locked Users");
		but.setBounds(510, 226, 99, 23);
		panel.add(but);
		
		Button button = new Button("Disabled Users");
		button.setBounds(132, 173, 91, 22);
		panel.add(button);
		
		JLabel lblTheLockedOut = new JLabel("The locked out user list refreshes every 15 seconds.");
		lblTheLockedOut.setBounds(270, 437, 301, -14);
		lblTheLockedOut.setForeground(Color.RED);
		panel.add(lblTheLockedOut);
		
		JLabel lblLockedOutUsers = new JLabel("Locked out users");
		lblLockedOutUsers.setBounds(637, 6, 112, 14);
		lblLockedOutUsers.setForeground(Color.WHITE);
		panel.add(lblLockedOutUsers);
		
		JLabel lblEnabledDisabled = new JLabel("Enabled / Disabled user display");
		lblEnabledDisabled.setBounds(22, 22, 254, 14);
		lblEnabledDisabled.setForeground(Color.WHITE);
		panel.add(lblEnabledDisabled);
		
		JButton btnNewButton = new JButton("Add to group");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(276, 34, 112, 28);
		panel.add(btnNewButton);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(276, 66, 118, 218);
		panel.add(scrollPane_4);
		
		JList list_4 = new JList();
		scrollPane_4.setViewportView(list_4);
		
		JButton btnOk = new JButton("OK");
		btnOk.setFont(new Font("SansSerif", Font.PLAIN, 10));
		btnOk.setBounds(278, 285, 52, 28);
		panel.add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("SansSerif", Font.PLAIN, 10));
		btnCancel.setBounds(336, 284, 62, 28);
		panel.add(btnCancel);
		
		
		
		
		
				
				
		
		
		JLabel lblActiveDirectoryGroups = new JLabel("Active Directory Groups");
		lblActiveDirectoryGroups.setBounds(37, 22, 167, 14);
		lblActiveDirectoryGroups.setForeground(Color.WHITE);
		panel_1.add(lblActiveDirectoryGroups);
		
		
		Button button_4 = new Button("Import Groups");
		button_4.setBounds(198, 43, 93, 23);
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button_4.setForeground(Color.DARK_GRAY);
		panel_1.add(button_4);
		
		Label label = new Label("It may take a moment for ");
		label.setForeground(Color.RED);
		label.setBounds(205, 242, 126, -38);
		label.setFont(new Font("Dialog", Font.PLAIN, 10));
		panel_1.add(label);
		
		Label label_1 = new Label("group members to populate.");
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("Dialog", Font.PLAIN, 10));
		label_1.setBounds(198, 278, 150, -28);
		panel_1.add(label_1);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.GRAY);
		menuBar.setBounds(0, 0, 770, 21);
		frmGuardianAngel.getContentPane().add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setBackground(Color.GRAY);
		menuBar.add(mnFile);
		
		JMenuItem mntmClose = new JMenuItem("Close");
		mnFile.add(mntmClose);
		
		but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Runnable thread = new Threading(button_2);
				new Thread(thread).start();
				but.setEnabled(false);
				lblTheLockedOut.setBounds(270, 437, 301, 14);
								
			}
				
		});
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 43, 182, 267);
		panel_1.add(scrollPane_2);
		
		JList list_1 = new JList();
		list_1.setBackground(Color.LIGHT_GRAY);
		list_1.setForeground(Color.BLACK);
		scrollPane_2.setViewportView(list_1);
		
		Label label_2 = new Label("Members of the group");
		label_2.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_2.setForeground(Color.WHITE);
		label_2.setBounds(354, 14, 150, 23);
		panel_1.add(label_2);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				powerShell pshell = new powerShell();
				String group = (String) list_1.getSelectedValue();
				String groupMember = (String) list_3.getSelectedValue();
				group = group.trim();
				groupMember = groupMember.trim();
				
				try{
					pshell.removeGroupMembers(group, groupMember);
				} catch(IOException ex){
					
				}
			}
		});
		btnRemove.setBounds(258, 116, 90, 28);
		panel_1.add(btnRemove);
		list_1.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent event) {
				powerShell pshell= new powerShell();
				
				gmlist.clear();
				list_3.setModel(gmlist);
				label.setBounds(205, 242, 126, 38);
				label_1.setBounds(198, 278, 150, 28);
				ArrayList<String> groupMems = new ArrayList<String>();
				ArrayList<String> groupFix = new ArrayList<String>();
				String group = "";
				try{
					if(!event.getValueIsAdjusting()){
						JList source = (JList)event.getSource();
						String groups = source.getSelectedValuesList().toString();
						groupFix.add(groups);
							for(String s: groupFix){
							s = s.replaceAll("[\\[\\]]", "");
							s = s.trim();
							group = s;
						}
						
						pshell.getGroupMembers(group);
						System.out.println(group);
						
					}
				} catch(IOException ex){
					
				}
				File in = new File("C:/ProgramData/Guardian Angel/GroupMembers.txt");
				try{
					Scanner ins = new Scanner(in);
					if(!(ins.hasNext()) ){
						groupMems.add("No members.");
					}
					while (ins.hasNextLine()){
						groupMems.add(ins.nextLine());
						
				} ins.close();
				
				
				} catch(FileNotFoundException ex){ 					
				}
				for(String s : groupMems){
					gmlist.addElement(s);										
				}
				list_3.setModel(gmlist);
			}
		});
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				powerShell pshell = new powerShell();
				
				try{
					String user = (String) list_2.getSelectedValue();
					//pshell.unlockUsers(user);
					String user2 = user;
					String command = "powershell.exe Import-Module ActiveDirectory; Unlock-AdAccount " + user2;
					//command 1 may no longer be needed.
					//String command1 = String.format(command, user);
					
					//Running powershell and running the commands / redirecting the input to a text file.
					Process pshell1 = Runtime.getRuntime().exec(command);
					pshell1.getOutputStream().close();
				} catch(IOException ex){
					
				}
			}
		});
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ulist.clear();
				powerShell pshell = new powerShell();
				ArrayList<String> users = new ArrayList<String>();
				try{
					pshell.enabledUsers();
				} catch(IOException ex){					
				}
				File in = new File("C:/ProgramData/Guardian Angel/EnabledUsers.txt");
				try{
					Scanner ins = new Scanner(in);
					
					while (ins.hasNextLine()){
						users.add(ins.nextLine());
				} ins.close();
				
				
				} catch(FileNotFoundException ex){ 					
				}
				for(String s : users){
					ulist.addElement(s);										
				}
				list.setModel(ulist);
				
			}
		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dulist.clear();
				powerShell pshell = new powerShell();
				ArrayList<String> users = new ArrayList<String>();
				try{
					pshell.enabledUsers();
				} catch(IOException ex){					
				}
				File in = new File("C:/ProgramData/Guardian Angel/DisabledUsers.txt");
				try{
					Scanner ins = new Scanner(in);
					
					while (ins.hasNextLine()){
						users.add(ins.nextLine());
				} ins.close();
				
				
				} catch(FileNotFoundException ex){ 					
				}
				for(String s : users){
					dulist.addElement(s);										
				}
				list.setModel(dulist);
				
			}
		});
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		//Displays all groups from startup
		
		powerShell pshell = new powerShell();
	
		ArrayList<String> groups = new ArrayList<String>();
		try{
			pshell.getGroups();
		} 	catch(IOException ex){					
	}
		File in = new File("C:/ProgramData/Guardian Angel/ADGroups.txt");
		try{
		Scanner ins = new Scanner(in);
		
		while (ins.hasNextLine()){
			groups.add(ins.nextLine());
		} ins.close();
	
		
		} catch(FileNotFoundException ex){ 					
		}
		for(String s : groups){
		glist.addElement(s);										
		}
		list_1.setModel(glist);
		
		}
		});
		
		
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}

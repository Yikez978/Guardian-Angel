package guardianangel.chasinghellsing.com;

import java.awt.Button;
import java.awt.EventQueue;
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
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class GAGUI {
	

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			
			
			public void run() {
				try {
					GAGUI window = new GAGUI();
					window.frame.setVisible(true);
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
		frame = new JFrame();
		frame.setBounds(100, 100, 518, 398);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		DefaultListModel<String> ulist = new DefaultListModel<String>();
		
		DefaultListModel<String> lulist = new DefaultListModel<String>();
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setToolTipText("Unlock, Enable, and Disable User accounts.");
		tabbedPane.setBounds(0, 25, 510, 346);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("User Accounts", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(0, 5, 276, 132);
		panel.add(scrollPane);
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(360, 56, 135, 218);
		panel.add(scrollPane_1);
		
		JList list_2 = new JList();
		scrollPane_1.setViewportView(list_2);
		Button button_1 = new Button("Enabled Users");
		button_1.setBounds(285, 5, 91, 22);
		panel.add(button_1);
		
		
		
		Button button_3 = new Button("Unlock");
		button_3.setBounds(403, 275, 46, 22);
		panel.add(button_3);
		
		Button btnEnableAccount = new Button("Enable Account");
		btnEnableAccount.setBounds(13, 142, 109, 23);
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
		btnDisableAcccount.setBounds(132, 142, 115, 23);
		panel.add(btnDisableAcccount);
		
		Button but = new Button("Locked Users");
		but.setBounds(261, 251, 99, 23);
		panel.add(but);
		
		Button button = new Button("Disabled Users");
		button.setBounds(285, 32, 91, 22);
		panel.add(button);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 510, 21);
		frame.getContentPane().add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmClose = new JMenuItem("Close");
		mnFile.add(mntmClose);
		but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Runnable thread = new Threading(button_2);
				new Thread(thread).start();
								
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
		
		
		
		
		
		
		
	}
}

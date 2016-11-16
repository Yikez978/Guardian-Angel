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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setSize(139, 273);
		scrollPane.setLocation(303, 0);
		JList list = new JList();
		list.setBounds(303, 0, 139, 273);
		frame.getContentPane().add(scrollPane);
		scrollPane.setViewportView(list);
		
		JList list_2 = new JList();
		list_2.setBounds(0, 84, 97, 189);
		frame.getContentPane().add(list_2);
		
		Button button = new Button("Disabled Users");
		button.setBounds(0, 0, 119, 22);
		frame.getContentPane().add(button);
		
		DefaultListModel<String> ulist = new DefaultListModel<String>();
		Button button_1 = new Button("Enabled Users");
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
		button_1.setBounds(0, 28, 119, 22);
		frame.getContentPane().add(button_1);
		
		DefaultListModel<String> lulist = new DefaultListModel<String>();
		JButton button_2 = new JButton("Lockedout Users");
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
		button_2.setBounds(0, 56, 119, 22);
		frame.getContentPane().add(button_2);
		
		
		
		Button button_3 = new Button("Unlock");
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
		button_3.setBounds(103, 222, 70, 22);
		frame.getContentPane().add(button_3);
		
		JButton btnEnableAccount = new JButton("Enable Account");
		btnEnableAccount.setBounds(174, 81, 119, 23);
		frame.getContentPane().add(btnEnableAccount);
		
		JButton btnDisableAcccount = new JButton("Disable Acccount");
		btnDisableAcccount.setBounds(174, 115, 118, 22);
		frame.getContentPane().add(btnDisableAcccount);
		
		JButton but = new JButton("Lockout Feed");
		but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Runnable thread = new Threading(button_2);
				new Thread(thread).start();
								
			}
				
		});
		but.setBounds(101, 250, 112, 23);
		frame.getContentPane().add(but);
		
		
		
		
		
	}
}

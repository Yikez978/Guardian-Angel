package guardianangel.chasinghellsing.com;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.DefaultListModel;

public class powerShell {
	public powerShell(){
		
	}
	public void lockedUsers()throws IOException{
		//Creating the file that contains the list of locked out users and 
		//creating a variable to run the powershell command to get these users from AD.
		File output = new File("C:/ProgramData/Guardian Angel/junklockedusers.txt");
		File output2 = new File("C:/ProgramData/Guardian Angel/lockedusers.txt");
		output.getParentFile().mkdir();
		output.createNewFile();
		output2.getParentFile().mkdir();
		output2.createNewFile();
		PrintWriter out = new PrintWriter(output);
		PrintWriter out2 = new PrintWriter(output2);
		String command = "powershell.exe Import-Module ActiveDirectory; Search-ADAccount -lockedout | ft samaccountname";
		String line;
		//Running powershell and running the commands / redirecting the input to a text file.
		Process pshell = Runtime.getRuntime().exec(command);
		pshell.getOutputStream().close();
		BufferedReader usersOut = new BufferedReader(new InputStreamReader(pshell.getInputStream()));
		while((line = usersOut.readLine()) != null){
			out.append(line);
			
		}
		out.close();
		
		Textfixer fix = new Textfixer();
		
		fix.removeSpacing(output,  out2);
		
		
	}
	//Gathers and sorts all enabled User accounts alphabetically.
	public void enabledUsers()throws IOException{
		File output = new File("C:/ProgramData/Guardian Angel/junkenabledusers.txt");
		File output2 = new File("C:/ProgramData/Guardian Angel/EnabledUsers.txt");
		output.getParentFile().mkdir();
		output.createNewFile();
		output2.getParentFile().mkdir();
		output2.createNewFile();
		PrintWriter out = new PrintWriter(output);
		PrintWriter out2 = new PrintWriter(output2);
		String command = "powershell.exe Import-Module ActiveDirectory; Get-ADUser -filter {enabled -eq $true} | ft samaccountname";
		String line;
		//Running powershell and running the commands / redirecting the input to a text file.
		Process pshell1 = Runtime.getRuntime().exec(command);
		pshell1.getOutputStream().close();
		BufferedReader usersOut = new BufferedReader(new InputStreamReader(pshell1.getInputStream()));
		while((line = usersOut.readLine()) != null){
			out.append(line);			
		}
		out.close();
		Textfixer fix = new Textfixer();
		//Importing data from the junk file and exporting to the Enabled
		//user file.
		fix.removeSpacing(output,  out2);
		
		
		
		
		
	}
	public void disabledUsers()throws IOException{
		File output = new File("C:/ProgramData/Guardian Angel/junkdisabledusers.txt");
		File output2 = new File("C:/ProgramData/Guardian Angel/DisabledUsers.txt");
		output.getParentFile().mkdir();
		output.createNewFile();
		output2.getParentFile().mkdir();
		output2.createNewFile();
		PrintWriter out = new PrintWriter(output);
		PrintWriter out2 = new PrintWriter(output2);
		String command = "powershell.exe Import-Module ActiveDirectory; Search-ADAccount -AccountDisabled | ft samaccountname";
		String line;
		//Running powershell and running the commands / redirecting the input to a text file.
		Process pshell1 = Runtime.getRuntime().exec(command);
		pshell1.getOutputStream().close();
		BufferedReader usersOut = new BufferedReader(new InputStreamReader(pshell1.getInputStream()));
		while((line = usersOut.readLine()) != null){
			out.append(line);			
		}
		out.close();
		Textfixer fix = new Textfixer();
		
		fix.removeSpacing(output,  out2);
		
		
		;
	}
	//Keeps lockedout users continually refreshing
	public void unlockUsers()throws IOException{
		DefaultListModel<String> lulist = new DefaultListModel<String>();
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
		
	}

}

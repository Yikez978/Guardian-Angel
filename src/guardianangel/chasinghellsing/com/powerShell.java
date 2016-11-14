package guardianangel.chasinghellsing.com;

import java.io.File;
import java.io.PrintWriter;

public class powerShell {
	public powerShell(){
		
	}
	public void lockedUsers(){
		File output = new File("C:/ProgramData/Guardian Angel/lockedusers.txt");
		PrintWriter out = new PrintWriter(output);
		String command = "powershell.exe, Import-Module ActiveDirectory; Search-ADAccount -lockedout | ft samaccountname";
	}

}

package guardianangel.chasinghellsing.com;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class powerShell {
	public powerShell(){
		
	}
	public void lockedUsers()throws IOException{
		File output = new File("C:/ProgramData/Guardian Angel/lockedusers.txt");
		output.getParentFile().mkdir();
		output.createNewFile();
		PrintWriter out = new PrintWriter(output);
		String command = "powershell.exe, Import-Module ActiveDirectory; Search-ADAccount -lockedout | ft samaccountname";
	}

}

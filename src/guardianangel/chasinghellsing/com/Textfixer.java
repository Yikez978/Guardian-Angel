package guardianangel.chasinghellsing.com;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

//Class that will be used to reformat the powershell output stored in the text files
//in order to create usable info.
public class Textfixer {
	public Textfixer(){
	}

	public void removeSpacing(File in, PrintWriter out)throws IOException{
		Scanner file = new Scanner(in);
		ArrayList<String> names = new ArrayList<String>();
		
		while (file.hasNext()){
			names.add(file.next());			
		}
		names.sort(String::compareToIgnoreCase);
		for(Iterator<String> it = names.listIterator(); it.hasNext();){
			String removeSam = it.next();
			if(removeSam.equals("samaccountname")){
				it.remove();
			}
			}
		
		for(String s : names){
			out.println(s);
		}
		file.close();
		out.close();
	}
	 

}

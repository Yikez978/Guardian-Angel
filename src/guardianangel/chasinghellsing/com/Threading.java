package guardianangel.chasinghellsing.com;

import javax.swing.JButton;

public class Threading implements Runnable {
	JButton button_2;
	public Threading(JButton button_2){
		this.button_2 = button_2;
		
	}
	public void run(){
		while(true){
			button_2.doClick();
			
			try{
				Thread.sleep(15000);
			} catch(InterruptedException ex){
			
			}
		}
	}

}

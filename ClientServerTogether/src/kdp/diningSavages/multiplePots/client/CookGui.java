package kdp.diningSavages.multiplePots.client;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import kdp.diningSavages.multiplePots.bothSide.RmiPot;

@SuppressWarnings("serial")
public class CookGui extends JFrame{
	
	JButton button;
	JTextArea text;
	
	public CookGui(RmiPot pot) {
		super("COOK");
		
		button = new JButton("Cook");
		text = new JTextArea();
		text.setEditable(false);
		
		button.addActionListener( 
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						button.setEnabled(false);
						
						Thread t = new Thread() {
							
							@Override
							public void run() {
								try {
									int foodType = pot.cook();
								
									SwingUtilities.invokeLater(
											new Runnable() {
												
												@Override
												public void run() {
													text.setText(String.valueOf(foodType));
													button.setEnabled(true);
												}
											});
								} catch (RemoteException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}							
						};
						
						t.start();
					}
				});
		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(200, 200, 300, 300);
		this.setLayout(new GridLayout(2, 1));
		this.add(button);
		this.add(text);
	}

}

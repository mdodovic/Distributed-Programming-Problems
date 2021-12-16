package kdp.diningSavages.onePot.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import kdp.diningSavages.onePot.bothSide.RemotePot;

@SuppressWarnings("serial")
public class CookGui extends JFrame{

	JButton button;
	
	public CookGui(RemotePot pot) {
		super("Cook");
		button = new JButton("Cook");
		
		button.addActionListener(
				new ActionListener() {					
					@Override
					public void actionPerformed(ActionEvent e) {
						button.setEnabled(false);
						
						Thread t = new Thread() {

							@Override
							public void run() {
								
								try {
									pot.cook();
								} catch (RemoteException e) {
									e.printStackTrace();
								}
								SwingUtilities.invokeLater(
										new Runnable() {
											
											@Override
											public void run() {
												button.setEnabled(true);
											}
										});
							}
						};
						t.start();
					}
				}
		);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(200, 200, 300, 300);
		this.add(button);
	}

}

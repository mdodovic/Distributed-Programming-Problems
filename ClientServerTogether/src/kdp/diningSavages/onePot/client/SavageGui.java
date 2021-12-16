package kdp.diningSavages.onePot.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import kdp.diningSavages.onePot.bothSide.NetPotI;

@SuppressWarnings("serial")
public class SavageGui extends JFrame{

	JButton button;
	
	public SavageGui(NetPotI pot, int id) {
		super("Savage " + id);
		
		button = new JButton("Savage " + id);
		
		button.addActionListener(
				new ActionListener() {					
					@Override
					public void actionPerformed(ActionEvent e) {
						button.setEnabled(false);
						
						Thread t = new Thread() {

							@Override
							public void run() {
								
								pot.eat();
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
		this.setBounds(600, 200, 300, 300);
		this.add(button);

	}

}

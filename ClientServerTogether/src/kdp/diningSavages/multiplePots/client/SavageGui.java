package kdp.diningSavages.multiplePots.client;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import kdp.diningSavages.multiplePots.bothSide.NetPot;

@SuppressWarnings("serial")
public class SavageGui extends JFrame{

	JButton button;
	JTextArea text;
	
	public SavageGui(NetPot pot, int i) {
		super("SAVAGE " + i);
		
		button = new JButton("Eat");
		text = new JTextArea();
		
		button.addActionListener( 
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						button.setEnabled(false);
						
						Thread t = new Thread() {
							
							@Override
							public void run() {
								String s = text.getText();
								int foodType;
								if("".equals(s)) {
									foodType = -1;
								} else {
									foodType = Integer.parseInt(s);
								}
								pot.eat(foodType);
								SwingUtilities.invokeLater(
										new Runnable() {
											
											@Override
											public void run() {
												text.setText("");
												button.setEnabled(true);
											}
										});
								
							}							
						};
						
						t.start();
					}
				});
		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(600, 200, 300, 300);
		this.setLayout(new GridLayout(2, 1));
		this.add(text);
		this.add(button);
	}

}

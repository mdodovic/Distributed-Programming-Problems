package kdp.diningSavages.multiplePots.concurrent;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class CookGui extends JFrame{
	
	JButton button;
	JTextArea text;
	
	public CookGui(Pot pot) {
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
								int foodType = pot.cook();
								
								SwingUtilities.invokeLater(
										new Runnable() {
											
											@Override
											public void run() {
												text.setText(String.valueOf(foodType));
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
		this.setBounds(200, 200, 300, 300);
		this.setLayout(new GridLayout(2, 1));
		this.add(button);
		this.add(text);
	}

}

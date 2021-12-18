package diningSavages.sockets;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import diningSavages.sockets.distributed.DiningSavages;

@SuppressWarnings("serial")
public class Cook extends JFrame {

	
	JButton button;
	JTextArea text;
	
	public Cook(DiningSavages pot) {		
		super("COOK");
		
		button = new JButton("Cook");
		text = new JTextArea();
		text.setEnabled(false);
		button.addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						button.setEnabled(false);
						
						Thread t = new Thread() {
							@Override
							public void run() {
								int type = pot.cook();

								SwingUtilities.invokeLater(
										new Runnable() {
											
											@Override
											public void run() {
												text.setText(String.valueOf(type));
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
		this.setBounds(600, 300, 300, 300);
		this.setLayout(new GridLayout(2, 1));
		this.add(button);		
		this.add(text);
	}	

}

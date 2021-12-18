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
public class Savage extends JFrame{

	JButton button;
	JTextArea text;
	
	public Savage(DiningSavages pot, int i) {
		
		super("Savage " + i);
		
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
								if(s.equals("")) {
									SwingUtilities.invokeLater(
											new Runnable() {
												
												@Override
												public void run() {
													text.setText("");
													button.setEnabled(true);
												}
											});									
									return;
								}
								int type = Integer.parseInt(s);
								if(type < 0 || type >= SavageMain.NUM_FOOD_TYPES) {
									SwingUtilities.invokeLater(
											new Runnable() {
												
												@Override
												public void run() {
													text.setText("");
													button.setEnabled(true);
												}
											});									
									return;
								}
								pot.eat(type);	
								
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
		this.setBounds(300, 300, 300, 300);
		this.setLayout(new GridLayout(2, 1));
		this.add(text);
		this.add(button);		
	}

}

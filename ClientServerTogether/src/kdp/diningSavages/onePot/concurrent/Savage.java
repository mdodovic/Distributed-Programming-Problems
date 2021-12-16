package kdp.diningSavages.onePot.concurrent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class Savage extends JFrame{

	JButton button;
	JTextArea textArea;
	
	
	public Savage(Pot pot, int id) {
		super("Savage " + id);
		button = new JButton("Savage " + id + " eat");

		button.addActionListener(new ActionListener() {
			
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
		});
		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//		this.setLayout(new GridLayout(2, 1));
		this.setBounds(600, 200, 300, 300);
		this.add(button);

		
	}
	
	

}

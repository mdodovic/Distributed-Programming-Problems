package kdp.diningSavages.onePot.concurrent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class Cook extends JFrame{

	JButton button;

	
	public Cook(Pot pot) {
		super("Cook");
		button = new JButton("Cook");
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				button.setEnabled(false);

				Thread t = new Thread() {
					@Override
					public void run() {
						pot.cook();
						
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
		this.setBounds(200, 200, 300, 300);
		this.add(button);

	}

}

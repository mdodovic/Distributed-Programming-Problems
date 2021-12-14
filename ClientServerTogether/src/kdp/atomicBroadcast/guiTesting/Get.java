package kdp.atomicBroadcast.guiTesting;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import kdp.atomicBroadcast.concurrentPart.AtomicBroadcastBuffer;
import kdp.atomicBroadcast.message.Message;

@SuppressWarnings("serial")
public class Get extends JFrame{

	JButton button;
	JTextArea textArea;
	
	public Get(AtomicBroadcastBuffer<Message<String>> buffer, int id) {
		super("Get"); // Frame name
		
		button = new JButton("Get");
		textArea = new JTextArea();
		
		textArea.setEditable(false); // Consumers can only receive messages
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				button.setEnabled(false);
				
				Thread t = new Thread() {
					
					@Override
					public void run() {
						Message<String> msg = buffer.get(id); // get from buffer
											
						SwingUtilities.invokeLater(new Runnable() {
							
							@Override
							public void run() {
								textArea.setText(msg.getBody()); // write text on text area
								button.setEnabled(true); // enable button for another getting
							}

						});

					}
				};
				t.start();
			}
		});
		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(2, 1));
		this.setBounds(600, 200, 300, 300);
		this.add(button);
		this.add(textArea);

	}

}

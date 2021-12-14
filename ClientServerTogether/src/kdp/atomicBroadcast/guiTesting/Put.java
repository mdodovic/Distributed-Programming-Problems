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
import kdp.atomicBroadcast.message.TextMessage;

@SuppressWarnings("serial")
public class Put extends JFrame{

	JButton button;
	JTextArea textArea;

	public Put(AtomicBroadcastBuffer<Message<String>> buffer) {
		super("Put");
		
		button = new JButton("Put");
		textArea = new JTextArea();
		
		button.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String text = textArea.getText();
				button.setEnabled(false);
				
				Thread t = new Thread() {
					
					@Override
					public void run() {
						Message<String> msg = new TextMessage();
						msg.setBody(text);
						buffer.put(msg);
						
						SwingUtilities.invokeLater(new Runnable() {
							
							@Override
							public void run() {
								textArea.setText("");
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
		this.setLayout(new GridLayout(2, 1));
		this.setBounds(200, 200, 300, 300);
		this.add(textArea);
		this.add(button);
		
	}
	
}

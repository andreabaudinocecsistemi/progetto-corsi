package it.ruffini.progettoCorsi;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.function.Consumer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class LoginFrame extends JFrame {
	
	private final JTextField username;
	private final JTextField password;
	
	public LoginFrame(Consumer<Boolean> callback) {
		
		
		setTitle("Login");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		
		JLabel labelUsername = new JLabel("Utente");
		username = new JTextField(50);
		username.setMaximumSize(new Dimension(150, 25));
		JLabel labelPassword = new JLabel("Password");
		password = new JTextField(50);
		password.setMaximumSize(new Dimension(150, 25));
		JButton confirmButton = new JButton("Login");
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(labelUsername);
		panel.add(new JScrollPane(username));
		panel.add(labelPassword);
		panel.add(new JScrollPane(password));
		panel.add(confirmButton);
		add(panel, BorderLayout.CENTER);
		
		confirmButton.addActionListener(click -> {
			
			String user = username.getText().trim();
			String pw = password.getText().trim();
			
			//se nessuno dei due è vuoto, allora controlliamo se l'utente esiste. Se anche solo uno dei due campi è vuoto, messaggio di errore
			if (!user.isEmpty() && !pw.isEmpty()) {
				boolean risultato = ConnessioneDB.checkUtente(user, pw);
				if (risultato == true) {
					callback.accept(risultato); //Se la select ha trovato un risultato, allora torno indietro con un valore true
					dispose();
				} else {
					//se l'utente non è stato trovato
					JOptionPane.showMessageDialog(this, "Utente non trovato");
				}
			} else {
				//se i campi utente o password non sono valorizzati
				JOptionPane.showMessageDialog(this, "Entrambi i campi devono essere valorizzati");
			}
		});
	}

}

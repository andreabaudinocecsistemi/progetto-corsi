package it.ruffini.progettoCorsi;

import java.awt.BorderLayout;
import java.util.function.Consumer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FrameRegistrazione extends JFrame {

	public FrameRegistrazione(Consumer<RisultatoRegistrazione> callback) {
		
		setTitle("Inserisci utente");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		
		JLabel labelUsername = new JLabel("Nome utente");
		JTextField username = new JTextField(50);
		JLabel labelPassword = new JLabel("Password");
		JTextField password = new JTextField(50);
		JLabel labelEmail = new JLabel("Email");
		JTextField email = new JTextField(50);
		JButton confirmButton = new JButton("Registrati");
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(labelUsername);
		panel.add(username);
		panel.add(labelPassword);
		panel.add(password);
		panel.add(labelEmail);
		panel.add(email);
		panel.add(confirmButton);
		add(panel, BorderLayout.CENTER);
		
		confirmButton.addActionListener(click -> {
			
			String user = username.getText().trim();
			String pw = password.getText().trim();
			String mail = email.getText().trim();
			
			if(!user.isEmpty() && !pw.isEmpty() && !mail.isEmpty() && mail.contains("@")) {
				boolean result = ConnessioneDB.registraUtente(user, pw, mail);
				
				RisultatoRegistrazione risultatoRegistrazione = new RisultatoRegistrazione();
				risultatoRegistrazione.setEsitoLogin(result);
				risultatoRegistrazione.setUsername(user);
				risultatoRegistrazione.setPassword(pw);
				if (result == true) {
					callback.accept(risultatoRegistrazione);
					dispose();
				} else {
					JOptionPane.showMessageDialog(this, "La registrazione non è andata a buon fine. Riprova!");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Per registrarti sono obbligatori tutti i campi proposti o la mail non è in formato corretto.");
			}
		});
	}
}

package it.ruffini.progettoCorsi;

import java.awt.Dimension;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FrameBase extends JFrame {
	
	boolean isLoggato = false;
	
	public FrameBase() {
		//inizializzo il componente che sto estendendo
		super();
	}

	public void start() {
		//creo il pannello di base e gli do un layout standard con layout verticale
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JLabel labelCitta = new JLabel("Città di nascita");
		JTextField cittaNascita = new JTextField(50);
		cittaNascita.setMaximumSize(new Dimension(150, 25));
		JLabel labelCodiceFiscale = new JLabel("Codice fiscale");
		JTextField codiceFiscale = new JTextField(20);
		codiceFiscale.setMaximumSize(new Dimension(150, 25));
		JLabel labelNome = new JLabel("Nome");
		JTextField nome = new JTextField(20);
		nome.setMaximumSize(new Dimension(150, 25));
		JLabel labelCognome = new JLabel("Cognome");
		JTextField cognome = new JTextField(20);
		cognome.setMaximumSize(new Dimension(150, 25));
		JLabel labelEmail = new JLabel("Email");
		JTextField email = new JTextField(50);
		email.setMaximumSize(new Dimension(150, 25));
		JLabel labelTelefono = new JLabel("Telefono");
		JTextField numeroTelefono = new JTextField(10);
		numeroTelefono.setMaximumSize(new Dimension(150, 25));
		JButton bottoneLogin = new JButton("Effettua il login");
		JButton inserisciAnagrafica = new JButton("Inserisci anagrafica");
		JButton bottoneRegistrazione = new JButton("Nuovo utente");
		JButton registrazioneCorsi = new JButton("Registra persona a un corso");
		JButton disconnetti = new JButton("Disconnetti");
		
		panel.add(labelCitta);
		panel.add(cittaNascita);
		panel.add(labelCodiceFiscale);
		panel.add(codiceFiscale);
		panel.add(labelNome);
		panel.add(nome);
		panel.add(labelCognome);
		panel.add(cognome);
		panel.add(labelEmail);
		panel.add(email);
		panel.add(labelTelefono);
		panel.add(numeroTelefono);
		panel.add(bottoneLogin);
		panel.add(disconnetti);
		panel.add(inserisciAnagrafica);
		panel.add(bottoneRegistrazione);
		panel.add(registrazioneCorsi);
		
		//definisco l'azione di chiusura del pannello e gli do le dimensioni base
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 500);
		this.add(panel);
		
		bottoneLogin.addActionListener(click -> {
			LoginFrame loginFrame = new LoginFrame(risultatoLogin -> {
				if (risultatoLogin == true) {
					JOptionPane.showMessageDialog(this, "Loggato correttamente");
					isLoggato = true;
				}
			});
			loginFrame.setVisible(true);
		});
		
		disconnetti.addActionListener(click -> {
			if (isLoggato == true) {
				isLoggato = false;
				JOptionPane.showMessageDialog(this, "Logout effettuato correttamente");
			} else {
				JOptionPane.showMessageDialog(this, "Devi prima effettuare il login");
			}
		});
		
		inserisciAnagrafica.addActionListener(click -> {
			String citta = cittaNascita.getText().trim();
			String cf = codiceFiscale.getText().trim();
			String nominativo = nome.getText().trim();
			String cogn = cognome.getText().trim();
			String mail = email.getText().trim();
			String tel = numeroTelefono.getText().trim();
			if (isLoggato == false) {
				JOptionPane.showMessageDialog(this,"Per poter inserire un' anagrafica devi essere loggato.");
			} else if(citta.isEmpty() || cf.isEmpty() || nominativo.isEmpty() || cogn.isEmpty() || mail.isEmpty() || tel.isEmpty()) {
				JOptionPane.showMessageDialog(this,"Inserisci tutti i dati nei campi!!");
			} else {
				ConnessioneDB.insertAnagrafica(citta, cf, nominativo, cogn, mail, tel);
			}
			
		});
		
		bottoneRegistrazione.addActionListener(click -> {
			FrameRegistrazione frameRegistrazione = new FrameRegistrazione(risultatoRegistrazione -> {
				if (risultatoRegistrazione.isEsitoLogin() == true) {
					String utente = risultatoRegistrazione.getUsername();
					String password = risultatoRegistrazione.getPassword();
					int sceltaLogin = JOptionPane.showConfirmDialog(this, "Vuoi loggarti automaticamente?", "Registrazione completata", JOptionPane.YES_NO_OPTION);
					if (!password.isEmpty() && !utente.isEmpty() && sceltaLogin == JOptionPane.YES_OPTION) {
						boolean verificaLogin = ConnessioneDB.checkUtente(utente, password);
						if (verificaLogin == true) {
							isLoggato = true;
							JOptionPane.showMessageDialog(this,"Benvenuto " + utente);
						}
					}
				}
			});
			frameRegistrazione.setVisible(true);
		});
		
		registrazioneCorsi.addActionListener(click -> {
			FrameRegistrazioneCorsi frame = new FrameRegistrazioneCorsi();
			frame.setVisible(true);
		});
	}
}

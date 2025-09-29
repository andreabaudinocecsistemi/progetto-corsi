package it.ruffini.progettoCorsi;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FrameRegistrazioneCorsi extends JFrame {
	
	public FrameRegistrazioneCorsi() {
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		setTitle("Inserisci una partecipazione");
		setSize(400,300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		
		JLabel labelCorsi = new JLabel("Corso selezionato");
		List<Corsi> opzioni = ConnessioneDB.getTitoloCorsi();
		
		JComboBox<Corsi> comboCorsi = new JComboBox<>();
		comboCorsi.setMaximumSize(new Dimension(250,25));
		
		if (opzioni.isEmpty()) {
			Corsi corsoErrore = new Corsi();
			corsoErrore.setTitolo("Nessun corso trovato");
			comboCorsi.addItem(corsoErrore);
		} else {
			for (Corsi titolo : opzioni) {
				comboCorsi.addItem(titolo);
			}
		}
		
		JLabel labelAnagrafiche = new JLabel("Anagrafica selezionata");
		List<Anagrafiche> opzioniAnagrafiche = ConnessioneDB.getAnagrafiche();
		
		JComboBox<Anagrafiche> comboAnagrafiche = new JComboBox<>();
		comboAnagrafiche.setMaximumSize(new Dimension(250,25));
		
		if (opzioniAnagrafiche.isEmpty()) {
			Anagrafiche erroreAnagrafiche = new Anagrafiche();
			erroreAnagrafiche.setNome("Nessuna persona trovata");
			comboAnagrafiche.addItem(erroreAnagrafiche);
		} else {
			for (Anagrafiche nominativo : opzioniAnagrafiche ) {
				comboAnagrafiche.addItem(nominativo);
			}
		}
		
		JLabel labelStatiPartecipazione = new JLabel("Stato partecipazione");
		List<StatiPartecipazione> opzioniStatiPartecipazione = ConnessioneDB.getStatiPartecipazione();
		
		JComboBox<StatiPartecipazione> comboStatiPartecipazione = new JComboBox<>();
		comboStatiPartecipazione.setMaximumSize(new Dimension(150,25));
		
		if (opzioniStatiPartecipazione.isEmpty()) {
			StatiPartecipazione statoErrore = new StatiPartecipazione();
			statoErrore.setDescrizione("Nessuno stato di partecipazione trovato");
			comboStatiPartecipazione.addItem(statoErrore);
		} else {
			for (StatiPartecipazione stato : opzioniStatiPartecipazione) {
				comboStatiPartecipazione.addItem(stato);
			}
		}
		
		JButton effettuaRegistrazione = new JButton("Registra al corso");
		
		panel.add(labelCorsi);
		panel.add(comboCorsi);
		panel.add(labelAnagrafiche);
		panel.add(comboAnagrafiche);
		panel.add(labelStatiPartecipazione);
		panel.add(comboStatiPartecipazione);
		panel.add(effettuaRegistrazione);
		add(panel, BorderLayout.CENTER);
		
		effettuaRegistrazione.addActionListener(click -> {
			Corsi corsoSelezionato = (Corsi)comboCorsi.getSelectedItem();
			Anagrafiche anagraficaSelezionata = (Anagrafiche)comboAnagrafiche.getSelectedItem();
			StatiPartecipazione statoSelezionato = (StatiPartecipazione)comboStatiPartecipazione.getSelectedItem();
			
			Integer idCorso = corsoSelezionato.getId();
			Integer idAnagrafica = anagraficaSelezionata.getId();
			Integer idStato = statoSelezionato.getId();
		});
	}

}

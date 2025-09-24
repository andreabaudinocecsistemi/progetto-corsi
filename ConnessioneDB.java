package it.ruffini.progettoCorsi;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConnessioneDB {

	private static final String URL = "jdbc:mysql://localhost:3306/corsi_db";
	private static final String USER = "root";
	private static final String PASSWORD = "";
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL,USER,PASSWORD);
	}
	
	public static List<Corsi> getTitoloCorsi() {
		List<Corsi> result = new ArrayList<>();
		
		String query = "Select * from corsi";
		
		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				Corsi corso = new Corsi();
				corso.setId(rs.getInt("id"));
				corso.setTitolo(rs.getString("titolo"));
				corso.setSede(rs.getString("sede"));
				result.add(corso);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static List<String> getAnagrafiche() {
		
		List<String> result = new ArrayList<>();
		
		String query = "SELECT * FROM anagrafiche";
		
		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				result.add(rs.getString("cognome") + rs.getString("nome") + rs.getString("codice_fiscale"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static List<StatiPartecipazione> getStatiPartecipazione() {
		
		List<StatiPartecipazione> result = new ArrayList<>();
		
		String query = "SELECT * FROM stati_partecipazione";
		
		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				StatiPartecipazione stato = new StatiPartecipazione();
				stato.setId(rs.getInt("id"));
				stato.setDescrizione(rs.getString("descrizione"));
				result.add(stato);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static void insertAnagrafica(String citta, String codiceFiscale, String nome, String cognome, String email, String telefono) {
		
		String query = "INSERT INTO `anagrafiche`(`cognome`, `nome`, `codice_fiscale`, `data_nascita`, `email`, `numero_telefono`, `città_nascita`) VALUES "
				+ "(?,?,?,?,?,?,?)";
		try {
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1,cognome);
			stmt.setString(2, nome);
			stmt.setString(3, codiceFiscale);
			stmt.setDate(4, Date.valueOf("1990-05-08"));
			stmt.setString(5, email);
			stmt.setInt(6, Integer.valueOf(telefono));
			stmt.setString(7, citta);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean checkUtente(String username, String password) {
		
		String query = "SELECT * FROM utenti WHERE utente = ? and password = ?";
		
		try {
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean registraUtente(String username, String password, String email) {
		
		String query = "INSERT INTO `utenti`(`utente`, `password`, `email`) VALUES (?,?,?)";
		
		try {
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setString(3, email);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}

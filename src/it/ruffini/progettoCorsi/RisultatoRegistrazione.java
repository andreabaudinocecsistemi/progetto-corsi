package it.ruffini.progettoCorsi;

public class RisultatoRegistrazione {

	private boolean esitoLogin;
	private String username;
	private String password;
	
	public boolean isEsitoLogin() {
		return esitoLogin;
	}
	public void setEsitoLogin(boolean esitoLogin) {
		this.esitoLogin = esitoLogin;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}

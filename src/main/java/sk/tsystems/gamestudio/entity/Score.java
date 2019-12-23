package sk.tsystems.gamestudio.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Score {
	private String username;
	private String game;
	private int value;
	@Id
	@GeneratedValue
	private int ident;

	public int getIdent() {
		return ident;
	}

	@Override
	public String toString() {
		return "Score [username=" + username + ", game=" + game + ", value=" + value + ", ident=" + ident + "]";
	}

	public void setIdent(int ident) {
		this.ident = ident;
	}

	public Score() {

	}

	public Score(String username, String game, int value) {
		super();
		this.username = username;
		this.game = game;
		this.value = value;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;

	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}

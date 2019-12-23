package sk.tsystems.gamestudio.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Comment {
@Id
@GeneratedValue
private int ident;
private String username;
private String game;
private String content;
public Comment() {
	
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
@Override
public String toString() {
	return "Comment [username=" + username + ", game=" + game + ", value=" + content + "]";
}

public Comment(String username, String game, String content) {
 
	this.username = username;
	this.game = game;
	this.content = content;
}
public int getIdent() {
	return ident;
}
public void setIdent(int ident) {
	this.ident = ident;
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
}

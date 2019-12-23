package sk.tsystems.gamestudio.service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sk.tsystems.gamestudio.entity.Comment;
import sk.tsystems.gamestudio.entity.Score;

/*
CREATE TABLE comment (username VARCHAR(64) NOT NULL,game VARCHAR(64) NOT NULL, content VARCHAR(64) NOT NULL)
*/

public class CommentServiceJDBC implements CommentService {
	private static final String URL = "jdbc:postgresql://localhost/gamestudio";
	private static final String LOGIN = "postgres";
	private static final String PASSWORD = "jahodka";
	private static final String INSERT = "INSERT INTO comment (username,game,value) VALUES (?,?,?)";
	private static final String SELECT = "SELECT * from comment WHERE game = ? ORDER BY value DESC LIMIT 10 ; ";

	@Override
	public void addComment(Comment comment) {
		try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(INSERT)) {
			ps.setString(1, comment.getUsername());
			ps.setString(2, comment.getGame());
			ps.setString(3, comment.getContent());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new GameStudioException(e);
		}
	}

	@Override
	public List<Comment> getTopComments(String game) {
		// TODO Auto-generated method stub
		try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
		PreparedStatement ps = connection.prepareStatement(SELECT)) { 
			ps.setString(1, game);
			try (ResultSet rs = ps.executeQuery()){
				List<Comment> comments = new ArrayList<Comment>();
				while(rs.next()) {
					Comment comment = new Comment(rs.getString(1),rs.getString(2),rs.getString(3));
					 comments.add(comment);
					 
					
				}
				return comments;
			}

	}catch (SQLException e) {
		throw new GameStudioException(e);
	}

	}

}

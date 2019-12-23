package sk.tsystems.gamestudio.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import sk.tsystems.gamestudio.entity.Comment;
import sk.tsystems.gamestudio.entity.Score;


@Component
@Transactional
public class CommentServiceJPA implements CommentService{
	@PersistenceContext
	private EntityManager entityManager;

 	
	 
	public void addComment(Comment comment) {
		// TODO Auto-generated method stub
		entityManager.persist(comment);
	}
	

	 

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getTopComments(String game) {
		// TODO Auto-generated method stub
		return(List<Comment>)entityManager.createQuery("select s from Comment s where s.game = :game").setParameter("game",game).getResultList();
	}
	
	
 

}
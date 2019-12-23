package sk.tsystems.gamestudio.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;


import sk.tsystems.gamestudio.entity.Player;
@Component
@Transactional
public class PlayerServiceJPA implements PlayerService{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void addPlayer(Player player) {
		entityManager.persist(player);
	}
	
 
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Player> getAllPlayer(String game) {
		// TODO Auto-generated method stub
		return(List<Player>)entityManager.createQuery("select p from Player p").getResultList();
		 
	}

	@Override
	public Player findPersonByName(String name) {
		try {
			return (Player ) entityManager.createQuery("select p from Player p where p.name = :name").setParameter("name", name).getSingleResult();
			
		}catch(Exception e) {
			return null;
		}
		
	}


	
	
 

}

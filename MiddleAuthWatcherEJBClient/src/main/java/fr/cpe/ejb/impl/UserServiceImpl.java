package fr.cpe.ejb.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import fr.cpe.ejb.UserService;
import fr.cpe.model.User;

@Stateless
public class UserServiceImpl implements UserService {
	
    @PersistenceContext
    EntityManager em;
	
    @Override
	public User getUserByLogin(String login){
    	try{
    		User user = (User) em.createQuery("from User u where u.login = :login")
				.setParameter("login", login)
				.getSingleResult();
    		return user;
    	}catch(NoResultException e){
    		return null;
    	}
	}

	@Override
	public User getUserByLoginAndPassword(String login, String password) {
		try{
    		User user = (User) em.createQuery("from User u where u.login = :login and u.password = :password")
				.setParameter("login", login)
				.setParameter("password", password)
				.getSingleResult();
    		return user;
    	}catch(NoResultException e){
    		return null;
    	}
	}
	
}

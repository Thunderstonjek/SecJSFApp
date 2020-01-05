package ch.gibm.facade;

import java.io.Serializable;

import ch.gibm.dao.EntityManagerHelper;
import ch.gibm.dao.UserDAO;
import ch.gibm.entity.User;

public class UserFacade implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private UserDAO userDAO = new UserDAO();

	public User getUserIfExists(String email, String password) {
		EntityManagerHelper.beginTransaction();
		User user = userDAO.findUserIfExists(email, password);
		EntityManagerHelper.commitAndCloseTransaction();
		return user;
	}
	
	public boolean checkIfUserExists(String email) {
		EntityManagerHelper.beginTransaction();
		boolean yes = userDAO.checkIfUserExists(email);
		EntityManagerHelper.commitAndCloseTransaction();
		return yes;
	}
}

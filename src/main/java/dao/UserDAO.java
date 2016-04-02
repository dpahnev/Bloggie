package dao;

import java.security.MessageDigest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import exceptions.InvalidUserNameException;
import model.User;

public class UserDAO implements IUserDAO {

	private static UserDAO instance = null;
	@PersistenceContext
	private EntityManager em;

	private UserDAO() {

	}

	public static UserDAO getInstance() {

		if (instance == null) {
			instance = new UserDAO();
		}
		return instance;

	}

	public void addUser(User user) throws InvalidUserNameException {
		user.setPassword(getHashedPassword(user.getPassword()));
		String txtQuery = "SELECT u FROM USERS u WHERE u.userName=:userName";
		TypedQuery<User> query = em.createQuery(txtQuery, User.class);
		query.setParameter("userName", user.getUsername());
		if (queryUser(query) == null) {
			em.persist(user);
		} else {
			throw new InvalidUserNameException("Sorry this username is taken!");
		}

	}

	public boolean validateUserCredentials(String userName, String password) {
		String txtQuery = "SELECT u FROM USERS u WHERE u.userName=:userName AND u.password=:password";
		TypedQuery<User> query = em.createQuery(txtQuery, User.class);
		query.setParameter("userName", userName);
		query.setParameter("password", getHashedPassword(password));
		return queryUser(query) != null;
	}

	public User findUserByName(String userName) {
		String txtQuery = "SELECT u FROM USERS u WHERE u.userName = :userName";
		TypedQuery<User> query = em.createQuery(txtQuery, User.class);
		query.setParameter("userName", userName);
		return queryUser(query);
	}

	private User queryUser(TypedQuery<User> query) {
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	private String getHashedPassword(String password) {
		try {
			MessageDigest mda = MessageDigest.getInstance("SHA-512");
			password = new String(mda.digest(password.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return password;
	}

}

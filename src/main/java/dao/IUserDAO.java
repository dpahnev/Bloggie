package dao;

import exceptions.InvalidUserNameException;
import model.User;

public interface IUserDAO {

	public void addUser(User user) throws InvalidUserNameException;

	public boolean validateUserCredentials(String userName, String password);

	public User findUserByName(String userName);

}

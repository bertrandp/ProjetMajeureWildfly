package fr.cpe.ejb;

import fr.cpe.model.User;

public interface UserService {

	User getUserByLogin(String login);

	User getUserByLoginAndPassword(String login, String password);

}

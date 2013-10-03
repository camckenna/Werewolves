package edu.wm.camckenna.werewolves.service;

import edu.wm.camckenna.werewolves.domain.User;

public interface IUserContext {
	User getCurrentUser();
    void setCurrentUser(User user);
}

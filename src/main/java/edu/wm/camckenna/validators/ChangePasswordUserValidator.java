package edu.wm.camckenna.validators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import edu.wm.camckenna.werewolves.dao.IUserDAO;
import edu.wm.camckenna.werewolves.domain.ChangePasswordUser;
import edu.wm.camckenna.werewolves.domain.TempUser;
import edu.wm.camckenna.werewolves.domain.User;
import edu.wm.camckenna.werewolves.exceptions.MultipleUsersWithSameIDException;
import edu.wm.camckenna.werewolves.exceptions.NoUserFoundException;
import edu.wm.camckenna.werewolves.service.UserService;

public class ChangePasswordUserValidator implements Validator{
	@Autowired private IUserDAO userDAO;
	@Autowired private BCryptPasswordEncoder passwordEncoder;
	private static final Logger logger = LoggerFactory.getLogger(ChangePasswordUser.class);
	
	@Override
	public boolean supports(Class<?> clazz) {
		return ChangePasswordUser.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "orignialPassword", "password.empty");
		ChangePasswordUser user = (ChangePasswordUser)target;
		User tUser = convertFromPrincipalNameToUser(user.getUsername());
		
		if(!passwordEncoder.matches(user.getOriginalPassword(),tUser.getUsername()))
			errors.rejectValue("originalPassword", "passwords.incorrect");
		
		if(!user.getNewPassword().equals(user.getConfirmNewPassword())){
			errors.rejectValue("confirmNewPassword", "passwords.must.match");
		}
		
	}
	private User convertFromPrincipalNameToUser(String name){
		try{
			return userDAO.getUserByUsername(name);
		}
		catch(MultipleUsersWithSameIDException e){
			logger.error("Too many users found for username: " + name);
			return null;
		} catch (NoUserFoundException e) {
			logger.error("No users found for username: " + name);
			return null;
		}
	}

}

package edu.wm.camckenna.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import edu.wm.camckenna.werewolves.dao.IUserDAO;
import edu.wm.camckenna.werewolves.domain.TempUser;

public class TempUserValidator implements Validator {

	@Autowired private IUserDAO userDAO;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return TempUser.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "email", "email.empty");
		ValidationUtils.rejectIfEmpty(errors, "username", "username.empty");
		ValidationUtils.rejectIfEmpty(errors, "firstName", "firstName.empty");
		ValidationUtils.rejectIfEmpty(errors, "lastName", "lastName.empty");
		ValidationUtils.rejectIfEmpty(errors, "password", "password.empty");
		ValidationUtils.rejectIfEmpty(errors, "confirmPassword", "confirmPassword.empty");
		TempUser user = (TempUser)target;
		try{
			userDAO.getUserByUsername(user.getUsername());
			errors.rejectValue("username", "username.already.in.use");
		}
		catch(Exception e){}
		
		if(user.getPassword() != null && user.getConfirmPassword() != null){
			if(!user.getPassword().equals(user.getConfirmPassword()))
					errors.rejectValue("confirmPassword", "passwords.must.match");
		}
		
	}

	


}

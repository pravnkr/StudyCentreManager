package com.ignoubadhega.studycentremanager.controller;

import java.util.Arrays;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ignoubadhega.studycentremanager.dto.StudyCentreUser;
import com.ignoubadhega.studycentremanager.entity.User;
import com.ignoubadhega.studycentremanager.service.UserService;


@Controller
@RequestMapping("/coordinator/register")
public class RegistrationController {
	
    @Autowired
    private UserService userService;
	
    private Logger logger = Logger.getLogger(getClass().getName());
    
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}	
	
	@GetMapping("/showRegistrationForm")
	public String showMyLoginPage(Model theModel) {
		
		theModel.addAttribute("studyCentreUser", new StudyCentreUser());
		
		return "registration-form";
	}

	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(
				@Valid @ModelAttribute("studyCentreUser") StudyCentreUser theUser, 
				BindingResult theBindingResult, 
				Model theModel) {
		
		String userName = theUser.getUserName();
		logger.info("Processing registration form for: " + userName);
		
		// form validation
		 if (theBindingResult.hasErrors()){
			 return "registration-form";
	        }

		 System.out.println(Arrays.toString(theUser.getRoles()));
		// check the database if user already exists
        User userNameExisting = userService.findByUserName(userName);
        User emailExisting = userService.findByEmail(theUser.getEmail());
        
        if (userNameExisting != null){
        	theModel.addAttribute("studyCentreUser", new StudyCentreUser());
			theModel.addAttribute("registrationError", "User name already exists.");

			logger.warning("User name already exists.");
        	return "registration-form";
        }
        if (emailExisting != null){
            theModel.addAttribute("studyCentreUser", new StudyCentreUser());
            theModel.addAttribute("registrationError", "Email already exists.");

            logger.warning("Email already exists.");
            return "registration-form";
        }
     // create user account        						
        userService.save(theUser);
        
        logger.info("Successfully created user: " + userName);
        
        return "registration-confirmation";		
	}
}

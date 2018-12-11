package com.imrancluster.savedream.controller;

import java.util.List;
import java.util.Map;
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

import com.imrancluster.savedream.entity.User;
import com.imrancluster.savedream.entity.UserType;
import com.imrancluster.savedream.service.UserService;
import com.imrancluster.savedream.user.SDUser;

@Controller
@RequestMapping("/register")
public class RegistrationController {

	@Autowired
    private UserService userService;
	
    private Logger logger = Logger.getLogger(getClass().getName());
    
    @InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
    
    @GetMapping("/form")
	public String showMyLoginPage(Model theModel) {
		
    	SDUser sdUser = new SDUser();
    	
    	List<UserType> types = userService.getUserTypes();
    	
		theModel.addAttribute("sdUser", sdUser);
		theModel.addAttribute("types", types);
		
		return "auth/registration-form";
	}
    
    @PostMapping("/process-form")
	public String processRegistrationForm(
				@Valid @ModelAttribute("sdUser") SDUser theSDUser, 
				BindingResult theBindingResult, 
				Model theModel) {
		
		String userName = theSDUser.getUserName();
		logger.info("Processing registration form for: " + userName);
		
		// form validation
		 if (theBindingResult.hasErrors()){
			 
			 List<UserType> types = userService.getUserTypes();
			 theModel.addAttribute("types", types);
			 
			 theModel.addAttribute("sdUser", theSDUser);
			
			 logger.info("Inside BindError: Has Error");
			 
			 // System.out.println("All Errors: " + theBindingResult.getAllErrors());
			 
			 return "auth/registration-form";
	        }

		// check the database if user already exists
        User existing = userService.findByUserName(userName);
        if (existing != null){
        	
        	SDUser sdUser = new SDUser();
        	theModel.addAttribute("sdUser", sdUser);
			theModel.addAttribute("registrationError", "User name already exists.");

			logger.warning("User name already exists.");
        	return "auth/registration-form";
        }
     // create user account        						
        userService.save(theSDUser);
        
        logger.info("Successfully created user: " + userName);
        
        return "auth/registration-confirmation";		
	}
}

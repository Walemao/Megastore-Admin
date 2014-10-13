package com.walemao.megastore.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.walemao.megastore.domain.User;
import com.walemao.megastore.domain.UserAuthority;
import com.walemao.megastore.domain.Authentication.RegistrationUsernameProvider;
import com.walemao.megastore.domain.validation.RegistrationValidator;
import com.walemao.megastore.repository.UserAuthorityDao;
import com.walemao.megastore.service.UserService;

@Controller
@RequestMapping("reg")
public class RegistrationController {
	
	private Logger logger = LoggerFactory.getLogger(RegistrationController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserAuthorityDao userAuthorityDao;
	
	@Autowired
	private RegistrationUsernameProvider provider;

	// Display the form on the get request
    @RequestMapping(method = RequestMethod.GET)
    public String showRegistration(@ModelAttribute("user") User user) 
    {
		return "registration";
    }
	
    // Process the form.
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody String processRegistration(@Validated @ModelAttribute("user") User user,
                    BindingResult result) 
    {
		if (result.hasErrors()) 
		{
			return "registration";
		}
		
		user.setPassword(provider.encodePassword(user));
		user.setSalt(user.getUsername());
		
		
		UserAuthority author = new UserAuthority();
		author.setUsername(user.getUsername());
		
		author.setAuthority("ROLE_USER");
		
		int id = userService.insert(user);
		userAuthorityDao.insert(author);
		return "registrationsuccess" + id;
    }
    
    @InitBinder
    protected void initBinder(WebDataBinder binder)
    {  
    	binder.setValidator(new RegistrationValidator());
    } 
}
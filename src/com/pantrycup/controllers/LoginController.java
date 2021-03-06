package com.pantrycup.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pantrycup.authentication.UserAuthenticator;
import com.pantrycup.dataproviders.CustomerUserDBTable;
import com.pantrycup.dataproviders.ServiceProviderDBTable;
import com.pantrycup.entities.CustomerUser;
import com.pantrycup.entities.ServiceProviderUser;
import com.pantrycup.spring.beans.session.UserSession;

@Controller
@RequestMapping(value="/")
public class LoginController 
{
	@Autowired
	UserSession currentSession;
	
    @RequestMapping(value="/doLogin", method = RequestMethod.POST)
    public String tryLogin(HttpServletRequest request,@RequestParam Map<String,String> allRequestParams, ModelMap model) 
    {        
		String username = allRequestParams.get("username");
		String password = allRequestParams.get("password");
				
		CustomerUserDBTable customerUserTable=new CustomerUserDBTable();
		CustomerUser customerUser = customerUserTable.findByUserName(username);
		
		ServiceProviderDBTable serviceProviderDBTable = new ServiceProviderDBTable();
		ServiceProviderUser serviceProviderUser = serviceProviderDBTable.findByUserName(username);	
		
		if(customerUser!= null && password.equals(customerUser.getUserCredentials().getPassword()))
		{
			UserAuthenticator.addToAuthenticatedSession(request.getSession());
			currentSession.setCurrentUser(customerUser);
			return "serviceprovidersearch";
		}
		else if(serviceProviderUser!= null && password.equals(serviceProviderUser.getUserCredentials().getPassword()))
		{
			UserAuthenticator.addToAuthenticatedSession(request.getSession());
			currentSession.setCurrentServiceProvider(serviceProviderUser);
			return "serviceproviderhome";
		}
		else
		{
			model.addAttribute("errors", "Wrong username or password, please try again");
			return "login";
		}
    }
    
    @RequestMapping(value="/doLogout", method = RequestMethod.GET)
    public String doLogout(HttpServletRequest request,@RequestParam Map<String,String> allRequestParams, ModelMap model) 
    {        
		UserAuthenticator.removeFromAuthenticatedSession(request.getSession());
		return "login";
    }

}

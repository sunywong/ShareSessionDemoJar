package com.suny;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class ShareSessionDemoController {

	 @Value("${sunyTestProperty}")
	 private String parameterFromProperty;
	 
	/* @Value("${mysql.jdbcName}")
	 private String jdbcName;
	 
	 @Value("${mysql.dbUrl}")
	 private String dbUrl;
	 
	 @Value("${mysql.userName}")
	 private String userName;
	 
	 @Value("${mysql.password}")
	 private String password;
	 */
	 	 
	 @RequestMapping("/hello")
	 public String say(HttpServletRequest request) {
		 System.out.println("test output in console");
		 HttpSession session = request.getSession();
		 return parameterFromProperty + " " + session.getClass() + "sessionID:"+ session.getId();
		 
	 }
	 
	 
	 @RequestMapping("/session/{email}")
	 @ResponseBody
	 public String TestSession(HttpServletRequest req, HttpServletResponse resp,@PathVariable String email){
		 	System.out.println("In  server console: @PathVariable String email:" + email);
		 	if (req.getSession().getAttribute("email")==null)
		 	{
		 		System.out.println("Set session(\"email\"):" + email);
		 		req.getSession().setAttribute("email", email);
		 	}
		 	String result="";
		 	String a = (String) req.getSession().getAttribute("email");
		 	result= parameterFromProperty + " in server console"  + "<br/>"  + 
				 	req.getSession().getClass()  + "<br/>" +  
				 	"sessionID: "+ req.getSession().getId()  + "<br/>" + "remoteAddr:"+ req.getRemoteAddr() +
				 	"<br/>" + "email in session is: "+ a;
			return result  ;
	}
	 
}

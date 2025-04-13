package impl;

import entily.User;
import jakarta.servlet.ServletContext;
import service.EmailService;
import util.SendEmailUtil;

public class EmailServiceImpl implements EmailService{
	private static final String EMAIL_WELCOME_SUBJECT = "Welcome to Online Entertainment";
	private static final String EMAIL_FORGOT_PASSWORD = "Online Entertainment - New password";
	@Override
	public void sendEmail(ServletContext context, User recipient, String type) {
		// TODO Auto-generated method stub
		
	    String    host = context.getInitParameter("host");
	    String    port = context.getInitParameter("port");
	 String       user = context.getInitParameter("user");
	      String  pass = context.getInitParameter("pass");
	      
	      
	      try {
	    	  String content = null;
	    	  String subject = null;
	    	  switch(type){
	    		  case "welcome":
	    			content = "Dear" + recipient.getUsername() +", hope you have a good time!"  ;
	    	 break;
	    		  case "forgot":
	    			  subject = EMAIL_FORGOT_PASSWORD;
	    			content ="Dear"  + recipient.getUsername( ) + ", your new password here: " + recipient.getPassword();
	    	default:
	    		subject = "Online Entertainment";
	    		content = "Maybe this email is wrong, don't care about it";
	    	  }
	    	  
	    	  SendEmailUtil.sendEmail(host, port,user, pass,recipient.getEmail(), subject, content);
	    	 	  
	    	  
	      }catch(Exception ex) {
	    	  ex.printStackTrace();
	      }
	      
	      
	      
	      
	}

}

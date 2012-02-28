package se.bettercode.bwin.pages.user;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.PasswordField;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import se.bettercode.bwin.entities.User;
import se.bettercode.bwin.pages.Index;

/**
 * Used to login a user to the webapp
 * @author max
 * @since 2010-05-11
 *
 */
public class Login {

	
	@Inject
    private Session session;
    
    @SuppressWarnings("unused")
	@SessionState
    private User user;
    
    
	@Persist
	@Property
	private String userName;


	@Property
	private String password;

    @SuppressWarnings("unused")
	@Component(id = "password")
    private PasswordField passwordField;
	
	@Component
	private Form form;
	
	
    @InjectPage
    private Index index;

    @CommitAfter 
	void onValidateForm() {
		User someUser = null;
		//Load user based on userName
		try {
			someUser = (User) session.load(User.class, userName);
			
			if (someUser==null) {
				form.recordError("Invalid username or password (b).");
			    return;
			}
			
			//See if user is locked out
			if (someUser.isLockedOut()) {
				form.recordError("Your account has been locked out due to an excessive number of incorrect login attempts.");
			    return;
			}		
			
		} catch (HibernateException e) {
		      form.recordError("Invalid username or password (a).");
		      return;
		}
		
		
		//Do a simple password comparison
		if (!password.equals(someUser.getPassword())) {		
			someUser.incrementLoginFailure();
		    //Persist the data	      
		    session.update(someUser);
	        form.recordError("Invalid username or password (" + someUser.getLoginFailures() + ").");

	        if (someUser.isLockedOut()) {
	          form.recordError("Your account has now been locked out.");
	      	  //TODO: Send email to some admin
	          //MailSender.send("smtp.gmail.com", 465, "max.wenzin+apptest@gmail.com", "max.wenzin@gmail.com", "Account locked out", "Some account has been locked out!");
	        }
	      
	      return;
	    } 

		//If there are no errors, the authentication is successful
		if (!form.getHasErrors()) {
	    	//Reset the failure counter
	    	someUser.resetLoginFailures();
	    	//Set the timestamp of the login
	    	someUser.setCurrentLoginNow();
	    	session.update(someUser);
	    	
	    	//Assign someUser to the session object
	    	user = someUser;			
		}

		
	  }
	  
	  @CommitAfter
	  Object onSuccess() {
	        return index;
	  }

}

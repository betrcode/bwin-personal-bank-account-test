package se.bettercode.bwin.pages;

import java.util.Date;
import org.apache.tapestry5.annotations.SessionState;
import se.bettercode.bwin.entities.User;

/**
 * Start page of application worktest1.
 */
public class Index {
    
    @SessionState(create=false)
    private User user;
    
    public boolean isLoggedIn() {
    	return user!=null;
    }
    
    public String getUsername() {
		return user.getUserName();
    }
    
    public long getMoney() {
		return user.getMoneyInCents();
    }
	
	public Date getCurrentTime() 
	{ 
		return new Date(); 
	}
	
	public User getUser() {
		return user;
	}
}

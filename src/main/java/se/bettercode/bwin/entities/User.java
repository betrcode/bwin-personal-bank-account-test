package se.bettercode.bwin.entities;


import java.sql.Timestamp;
import java.util.Date;

import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Entity class to hold a webapp user
 * @author max
 *
 */
@Entity
public class User extends HibernateEntityBase {
	
    @Id
	@Validate("required")
	private String userName;
    
    @Validate("required")
	private String password;

    @NonVisual    
    private long moneyInCents = 100; //Lets give all some starting money 
 
    @NonVisual    
	private String moneyCurrencyCode = "SEK";

    @NonVisual    
	private int loginFailures = 0;
	
    @NonVisual    
	private boolean lockedOut = false;
    
    @NonVisual
    private Timestamp lastLogin;

    @NonVisual
    private Timestamp currentLogin;
    
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getMoneyInCents() {
		return moneyInCents;
	}
	public void setMoneyInCents(long moneyInCents) {
		this.moneyInCents = moneyInCents;
	}
	public String getMoneyCurrencyCode() {
		return moneyCurrencyCode;
	}
	public void setMoneyCurrencyCode(String moneyCurrencyCode) {
		this.moneyCurrencyCode = moneyCurrencyCode;
	}
	public int getLoginFailures() {
		return loginFailures;
	}
	public void setLoginFailures(int loginFailures) {
		this.loginFailures = loginFailures;
	}
	public void incrementLoginFailure() {
		this.loginFailures++;
		if (loginFailures==3) {
			setLockedOut(true);
		}
	}
	
	/**
	 * Convenience method 
	 */
	public void resetLoginFailures() {
		setLoginFailures(0);
	}
	
	public boolean isLockedOut() {
		return lockedOut;
	}
	
	public void setLockedOut(boolean lockedOut) {
		this.lockedOut = lockedOut;
	}
	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}
	public Timestamp getLastLogin() {
		return lastLogin;
	}
	public void setCurrentLogin(Timestamp currentLogin) {
		this.currentLogin = currentLogin;
	}
	/**
	 * This will set the currentLogin to the now() 
	 * and store the old value away in lastLogin
	 * @param currentLogin
	 */
	public void setCurrentLoginNow() {
		setLastLogin(this.currentLogin);
		Date today = new Date();
		this.currentLogin = new Timestamp(today.getTime());
	}
	public Timestamp getCurrentLogin() {
		return currentLogin;
	}

}

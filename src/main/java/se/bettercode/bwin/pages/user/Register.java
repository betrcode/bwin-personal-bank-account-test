package se.bettercode.bwin.pages.user;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;


import se.bettercode.bwin.entities.User;
import se.bettercode.bwin.pages.Index;

/**
 * Registration page
 * @author max
 *
 */
public class Register {

	@Property
	private User user;

    @Inject
    private Session session;

    @InjectPage
    private Index index;

    @CommitAfter
	void onValidateForm() {
		session.persist(user);
    }    
    
    Object onSuccess() {
         return index;
    }

    
    
}

package se.bettercode.bwin.pages.user;

import org.apache.tapestry5.annotations.BeforeRenderTemplate;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.SessionState;

import se.bettercode.bwin.entities.User;
import se.bettercode.bwin.pages.Index;

public class Logout {

    @SuppressWarnings("unused")
	@SessionState(create=false)
    private User user;
    
	@InjectPage
	private Index index;

	@BeforeRenderTemplate
	Object logout() {
		user = null;
		return index;
	}
}

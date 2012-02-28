package se.bettercode.bwin.entities;

import org.testng.Assert;
import org.testng.annotations.Test;

public class UserTest {

	@Test
	public void testDummy() {}
	
	@Test
	public void testCreate() {
		User user = new User();
		
		//Test the default values
		Assert.assertEquals(user.getLoginFailures(), 0);
		
	}
}

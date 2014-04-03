package com.kumar.mis.app.shared;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.shared.GWT;
import com.kumar.mis.app.shared.common.login.UserContext;
import com.kumar.mis.app.shared.domain.login.User;

public class UserStore {

	private static List<User> userList = new ArrayList<User>();

	private static Map<String, List<String>> roleMapping = new HashMap<String, List<String>>();

	public UserStore() {
		this.initStore();
		this.initRoles();
	}

	private void initRoles() {

		List<String> allRoles = new ArrayList<String>();
		allRoles.add("ALL");

		roleMapping.put("kumar@kumar.com", allRoles);
	}

	private void initStore() {
		User kumarUser = new User();
		kumarUser.setEmailAddress("kumar@kumar.com");
		kumarUser.setPassword("123456");
		userList.add(kumarUser);
	}

	public static UserContext authenticate(User loginUser) {

		UserContext uc = null;
		User foundUser = null;
		for (User user : userList) {

			if (loginUser.getEmailAddress().equalsIgnoreCase(
					user.getEmailAddress())&&loginUser.getPassword().equals(user.getPassword())) {
				foundUser = user;
				break;
			}
		}

		if (foundUser != null) {
			uc = new UserContext(foundUser);
			uc.setRoles(roleMapping.get(foundUser.getEmailAddress()));
			GWT.log(uc.toString());
		}
		

		return uc;

	}

}

package com.kumar.mis.app.shared.common.login;

import java.util.List;

import com.kumar.mis.app.shared.domain.login.User;

public class UserContext {

	private User user;
	private List<String> roles;

	public UserContext(User user) {
		this();
		this.user = user;
	}

	public UserContext() {

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "UserContext [user=" + user + ", roles=" + roles + "]";
	}

}

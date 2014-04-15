package com.kumar.mis.app.shared.common.data;

import java.util.HashMap;

import com.google.gwt.user.client.rpc.core.java.util.Collections;

public class StaticDataDb {

	private static HashMap<String, String> cities = new HashMap<String, String>();
	private static HashMap<String, String> states = new HashMap<String, String>();
	private static HashMap<String, String> countries = new HashMap<String, String>();
	private static HashMap<String, String> requestMode = new HashMap<String, String>();

	static {
		cities.put("Chennai", "Chennai");
		cities.put("Trichy", "Trichy");
		cities.put("Madurai", "Madurai");
		cities.put("Others", "Others");

		states.put("TamilNadu", "TamilNadu");
		states.put("Andhra Pradesh", "Andhra Pradesh");
		states.put("Kerala", "Kerala");
		states.put("Maharastra", "Maharastra");
		states.put("Others", "Others");

		countries.put("India", "India");
		countries.put("USA", "USA");
		countries.put("UK", "UK");

		requestMode.put("email", "Email");
		requestMode.put("phone", "Phone");
		requestMode.put("others", "Others");
	}

	public static HashMap<String, String> getStates() {

		return states;

	}

	public static HashMap<String, String> getCountries() {
		return countries;
	}

	public static HashMap<String, String> getCities() {
		return cities;
	}

	public static HashMap<String, String> getRequestMode() {
		return requestMode;
	}

}

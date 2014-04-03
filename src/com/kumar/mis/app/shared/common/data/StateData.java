package com.kumar.mis.app.shared.common.data;

import java.util.ArrayList;
import java.util.List;

import com.kumar.mis.app.shared.domain.State;

public class StateData {
	
	public static List<State> getStates() {
	    List<State> states = new ArrayList<State>();
	    states.add(new State("AL", "Alabama"));
	    states.add(new State("AK", "Alaska"));
	    states.add(new State("AZ", "Arizona"));
	    states.add(new State("AR", "Arkansas"));
	    states.add(new State("CA", "California"));
	    states.add(new State("CO", "Colorado"));
	    
	    return states;
	  }

}

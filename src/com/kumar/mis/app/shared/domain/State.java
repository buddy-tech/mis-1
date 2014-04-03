package com.kumar.mis.app.shared.domain;


/**
 * Used for State information (Static data)
 * @author Kumar Jeyaprakasam
 *
 */
public class State {

	private String stateId;
	private String stateName;

	public State() {

	}

	public State(String stateId, String stateName) {
		super();
		this.stateId = stateId;
		this.stateName = stateName;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	@Override
	public String toString() {
		return "State [stateId=" + stateId + ", stateName=" + stateName + "]";
	}

}

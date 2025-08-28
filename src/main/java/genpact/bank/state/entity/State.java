package genpact.bank.state.entity;

public class State {

	private int stateId;
	private String stateName;

	public State() {
		super();
	}

	public State(int stateId, String stateName) {
		super();
		this.stateId = stateId;
		this.stateName = stateName;
	}

	public State(String stateName) {
		super();
		this.stateName = stateName;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
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
		return "\n State [stateId=" + stateId + ", stateName=" + stateName + "]";
	}

}

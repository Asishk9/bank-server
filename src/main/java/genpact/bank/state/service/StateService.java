package genpact.bank.state.service;

import java.util.List;

import genpact.bank.state.entity.State;

public interface StateService {

	int addState(State state);

	int modifyState(State state);

	int removeState(int stateId);

	State fetchStateById(int stateId);

	List<State> fetchAllStates();

	State fetchStateByName(String stateName);

}

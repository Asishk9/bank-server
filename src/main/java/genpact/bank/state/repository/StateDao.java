package genpact.bank.state.repository;

import java.util.List;

import genpact.bank.state.entity.State;

public interface StateDao {
	public List<State> getAllStates();
	public int insertState(State state);
	public State getStateById(int stateId);
	public int updateState(State state);
	public int deleteState(int stateId);
	public State getStateByName(String stateName);
}

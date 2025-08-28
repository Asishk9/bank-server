package genpact.bank.state.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import genpact.bank.state.entity.State;
import genpact.bank.state.repository.StateDao;

@Service
public class StateServiceImpl implements StateService{

	@Autowired
	StateDao stateDao;
	
	@Override
	public int addState(State state) {
		 return stateDao.insertState(state);
	}

	@Override
	public int modifyState(State state) {
		return stateDao.updateState(state);
	}

	@Override
	public int removeState(int stateId) {
		return stateDao.deleteState(stateId);
	}

	@Override
	public State fetchStateById(int stateId) {
		return stateDao.getStateById(stateId);
	}

	@Override
	public List<State> fetchAllStates() {
		return stateDao.getAllStates();
	}

	@Override
	public State fetchStateByName(String stateName) {
		return stateDao.getStateByName(stateName);
	}

}

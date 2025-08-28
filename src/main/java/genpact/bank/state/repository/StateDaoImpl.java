package genpact.bank.state.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import genpact.bank.state.entity.State;

@Repository
public class StateDaoImpl implements StateDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<State> getAllStates() {
		String SELECT_ALL_STATES = "SELECT state_id, state_name FROM state";
		return jdbcTemplate.query(SELECT_ALL_STATES, new StateRowMapper());

	}

	@Override
	public int insertState(State state) {
		String INSERT_STATE = "INSERT INTO state (state_name) VALUES (?)";
		return jdbcTemplate.update(INSERT_STATE, state.getStateName());

	}

	@Override
	public State getStateById(int stateId) {
		String SELECT_STATE_BY_ID = "SELECT state_id, state_name FROM state WHERE state_id = ?";
		return jdbcTemplate.queryForObject(SELECT_STATE_BY_ID, new StateRowMapper(), stateId);

	}
	

	@Override
	public State getStateByName(String stateName) {
		String SELECT_STATE_BY_NAME = "SELECT state_id, state_name FROM state WHERE state_name = ?";
		return jdbcTemplate.queryForObject(SELECT_STATE_BY_NAME, new StateRowMapper(), stateName);
	}

	@Override
	public int updateState(State state) {
		String UPDATE_STATE = "UPDATE state SET state_name = ? WHERE state_id = ?";
		return jdbcTemplate.update(UPDATE_STATE, state.getStateName(), state.getStateId());

	}

	@Override
	public int deleteState(int stateId) {
		String DELETE_STATE = "DELETE FROM state WHERE state_id = ?";
		return jdbcTemplate.update(DELETE_STATE, stateId);
	}

	private static class StateRowMapper implements RowMapper<State> {
		@Override
		public State mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new State(rs.getInt("state_id"), rs.getString("state_name"));
		}
	}


}

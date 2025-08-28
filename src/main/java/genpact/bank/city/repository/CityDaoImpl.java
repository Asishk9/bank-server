package genpact.bank.city.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import genpact.bank.city.entities.City;

@Repository
public class CityDaoImpl implements CityDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<City> getAllCities() {
		String SELECT_ALL_CITIES = "SELECT city_id, city_name, state_id FROM city";
		return jdbcTemplate.query(SELECT_ALL_CITIES, new CityRowMapper());

	}

	@Override
	public int insertCity(City city) {
		String INSERT_CITY = "INSERT INTO city (city_name, state_id) VALUES (?, ?)";
		return jdbcTemplate.update(INSERT_CITY, city.getCityName(), city.getStateId());

	}

	@Override
	public City getCityById(int cityId) {
		String SELECT_CITY_BY_ID = "SELECT city_id, city_name, state_id FROM city WHERE city_id = ?";
		return jdbcTemplate.queryForObject(SELECT_CITY_BY_ID, new CityRowMapper(), cityId);

	}
	
	@Override
	public City getCityByName(String cityName) {
		String SELECT_CITY_BY_NAME = "SELECT city_id, city_name, state_id FROM city WHERE city_name = ?";
		return jdbcTemplate.queryForObject(SELECT_CITY_BY_NAME, new CityRowMapper(), cityName);
	}

	@Override
	public List<City> getCitiesByStateId(int stateId) {
		String SELECT_CITY_BY_STATE = "SELECT city_id, city_name, state_id FROM city WHERE state_id = ?";
		return jdbcTemplate.query(SELECT_CITY_BY_STATE, new CityRowMapper(), stateId);

	}

	@Override
	public int updateCity(City city) {
		String UPDATE_CITY = "UPDATE city SET city_name = ?, state_id = ? WHERE city_id = ?";
		return jdbcTemplate.update(UPDATE_CITY, city.getCityName(), city.getStateId(), city.getCityId());

	}

	@Override
	public int deleteCity(int cityId) {
		String DELETE_CITY = "DELETE FROM city WHERE city_id = ?";
		return jdbcTemplate.update(DELETE_CITY, cityId);
	}

	private static class CityRowMapper implements RowMapper<City> {
		@Override
		public City mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new City(rs.getInt("city_id"), rs.getString("city_name"), rs.getInt("state_id"));
		}
	}

	

	
}

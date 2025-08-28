package genpact.bank.city.repository;

import java.util.List;

import genpact.bank.city.entities.City;

public interface CityDao {
	public List<City> getAllCities();
	public int insertCity(City city);
	public City getCityById(int cityId);
	public List<City> getCitiesByStateId(int stateId);
	public int updateCity(City city);
	public int deleteCity(int cityId);
	public City getCityByName(String cityName);
}

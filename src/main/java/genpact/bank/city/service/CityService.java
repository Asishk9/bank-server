package genpact.bank.city.service;

import java.util.List;

import genpact.bank.city.entities.City;

public interface CityService {

	int addCity(City city);

	int modifyCity(City city);

	int removeCity(int cityId);

	City fetchCityById(int cityId);

	List<City> fetchAllCities();

	List<City> fetchCitiesByStateId(int stateId);

	City fetchCityByName(String cityName);


}

package genpact.bank.city.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import genpact.bank.city.entities.City;
import genpact.bank.city.repository.CityDao;

@Service
public class CityServiceImpl implements CityService{

	@Autowired
	CityDao cityDao;
	
	@Override
	public int addCity(City city) {
		return cityDao.insertCity(city);
	}

	@Override
	public int modifyCity(City city) {
		return cityDao.updateCity(city);
	}

	@Override
	public int removeCity(int cityId) {
		return cityDao.deleteCity(cityId);
	}

	@Override
	public City fetchCityById(int cityId) {
		return cityDao.getCityById(cityId);
	}
	
	@Override
	public City fetchCityByName(String cityName) {
		return cityDao.getCityByName(cityName);
	}

	@Override
	public List<City> fetchAllCities() {
		return cityDao.getAllCities();
	}

	@Override
	public List<City> fetchCitiesByStateId(int stateId) {
		return cityDao.getCitiesByStateId(stateId);
	}

	

}

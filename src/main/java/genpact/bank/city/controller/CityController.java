package genpact.bank.city.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import genpact.bank.city.entities.City;
import genpact.bank.city.service.CityService;

@Controller
@RequestMapping("api/city")
public class CityController {

	@Autowired
	CityService cityService;

	@PostMapping("/addCity")
	public ResponseEntity<Integer> addCity(@ModelAttribute City city) {
		int result = cityService.addCity(city);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@GetMapping("/")
	public ResponseEntity<List<City>> fetchAllCities() {
		List<City> cities = cityService.fetchAllCities();
		return new ResponseEntity<>(cities, HttpStatus.OK);
	}

	@GetMapping("/cityDetails/{cityId}")
	public ResponseEntity<City> fetchCityById(@PathVariable int cityId) {
		City city = cityService.fetchCityById(cityId);
		return new ResponseEntity<>(city, HttpStatus.OK);
	}
	
	@GetMapping("/cityDetailsByName/{cityName}")
	public ResponseEntity<City> fetchCityByName(@PathVariable String cityName) {
		City city = cityService.fetchCityByName(cityName);
		return new ResponseEntity<>(city, HttpStatus.OK);
	}
	
	
	@GetMapping("/cities/{stateId}")
	public ResponseEntity<List<City>> fetchCitiesByStateId(@PathVariable int stateId) {
		List<City> cities = cityService.fetchCitiesByStateId(stateId);
		return new ResponseEntity<>(cities, HttpStatus.OK);
	}
	
	@PutMapping("/update/{cityId}")
	public ResponseEntity<Integer> modifyCity(@PathVariable int cityId, @ModelAttribute City city) {
		city.setCityId(cityId);
		int result = cityService.modifyCity(city);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{cityId}")
	public ResponseEntity<Integer> removeCity(@PathVariable int cityId) {
		int result = cityService.removeCity(cityId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}

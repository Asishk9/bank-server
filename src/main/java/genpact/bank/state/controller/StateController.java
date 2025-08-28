package genpact.bank.state.controller;

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

import genpact.bank.state.entity.State;
import genpact.bank.state.service.StateService;

@Controller
@RequestMapping("api/state")
public class StateController {

	@Autowired
	StateService stateService;
	
	 @GetMapping("/")
	    public ResponseEntity<List<State>> fetchAllStates() {
	        List<State> states = stateService.fetchAllStates();
	        return new ResponseEntity<>(states, HttpStatus.OK);
	    }

	@PostMapping("/addState")
    public ResponseEntity<Integer> addState(@ModelAttribute State state) {
        int result = stateService.addState(state);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

	 @GetMapping("/stateDetails/{stateId}")
	    public ResponseEntity<State> fetchStateById(@PathVariable int stateId) {
	        State state = stateService.fetchStateById(stateId);
	        return new ResponseEntity<>(state, HttpStatus.OK);
	    }
	 
	 @GetMapping("/stateDetailsByName/{stateName}")
	    public ResponseEntity<State> fetchStateByName(@PathVariable String stateName) {
	        State state = stateService.fetchStateByName(stateName);
	        return new ResponseEntity<>(state, HttpStatus.OK);
	    }
	 
    @PutMapping("/update/{stateId}")
    public ResponseEntity<Integer> modifyState(@PathVariable int stateId, @ModelAttribute State state) {
        state.setStateId(stateId);
        int result = stateService.modifyState(state);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{stateId}")
    public ResponseEntity<Integer> removeState(@PathVariable int stateId) {
        int result = stateService.removeState(stateId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}

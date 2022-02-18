package net.codejava.idiom;

import java.util.List;
//import java.util.NoSuchElementException;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/public") // public: user can get the data without authentication
public class IdiomController {
	@Autowired
	private IdiomService service;

	/**
	 * Get all idioms
	 * 
	 * @returns
	 */
	@GetMapping("/idioms")
	public List<Idiom> list() {
		return service.listAll();
	}

	/**
	 * Get idioms by keyword
	 * 
	 * @param keyword
	 * @return
	 */
	@GetMapping("/idioms/{keyword}")
	public List<Idiom> list(@PathVariable String keyword) {
		return service.listAll(keyword);
	}

	/**
	 * Get idioms by id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/idiom/{id}")
	public ResponseEntity<Idiom> get(@PathVariable Integer id) {
		try {
			Idiom idiom = service.get(id);
			return new ResponseEntity<Idiom>(idiom, HttpStatus.OK);

		} catch (NoSuchElementException e) {
			return new ResponseEntity<Idiom>(HttpStatus.NOT_FOUND);
		}
	}
}

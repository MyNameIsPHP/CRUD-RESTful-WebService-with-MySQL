package net.codejava.idiom;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IdiomController {
	@Autowired
	private IdiomService service;
	
	@GetMapping("/idioms")
	public List<Idiom> list() {
		return service.listAll();
	}
	
	@GetMapping("/idioms/{id}")
	public ResponseEntity<Idiom> get(@PathVariable Integer id) {
		try {
			Idiom idiom = service.get(id);
			return new ResponseEntity<Idiom>(idiom, HttpStatus.OK);

		} catch (NoSuchElementException e) {
			return new ResponseEntity<Idiom>(HttpStatus.NOT_FOUND);
		}
	}
}

package net.codejava.pverb;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhrasalVerbController {
	@Autowired
	private PhrasalVerbService service;
	
	@GetMapping("/pverbs/{keyword}")
	public List<PhrasalVerb> list(@PathVariable String keyword) {
		return service.listAll(keyword);
	}
	
}

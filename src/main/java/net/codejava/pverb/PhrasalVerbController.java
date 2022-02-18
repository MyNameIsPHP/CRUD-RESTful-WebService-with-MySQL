package net.codejava.pverb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/public")
public class PhrasalVerbController {
	@Autowired
	private PhrasalVerbService service;

	/**
	 * Get phrasal verbs by keyword
	 * 
	 * @param keyword
	 * @return
	 */
	@GetMapping("/pverbs/{keyword}")
	public List<PhrasalVerb> list(@PathVariable String keyword) {
		return service.listAll(keyword);
	}

}

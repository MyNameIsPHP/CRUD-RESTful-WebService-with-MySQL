package net.codejava.pverb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PhrasalVerbService {
	
	@Autowired
	private PhrasalVerbRepository repo;
	
	public List<PhrasalVerb> listAll(String keyword) {
		if (keyword != null) {
			return repo.findAll(keyword);
		}
		return repo.findAll();
	}
	
	public PhrasalVerb get(Integer id) {
		return repo.findById(id).get();
	}
	
}

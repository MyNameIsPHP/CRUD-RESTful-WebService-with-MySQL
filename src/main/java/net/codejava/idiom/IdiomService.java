package net.codejava.idiom;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IdiomService {
	@Autowired
	private IdiomRepository repo;

	public List<Idiom> listAll() {
		return repo.findAll();
	}

	public List<Idiom> listAll(String keyword) {
		if (keyword != null) {
			return repo.findAll(keyword);
		}
		return repo.findAll();
	}

	public Idiom get(Integer id) {
		return repo.findById(id).get();
	}
}

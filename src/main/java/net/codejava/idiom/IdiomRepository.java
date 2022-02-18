package net.codejava.idiom;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IdiomRepository extends JpaRepository<Idiom, Integer> {
	@Query("SELECT p FROM Idiom p WHERE p.also LIKE %?1%")
	public List<Idiom> findAll(String keyword);
}

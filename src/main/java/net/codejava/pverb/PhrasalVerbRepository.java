package net.codejava.pverb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PhrasalVerbRepository extends JpaRepository<PhrasalVerb, Integer> {
	@Query("SELECT p FROM PhrasalVerb p WHERE p.pverb LIKE %?1%")
	public List<PhrasalVerb> findAll(String keyword);
}

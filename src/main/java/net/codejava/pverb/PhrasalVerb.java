package net.codejava.pverb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "phrasal_verb")
public class PhrasalVerb {
	private Integer id;
	private String pverb;
	private String definition;
	private String example;

	public PhrasalVerb() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PhrasalVerb(Integer id, String pverb, String definition, String example) {
		super();
		this.id = id;
		this.pverb = pverb;
		this.definition = definition;
		this.example = example;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPverb() {
		return pverb;
	}

	public void setPverb(String pverb) {
		this.pverb = pverb;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}
}

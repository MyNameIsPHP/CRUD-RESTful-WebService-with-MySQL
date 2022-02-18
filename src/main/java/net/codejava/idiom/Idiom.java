package net.codejava.idiom;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "idiom")
public class Idiom {
	private Integer id;
	private String words;
	private String also; // synonym
	private String meaning;
	private String example;
	private String origin;

	public Idiom() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Idiom(Integer id, String words, String also, String meaning, String example, String origin) {
		super();
		this.id = id;
		this.words = words;
		this.also = also;
		this.meaning = meaning;
		this.example = example;
		this.origin = origin;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWords() {
		return words;
	}

	public void setWords(String words) {
		this.words = words;
	}

	public String getAlso() {
		return also;
	}

	public void setAlso(String also) {
		this.also = also;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

}

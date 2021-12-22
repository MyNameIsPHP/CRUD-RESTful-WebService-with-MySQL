package net.codejava.idiom;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="idiom")
public class Idiom {
	private Integer id;
	private String sentence;
	private String meaning;
	private String example;
	
	
	public Idiom() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Idiom(Integer id, String sentence, String meaning, String example) {
		super();
		this.id = id;
		this.sentence = sentence;
		this.meaning = meaning;
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
	public String getSentence() {
		return sentence;
	}
	public void setSentence(String sentence) {
		this.sentence = sentence;
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
	
	

}

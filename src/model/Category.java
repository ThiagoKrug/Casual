package model;

import java.util.ArrayList;
import java.util.List;

public class Category extends Model {
	
	private int id;
	private String name;
	private String link;
	private List<Person> persons;
	private List<PersonCategory> personsCategories;
	
	public Category() {
		this.persons = new ArrayList<Person>();
		this.personsCategories = new ArrayList<>();
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLink() {
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Person> getPersons() {
		return persons;
	}
	
	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
	
	public boolean addPerson(Person person) {
		return this.persons.add(person);
	}
	
	public List<PersonCategory> getPersonsCategories() {
		return personsCategories;
	}
	
	public void setPersonsCategories(List<PersonCategory> personsCategories) {
		this.personsCategories = personsCategories;
	}
	
	public boolean addPersonCategory(PersonCategory personCategory) {
		return this.personsCategories.add(personCategory);
	}

}

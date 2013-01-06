package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.rank.Judge;

public class Person extends Model {

	private int id;
	private String name;
	private List<Relationship> relationships;
	private List<Category> categories;
	private Class<? extends Judge> calculatedBy = null;
	private int relationshipsNumber;
	private int pageOccurrencesNumber;
	private double popularity;
	private double temporaryPopularity;

	public Person() {
		this.relationships = new ArrayList<>();
		this.relationshipsNumber = 0;
		this.categories = new ArrayList<>();
		this.popularity = 1;
                this.temporaryPopularity = 0;
	}

	public Person(int id, String name, ArrayList<Relationship> relationships) {
		this.id = id;
		this.name = name;
		this.relationships = relationships;
		this.relationshipsNumber = 0;
		this.popularity = 1;
                this.temporaryPopularity = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Relationship> getRelationships() {
		return relationships;
	}

	public void setRelationships(List<Relationship> relationships) {
		if (relationships != null) {
			int count = 0;
			for (Relationship relationship : relationships) {
				count = count + relationship.getRelationshipsLinksNumber();
			}
			this.setRelationshipsNumber(count);
		} else {
			this.setRelationshipsNumber(0);
		}
		this.relationships = relationships;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public boolean addCategory(Category category) {
		return this.categories.add(category);
	}

	public Class<? extends Judge> getCalculatedBy() {
		return calculatedBy;
	}

	public int getRelationshipsNumber() {
		return relationshipsNumber;
	}

	public void setRelationshipsNumber(int relationshipsNumber) {
		this.relationshipsNumber = relationshipsNumber;
	}

	public int getPageOccurrencesNumber() {
		return pageOccurrencesNumber;
	}

	public void setPageOccurrencesNumber(int pageOccurrencesNumber) {
		this.pageOccurrencesNumber = pageOccurrencesNumber;
	}

	public void addPageOccurrencesNumber() {
		this.pageOccurrencesNumber++;
	}

	public double getPopularity() {
		return popularity;
	}

	public void setPopularity(double popularity) {
		this.popularity = popularity;
	}
	
	public double getTemporaryPopularity() {
		return temporaryPopularity;
	}
	
	public void setTemporaryPopularity(double temporaryPopularity) {
		this.temporaryPopularity = temporaryPopularity;
	}

	public void setCalculatedBy(Class<? extends Judge> calculatedBy) {
		this.calculatedBy = calculatedBy;
	}

	public void categoriesToRelationships(HashMap<String, Person> indexedData) {
		for (Category category : categories) {
			for (Person person : category.getPersons()) {
				if (person != this) {
					extractRelationship(category, person);
				}
			}
		}
	}

	private void extractRelationship(Category category, Person person) {
		RelationshipLink rLink = new RelationshipLink();
		rLink.setLink(category.getLink());
		rLink.setName(category.getName());

		boolean newRelationship = true;

		for (Relationship relationship : relationships) {
			if (relationship.getPerson2() == person) {
				relationship.addRelationshipLink(rLink);
				newRelationship = false;
				break;
			}
		}

		if (newRelationship) {
			Relationship relationship = new Relationship();
			
			relationship.setPerson1(this);
			relationship.setPerson2(person);
			relationship.addRelationshipLink(rLink);
			this.relationships.add(relationship);
		}
	}

	public void calculatePopularity() {
		this.setPopularity(this.getTemporaryPopularity());
	}
	
	public void calculateTemporaryPopularity() {
		int count = 0;
		for (Category category : categories) {
			for (Person person : category.getPersons()) {
				if (person != this) {
					count += person.getPopularity();
				}
			}
		}
		this.setTemporaryPopularity(count + this.getPopularity());
	}

	@Override
	public boolean equals(Object obj) {
		if (this.name.equalsIgnoreCase(((Person) obj).getName())) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "[Person: id: " + this.id + ", name: " + this.name
				+ ", relationshipNumber: " + this.relationshipsNumber + "]";
	}
}

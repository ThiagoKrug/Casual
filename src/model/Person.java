package model;

import java.util.ArrayList;
import java.util.List;

import model.rank.Judge;

public class Person extends Model {

	private int id;
	private String name;
	private List<Relationship> relationships;
	private Class<? extends Judge> calculatedBy = null;
	private int relationshipsNumber;
	private int pageOccurrencesNumber;
	private double popularity;

	public Person() {
		this.relationships = new ArrayList<>();
		this.relationshipsNumber = 0;
	}

	public Person(int id, String name, ArrayList<Relationship> relationships) {
		this.id = id;
		this.name = name;
		this.relationships = relationships;
		this.relationshipsNumber = 0;
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
	
	public void setCalculatedBy(Class<? extends Judge> calculatedBy) {
		this.calculatedBy = calculatedBy;
	}

	@Override
	public boolean equals(Object obj) {
		if (this.name.equalsIgnoreCase(((Person) obj).getName())) {
			return true;
		}
		return false;
	}
	
	/*public void sortBy(Judge calculator)
			throws ServletException {
		new Judge2().computeScore(this, calculator);
		Collections.sort(this.relationships);
		this.setCalculatedBy(calculator.getClass());
	}*/

	@Override
	public String toString() {
		return "[Person: id: " + this.id + ", name: " + this.name
				+ ", relationshipNumber: " + this.relationshipsNumber + "]";
	}
}

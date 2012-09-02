package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;

import model.comparator.RelationshipComparator;

public class Person extends Model implements Comparable<Person>, Comparator<Person> {

	private int id;
	private String name;
	private List<Relationship> relationships;
	private Class<? extends RelationshipComparator> orderBy = null;
	private int relationshipsNumber;
	private int pageOccurrencesNumber;
	private double score;

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

	public Class<? extends RelationshipComparator> getOrderBy() {
		return orderBy;
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
	
	public double getScore() {
		return score;
	}
	
	public void setScore(double score) {
		this.score = score;
	}

	private void setOrderBy(Class<? extends RelationshipComparator> orderBy) {
		this.orderBy = orderBy;
	}

	@Override
	public boolean equals(Object obj) {
		if (this.name.equalsIgnoreCase(((Person) obj).getName())) {
			return true;
		}
		return false;
	}

	public void sortBy(RelationshipComparator comparator)
			throws ServletException {
		for (Relationship relationship : this.relationships) {
			try {
				relationship.calculateRank(comparator.getRank());
			} catch (ServletException e) {
				throw new ServletException(e);
			}
		}
		Collections.sort(this.relationships, comparator);
		this.setOrderBy(comparator.getClass());
	}

	@Override
	public String toString() {
		return "[Person: id: " + this.id + ", name: " + this.name
				+ ", relationshipNumber: " + this.relationshipsNumber + "]";
	}

	@Override
	public int compareTo(Person person) {
		return this.name.compareTo(person.getName());
	}

	@Override
	public int compare(Person p1, Person p2) {
		if (p1.getScore() == p2.getScore()) {
			return 0;
		} else if (p1.getScore() < p2.getScore()) {
			return 1;
		} else {
			return -1;
		}
	}

}

package model;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

public class Relationship extends Model implements Comparable<Relationship> {

	private Person person1;
	private Person person2;
	private List<RelationshipLink> relationshipLinks;
	private int relationshipsLinksNumber;
	private double averageDistance;
	private double maxDistance;
	private double minDistance;
	private double averageDistanceAndRelationshipsLinksNumber;
	private double averageDistanceAndPerson2RelationshipsNumber;
	private double popularity;
	private double score;

	public Relationship() {
		this.relationshipLinks = new ArrayList<>();
		this.relationshipsLinksNumber = 0;
		this.averageDistance = 0;
		this.maxDistance = Double.MIN_VALUE;
		this.minDistance = Double.MAX_VALUE;
		this.averageDistanceAndRelationshipsLinksNumber = 0;
		this.averageDistanceAndPerson2RelationshipsNumber = 0;
		this.popularity = 0;
		this.score = 0;
	}

	public Relationship(Person person1, Person person2,
			ArrayList<RelationshipLink> relationshipLinks)
			throws ServletException {
		this.person1 = person1;
		this.person2 = person2;
		this.relationshipLinks = relationshipLinks;
		this.relationshipsLinksNumber = 0;
		this.averageDistance = 0;
		this.maxDistance = Double.MIN_VALUE;
		this.minDistance = Double.MAX_VALUE;
		this.averageDistanceAndRelationshipsLinksNumber = 0;
		this.averageDistanceAndPerson2RelationshipsNumber = 0;
		this.popularity = 0;
		this.score = 0;
	}

	public Person getPerson1() {
		return person1;
	}

	public void setPerson1(Person person1) {
		this.person1 = person1;
	}

	public Person getPerson2() {
		return person2;
	}

	public void setPerson2(Person person2) {
		this.person2 = person2;
	}

	public List<RelationshipLink> getRelationshipLinks() {
		return relationshipLinks;
	}

	public void setRelationshipLinks(List<RelationshipLink> relationshipLinks) {
		this.relationshipLinks = relationshipLinks;
	}

	public boolean addRelationshipLink(RelationshipLink relationshipLink) {
		return this.relationshipLinks.add(relationshipLink);
	}

	public boolean removeRelationshipLink(RelationshipLink relationshipLink) {
		return this.relationshipLinks.remove(relationshipLink);
	}

	public int getRelationshipsLinksNumber() {
		return relationshipsLinksNumber;
	}

	public void setRelationshipsLinksNumber(int relationshipsLinksNumber) {
		this.relationshipsLinksNumber = relationshipsLinksNumber;
	}
	
	public double getAverageDistance() {
		return averageDistance;
	}

	public void setAverageDistance(double averageDistance) {
		this.averageDistance = averageDistance;
	}

	public double getMaxDistance() {
		return maxDistance;
	}

	public void setMaxDistance(double maxDistance) {
		this.maxDistance = maxDistance;
	}

	public double getMinDistance() {
		return minDistance;
	}

	public void setMinDistance(double minDistance) {
		this.minDistance = minDistance;
	}

	public double getAverageDistanceAndRelationshipsLinksNumber() {
		return averageDistanceAndRelationshipsLinksNumber;
	}

	public void setAverageDistanceAndRelationshipsLinksNumber(
			double averageDistanceAndRelationshipsLinksNumber) {
		this.averageDistanceAndRelationshipsLinksNumber = averageDistanceAndRelationshipsLinksNumber;
	}

	public double getAverageDistanceAndPerson2RelationshipsNumber() {
		return averageDistanceAndPerson2RelationshipsNumber;
	}

	public void setAverageDistanceAndPerson2RelationshipsNumber(
			double averageDistanceAndPerson2RelationshipsNumber) {
		this.averageDistanceAndPerson2RelationshipsNumber = averageDistanceAndPerson2RelationshipsNumber;
	}
	
	public double getPopularity() {
		return popularity;
	}
	
	public void setPopularity(double popularity) {
		this.popularity = popularity;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "\t[Relationship: Person1: " + this.person1 + ", Person2: "
				+ this.person2 + "]";
	}

	@Override
	public int compareTo(Relationship r) {
		if (this.getScore() == r.getScore()) {
			return 0;
		} else if (this.getScore() < r.getScore()) {
			return 1;
		} else {
			return -1;
		}
	}
}

package model;

import java.util.ArrayList;

import javax.servlet.ServletException;

import model.rank.RelationshipRank;

public class Relationship extends Model {

	private Person person1;
	private Person person2;
	private ArrayList<RelationshipLink> relationshipLinks;
	private int relationshipsLinksNumber;
	private double averageDistance;
	private int maxDistance;
	private int minDistance;
	private double averageDistanceAndRelationshipsLinksNumber;
	private double averageDistanceAndPerson2RelationshipsNumber;

	public Relationship() {
		this.relationshipLinks = new ArrayList<>();
		this.relationshipsLinksNumber = 0;
		this.averageDistance = 0;
		this.maxDistance = Integer.MIN_VALUE;
		this.minDistance = Integer.MAX_VALUE;
		this.averageDistanceAndRelationshipsLinksNumber = 0;
		this.averageDistanceAndPerson2RelationshipsNumber = 0;
	}

	public Relationship(Person person1, Person person2,
			ArrayList<RelationshipLink> relationshipLinks)
			throws ServletException {
		this.person1 = person1;
		this.person2 = person2;
		this.relationshipLinks = relationshipLinks;
		this.relationshipsLinksNumber = 0;
		this.averageDistance = 0;
		this.maxDistance = Integer.MIN_VALUE;
		this.minDistance = Integer.MAX_VALUE;
		this.averageDistanceAndRelationshipsLinksNumber = 0;
		this.averageDistanceAndPerson2RelationshipsNumber = 0;
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

	public ArrayList<RelationshipLink> getRelationshipLinks() {
		return relationshipLinks;
	}

	public void setRelationshipLinks(
			ArrayList<RelationshipLink> relationshipLinks) {
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

	public int getMaxDistance() {
		return maxDistance;
	}

	public void setMaxDistance(int maxDistance) {
		this.maxDistance = maxDistance;
	}

	public int getMinDistance() {
		return minDistance;
	}

	public void setMinDistance(int minDistance) {
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

	public void calculateRank(Class<? extends RelationshipRank> rankType)
			throws ServletException {
		RelationshipRank rr = null;
		try {
			rr = rankType.newInstance();
			rr.rank(this);
		} catch (InstantiationException | IllegalAccessException e) {
			throw new ServletException(e);
		}
	}

	@Override
	public String toString() {
		return "\t[Relationship: Person1: " + this.person1 + ", Person2: "
				+ this.person2 + "]";
	}

}

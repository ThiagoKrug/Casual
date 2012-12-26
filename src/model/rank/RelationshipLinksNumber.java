package model.rank;

import java.util.List;

import model.Person;
import model.Relationship;

public class RelationshipLinksNumber extends Judge {

	@Override
	public void rank(Person person) {
		List<Relationship> relationships = person.getRelationships();
		for (Relationship relationship : relationships) {
			relationship.setScore(relationship.getRelationshipLinks().size());
		}
		person.setCalculatedBy(RelationshipLinksNumber.class);
	}
}
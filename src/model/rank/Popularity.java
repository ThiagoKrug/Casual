package model.rank;

import model.Person;
import model.Relationship;

public class Popularity extends Judge {

	@Override
	public void rank(Person person) {
		for (Relationship relationship : person.getRelationships()) {
			relationship.setScore(relationship.getPerson2().getPopularity());
		}
	}

}

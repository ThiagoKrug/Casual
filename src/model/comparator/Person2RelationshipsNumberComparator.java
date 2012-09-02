package model.comparator;

import model.Model;
import model.Relationship;
import model.rank.Person2RelationshipsNumber;

public class Person2RelationshipsNumberComparator extends RelationshipComparator {
	
	public Person2RelationshipsNumberComparator() {
		super.rankType = Person2RelationshipsNumber.class;
	}

	@Override
	public int compare(Relationship r1, Relationship r2) {
		if (r1.getPerson2().getRelationshipsNumber() == r2.getPerson2().getRelationshipsNumber()) {
			return 0;
		} else if (r1.getPerson2().getRelationshipsNumber() < r2.getPerson2().getRelationshipsNumber()) {
			return 1;
		}
		return -1;
	}

	@Override
	public void printRank(Model model) {
		Relationship relationship = (Relationship) model;
		System.out.println(", rank: " + relationship.getPerson2().getRelationshipsNumber());
	}
	
}

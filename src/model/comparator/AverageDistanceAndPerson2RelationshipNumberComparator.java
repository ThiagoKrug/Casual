package model.comparator;

import model.Model;
import model.Relationship;
import model.rank.AverageDistanceAndPerson2RelationshipNumber;

public class AverageDistanceAndPerson2RelationshipNumberComparator extends
		RelationshipComparator {
	
	public AverageDistanceAndPerson2RelationshipNumberComparator() {
		super.rankType = AverageDistanceAndPerson2RelationshipNumber.class;
	}

	@Override
	public int compare(Relationship r1, Relationship r2) {
		if ((r1.getAverageDistanceAndPerson2RelationshipsNumber()) == (r2
				.getAverageDistanceAndPerson2RelationshipsNumber())) {
			return 0;
		} else if ((r1.getAverageDistanceAndPerson2RelationshipsNumber()) < (r2
				.getAverageDistanceAndPerson2RelationshipsNumber())) {
			return 1;
		}
		return -1;
	}

	@Override
	public void printRank(Model model) {
		Relationship relationship = (Relationship) model;
		System.out.println(", rank: " + relationship.getAverageDistanceAndPerson2RelationshipsNumber());
	}

}

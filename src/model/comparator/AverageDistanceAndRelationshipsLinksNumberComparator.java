package model.comparator;

import model.Model;
import model.Relationship;
import model.rank.AverageDistanceAndRelationshipsLinksNumber;

public class AverageDistanceAndRelationshipsLinksNumberComparator extends
		RelationshipComparator {
	
	public AverageDistanceAndRelationshipsLinksNumberComparator() {
		super.rankType = AverageDistanceAndRelationshipsLinksNumber.class;
	}

	@Override
	public int compare(Relationship r1, Relationship r2) {
		if ((r1.getAverageDistanceAndRelationshipsLinksNumber() == (r2
				.getAverageDistanceAndRelationshipsLinksNumber()))) {
			return 0;
		} else if ((r1.getAverageDistanceAndRelationshipsLinksNumber() < (r2
				.getAverageDistanceAndRelationshipsLinksNumber()))) {
			return 1;
		}
		return -1;
	}

	@Override
	public void printRank(Model model) {
		Relationship relationship = (Relationship) model;
		System.out.println(", rank: " + relationship.getAverageDistanceAndRelationshipsLinksNumber());
	}

}

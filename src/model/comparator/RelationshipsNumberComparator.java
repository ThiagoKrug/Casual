package model.comparator;

import model.Model;
import model.Relationship;
import model.rank.RelationshipsNumber;

public class RelationshipsNumberComparator extends RelationshipComparator {
	
	public RelationshipsNumberComparator() {
		super.rankType = RelationshipsNumber.class;
	}

	@Override
	public int compare(Relationship r1, Relationship r2) {
		if (r1.getRelationshipsLinksNumber() == r2
				.getRelationshipsLinksNumber()) {
			return 0;
		} else if (r1.getRelationshipsLinksNumber() < r2
				.getRelationshipsLinksNumber()) {
			return 1;
		}
		return -1;
	}

	@Override
	public void printRank(Model model) {
		Relationship relationship = (Relationship) model;
		System.out.println(", rank: " + relationship.getRelationshipsLinksNumber());
	}
	
}

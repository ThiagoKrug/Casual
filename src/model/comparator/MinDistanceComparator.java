package model.comparator;

import model.Model;
import model.Relationship;
import model.rank.MinDistance;

public class MinDistanceComparator extends RelationshipComparator {
	
	public MinDistanceComparator() {
		super.rankType = MinDistance.class;
	}

	@Override
	public int compare(Relationship r1, Relationship r2) {
		if (r1.getMinDistance() == r2.getMinDistance()) {
			return 0;
		} else if (r1.getMinDistance() > r2.getMinDistance()) {
			return 1;
		}
		return -1;
	}

	@Override
	public void printRank(Model model) {
		Relationship relationship = (Relationship) model;
		System.out.println(", rank: " + relationship.getMinDistance());
	}
	
}

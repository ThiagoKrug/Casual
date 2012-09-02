package model.comparator;

import model.Model;
import model.Relationship;
import model.rank.MaxDistance;

public class MaxDistanceComparator extends RelationshipComparator {
	
	public MaxDistanceComparator() {
		super.rankType = MaxDistance.class;
	}

	@Override
	public int compare(Relationship r1, Relationship r2) {
		if (r1.getMaxDistance() == r2.getMaxDistance()) {
			return 0;
		} else if (r1.getMaxDistance() > r2.getMaxDistance()) {
			return 1;
		}
		return -1;
	}

	@Override
	public void printRank(Model model) {
		Relationship relationship = (Relationship) model;
		System.out.println(", rank: " + relationship.getMaxDistance());
	}
	
}

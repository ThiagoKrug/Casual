package model.comparator;

import model.Model;
import model.Relationship;
import model.rank.AverageDistance;

public class AverageDistanceComparator extends RelationshipComparator {
	
	public AverageDistanceComparator() {
		super.rankType = AverageDistance.class;
	}

	@Override
	public int compare(Relationship r1, Relationship r2) {
		if (r1.getAverageDistance() == r2.getAverageDistance()) {
			return 0;
		} else if (r1.getAverageDistance() > r2.getAverageDistance()) {
			return 1;
		}
		return -1;
	}

	@Override
	public void printRank(Model model) {
		Relationship relationship = (Relationship) model;
		System.out.println(", rank: " + relationship.getAverageDistance());
	}
	
}

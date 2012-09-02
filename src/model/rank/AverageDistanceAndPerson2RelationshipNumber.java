package model.rank;

import model.Model;
import model.Relationship;

public class AverageDistanceAndPerson2RelationshipNumber extends
		RelationshipRank {

	@Override
	public void rank(Model model) {
		Relationship relationship = (Relationship) model;
		relationship
				.setAverageDistanceAndPerson2RelationshipsNumber(relationship
						.getPerson2().getRelationshipsNumber()
						/ relationship.getAverageDistance());
	}

}

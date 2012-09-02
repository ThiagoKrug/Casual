package model.rank;

import model.Model;
import model.Relationship;

public class AverageDistanceAndRelationshipsLinksNumber extends
		RelationshipRank {

	@Override
	public void rank(Model model) {
		Relationship relationship = (Relationship) model;
		relationship.setAverageDistanceAndRelationshipsLinksNumber(relationship
				.getRelationshipsLinksNumber()
				/ relationship.getAverageDistance());
	}

}

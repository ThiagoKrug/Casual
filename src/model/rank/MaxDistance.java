package model.rank;

import model.Model;
import model.Relationship;
import model.RelationshipLink;

public class MaxDistance extends RelationshipRank {

	@Override
	public void rank(Model model) {
		Relationship relationship = (Relationship) model;
		for (RelationshipLink relationshipLink : relationship
				.getRelationshipLinks()) {
			if (relationshipLink.getMaxDistance() > relationship.getMaxDistance()) {
				relationship.setMaxDistance(relationshipLink.getMaxDistance());
			}
		}
	}

}

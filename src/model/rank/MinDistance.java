package model.rank;

import model.Model;
import model.Relationship;
import model.RelationshipLink;

public class MinDistance extends RelationshipRank {

	@Override
	public void rank(Model model) {
		Relationship relationship = (Relationship) model;
		for (RelationshipLink relationshipLink : relationship
				.getRelationshipLinks()) {
			if (relationshipLink.getMinDistance() < relationship.getMinDistance()) {
				relationship.setMinDistance(relationshipLink.getMinDistance());
			}
		}
	}

}

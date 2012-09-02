package model.rank;

import model.Model;
import model.Relationship;
import model.RelationshipLink;

public class RelationshipsNumber extends RelationshipRank {

	@Override
	public void rank(Model model) {
		Relationship relationship = (Relationship) model;
		int count = 0;
		for (RelationshipLink relationshipLink : relationship.getRelationshipLinks()) {
			count = count + relationshipLink.getOccurrenceNumber();
		}
		relationship.setRelationshipsLinksNumber(count);
	}

}

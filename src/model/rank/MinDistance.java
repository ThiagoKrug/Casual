package model.rank;

import java.util.List;

import model.Person;
import model.Relationship;
import model.RelationshipLink;

public class MinDistance extends Judge {

	@Override
	public void rank(Person person) {
		List<Relationship> relationships = person.getRelationships();
		for (Relationship relationship : relationships) {
			List<RelationshipLink> rlinks = relationship.getRelationshipLinks();
			for (RelationshipLink relationshipLink : rlinks) {
				if (relationshipLink.getMinDistance() < relationship
						.getMinDistance()) {
					relationship.setMinDistance(relationshipLink
							.getMinDistance());
				}
			}
			relationship.setScore(relationship.getMinDistance());
		}
		person.setCalculatedBy(MinDistance.class);
	}

}

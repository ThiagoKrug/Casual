package model.rank;

import java.util.List;

import model.Person;
import model.Relationship;
import model.RelationshipLink;

public class MaxDistance extends Judge {

	@Override
	public void rank(Person person) {
		List<Relationship> relationships = person.getRelationships();
		for (Relationship relationship : relationships) {
			List<RelationshipLink> rlinks = relationship.getRelationshipLinks();
			for (RelationshipLink relationshipLink : rlinks) {
				if (relationshipLink.getMaxDistance() > relationship
						.getMaxDistance()) {
					relationship.setMaxDistance(relationshipLink
							.getMaxDistance());
				}
			}
			relationship.setScore(relationship.getMaxDistance());
		}
		person.setCalculatedBy(MaxDistance.class);
	}

}

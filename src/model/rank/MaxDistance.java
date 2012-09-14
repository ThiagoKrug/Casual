package model.rank;

import java.util.List;

import model.Person;
import model.Relationship;
import model.RelationshipLink;

public class MaxDistance extends Judge {

	@Override
	public void rank(Person person) {
		List<Relationship> relationships = person.getRelationships();
		double aux = Double.MIN_VALUE;
		for (Relationship relationship : relationships) {
			List<RelationshipLink> rlinks = relationship.getRelationshipLinks();
			for (RelationshipLink relationshipLink : rlinks) {
				if (relationshipLink.getMaxDistance() > relationship
						.getMaxDistance()) {
					relationship.setMaxDistance(relationshipLink
							.getMaxDistance());
				}
			}
			if (aux < relationship.getMaxDistance()) {
				aux = relationship.getMaxDistance();
			}
		}
		for (Relationship relationship : relationships) {
			relationship.setScore(1.0d - (relationship.getMaxDistance() / aux));
		}
		person.setCalculatedBy(MaxDistance.class);
	}

}

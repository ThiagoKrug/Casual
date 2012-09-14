package model.rank;

import java.util.List;

import model.Person;
import model.Relationship;
import model.RelationshipLink;

public class MinDistance extends Judge {

	@Override
	public void rank(Person person) {
		List<Relationship> relationships = person.getRelationships();
		MaxDistance md = new MaxDistance();
		md.rank(person);
		double aux = Double.MIN_VALUE;
		for (Relationship relationship : relationships) {
			if (aux < relationship.getMaxDistance()) {
				aux = relationship.getMaxDistance();
			}
		}
		for (Relationship relationship : relationships) {
			List<RelationshipLink> rlinks = relationship.getRelationshipLinks();
			for (RelationshipLink relationshipLink : rlinks) {
				if (relationshipLink.getMinDistance() < relationship
						.getMinDistance()) {
					relationship.setMinDistance(relationshipLink
							.getMinDistance());
				}
			}
			//relationship.setMinDistance(1.0d - (relationship.getMinDistance() / aux));
			relationship.setScore(1.0d - (relationship.getMinDistance() / aux));
		}
		person.setCalculatedBy(MinDistance.class);
	}

}

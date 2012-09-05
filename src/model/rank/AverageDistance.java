package model.rank;

import java.util.List;

import model.Person;
import model.Relationship;
import model.RelationshipLink;

public class AverageDistance extends Judge {

	@Override
	public void rank(Person person) {
		List<Relationship> relationships = person.getRelationships();
		MaxDistance md = new MaxDistance();
		md.rank(person);
		int aux = Integer.MIN_VALUE;
		for (Relationship relationship : relationships) {
			if (aux < relationship.getMaxDistance()) {
				aux = relationship.getMaxDistance();
			}
		}
		for (Relationship relationship : relationships) {
			List<RelationshipLink> rlinks = relationship.getRelationshipLinks();
			int sum = 0;
			for (RelationshipLink rlink : rlinks) {
				sum += rlink.getAverageDistance();
			}
			relationship.setAverageDistance(aux / (sum / rlinks.size()));
			relationship.setScore(relationship.getAverageDistance());
		}
		person.setCalculatedBy(AverageDistance.class);
	}

}

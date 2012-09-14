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
		double aux = Double.MIN_VALUE;
		for (Relationship relationship : relationships) {
			if (aux < relationship.getMaxDistance()) {
				aux = relationship.getMaxDistance();
			}
		}
		for (Relationship relationship : relationships) {
			List<RelationshipLink> rlinks = relationship.getRelationshipLinks();
			double sum = 0;
			for (RelationshipLink rlink : rlinks) {
				sum += rlink.getAverageDistance();
			}
			//relationship.setAverageDistance(1.0d - ((sum / rlinks.size()) / aux));
			relationship.setScore(1.0d - ((sum / rlinks.size()) / aux));
		}
		person.setCalculatedBy(AverageDistance.class);
	}

}

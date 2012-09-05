package model.rank;

import java.util.List;

import model.Person;
import model.Relationship;

/**
 * Realiza o cálculo da distância média entre a pessoa 1 e pessoa 2 e a
 * popularidade da pessoa 2
 * 
 * @author thiago
 * 
 */
public class AverageDistanceAndPerson2RelationshipNumber extends
		Judge {

	@Override
	public void rank(Person person) {
		AverageDistance ad = new AverageDistance();
		RelationshipsNumber rn = new RelationshipsNumber();
		List<Relationship> relationships = person.getRelationships();
		for (Relationship relationship : relationships) {
			ad.rank(person);
			rn.rank(relationship.getPerson2());
			relationship
					.setAverageDistanceAndPerson2RelationshipsNumber(relationship
							.getPerson2().getRelationshipsNumber()
							/ relationship.getAverageDistance());
			relationship.setScore(relationship
					.getAverageDistanceAndPerson2RelationshipsNumber());
		}
		person.setCalculatedBy(AverageDistanceAndPerson2RelationshipNumber.class);
	}

}

package model.rank;

import java.util.List;

import model.Person;
import model.Relationship;

/**
 * Realiza o cálculo da distância média e o número de ocorrências entre a pessoa
 * 1 e pessoa 2.
 * 
 * @author thiago
 * 
 */
public class AverageDistanceAndRelationshipsLinksNumber extends
		Judge {

	@Override
	public void rank(Person person) {
		AverageDistance ad = new AverageDistance();
		RelationshipOccurrenceNumber rln = new RelationshipOccurrenceNumber();
		List<Relationship> relationships = person.getRelationships();
		for (Relationship relationship : relationships) {
			ad.rank(person);
			rln.rank(person);
			relationship.setAverageDistanceAndRelationshipsLinksNumber(relationship
							.getRelationshipsLinksNumber()
							/ relationship.getAverageDistance());
			relationship.setScore(relationship
					.getAverageDistanceAndRelationshipsLinksNumber());
		}
		person.setCalculatedBy(AverageDistanceAndRelationshipsLinksNumber.class);
	}

}

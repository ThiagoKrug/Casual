package model.rank;

import java.util.List;

import model.Person;
import model.Relationship;

/**
 * Realiza o cálculo do número de ocorrências de uma pessoa, ou seja, sua
 * "popularidade".
 * 
 * @author thiago
 * 
 */
public class RelationshipsNumber extends Judge {

	@Override
	public void rank(Person person) {
		List<Relationship> relationships = person.getRelationships();
		RelationshipOccurrenceNumber ron = new RelationshipOccurrenceNumber();
		int count = 0;
		for (Relationship relationship : relationships) {
			ron.rank(relationship.getPerson2());
			count = count + relationship.getRelationshipsLinksNumber();
		}
		person.setRelationshipsNumber(count);
		person.setCalculatedBy(RelationshipsNumber.class);
	}
}

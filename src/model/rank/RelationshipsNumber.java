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
		RelationshipLinksNumber rln = new RelationshipLinksNumber();
		int count = 0;
		for (Relationship relationship : relationships) {
			rln.rank(relationship.getPerson2());
			count = count + relationship.getRelationshipsLinksNumber();
		}
		person.setRelationshipsNumber(count);
		person.setCalculatedBy(RelationshipsNumber.class);
	}
}

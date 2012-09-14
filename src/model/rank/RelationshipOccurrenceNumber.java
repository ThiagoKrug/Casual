package model.rank;

import java.util.List;

import model.Person;
import model.Relationship;
import model.RelationshipLink;

/**
 * Realiza o cálculo do número de ocorrências entre duas pessoas. Ou seja, o
 * número de vezes que elas aparecem juntas.
 * 
 * @author thiago
 * 
 */
public class RelationshipOccurrenceNumber extends Judge {

	@Override
	public void rank(Person person) {
		List<Relationship> relationships = person.getRelationships();
		// double aux = Double.MIN_VALUE;
		int aux = Integer.MIN_VALUE;
		for (Relationship relationship : relationships) {
			int count = 0;
			for (RelationshipLink relationshipLink : relationship
					.getRelationshipLinks()) {
				count = count + relationshipLink.getOccurrenceNumber();
			}
			relationship.setRelationshipsLinksNumber(count);
			if (aux < relationship.getRelationshipsLinksNumber()) {
				aux = relationship.getRelationshipsLinksNumber();
			}
		}
		for (Relationship relationship : relationships) {
			relationship.setScore((double) relationship
					.getRelationshipsLinksNumber() / (double) aux);
		}
		person.setCalculatedBy(RelationshipOccurrenceNumber.class);
	}
}

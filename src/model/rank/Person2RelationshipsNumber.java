package model.rank;

import java.util.List;

import model.Person;
import model.Relationship;

/**
 * Realiza o cálculo da popularidade das pessoas relacionadas com esta primeira.
 * 
 * @author thiago
 *
 */
public class Person2RelationshipsNumber extends Judge {

	@Override
	public void rank(Person person) {
		List<Relationship> relationships = person.getRelationships();
		RelationshipsNumber rn = new RelationshipsNumber();
		for (Relationship relationship : relationships) {
			Person person2 = relationship.getPerson2();
			rn.rank(person2);
			relationship.setScore(person2.getRelationshipsNumber());
		}
		person.setCalculatedBy(Person2RelationshipsNumber.class);
	}

}

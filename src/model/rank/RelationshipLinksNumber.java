package model.rank;

import java.util.List;

import model.Model;
import model.Person;
import model.Relationship;

public class RelationshipLinksNumber extends RelationshipRank {

	@Override
	public void rank(Model model) {
		Person person = (Person) model;
		List<Relationship> relationships = person.getRelationships();
		RelationshipsNumber rn = new RelationshipsNumber();
		int count = 0;
		for (Relationship relationship : relationships) {
			rn.rank(relationship);
			count = count + relationship.getRelationshipsLinksNumber();
		}
		person.setRelationshipsNumber(count);
	}

}

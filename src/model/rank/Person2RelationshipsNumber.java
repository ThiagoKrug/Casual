package model.rank;

import java.util.ArrayList;

import model.Model;
import model.Relationship;

public class Person2RelationshipsNumber extends RelationshipRank {

	@Override
	public void rank(Model model) {
		Relationship r = (Relationship) model;
		RelationshipLinksNumber rn = new RelationshipLinksNumber();
		ArrayList<Relationship> relationships = r.getPerson2().getRelationships();
		for (Relationship relationship : relationships) {
			rn.rank(relationship.getPerson2());
		}
	}

}

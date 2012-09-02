package model.rank;

import java.util.List;

import model.Model;
import model.Relationship;
import model.RelationshipLink;

public class AverageDistance extends RelationshipRank {

	@Override
	public void rank(Model model) {
		Relationship relationship = (Relationship) model;
		List<RelationshipLink> rlinks = relationship.getRelationshipLinks();
		int sum = 0;
		for (RelationshipLink rlink : rlinks) {
			sum += rlink.getAverageDistance();
		}
		relationship.setAverageDistance(sum / rlinks.size());
	}

}

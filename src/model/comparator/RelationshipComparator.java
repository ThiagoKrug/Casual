package model.comparator;

import java.util.Comparator;

import model.Model;
import model.Relationship;
import model.rank.RelationshipRank;

public abstract class RelationshipComparator implements Comparator<Relationship> {
	
	protected Class<? extends RelationshipRank> rankType;
	
	public abstract void printRank(Model model);
	
	public Class<? extends RelationshipRank> getRank() {
		return this.rankType;
	}
}

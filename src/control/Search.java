package control;

import java.util.List;

import javax.servlet.ServletException;

import model.Person;
import model.Relationship;
import model.comparator.RelationshipComparator;

public interface Search {
	
	public String didYouMean(String search, double minScore);
	
	public List<Relationship> searchBy(Person search, RelationshipComparator comparator) throws ServletException;
	
	public List<Relationship> searchBy(String search, RelationshipComparator comparator) throws ServletException;

}

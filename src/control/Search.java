package control;

import java.util.ArrayList;

import javax.servlet.ServletException;

import model.Person;
import model.Relationship;
import model.comparator.RelationshipComparator;

public interface Search {
	
	public String didYouMean(String search, double minScore);
	
	public ArrayList<Relationship> searchBy(Person search, RelationshipComparator comparator) throws ServletException;
	
	public ArrayList<Relationship> searchBy(String search, RelationshipComparator comparator) throws ServletException;

}

package control;

import java.util.List;

import javax.servlet.ServletException;

import model.Person;
import model.Relationship;
import model.rank.Judge;

public interface Search {
	
	public String didYouMean(String search, double minScore);

	public List<Relationship> searchBy(Person search, Judge calculator) throws ServletException;
	
	public List<Relationship> searchBy(String search, Judge calculator) throws ServletException;

}

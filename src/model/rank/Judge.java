package model.rank;

import java.util.List;

import model.Person;
import model.Relationship;

public abstract class Judge {

	public abstract void rank(Person person);

	public void computeScore(Person person, boolean computePopularity) {
		if (person.getCalculatedBy() != this.getClass()) {
			this.rank(person);

			if (computePopularity) {
				double soma = 0;
				for (Relationship relationship : person.getRelationships()) {
					soma += relationship.getScore();
				}
				person.setPopularity(soma);
			}
		}
	}

	public void computeScore(List<Person> persons, boolean computePopularity) {
		for (Person person : persons) {
			this.computeScore(person, computePopularity);
		}
	}

}

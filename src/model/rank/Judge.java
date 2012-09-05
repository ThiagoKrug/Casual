package model.rank;

import model.Person;

public abstract class Judge {
	
	public abstract void rank(Person person);
	
	public void computeScore(Person person) {
		if (person.getCalculatedBy() != this.getClass()) {
			this.rank(person);
		}
	}

}

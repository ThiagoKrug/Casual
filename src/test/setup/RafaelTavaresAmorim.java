package test.setup;

import java.sql.Connection;

import model.Person;
import model.Relationship;
import model.RelationshipLink;
import dao.RelationshipDAO;

public class RafaelTavaresAmorim {

	public RafaelTavaresAmorim(Connection connection) {
		setRelationshipsJuliano(connection);
	}

	/**
	 * Cria os relacionamentos para a pessoa Rafael Tavares Amorim e a pessoa
	 * Juliano Rodovalho Macedo
	 */
	public static void setRelationshipsJuliano(Connection connection) {
		RelationshipDAO rdao = new RelationshipDAO(connection);
		Relationship relationship = new Relationship();

		// relacionamento entre Rafael e Juliano Rodovalho Macedo no link
		// "http://www.google.com.br/"
		RelationshipLink rLink1 = new RelationshipLink();
		rLink1.setAverageDistance(1286);
		rLink1.setLink(SetUp.GOOGLE);
		rLink1.setMaxDistance(8618);
		rLink1.setMinDistance(89);
		rLink1.setOccurrenceNumber(6);
		relationship.addRelationshipLink(rLink1);

		// relacionamento entre Rafael e Juliano Rodovalho Macedo no link
		// "http://pt.wikipedia.org/"
		RelationshipLink rLink2 = new RelationshipLink();
		rLink2.setAverageDistance(397);
		rLink2.setLink(SetUp.WIKIPEDIA);
		rLink2.setMaxDistance(1825);
		rLink2.setMinDistance(94);
		rLink2.setOccurrenceNumber(4);
		relationship.addRelationshipLink(rLink2);

		// relacionamento entre Rafael e Juliano Rodovalho Macedo no link
		// "http://unipampa.edu.br/"
		RelationshipLink rLink3 = new RelationshipLink();
		rLink3.setAverageDistance(907);
		rLink3.setLink(SetUp.UNIPAMPA);
		rLink3.setMaxDistance(1395);
		rLink3.setMinDistance(4);
		rLink3.setOccurrenceNumber(6);
		relationship.addRelationshipLink(rLink3);

		// insere os relacionamentos no banco
		relationship.setPerson1(new Person(9, "", null)); // id Rafael == 9
		relationship.setPerson2(new Person(10, "", null)); // id Juliano
															// Rodovalho Macedo
															// == 10
		rdao.insert(relationship);
	}
}

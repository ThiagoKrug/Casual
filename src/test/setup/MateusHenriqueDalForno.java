package test.setup;

import java.sql.Connection;

import model.Person;
import model.Relationship;
import model.RelationshipLink;
import dao.RelationshipDAO;

public class MateusHenriqueDalForno {

	public MateusHenriqueDalForno(Connection connection) {
		setRelationshipsRafhael(connection);
		setRelationshipsThiago(connection);
		// setRelationshipsRenan(connection);
		setRelationshipsRafael(connection);
		setRelationshipsJuliano(connection);
	}

	/**
	 * Cria os relacionamentos para a pessoa Mateus Henrique Dal Forno e a
	 * pessoa Rafhael Rodrigues Cunha
	 */
	public static void setRelationshipsRafhael(Connection connection) {
		RelationshipDAO rdao = new RelationshipDAO(connection);
		Relationship relationship = new Relationship();

		// relacionamento entre Mateus e Rafhael Rodrigues Cunha no link
		// "http://www.google.com.br/"
		RelationshipLink rLink1 = new RelationshipLink();
		rLink1.setAverageDistance(400);
		rLink1.setLink(SetUp.GOOGLE);
		rLink1.setMaxDistance(23734);
		rLink1.setMinDistance(247);
		rLink1.setOccurrenceNumber(3);
		relationship.addRelationshipLink(rLink1);

		// relacionamento entre Mateus e Rafhael Rodrigues Cunha no link
		// "http://pt.wikipedia.org/"
		RelationshipLink rLink2 = new RelationshipLink();
		rLink2.setAverageDistance(194);
		rLink2.setLink(SetUp.WIKIPEDIA);
		rLink2.setMaxDistance(2447);
		rLink2.setMinDistance(55);
		rLink2.setOccurrenceNumber(4);
		relationship.addRelationshipLink(rLink2);

		// relacionamento entre Mateus e Rafhael Rodrigues Cunha no link
		// "http://unipampa.edu.br/"
		RelationshipLink rLink3 = new RelationshipLink();
		rLink3.setAverageDistance(388);
		rLink3.setLink(SetUp.UNIPAMPA);
		rLink3.setMaxDistance(3942);
		rLink3.setMinDistance(87);
		rLink3.setOccurrenceNumber(5);
		relationship.addRelationshipLink(rLink3);

		// insere os relacionamentos no banco
		relationship.setPerson1(new Person(5, "", null)); // id Mateus == 5
		relationship.setPerson2(new Person(6, "", null)); // id Rafhael
															// Rodrigues Cunha
															// == 6
		rdao.insert(relationship);
	}

	/**
	 * Cria os relacionamentos para a pessoa Mateus Henrique Dal Forno e a
	 * pessoa Thiago Aquino de Lima
	 */
	public static void setRelationshipsThiago(Connection connection) {
		RelationshipDAO rdao = new RelationshipDAO(connection);
		Relationship relationship = new Relationship();

		// relacionamento entre Mateus e Thiago Aquino de Lima no link
		// "http://www.google.com.br/"
		RelationshipLink rLink1 = new RelationshipLink();
		rLink1.setAverageDistance(2009);
		rLink1.setLink(SetUp.GOOGLE);
		rLink1.setMaxDistance(34310);
		rLink1.setMinDistance(473);
		rLink1.setOccurrenceNumber(1);
		relationship.addRelationshipLink(rLink1);

		// relacionamento entre Mateus e Thiago Aquino de Lima no link
		// "http://pt.wikipedia.org/"
		RelationshipLink rLink2 = new RelationshipLink();
		rLink2.setAverageDistance(294);
		rLink2.setLink(SetUp.WIKIPEDIA);
		rLink2.setMaxDistance(294);
		rLink2.setMinDistance(294);
		rLink2.setOccurrenceNumber(1);
		relationship.addRelationshipLink(rLink2);

		// relacionamento entre Mateus e Thiago Aquino de Lima no link
		// "http://unipampa.edu.br/"
		/*
		 * RelationshipLink rLink3 = new RelationshipLink();
		 * rLink3.setAverageDistance(191); rLink3.setLink(SetUp.UNIPAMPA);
		 * rLink3.setMaxDistance(6583); rLink3.setMinDistance(34);
		 * rLink3.setOccurrenceNumber(3);
		 * relationship.addRelationshipLink(rLink3);
		 */

		// insere os relacionamentos no banco
		relationship.setPerson1(new Person(5, "", null)); // id Mateus == 5
		relationship.setPerson2(new Person(7, "", null)); // id Thiago Aquino de
															// Lima == 7
		rdao.insert(relationship);
	}

	/**
	 * Cria os relacionamentos para a pessoa Mateus Henrique Dal Forno e a
	 * pessoa Rafael Tavares Amorim
	 */
	public static void setRelationshipsRafael(Connection connection) {
		RelationshipDAO rdao = new RelationshipDAO(connection);
		Relationship relationship = new Relationship();

		// relacionamento entre Mateus e Rafael Tavares Amorim no link
		// "http://www.google.com.br/"
		/*
		 * RelationshipLink rLink1 = new RelationshipLink();
		 * rLink1.setAverageDistance(412); rLink1.setLink(SetUp.GOOGLE);
		 * rLink1.setMaxDistance(40712); rLink1.setMinDistance(200);
		 * rLink1.setOccurrenceNumber(4);
		 * relationship.addRelationshipLink(rLink1);
		 */

		// relacionamento entre Mateus e Rafael Tavares Amorim no link
		// "http://pt.wikipedia.org/"
		/*
		 * RelationshipLink rLink2 = new RelationshipLink();
		 * rLink2.setAverageDistance(63); rLink2.setLink(SetUp.WIKIPEDIA);
		 * rLink2.setMaxDistance(320); rLink2.setMinDistance(9);
		 * rLink2.setOccurrenceNumber(3);
		 * relationship.addRelationshipLink(rLink2);
		 */

		// relacionamento entre Mateus e Rafael Tavares Amorim no link
		// "http://unipampa.edu.br/"
		RelationshipLink rLink3 = new RelationshipLink();
		rLink3.setAverageDistance(87);
		rLink3.setLink(SetUp.UNIPAMPA);
		rLink3.setMaxDistance(327);
		rLink3.setMinDistance(35);
		rLink3.setOccurrenceNumber(2);
		relationship.addRelationshipLink(rLink3);

		// insere os relacionamentos no banco
		relationship.setPerson1(new Person(5, "", null)); // id Mateus == 5
		relationship.setPerson2(new Person(9, "", null)); // id Rafael Tavares
															// Amorim == 9
		rdao.insert(relationship);
	}

	/**
	 * Cria os relacionamentos para a pessoa Mateus Henrique Dal Forno e a
	 * pessoa Juliano Rodovalho Macedo
	 */
	public static void setRelationshipsJuliano(Connection connection) {
		RelationshipDAO rdao = new RelationshipDAO(connection);
		Relationship relationship = new Relationship();

		// relacionamento entre Mateus e Juliano Rodovalho Macedo no link
		// "http://www.google.com.br/"
		/*RelationshipLink rLink1 = new RelationshipLink();
		rLink1.setAverageDistance(533);
		rLink1.setLink(SetUp.GOOGLE);
		rLink1.setMaxDistance(21643);
		rLink1.setMinDistance(312);
		rLink1.setOccurrenceNumber(3);
		relationship.addRelationshipLink(rLink1);*/

		// relacionamento entre Mateus e Juliano Rodovalho Macedo no link
		// "http://pt.wikipedia.org/"
		RelationshipLink rLink2 = new RelationshipLink();
		rLink2.setAverageDistance(2370);
		rLink2.setLink(SetUp.WIKIPEDIA);
		rLink2.setMaxDistance(12949);
		rLink2.setMinDistance(340);
		rLink2.setOccurrenceNumber(3);
		relationship.addRelationshipLink(rLink2);

		/*
		 * Não existe relacionamento entre Thiago e Juliano Rodovalho Macedo
		 * nesse site // relacionamento entre Thiago e Juliano Rodovalho Macedo
		 * no link // "http://unipampa.edu.br/" RelationshipLink rLink3 = new
		 * RelationshipLink(); rLink3.setAverageDistance(371);
		 * rLink3.setLink(unipampa); rLink3.setMaxDistance(11765);
		 * rLink3.setMinDistance(19); rLink3.setOccurrenceNumber(7);
		 * relationship.addRelationshipLink(rLink3);
		 */

		// insere os relacionamentos no banco
		relationship.setPerson1(new Person(5, "", null)); // id Mateus == 5
		relationship.setPerson2(new Person(10, "", null)); // id Juliano
															// Rodovalho Macedo
															// == 10
		rdao.insert(relationship);
	}

}

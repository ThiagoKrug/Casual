package test.setup;

import java.sql.Connection;

import model.Person;
import model.Relationship;
import model.RelationshipLink;
import dao.RelationshipDAO;

public class HelisonReusTeixeira {

	public HelisonReusTeixeira(Connection connection) {
		setRelationshipsMarcelo(connection);
		setRelationshipsMateus(connection);
		setRelationshipsRafhael(connection);
		// setRelationshipsThiago(connection);
		// setRelationshipsRenan(connection);
		setRelationshipsRafael(connection);
		setRelationshipsJuliano(connection);
	}

	/**
	 * Cria os relacionamentos para a pessoa Helison Reus Teixeira e a pessoa
	 * Marcelo Maia Lopes
	 */
	public static void setRelationshipsMarcelo(Connection connection) {
		RelationshipDAO rdao = new RelationshipDAO(connection);
		Relationship relationship = new Relationship();

		// relacionamento entre Helison e Marcelo Maia Lopes no link
		// "http://www.google.com.br/"
		RelationshipLink rLink1 = new RelationshipLink();
		rLink1.setAverageDistance(958);
		rLink1.setLink(SetUp.GOOGLE);
		rLink1.setMaxDistance(79825);
		rLink1.setMinDistance(48);
		rLink1.setOccurrenceNumber(7);
		relationship.addRelationshipLink(rLink1);

		// relacionamento entre Helison e Marcelo Maia Lopes no link
		// "http://pt.wikipedia.org/"
		RelationshipLink rLink2 = new RelationshipLink();
		rLink2.setAverageDistance(752);
		rLink2.setLink(SetUp.WIKIPEDIA);
		rLink2.setMaxDistance(23984);
		rLink2.setMinDistance(632);
		rLink2.setOccurrenceNumber(5);
		relationship.addRelationshipLink(rLink2);

		// relacionamento entre Helison e Marcelo Maia Lopes no link
		// "http://unipampa.edu.br/"
		RelationshipLink rLink3 = new RelationshipLink();
		rLink3.setAverageDistance(984);
		rLink3.setLink(SetUp.UNIPAMPA);
		rLink3.setMaxDistance(9485);
		rLink3.setMinDistance(54);
		rLink3.setOccurrenceNumber(2);
		relationship.addRelationshipLink(rLink3);

		// insere os relacionamentos no banco
		relationship.setPerson1(new Person(2, "", null)); // id Helison == 2
		relationship.setPerson2(new Person(3, "", null)); // id Marcelo Maia
															// Lopes == 3
		rdao.insert(relationship);
	}

	/**
	 * Cria os relacionamentos para a pessoa Helison Reus Teixeira e a pessoa
	 * Mateus Henrique Dal Forno
	 */
	public static void setRelationshipsMateus(Connection connection) {
		RelationshipDAO rdao = new RelationshipDAO(connection);
		Relationship relationship = new Relationship();

		// relacionamento entre Helison e Mateus Henrique Dal Forno no link
		// "http://www.google.com.br/"
		RelationshipLink rLink1 = new RelationshipLink();
		rLink1.setAverageDistance(451);
		rLink1.setLink(SetUp.GOOGLE);
		rLink1.setMaxDistance(53451);
		rLink1.setMinDistance(94);
		rLink1.setOccurrenceNumber(4);
		relationship.addRelationshipLink(rLink1);

		// relacionamento entre Helison e Mateus Henrique Dal Forno no link
		// "http://pt.wikipedia.org/"
		RelationshipLink rLink2 = new RelationshipLink();
		rLink2.setAverageDistance(972);
		rLink2.setLink(SetUp.WIKIPEDIA);
		rLink2.setMaxDistance(9382);
		rLink2.setMinDistance(40);
		rLink2.setOccurrenceNumber(5);
		relationship.addRelationshipLink(rLink2);

		// relacionamento entre Helison e Mateus Henrique Dal Forno no link
		// "http://unipampa.edu.br/"
		RelationshipLink rLink3 = new RelationshipLink();
		rLink3.setAverageDistance(7343);
		rLink3.setLink(SetUp.UNIPAMPA);
		rLink3.setMaxDistance(90348);
		rLink3.setMinDistance(126);
		rLink3.setOccurrenceNumber(3);
		relationship.addRelationshipLink(rLink3);

		// insere os relacionamentos no banco
		relationship.setPerson1(new Person(2, "", null)); // id Helison == 2
		relationship.setPerson2(new Person(5, "", null)); // id Mateus Henrique
															// Dal Forno == 5
		rdao.insert(relationship);
	}

	/**
	 * Cria os relacionamentos para a pessoa Helison Reus Teixeira e a pessoa
	 * Rafhael Rodrigues Cunha
	 */
	public static void setRelationshipsRafhael(Connection connection) {
		RelationshipDAO rdao = new RelationshipDAO(connection);
		Relationship relationship = new Relationship();

		// relacionamento entre Helison e Rafhael Rodrigues Cunha no link
		// "http://www.google.com.br/"
		RelationshipLink rLink1 = new RelationshipLink();
		rLink1.setAverageDistance(579);
		rLink1.setLink(SetUp.GOOGLE);
		rLink1.setMaxDistance(39427);
		rLink1.setMinDistance(66);
		rLink1.setOccurrenceNumber(10);
		relationship.addRelationshipLink(rLink1);

		// relacionamento entre Helison e Rafhael Rodrigues Cunha no link
		// "http://pt.wikipedia.org/"
		RelationshipLink rLink2 = new RelationshipLink();
		rLink2.setAverageDistance(833);
		rLink2.setLink(SetUp.WIKIPEDIA);
		rLink2.setMaxDistance(40574);
		rLink2.setMinDistance(33);
		rLink2.setOccurrenceNumber(3);
		relationship.addRelationshipLink(rLink2);

		// relacionamento entre Helison e Rafhael Rodrigues Cunha no link
		// "http://unipampa.edu.br/"
		/*
		 * RelationshipLink rLink3 = new RelationshipLink();
		 * rLink3.setAverageDistance(234); rLink3.setLink(SetUp.UNIPAMPA);
		 * rLink3.setMaxDistance(7777); rLink3.setMinDistance(101);
		 * rLink3.setOccurrenceNumber(4);
		 * relationship.addRelationshipLink(rLink3);
		 */

		// insere os relacionamentos no banco
		relationship.setPerson1(new Person(2, "", null)); // id Helison == 2
		relationship.setPerson2(new Person(6, "", null)); // id Rafhael
															// Rodrigues Cunha
															// == 6
		rdao.insert(relationship);
	}

	/**
	 * Cria os relacionamentos para a pessoa Helison Reus Teixeira e a pessoa
	 * Rafael Tavares Amorim
	 */
	public static void setRelationshipsRafael(Connection connection) {
		RelationshipDAO rdao = new RelationshipDAO(connection);
		Relationship relationship = new Relationship();

		// relacionamento entre Helison e Rafael Tavares Amorim no link
		// "http://www.google.com.br/"
		RelationshipLink rLink1 = new RelationshipLink();
		rLink1.setAverageDistance(847);
		rLink1.setLink(SetUp.GOOGLE);
		rLink1.setMaxDistance(34752);
		rLink1.setMinDistance(38);
		rLink1.setOccurrenceNumber(2);
		relationship.addRelationshipLink(rLink1);

		// relacionamento entre Helison e Rafael Tavares Amorim no link
		// "http://pt.wikipedia.org/"
		RelationshipLink rLink2 = new RelationshipLink();
		rLink2.setAverageDistance(7823);
		rLink2.setLink(SetUp.WIKIPEDIA);
		rLink2.setMaxDistance(38647);
		rLink2.setMinDistance(673);
		rLink2.setOccurrenceNumber(3);
		relationship.addRelationshipLink(rLink2);

		// relacionamento entre Helison e Rafael Tavares Amorim no link
		// "http://unipampa.edu.br/"
		RelationshipLink rLink3 = new RelationshipLink();
		rLink3.setAverageDistance(485);
		rLink3.setLink(SetUp.UNIPAMPA);
		rLink3.setMaxDistance(80707);
		rLink3.setMinDistance(78);
		rLink3.setOccurrenceNumber(5);
		relationship.addRelationshipLink(rLink3);

		// insere os relacionamentos no banco
		relationship.setPerson1(new Person(2, "", null)); // id Helison == 2
		relationship.setPerson2(new Person(9, "", null)); // id Rafael Tavares
															// Amorim == 9
		rdao.insert(relationship);
	}

	/**
	 * Cria os relacionamentos para a pessoa Helison Reus Teixeira e a pessoa
	 * Juliano Rodovalho Macedo
	 */
	public static void setRelationshipsJuliano(Connection connection) {
		RelationshipDAO rdao = new RelationshipDAO(connection);
		Relationship relationship = new Relationship();

		// relacionamento entre Helison e Juliano Rodovalho Macedo no link
		// "http://www.google.com.br/"
		RelationshipLink rLink1 = new RelationshipLink();
		rLink1.setAverageDistance(837);
		rLink1.setLink(SetUp.GOOGLE);
		rLink1.setMaxDistance(29474);
		rLink1.setMinDistance(73);
		rLink1.setOccurrenceNumber(3);
		relationship.addRelationshipLink(rLink1);

		// relacionamento entre Helison e Juliano Rodovalho Macedo no link
		// "http://pt.wikipedia.org/"
		/*
		 * RelationshipLink rLink2 = new RelationshipLink();
		 * rLink2.setAverageDistance(878); rLink2.setLink(SetUp.WIKIPEDIA);
		 * rLink2.setMaxDistance(4828); rLink2.setMinDistance(537);
		 * rLink2.setOccurrenceNumber(2);
		 * relationship.addRelationshipLink(rLink2);
		 */

		// relacionamento entre Helison e Juliano Rodovalho Macedo no link
		// "http://unipampa.edu.br/"
		RelationshipLink rLink3 = new RelationshipLink();
		rLink3.setAverageDistance(85);
		rLink3.setLink(SetUp.UNIPAMPA);
		rLink3.setMaxDistance(992);
		rLink3.setMinDistance(24);
		rLink3.setOccurrenceNumber(1);
		relationship.addRelationshipLink(rLink3);

		// insere os relacionamentos no banco
		relationship.setPerson1(new Person(2, "", null)); // id Helison == 2
		relationship.setPerson2(new Person(10, "", null)); // id Juliano
															// Rodovalho Macedo
															// == 10
		rdao.insert(relationship);
	}

}

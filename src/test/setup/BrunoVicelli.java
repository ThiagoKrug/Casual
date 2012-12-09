package test.setup;

import java.sql.Connection;

import model.Person;
import model.Relationship;
import model.RelationshipLink;
import dao.RelationshipDAO;

public class BrunoVicelli {

	public BrunoVicelli(Connection connection) {
		setRelationshipsHelison(connection);
		setRelationshipsMarcelo(connection);
		setRelationshipsMateus(connection);
		// setRelationshipsRafhael(connection);
		setRelationshipsThiago(connection);
		// setRelationshipsRenan(connection);
		setRelationshipsRafael(connection);
		setRelationshipsJuliano(connection);
	}

	/**
	 * Cria os relacionamentos para a pessoa Bruno Vicelli e a pessoa Helison
	 * Reus Teixeira
	 */
	public static void setRelationshipsHelison(Connection connection) {
		RelationshipDAO rdao = new RelationshipDAO(connection);
		Relationship relationship = new Relationship();

		// relacionamento entre Bruno e Helison Reus Teixeira no link
		// "http://www.google.com.br/"
		RelationshipLink rLink1 = new RelationshipLink();
		rLink1.setAverageDistance(328);
		rLink1.setLink(SetUp.GOOGLE);
		rLink1.setMaxDistance(8341);
		rLink1.setMinDistance(74);
		rLink1.setOccurrenceNumber(6);
		relationship.addRelationshipLink(rLink1);

		// relacionamento entre Bruno e Helison Reus Teixeira no link
		// "http://pt.wikipedia.org/"
		RelationshipLink rLink2 = new RelationshipLink();
		rLink2.setAverageDistance(174);
		rLink2.setLink(SetUp.WIKIPEDIA);
		rLink2.setMaxDistance(9231);
		rLink2.setMinDistance(73);
		rLink2.setOccurrenceNumber(5);
		relationship.addRelationshipLink(rLink2);

		// relacionamento entre Bruno e Helison Reus Teixeira no link
		// "http://unipampa.edu.br/"
		RelationshipLink rLink3 = new RelationshipLink();
		rLink3.setAverageDistance(89);
		rLink3.setLink(SetUp.UNIPAMPA);
		rLink3.setMaxDistance(5398);
		rLink3.setMinDistance(48);
		rLink3.setOccurrenceNumber(2);
		relationship.addRelationshipLink(rLink3);

		// insere os relacionamentos no banco
		relationship.setPerson1(new Person(1, "", null)); // id Bruno == 1
		relationship.setPerson2(new Person(2, "", null)); // id Helison Reus
															// Teixeira == 2
		rdao.insert(relationship);
	}

	/**
	 * Cria os relacionamentos para a pessoa Bruno Vicelli e a pessoa Marcelo
	 * Maia Lopes
	 */
	public static void setRelationshipsMarcelo(Connection connection) {
		RelationshipDAO rdao = new RelationshipDAO(connection);
		Relationship relationship = new Relationship();

		// relacionamento entre Bruno e Marcelo Maia Lopes no link
		// "http://www.google.com.br/"
		RelationshipLink rLink1 = new RelationshipLink();
		rLink1.setAverageDistance(78);
		rLink1.setLink(SetUp.GOOGLE);
		rLink1.setMaxDistance(7229);
		rLink1.setMinDistance(1);
		rLink1.setOccurrenceNumber(3);
		relationship.addRelationshipLink(rLink1);

		// relacionamento entre Bruno e Marcelo Maia Lopes no link
		// "http://pt.wikipedia.org/"
		/*
		 * RelationshipLink rLink2 = new RelationshipLink();
		 * rLink2.setAverageDistance(45); rLink2.setLink(SetUp.WIKIPEDIA);
		 * rLink2.setMaxDistance(2006); rLink2.setMinDistance(10);
		 * rLink2.setOccurrenceNumber(3);
		 * relationship.addRelationshipLink(rLink2);
		 */

		// relacionamento entre Bruno e Marcelo Maia Lopes no link
		// "http://unipampa.edu.br/"
		RelationshipLink rLink3 = new RelationshipLink();
		rLink3.setAverageDistance(84);
		rLink3.setLink(SetUp.UNIPAMPA);
		rLink3.setMaxDistance(3327);
		rLink3.setMinDistance(9);
		rLink3.setOccurrenceNumber(5);
		relationship.addRelationshipLink(rLink3);

		// insere os relacionamentos no banco
		relationship.setPerson1(new Person(1, "", null)); // id Bruno == 1
		relationship.setPerson2(new Person(3, "", null)); // id Marcelo Maia
															// Lopes == 3
		rdao.insert(relationship);
	}

	/**
	 * Cria os relacionamentos para a pessoa Bruno Vicelli e a pessoa Mateus
	 * Henrique Dal Forno
	 */
	public static void setRelationshipsMateus(Connection connection) {
		RelationshipDAO rdao = new RelationshipDAO(connection);
		Relationship relationship = new Relationship();

		// relacionamento entre Bruno e Mateus Henrique Dal Forno no link
		// "http://www.google.com.br/"
		RelationshipLink rLink1 = new RelationshipLink();
		rLink1.setAverageDistance(354);
		rLink1.setLink(SetUp.GOOGLE);
		rLink1.setMaxDistance(23479);
		rLink1.setMinDistance(37);
		rLink1.setOccurrenceNumber(1);
		relationship.addRelationshipLink(rLink1);

		// relacionamento entre Bruno e Mateus Henrique Dal Forno no link
		// "http://pt.wikipedia.org/"
		RelationshipLink rLink2 = new RelationshipLink();
		rLink2.setAverageDistance(302);
		rLink2.setLink(SetUp.WIKIPEDIA);
		rLink2.setMaxDistance(35982);
		rLink2.setMinDistance(4);
		rLink2.setOccurrenceNumber(2);
		relationship.addRelationshipLink(rLink2);

		// relacionamento entre Bruno e Mateus Henrique Dal Forno no link
		// "http://unipampa.edu.br/"
		RelationshipLink rLink3 = new RelationshipLink();
		rLink3.setAverageDistance(884);
		rLink3.setLink(SetUp.UNIPAMPA);
		rLink3.setMaxDistance(984);
		rLink3.setMinDistance(42);
		rLink3.setOccurrenceNumber(3);
		relationship.addRelationshipLink(rLink3);

		// insere os relacionamentos no banco
		relationship.setPerson1(new Person(1, "", null)); // id Bruno == 1
		relationship.setPerson2(new Person(5, "", null)); // id Mateus Henrique
															// Dal Forno == 5
		rdao.insert(relationship);
	}

	/**
	 * Cria os relacionamentos para a pessoa Bruno Vicelli e a pessoa Thiago
	 * Aquino de Lima
	 */
	public static void setRelationshipsThiago(Connection connection) {
		RelationshipDAO rdao = new RelationshipDAO(connection);
		Relationship relationship = new Relationship();

		// relacionamento entre Bruno e Thiago Aquino de Lima no link
		// "http://www.google.com.br/"
		/*
		 * RelationshipLink rLink1 = new RelationshipLink();
		 * rLink1.setAverageDistance(89); rLink1.setLink(SetUp.GOOGLE);
		 * rLink1.setMaxDistance(82100); rLink1.setMinDistance(11);
		 * rLink1.setOccurrenceNumber(6);
		 * relationship.addRelationshipLink(rLink1);
		 */

		// relacionamento entre Bruno e Thiago Aquino de Lima no link
		// "http://pt.wikipedia.org/"
		RelationshipLink rLink2 = new RelationshipLink();
		rLink2.setAverageDistance(298);
		rLink2.setLink(SetUp.WIKIPEDIA);
		rLink2.setMaxDistance(498);
		rLink2.setMinDistance(95);
		rLink2.setOccurrenceNumber(3);
		relationship.addRelationshipLink(rLink2);

		// relacionamento entre Bruno e Thiago Aquino de Lima no link
		// "http://unipampa.edu.br/"
		/*
		 * RelationshipLink rLink3 = new RelationshipLink();
		 * rLink3.setAverageDistance(191); rLink3.setLink(SetUp.UNIPAMPA);
		 * rLink3.setMaxDistance(6583); rLink3.setMinDistance(34);
		 * rLink3.setOccurrenceNumber(3);
		 * relationship.addRelationshipLink(rLink3);
		 */

		// insere os relacionamentos no banco
		relationship.setPerson1(new Person(1, "", null)); // id Bruno == 1
		relationship.setPerson2(new Person(7, "", null)); // id Thiago Aquino de
															// Lima == 7
		rdao.insert(relationship);
	}

	/**
	 * Cria os relacionamentos para a pessoa Bruno Vicelli e a pessoa Rafael
	 * Tavares Amorim
	 */
	public static void setRelationshipsRafael(Connection connection) {
		RelationshipDAO rdao = new RelationshipDAO(connection);
		Relationship relationship = new Relationship();

		// relacionamento entre Bruno e Rafael Tavares Amorim no link
		// "http://www.google.com.br/"
		RelationshipLink rLink1 = new RelationshipLink();
		rLink1.setAverageDistance(498);
		rLink1.setLink(SetUp.GOOGLE);
		rLink1.setMaxDistance(9803);
		rLink1.setMinDistance(92);
		rLink1.setOccurrenceNumber(4);
		relationship.addRelationshipLink(rLink1);

		// relacionamento entre Bruno e Rafael Tavares Amorim no link
		// "http://pt.wikipedia.org/"
		RelationshipLink rLink2 = new RelationshipLink();
		rLink2.setAverageDistance(327);
		rLink2.setLink(SetUp.WIKIPEDIA);
		rLink2.setMaxDistance(2584);
		rLink2.setMinDistance(49);
		rLink2.setOccurrenceNumber(1);
		relationship.addRelationshipLink(rLink2);

		// relacionamento entre Bruno e Rafael Tavares Amorim no link
		// "http://unipampa.edu.br/"
		RelationshipLink rLink3 = new RelationshipLink();
		rLink3.setAverageDistance(457);
		rLink3.setLink(SetUp.UNIPAMPA);
		rLink3.setMaxDistance(91082);
		rLink3.setMinDistance(84);
		rLink3.setOccurrenceNumber(1);
		relationship.addRelationshipLink(rLink3);

		// insere os relacionamentos no banco
		relationship.setPerson1(new Person(1, "", null)); // id Bruno == 1
		relationship.setPerson2(new Person(9, "", null)); // id Rafael Tavares
															// Amorim == 9
		rdao.insert(relationship);
	}

	/**
	 * Cria os relacionamentos para a pessoa Bruno Vicelli e a pessoa Juliano
	 * Rodovalho Macedo
	 */
	public static void setRelationshipsJuliano(Connection connection) {
		RelationshipDAO rdao = new RelationshipDAO(connection);
		Relationship relationship = new Relationship();

		// relacionamento entre Bruno e Juliano Rodovalho Macedo no link
		// "http://www.google.com.br/"
		/*
		 * RelationshipLink rLink1 = new RelationshipLink();
		 * rLink1.setAverageDistance(533); rLink1.setLink(SetUp.GOOGLE);
		 * rLink1.setMaxDistance(21643); rLink1.setMinDistance(312);
		 * rLink1.setOccurrenceNumber(3);
		 * relationship.addRelationshipLink(rLink1);
		 */

		// relacionamento entre Bruno e Juliano Rodovalho Macedo no link
		// "http://pt.wikipedia.org/"
		RelationshipLink rLink2 = new RelationshipLink();
		rLink2.setAverageDistance(878);
		rLink2.setLink(SetUp.WIKIPEDIA);
		rLink2.setMaxDistance(4828);
		rLink2.setMinDistance(537);
		rLink2.setOccurrenceNumber(2);
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
		relationship.setPerson1(new Person(1, "", null)); // id Bruno == 1
		relationship.setPerson2(new Person(10, "", null)); // id Juliano
															// Rodovalho Macedo
															// == 10
		rdao.insert(relationship);
	}

}

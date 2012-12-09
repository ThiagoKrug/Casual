package test.setup;

import java.sql.Connection;

import model.Person;
import model.Relationship;
import model.RelationshipLink;
import dao.RelationshipDAO;

public class MarceloMaiaLopes {

	public MarceloMaiaLopes(Connection connection) {
		// setRelationshipsMateus(connection);
		setRelationshipsRafhael(connection);
		setRelationshipsThiago(connection);
		setRelationshipsRenan(connection);
		setRelationshipsRafael(connection);
		setRelationshipsJuliano(connection);
	}

	/**
	 * Cria os relacionamentos para a pessoa Marcelo Maia Lopes e a pessoa
	 * Rafhael Rodrigues Cunha
	 */
	public static void setRelationshipsRafhael(Connection connection) {
		RelationshipDAO rdao = new RelationshipDAO(connection);
		Relationship relationship = new Relationship();

		// relacionamento entre Marcelo e Rafhael Rodrigues Cunha no link
		// "http://www.google.com.br/"
		/*
		 * RelationshipLink rLink1 = new RelationshipLink();
		 * rLink1.setAverageDistance(122); rLink1.setLink(SetUp.GOOGLE);
		 * rLink1.setMaxDistance(108313); rLink1.setMinDistance(44);
		 * rLink1.setOccurrenceNumber(7);
		 * relationship.addRelationshipLink(rLink1);
		 */

		// relacionamento entre Marcelo e Rafhael Rodrigues Cunha no link
		// "http://pt.wikipedia.org/"
		/*
		 * RelationshipLink rLink2 = new RelationshipLink();
		 * rLink2.setAverageDistance(188); rLink2.setLink(SetUp.WIKIPEDIA);
		 * rLink2.setMaxDistance(50862); rLink2.setMinDistance(63);
		 * rLink2.setOccurrenceNumber(4);
		 * relationship.addRelationshipLink(rLink2);
		 */

		// relacionamento entre Marcelo e Rafhael Rodrigues Cunha no link
		// "http://unipampa.edu.br/"
		RelationshipLink rLink3 = new RelationshipLink();
		rLink3.setAverageDistance(974);
		rLink3.setLink(SetUp.UNIPAMPA);
		rLink3.setMaxDistance(2374);
		rLink3.setMinDistance(80);
		rLink3.setOccurrenceNumber(3);
		relationship.addRelationshipLink(rLink3);

		// insere os relacionamentos no banco
		relationship.setPerson1(new Person(3, "", null)); // id Marcelo == 3
		relationship.setPerson2(new Person(6, "", null)); // id Rafhael
															// Rodrigues Cunha
															// == 6
		rdao.insert(relationship);
	}

	/**
	 * Cria os relacionamentos para a pessoa Marcelo Maia Lopes e a pessoa
	 * Thiago Aquino de Lima
	 */
	public static void setRelationshipsThiago(Connection connection) {
		RelationshipDAO rdao = new RelationshipDAO(connection);
		Relationship relationship = new Relationship();

		// relacionamento entre Marcelo e Thiago Aquino de Lima no link
		// "http://www.google.com.br/"
		RelationshipLink rLink1 = new RelationshipLink();
		rLink1.setAverageDistance(8217);
		rLink1.setLink(SetUp.GOOGLE);
		rLink1.setMaxDistance(37938);
		rLink1.setMinDistance(94);
		rLink1.setOccurrenceNumber(4);
		relationship.addRelationshipLink(rLink1);

		// relacionamento entre Marcelo e Thiago Aquino de Lima no link
		// "http://pt.wikipedia.org/"
		/*
		 * RelationshipLink rLink2 = new RelationshipLink();
		 * rLink2.setAverageDistance(5234); rLink2.setLink(SetUp.WIKIPEDIA);
		 * rLink2.setMaxDistance(5234); rLink2.setMinDistance(5234);
		 * rLink2.setOccurrenceNumber(1);
		 * relationship.addRelationshipLink(rLink2);
		 */

		// relacionamento entre Marcelo e Thiago Aquino de Lima no link
		// "http://unipampa.edu.br/"
		/*
		 * RelationshipLink rLink3 = new RelationshipLink();
		 * rLink3.setAverageDistance(191); rLink3.setLink(SetUp.UNIPAMPA);
		 * rLink3.setMaxDistance(6583); rLink3.setMinDistance(34);
		 * rLink3.setOccurrenceNumber(3);
		 * relationship.addRelationshipLink(rLink3)
		 */

		// insere os relacionamentos no banco
		relationship.setPerson1(new Person(3, "", null)); // id Marcelo == 3
		relationship.setPerson2(new Person(7, "", null)); // id Thiago Aquino de
															// Lima == 7
		rdao.insert(relationship);
	}

	/**
	 * Cria os relacionamentos para a pessoa Marcelo Maia Lopes e a pessoa Renan
	 * Marcel Uchôa
	 */
	public static void setRelationshipsRenan(Connection connection) {
		RelationshipDAO rdao = new RelationshipDAO(connection);
		Relationship relationship = new Relationship();

		// relacionamento entre Marcelo e Renan Marcel Uchôa no link
		// "http://www.google.com.br/"
		RelationshipLink rLink1 = new RelationshipLink();
		rLink1.setAverageDistance(9784);
		rLink1.setLink(SetUp.GOOGLE);
		rLink1.setMaxDistance(38572);
		rLink1.setMinDistance(979);
		rLink1.setOccurrenceNumber(4);
		relationship.addRelationshipLink(rLink1);

		// relacionamento entre Marcelo e Renan Marcel Uchôa no link
		// "http://pt.wikipedia.org/"
		RelationshipLink rLink2 = new RelationshipLink();
		rLink2.setAverageDistance(832);
		rLink2.setLink(SetUp.WIKIPEDIA);
		rLink2.setMaxDistance(1111);
		rLink2.setMinDistance(444);
		rLink2.setOccurrenceNumber(4);
		relationship.addRelationshipLink(rLink2);

		// relacionamento entre Marcelo e Renan Marcel Uchôa no link
		// "http://unipampa.edu.br/"
		RelationshipLink rLink3 = new RelationshipLink();
		rLink3.setAverageDistance(458);
		rLink3.setLink(SetUp.UNIPAMPA);
		rLink3.setMaxDistance(8784);
		rLink3.setMinDistance(97);
		rLink3.setOccurrenceNumber(4);
		relationship.addRelationshipLink(rLink3);

		// insere os relacionamentos no banco
		relationship.setPerson1(new Person(3, "", null)); // id Marcelo == 3
		relationship.setPerson2(new Person(8, "", null)); // id Renan Marcel
															// Uchôa == 8
		rdao.insert(relationship);
	}

	/**
	 * Cria os relacionamentos para a pessoa Marcelo Maia Lopes e a pessoa
	 * Rafael Tavares Amorim
	 */
	public static void setRelationshipsRafael(Connection connection) {
		RelationshipDAO rdao = new RelationshipDAO(connection);
		Relationship relationship = new Relationship();

		// relacionamento entre Marcelo e Rafael Tavares Amorim no link
		// "http://www.google.com.br/"
		RelationshipLink rLink1 = new RelationshipLink();
		rLink1.setAverageDistance(2484);
		rLink1.setLink(SetUp.GOOGLE);
		rLink1.setMaxDistance(22222);
		rLink1.setMinDistance(100);
		rLink1.setOccurrenceNumber(3);
		relationship.addRelationshipLink(rLink1);

		// relacionamento entre Marcelo e Rafael Tavares Amorim no link
		// "http://pt.wikipedia.org/"
		RelationshipLink rLink2 = new RelationshipLink();
		rLink2.setAverageDistance(3835);
		rLink2.setLink(SetUp.WIKIPEDIA);
		rLink2.setMaxDistance(24784);
		rLink2.setMinDistance(12);
		rLink2.setOccurrenceNumber(6);
		relationship.addRelationshipLink(rLink2);

		// relacionamento entre Marcelo e Rafael Tavares Amorim no link
		// "http://unipampa.edu.br/"
		RelationshipLink rLink3 = new RelationshipLink();
		rLink3.setAverageDistance(1775);
		rLink3.setLink(SetUp.UNIPAMPA);
		rLink3.setMaxDistance(9078);
		rLink3.setMinDistance(88);
		rLink3.setOccurrenceNumber(7);
		relationship.addRelationshipLink(rLink3);

		// insere os relacionamentos no banco
		relationship.setPerson1(new Person(3, "", null)); // id Marcelo == 3
		relationship.setPerson2(new Person(9, "", null)); // id Rafael Tavares
															// Amorim == 9
		rdao.insert(relationship);
	}

	/**
	 * Cria os relacionamentos para a pessoa Marcelo Maia Lopes e a pessoa
	 * Juliano Rodovalho Macedo
	 */
	public static void setRelationshipsJuliano(Connection connection) {
		RelationshipDAO rdao = new RelationshipDAO(connection);
		Relationship relationship = new Relationship();

		// relacionamento entre Marcelo e Juliano Rodovalho Macedo no link
		// "http://www.google.com.br/"
		RelationshipLink rLink1 = new RelationshipLink();
		rLink1.setAverageDistance(5843);
		rLink1.setLink(SetUp.GOOGLE);
		rLink1.setMaxDistance(10134);
		rLink1.setMinDistance(48);
		rLink1.setOccurrenceNumber(5);
		relationship.addRelationshipLink(rLink1);

		// relacionamento entre Marcelo e Juliano Rodovalho Macedo no link
		// "http://pt.wikipedia.org/"
		RelationshipLink rLink2 = new RelationshipLink();
		rLink2.setAverageDistance(30);
		rLink2.setLink(SetUp.WIKIPEDIA);
		rLink2.setMaxDistance(924);
		rLink2.setMinDistance(2);
		rLink2.setOccurrenceNumber(2);
		relationship.addRelationshipLink(rLink2);

		// relacionamento entre Marcelo e Juliano Rodovalho Macedo no link
		// "http://unipampa.edu.br/"
		RelationshipLink rLink3 = new RelationshipLink();
		rLink3.setAverageDistance(3531);
		rLink3.setLink(SetUp.UNIPAMPA);
		rLink3.setMaxDistance(5489);
		rLink3.setMinDistance(1780);
		rLink3.setOccurrenceNumber(2);
		relationship.addRelationshipLink(rLink3);

		// insere os relacionamentos no banco
		relationship.setPerson1(new Person(3, "", null)); // id Marcelo == 3
		relationship.setPerson2(new Person(10, "", null)); // id Juliano
															// Rodovalho Macedo
															// == 10
		rdao.insert(relationship);
	}

}

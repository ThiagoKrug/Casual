package test.setup;

import java.sql.Connection;

import model.Person;
import model.Relationship;
import model.RelationshipLink;
import dao.RelationshipDAO;

public class RafhaelRodriguesCunha {

	public RafhaelRodriguesCunha(Connection connection) {
		setRelationshipsThiago(connection);
		setRelationshipsRenan(connection);
		setRelationshipsRafael(connection);
		// setRelationshipsJuliano(connection);
	}

	/**
	 * Cria os relacionamentos para a pessoa Rafhael Rodrigues Cunha e a pessoa
	 * Thiago Aquino de Lima
	 */
	public static void setRelationshipsThiago(Connection connection) {
		RelationshipDAO rdao = new RelationshipDAO(connection);
		Relationship relationship = new Relationship();

		// relacionamento entre Rafhael e Thiago Aquino de Lima no link
		// "http://www.google.com.br/"
		RelationshipLink rLink1 = new RelationshipLink();
		rLink1.setAverageDistance(1837);
		rLink1.setLink(SetUp.GOOGLE);
		rLink1.setMaxDistance(2942);
		rLink1.setMinDistance(24);
		rLink1.setOccurrenceNumber(5);
		relationship.addRelationshipLink(rLink1);

		// relacionamento entre Rafhael e Thiago Aquino de Lima no link
		// "http://pt.wikipedia.org/"
		RelationshipLink rLink2 = new RelationshipLink();
		rLink2.setAverageDistance(971);
		rLink2.setLink(SetUp.WIKIPEDIA);
		rLink2.setMaxDistance(8937);
		rLink2.setMinDistance(18);
		rLink2.setOccurrenceNumber(4);
		relationship.addRelationshipLink(rLink2);

		// relacionamento entre Rafhael e Thiago Aquino de Lima no link
		// "http://unipampa.edu.br/"
		RelationshipLink rLink3 = new RelationshipLink();
		rLink3.setAverageDistance(208);
		rLink3.setLink(SetUp.UNIPAMPA);
		rLink3.setMaxDistance(947);
		rLink3.setMinDistance(6);
		rLink3.setOccurrenceNumber(4);
		relationship.addRelationshipLink(rLink3);

		// insere os relacionamentos no banco
		relationship.setPerson1(new Person(6, "", null)); // id Rafhael == 6
		relationship.setPerson2(new Person(7, "", null)); // id Thiago Aquino de
															// Lima == 7
		rdao.insert(relationship);
	}

	/**
	 * Cria os relacionamentos para a pessoa Rafhael Rodrigues Cunha e a pessoa
	 * Renan Marcel Uchôa
	 */
	public static void setRelationshipsRenan(Connection connection) {
		RelationshipDAO rdao = new RelationshipDAO(connection);
		Relationship relationship = new Relationship();

		// relacionamento entre Rafhael e Renan Marcel Uchôa no link
		// "http://www.google.com.br/"
		/*
		 * RelationshipLink rLink1 = new RelationshipLink();
		 * rLink1.setAverageDistance(211); rLink1.setLink(SetUp.GOOGLE);
		 * rLink1.setMaxDistance(13234); rLink1.setMinDistance(136);
		 * rLink1.setOccurrenceNumber(5);
		 * relationship.addRelationshipLink(rLink1);
		 */

		// relacionamento entre Rafhael e Renan Marcel Uchôa no link
		// "http://pt.wikipedia.org/"
		/*
		 * RelationshipLink rLink2 = new RelationshipLink();
		 * rLink2.setAverageDistance(174); rLink2.setLink(SetUp.WIKIPEDIA);
		 * rLink2.setMaxDistance(260); rLink2.setMinDistance(88);
		 * rLink2.setOccurrenceNumber(2);
		 * relationship.addRelationshipLink(rLink2);
		 */

		// relacionamento entre Rafhael e Renan Marcel Uchôa no link
		// "http://unipampa.edu.br/"
		RelationshipLink rLink3 = new RelationshipLink();
		rLink3.setAverageDistance(119);
		rLink3.setLink(SetUp.UNIPAMPA);
		rLink3.setMaxDistance(234);
		rLink3.setMinDistance(4);
		rLink3.setOccurrenceNumber(2);
		relationship.addRelationshipLink(rLink3);

		// insere os relacionamentos no banco
		relationship.setPerson1(new Person(6, "", null)); // id Rafhael == 6
		relationship.setPerson2(new Person(8, "", null)); // id Renan Marcel
															// Uchôa == 8
		rdao.insert(relationship);
	}

	/**
	 * Cria os relacionamentos para a pessoa Rafhael Rodrigues Cunha e a pessoa
	 * Rafael Tavares Amorim
	 */
	public static void setRelationshipsRafael(Connection connection) {
		RelationshipDAO rdao = new RelationshipDAO(connection);
		Relationship relationship = new Relationship();

		// relacionamento entre Rafhael e Rafael Tavares Amorim no link
		// "http://www.google.com.br/"
		RelationshipLink rLink1 = new RelationshipLink();
		rLink1.setAverageDistance(1275);
		rLink1.setLink(SetUp.GOOGLE);
		rLink1.setMaxDistance(18307);
		rLink1.setMinDistance(787);
		rLink1.setOccurrenceNumber(2);
		relationship.addRelationshipLink(rLink1);

		// relacionamento entre Rafhael e Rafael Tavares Amorim no link
		// "http://pt.wikipedia.org/"
		RelationshipLink rLink2 = new RelationshipLink();
		rLink2.setAverageDistance(3550);
		rLink2.setLink(SetUp.WIKIPEDIA);
		rLink2.setMaxDistance(59730);
		rLink2.setMinDistance(978);
		rLink2.setOccurrenceNumber(3);
		relationship.addRelationshipLink(rLink2);

		// relacionamento entre Rafhael e Rafael Tavares Amorim no link
		// "http://unipampa.edu.br/"
		RelationshipLink rLink3 = new RelationshipLink();
		rLink3.setAverageDistance(1833);
		rLink3.setLink(SetUp.UNIPAMPA);
		rLink3.setMaxDistance(3749);
		rLink3.setMinDistance(1476);
		rLink3.setOccurrenceNumber(2);
		relationship.addRelationshipLink(rLink3);

		// insere os relacionamentos no banco
		relationship.setPerson1(new Person(6, "", null)); // id Rafhael == 6
		relationship.setPerson2(new Person(9, "", null)); // id Rafael Tavares
															// Amorim == 9
		rdao.insert(relationship);
	}

}

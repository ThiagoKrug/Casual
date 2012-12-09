package test.setup;

import java.sql.Connection;

import model.Person;
import model.Relationship;
import model.RelationshipLink;
import dao.RelationshipDAO;

public class ThiagoAquinoDeLima {

	public ThiagoAquinoDeLima(Connection connection) {
		setRelationshipsRenan(connection);
		setRelationshipsRafael(connection);
		setRelationshipsJuliano(connection);
	}

	/**
	 * Cria os relacionamentos para a pessoa Thiago Aquino de Lima e a pessoa
	 * Renan Marcel Uchôa
	 */
	public static void setRelationshipsRenan(Connection connection) {
		RelationshipDAO rdao = new RelationshipDAO(connection);
		Relationship relationship = new Relationship();

		// relacionamento entre Thiago e Renan Marcel Uchôa no link
		// "http://www.google.com.br/"
		RelationshipLink rLink1 = new RelationshipLink();
		rLink1.setAverageDistance(174);
		rLink1.setLink(SetUp.GOOGLE);
		rLink1.setMaxDistance(2591);
		rLink1.setMinDistance(81);
		rLink1.setOccurrenceNumber(3);
		relationship.addRelationshipLink(rLink1);

		// relacionamento entre Thiago e Renan Marcel Uchôa no link
		// "http://pt.wikipedia.org/"
		/*
		 * RelationshipLink rLink2 = new RelationshipLink();
		 * rLink2.setAverageDistance(174); rLink2.setLink(SetUp.WIKIPEDIA);
		 * rLink2.setMaxDistance(260); rLink2.setMinDistance(88);
		 * rLink2.setOccurrenceNumber(2);
		 * relationship.addRelationshipLink(rLink2);
		 */

		// relacionamento entre Thiago e Renan Marcel Uchôa no link
		// "http://unipampa.edu.br/"
		RelationshipLink rLink3 = new RelationshipLink();
		rLink3.setAverageDistance(1075);
		rLink3.setLink(SetUp.UNIPAMPA);
		rLink3.setMaxDistance(19451);
		rLink3.setMinDistance(55);
		rLink3.setOccurrenceNumber(2);
		relationship.addRelationshipLink(rLink3);

		// insere os relacionamentos no banco
		relationship.setPerson1(new Person(7, "", null)); // id Thiago == 7
		relationship.setPerson2(new Person(8, "", null)); // id Renan Marcel
															// Uchôa == 8
		rdao.insert(relationship);
	}

	/**
	 * Cria os relacionamentos para a pessoa Thiago Aquino de Lima e a pessoa
	 * Rafael Tavares Amorim
	 */
	public static void setRelationshipsRafael(Connection connection) {
		RelationshipDAO rdao = new RelationshipDAO(connection);
		Relationship relationship = new Relationship();

		// relacionamento entre Thiago e Rafael Tavares Amorim no link
		// "http://www.google.com.br/"
		RelationshipLink rLink1 = new RelationshipLink();
		rLink1.setAverageDistance(8091);
		rLink1.setLink(SetUp.GOOGLE);
		rLink1.setMaxDistance(76078);
		rLink1.setMinDistance(377);
		rLink1.setOccurrenceNumber(5);
		relationship.addRelationshipLink(rLink1);

		// relacionamento entre Thiago e Rafael Tavares Amorim no link
		// "http://pt.wikipedia.org/"
		RelationshipLink rLink2 = new RelationshipLink();
		rLink2.setAverageDistance(273);
		rLink2.setLink(SetUp.WIKIPEDIA);
		rLink2.setMaxDistance(8247);
		rLink2.setMinDistance(57);
		rLink2.setOccurrenceNumber(5);
		relationship.addRelationshipLink(rLink2);

		// relacionamento entre Thiago e Rafael Tavares Amorim no link
		// "http://unipampa.edu.br/"
		RelationshipLink rLink3 = new RelationshipLink();
		rLink3.setAverageDistance(912);
		rLink3.setLink(SetUp.UNIPAMPA);
		rLink3.setMaxDistance(2925);
		rLink3.setMinDistance(1432);
		rLink3.setOccurrenceNumber(2);
		relationship.addRelationshipLink(rLink3);

		// insere os relacionamentos no banco
		relationship.setPerson1(new Person(7, "", null)); // id Thiago == 7
		relationship.setPerson2(new Person(9, "", null)); // id Rafael Tavares
															// Amorim == 9
		rdao.insert(relationship);
	}

	/**
	 * Cria os relacionamentos para a pessoa Thiago Cassio Krug e a pessoa
	 * Juliano Rodovalho Macedo
	 */
	public static void setRelationshipsJuliano(Connection connection) {
		RelationshipDAO rdao = new RelationshipDAO(connection);
		Relationship relationship = new Relationship();

		// relacionamento entre Thiago e Juliano Rodovalho Macedo no link
		// "http://www.google.com.br/"
		RelationshipLink rLink1 = new RelationshipLink();
		rLink1.setAverageDistance(843);
		rLink1.setLink(SetUp.GOOGLE);
		rLink1.setMaxDistance(7165);
		rLink1.setMinDistance(748);
		rLink1.setOccurrenceNumber(3);
		relationship.addRelationshipLink(rLink1);

		// relacionamento entre Thiago e Juliano Rodovalho Macedo no link
		// "http://pt.wikipedia.org/"
		/*
		 * RelationshipLink rLink2 = new RelationshipLink();
		 * rLink2.setAverageDistance(5820); rLink2.setLink(SetUp.WIKIPEDIA);
		 * rLink2.setMaxDistance(9312); rLink2.setMinDistance(2328);
		 * rLink2.setOccurrenceNumber(2);
		 * relationship.addRelationshipLink(rLink2);
		 */

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
		relationship.setPerson1(new Person(7, "", null)); // id Thiago == 7
		relationship.setPerson2(new Person(10, "", null)); // id Juliano
															// Rodovalho Macedo
															// == 10
		rdao.insert(relationship);
	}

}

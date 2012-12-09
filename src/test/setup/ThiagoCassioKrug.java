package test.setup;

import java.sql.Connection;

import model.Person;
import model.Relationship;
import model.RelationshipLink;
import dao.RelationshipDAO;

public class ThiagoCassioKrug {
	
	public ThiagoCassioKrug(Connection connection) {
		setRelationshipsBruno(connection);
		setRelationshipsHelison(connection);
		setRelationshipsMarcelo(connection);
		setRelationshipsMateus(connection);
		setRelationshipsRafhael(connection);
		setRelationshipsThiago(connection);
		setRelationshipsRenan(connection);
		setRelationshipsRafael(connection);
		setRelationshipsJuliano(connection);
	}
	
	/**
	 * Cria os relacionamentos para a pessoa Thiago Cassio Krug e a pessoa Bruno
	 * Vicelli
	 */
	public static void setRelationshipsBruno(Connection connection) {
		RelationshipDAO rdao = new RelationshipDAO(connection);
		Relationship relationship = new Relationship();

		// relacionamento entre Thiago e Bruno no link
		// "http://www.google.com.br/"
		RelationshipLink rLink1 = new RelationshipLink();
		rLink1.setAverageDistance(30);
		rLink1.setLink(SetUp.GOOGLE);
		rLink1.setMaxDistance(476);
		rLink1.setMinDistance(6);
		rLink1.setOccurrenceNumber(5);
		relationship.addRelationshipLink(rLink1);

		// relacionamento entre Thiago e Bruno no link
		// "http://pt.wikipedia.org/"
		RelationshipLink rLink2 = new RelationshipLink();
		rLink2.setAverageDistance(45);
		rLink2.setLink(SetUp.WIKIPEDIA);
		rLink2.setMaxDistance(2012);
		rLink2.setMinDistance(10);
		rLink2.setOccurrenceNumber(3);
		relationship.addRelationshipLink(rLink2);

		// relacionamento entre Thiago e Bruno no link "http://unipampa.edu.br/"
		RelationshipLink rLink3 = new RelationshipLink();
		rLink3.setAverageDistance(25);
		rLink3.setLink(SetUp.UNIPAMPA);
		rLink3.setMaxDistance(1123);
		rLink3.setMinDistance(2);
		rLink3.setOccurrenceNumber(4);
		relationship.addRelationshipLink(rLink3);

		// insere os relacionamentos no banco
		relationship.setPerson1(new Person(4, "", null)); // id Thiago == 4
		relationship.setPerson2(new Person(1, "", null)); // id Bruno == 1
		rdao.insert(relationship);
	}

	/**
	 * Cria os relacionamentos para a pessoa Thiago Cassio Krug e a pessoa
	 * Helison Reus Teixeira
	 */
	public static void setRelationshipsHelison(Connection connection) {
		RelationshipDAO rdao = new RelationshipDAO(connection);
		Relationship relationship = new Relationship();

		// relacionamento entre Thiago e Helison Reus Teixeira no link
		// "http://www.google.com.br/"
		RelationshipLink rLink1 = new RelationshipLink();
		rLink1.setAverageDistance(41);
		rLink1.setLink(SetUp.GOOGLE);
		rLink1.setMaxDistance(879);
		rLink1.setMinDistance(10);
		rLink1.setOccurrenceNumber(5);
		relationship.addRelationshipLink(rLink1);

		// relacionamento entre Thiago e Helison Reus Teixeira no link
		// "http://pt.wikipedia.org/"
		RelationshipLink rLink2 = new RelationshipLink();
		rLink2.setAverageDistance(44);
		rLink2.setLink(SetUp.WIKIPEDIA);
		rLink2.setMaxDistance(3070);
		rLink2.setMinDistance(17);
		rLink2.setOccurrenceNumber(4);
		relationship.addRelationshipLink(rLink2);

		// relacionamento entre Thiago e Helison Reus Teixeira no link
		// "http://unipampa.edu.br/"
		RelationshipLink rLink3 = new RelationshipLink();
		rLink3.setAverageDistance(24);
		rLink3.setLink(SetUp.UNIPAMPA);
		rLink3.setMaxDistance(899);
		rLink3.setMinDistance(13);
		rLink3.setOccurrenceNumber(5);
		relationship.addRelationshipLink(rLink3);

		// insere os relacionamentos no banco
		relationship.setPerson1(new Person(4, "", null)); // id Thiago == 4
		relationship.setPerson2(new Person(2, "", null)); // id Helison Reus
															// Teixeira == 2
		rdao.insert(relationship);
	}
	
	/**
	 * Cria os relacionamentos para a pessoa Thiago Cassio Krug e a pessoa
	 * Marcelo Maia Lopes
	 */
	public static void setRelationshipsMarcelo(Connection connection) {
		RelationshipDAO rdao = new RelationshipDAO(connection);
		Relationship relationship = new Relationship();

		// relacionamento entre Thiago e Marcelo Maia Lopes no link
		// "http://www.google.com.br/"
		RelationshipLink rLink1 = new RelationshipLink();
		rLink1.setAverageDistance(20);
		rLink1.setLink(SetUp.GOOGLE);
		rLink1.setMaxDistance(500);
		rLink1.setMinDistance(3);
		rLink1.setOccurrenceNumber(3);
		relationship.addRelationshipLink(rLink1);

		// relacionamento entre Thiago e Marcelo Maia Lopes no link
		// "http://pt.wikipedia.org/"
		RelationshipLink rLink2 = new RelationshipLink();
		rLink2.setAverageDistance(45);
		rLink2.setLink(SetUp.WIKIPEDIA);
		rLink2.setMaxDistance(2006);
		rLink2.setMinDistance(10);
		rLink2.setOccurrenceNumber(3);
		relationship.addRelationshipLink(rLink2);

		// relacionamento entre Thiago e Marcelo Maia Lopes no link
		// "http://unipampa.edu.br/"
		RelationshipLink rLink3 = new RelationshipLink();
		rLink3.setAverageDistance(25);
		rLink3.setLink(SetUp.UNIPAMPA);
		rLink3.setMaxDistance(1123);
		rLink3.setMinDistance(2);
		rLink3.setOccurrenceNumber(4);
		relationship.addRelationshipLink(rLink3);

		// insere os relacionamentos no banco
		relationship.setPerson1(new Person(4, "", null)); // id Thiago == 4
		relationship.setPerson2(new Person(3, "", null)); // id Marcelo Maia Lopes == 3
		rdao.insert(relationship);
	}
	
	/**
	 * Cria os relacionamentos para a pessoa Thiago Cassio Krug e a pessoa
	 * Mateus Henrique Dal Forno
	 */
	public static void setRelationshipsMateus(Connection connection) {
		RelationshipDAO rdao = new RelationshipDAO(connection);
		Relationship relationship = new Relationship();

		// relacionamento entre Thiago e Mateus Henrique Dal Forno no link
		// "http://www.google.com.br/"
		RelationshipLink rLink1 = new RelationshipLink();
		rLink1.setAverageDistance(60);
		rLink1.setLink(SetUp.GOOGLE);
		rLink1.setMaxDistance(1733);
		rLink1.setMinDistance(53);
		rLink1.setOccurrenceNumber(5);
		relationship.addRelationshipLink(rLink1);

		// relacionamento entre Thiago e Mateus Henrique Dal Forno no link
		// "http://pt.wikipedia.org/"
		RelationshipLink rLink2 = new RelationshipLink();
		rLink2.setAverageDistance(90);
		rLink2.setLink(SetUp.WIKIPEDIA);
		rLink2.setMaxDistance(23431);
		rLink2.setMinDistance(74);
		rLink2.setOccurrenceNumber(4);
		relationship.addRelationshipLink(rLink2);

		// relacionamento entre Thiago e Mateus Henrique Dal Forno no link
		// "http://unipampa.edu.br/"
		RelationshipLink rLink3 = new RelationshipLink();
		rLink3.setAverageDistance(39);
		rLink3.setLink(SetUp.UNIPAMPA);
		rLink3.setMaxDistance(8742);
		rLink3.setMinDistance(21);
		rLink3.setOccurrenceNumber(3);
		relationship.addRelationshipLink(rLink3);

		// insere os relacionamentos no banco
		relationship.setPerson1(new Person(4, "", null)); // id Thiago == 4
		relationship.setPerson2(new Person(5, "", null)); // id Mateus Henrique Dal Forno == 5
		rdao.insert(relationship);
	}
	
	/**
	 * Cria os relacionamentos para a pessoa Thiago Cassio Krug e a pessoa
	 * Rafhael Rodrigues Cunha
	 */
	public static void setRelationshipsRafhael(Connection connection) {
		RelationshipDAO rdao = new RelationshipDAO(connection);
		Relationship relationship = new Relationship();

		// relacionamento entre Thiago e Rafhael Rodrigues Cunha no link
		// "http://www.google.com.br/"
		RelationshipLink rLink1 = new RelationshipLink();
		rLink1.setAverageDistance(122);
		rLink1.setLink(SetUp.GOOGLE);
		rLink1.setMaxDistance(108313);
		rLink1.setMinDistance(44);
		rLink1.setOccurrenceNumber(7);
		relationship.addRelationshipLink(rLink1);

		// relacionamento entre Thiago e Rafhael Rodrigues Cunha no link
		// "http://pt.wikipedia.org/"
		RelationshipLink rLink2 = new RelationshipLink();
		rLink2.setAverageDistance(188);
		rLink2.setLink(SetUp.WIKIPEDIA);
		rLink2.setMaxDistance(50862);
		rLink2.setMinDistance(63);
		rLink2.setOccurrenceNumber(4);
		relationship.addRelationshipLink(rLink2);

		// relacionamento entre Thiago e Rafhael Rodrigues Cunha no link
		// "http://unipampa.edu.br/"
		RelationshipLink rLink3 = new RelationshipLink();
		rLink3.setAverageDistance(234);
		rLink3.setLink(SetUp.UNIPAMPA);
		rLink3.setMaxDistance(7777);
		rLink3.setMinDistance(101);
		rLink3.setOccurrenceNumber(4);
		relationship.addRelationshipLink(rLink3);

		// insere os relacionamentos no banco
		relationship.setPerson1(new Person(4, "", null)); // id Thiago == 4
		relationship.setPerson2(new Person(6, "", null)); // id Rafhael Rodrigues Cunha == 6
		rdao.insert(relationship);
	}
	
	/**
	 * Cria os relacionamentos para a pessoa Thiago Cassio Krug e a pessoa
	 * Thiago Aquino de Lima
	 */
	public static void setRelationshipsThiago(Connection connection) {
		RelationshipDAO rdao = new RelationshipDAO(connection);
		Relationship relationship = new Relationship();

		// relacionamento entre Thiago e Thiago Aquino de Lima no link
		// "http://www.google.com.br/"
		RelationshipLink rLink1 = new RelationshipLink();
		rLink1.setAverageDistance(89);
		rLink1.setLink(SetUp.GOOGLE);
		rLink1.setMaxDistance(82100);
		rLink1.setMinDistance(11);
		rLink1.setOccurrenceNumber(6);
		relationship.addRelationshipLink(rLink1);

		// relacionamento entre Thiago e Thiago Aquino de Lima no link
		// "http://pt.wikipedia.org/"
		RelationshipLink rLink2 = new RelationshipLink();
		rLink2.setAverageDistance(5234);
		rLink2.setLink(SetUp.WIKIPEDIA);
		rLink2.setMaxDistance(5234);
		rLink2.setMinDistance(5234);
		rLink2.setOccurrenceNumber(1);
		relationship.addRelationshipLink(rLink2);

		// relacionamento entre Thiago e Thiago Aquino de Lima no link
		// "http://unipampa.edu.br/"
		RelationshipLink rLink3 = new RelationshipLink();
		rLink3.setAverageDistance(191);
		rLink3.setLink(SetUp.UNIPAMPA);
		rLink3.setMaxDistance(6583);
		rLink3.setMinDistance(34);
		rLink3.setOccurrenceNumber(3);
		relationship.addRelationshipLink(rLink3);

		// insere os relacionamentos no banco
		relationship.setPerson1(new Person(4, "", null)); // id Thiago == 4
		relationship.setPerson2(new Person(7, "", null)); // id Thiago Aquino de Lima == 7
		rdao.insert(relationship);
	}
	
	/**
	 * Cria os relacionamentos para a pessoa Thiago Cassio Krug e a pessoa
	 * Renan Marcel Uchôa
	 */
	public static void setRelationshipsRenan(Connection connection) {
		RelationshipDAO rdao = new RelationshipDAO(connection);
		Relationship relationship = new Relationship();

		// relacionamento entre Thiago e Renan Marcel Uchôa no link
		// "http://www.google.com.br/"
		RelationshipLink rLink1 = new RelationshipLink();
		rLink1.setAverageDistance(211);
		rLink1.setLink(SetUp.GOOGLE);
		rLink1.setMaxDistance(13234);
		rLink1.setMinDistance(136);
		rLink1.setOccurrenceNumber(5);
		relationship.addRelationshipLink(rLink1);

		// relacionamento entre Thiago e Renan Marcel Uchôa no link
		// "http://pt.wikipedia.org/"
		RelationshipLink rLink2 = new RelationshipLink();
		rLink2.setAverageDistance(174);
		rLink2.setLink(SetUp.WIKIPEDIA);
		rLink2.setMaxDistance(260);
		rLink2.setMinDistance(88);
		rLink2.setOccurrenceNumber(2);
		relationship.addRelationshipLink(rLink2);

		// relacionamento entre Thiago e Renan Marcel Uchôa no link
		// "http://unipampa.edu.br/"
		RelationshipLink rLink3 = new RelationshipLink();
		rLink3.setAverageDistance(111);
		rLink3.setLink(SetUp.UNIPAMPA);
		rLink3.setMaxDistance(5626);
		rLink3.setMinDistance(73);
		rLink3.setOccurrenceNumber(4);
		relationship.addRelationshipLink(rLink3);

		// insere os relacionamentos no banco
		relationship.setPerson1(new Person(4, "", null)); // id Thiago == 4
		relationship.setPerson2(new Person(8, "", null)); // id Renan Marcel Uchôa == 8
		rdao.insert(relationship);
	}
	
	/**
	 * Cria os relacionamentos para a pessoa Thiago Cassio Krug e a pessoa
	 * Rafael Tavares Amorim
	 */
	public static void setRelationshipsRafael(Connection connection) {
		RelationshipDAO rdao = new RelationshipDAO(connection);
		Relationship relationship = new Relationship();

		// relacionamento entre Thiago e Rafael Tavares Amorim no link
		// "http://www.google.com.br/"
		RelationshipLink rLink1 = new RelationshipLink();
		rLink1.setAverageDistance(412);
		rLink1.setLink(SetUp.GOOGLE);
		rLink1.setMaxDistance(40712);
		rLink1.setMinDistance(200);
		rLink1.setOccurrenceNumber(4);
		relationship.addRelationshipLink(rLink1);

		// relacionamento entre Thiago e Rafael Tavares Amorim no link
		// "http://pt.wikipedia.org/"
		RelationshipLink rLink2 = new RelationshipLink();
		rLink2.setAverageDistance(63);
		rLink2.setLink(SetUp.WIKIPEDIA);
		rLink2.setMaxDistance(320);
		rLink2.setMinDistance(9);
		rLink2.setOccurrenceNumber(3);
		relationship.addRelationshipLink(rLink2);

		// relacionamento entre Thiago e Rafael Tavares Amorim no link
		// "http://unipampa.edu.br/"
		RelationshipLink rLink3 = new RelationshipLink();
		rLink3.setAverageDistance(371);
		rLink3.setLink(SetUp.UNIPAMPA);
		rLink3.setMaxDistance(11765);
		rLink3.setMinDistance(19);
		rLink3.setOccurrenceNumber(7);
		relationship.addRelationshipLink(rLink3);

		// insere os relacionamentos no banco
		relationship.setPerson1(new Person(4, "", null)); // id Thiago == 4
		relationship.setPerson2(new Person(9, "", null)); // id Rafael Tavares Amorim == 9
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
		rLink1.setAverageDistance(533);
		rLink1.setLink(SetUp.GOOGLE);
		rLink1.setMaxDistance(21643);
		rLink1.setMinDistance(312);
		rLink1.setOccurrenceNumber(3);
		relationship.addRelationshipLink(rLink1);

		// relacionamento entre Thiago e Juliano Rodovalho Macedo no link
		// "http://pt.wikipedia.org/"
		RelationshipLink rLink2 = new RelationshipLink();
		rLink2.setAverageDistance(5820);
		rLink2.setLink(SetUp.WIKIPEDIA);
		rLink2.setMaxDistance(9312);
		rLink2.setMinDistance(2328);
		rLink2.setOccurrenceNumber(2);
		relationship.addRelationshipLink(rLink2);

		/* Não existe relacionamento entre Thiago e Juliano Rodovalho Macedo nesse site 
		// relacionamento entre Thiago e Juliano Rodovalho Macedo no link
		// "http://unipampa.edu.br/"
		RelationshipLink rLink3 = new RelationshipLink();
		rLink3.setAverageDistance(371);
		rLink3.setLink(unipampa);
		rLink3.setMaxDistance(11765);
		rLink3.setMinDistance(19);
		rLink3.setOccurrenceNumber(7);
		relationship.addRelationshipLink(rLink3);*/

		// insere os relacionamentos no banco
		relationship.setPerson1(new Person(4, "", null)); // id Thiago == 4
		relationship.setPerson2(new Person(10, "", null)); // id Juliano Rodovalho Macedo == 10
		rdao.insert(relationship);
	}

}

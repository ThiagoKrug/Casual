package test.setup;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionFactory;
import dao.PersonDAO;
import model.Person;

/**
 * Esta classe apenas inicializa os relacionamentos entre as pessoas. Todos os
 * relacionamentos estão em função da pessoa Thiago Cassio Krug, logo, essa
 * pessoa deve ser usada na pesquisa para a obtenção dos resultados.
 * 
 * @author thiago
 * 
 */
public class SetUp {

	public static final String GOOGLE = "http://www.google.com.br/";
	public static final String WIKIPEDIA = "http://pt.wikipedia.org/";
	public static final String UNIPAMPA = "http://unipampa.edu.br/";

	public static void createPersons() {
		Connection connection = new ConnectionFactory().getTestConnection();
		List<Person> persons = new ArrayList<>();

		Person BrunoVicelli = new Person();
		BrunoVicelli.setName("Bruno Vicelli"); // id: 1
		persons.add(BrunoVicelli);

		Person HelisonReusTeixeira = new Person();
		HelisonReusTeixeira.setName("Helison Reus Teixeira"); // id: 2
		persons.add(HelisonReusTeixeira);

		Person MarceloMaiaLopes = new Person();
		MarceloMaiaLopes.setName("Marcelo Maia Lopes"); // id: 3
		persons.add(MarceloMaiaLopes);

		Person ThiagoCassioKrug = new Person();
		ThiagoCassioKrug.setName("Thiago Cassio Krug"); // id: 4
		persons.add(ThiagoCassioKrug);

		Person MateusHenriqueDalForno = new Person();
		MateusHenriqueDalForno.setName("Mateus Henrique Dal Forno"); // id: 5
		persons.add(MateusHenriqueDalForno);

		Person RafhaelRodriguesCunha = new Person();
		RafhaelRodriguesCunha.setName("Rafhael Rodrigues Cunha"); // id: 6
		persons.add(RafhaelRodriguesCunha);

		Person ThiagoAquinodeLima = new Person();
		ThiagoAquinodeLima.setName("Thiago Aquino de Lima"); // id: 7
		persons.add(ThiagoAquinodeLima);

		Person RenanMarcelUchôa = new Person();
		RenanMarcelUchôa.setName("Renan Marcel Uchôa"); // id: 8
		persons.add(RenanMarcelUchôa);

		Person RafaelTavaresAmorim = new Person();
		RafaelTavaresAmorim.setName("Rafael Tavares Amorim"); // id: 9
		persons.add(RafaelTavaresAmorim);

		Person JulianoRodovalhoMacedo = new Person();
		JulianoRodovalhoMacedo.setName("Juliano Rodovalho Macedo"); // id: 10
		persons.add(JulianoRodovalhoMacedo);

		Person WolmirNemitzNeto = new Person();
		WolmirNemitzNeto.setName("Wolmir Nemitz Neto"); // id: 11
		persons.add(WolmirNemitzNeto);

		Person NasserOthmanRahman = new Person();
		NasserOthmanRahman.setName("Nasser Othman Rahman"); // id: 12
		persons.add(NasserOthmanRahman);

		Person LíberoRodrigues = new Person();
		LíberoRodrigues.setName("Líbero Rodrigues"); // id: 13
		persons.add(LíberoRodrigues);

		Person LeandroLunkesAmaral = new Person();
		LeandroLunkesAmaral.setName("Leandro Lunkes Amaral"); // id: 14
		persons.add(LeandroLunkesAmaral);

		PersonDAO pdao = new PersonDAO(connection);
		pdao.insert(persons);

		new ThiagoCassioKrug(connection);
		new BrunoVicelli(connection);
		new HelisonReusTeixeira(connection);
		new MarceloMaiaLopes(connection);
		new MateusHenriqueDalForno(connection);
		new RafhaelRodriguesCunha(connection);
		new ThiagoAquinoDeLima(connection);
		new RenanMarcelUchoa(connection);
		new RafaelTavaresAmorim(connection);
	}

	public static List<Person> loadPersons(Connection connection) {
		PersonDAO pdao = new PersonDAO(connection);
		return pdao.getAllPersons();
	}
}

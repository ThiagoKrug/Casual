package crawler.focused;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import jdbc.ConnectionFactory;
import model.Person;
import model.Relationship;
import model.RelationshipLink;
import dao.PersonDAO;
import dao.RelationshipDAO;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import graphviz.GraphViz;

public class FocusedCrawler extends WebCrawler {

	private boolean isCategory;
	private String urlCategory;
	private String urlWiki;
	private String url;
	private GraphViz graph;
	private int cont;
	private Extractor ex;
	private RelationshipExtractor re;
	private List<Person> persons;
	private Connection connection;
	private int pagesToVisit;

	private final static Pattern FILTERS = Pattern
			.compile(".*(\\.(css|js|bmp|gif|jpe?g"
					+ "|png|tiff?|mid|mp2|mp3|mp4"
					+ "|wav|avi|mov|mpeg|ram|m4v|pdf"
					+ "|rm|smil|wmv|swf|wma|zip|rar|gz))$");

	public FocusedCrawler() {
		this.persons = new ArrayList<>();
		this.ex = new Extractor();
		this.re = new RelationshipExtractor(true);
		this.isCategory = false;
		this.urlWiki = "http://pt.wikipedia.org/wiki/".toLowerCase();
		this.urlCategory = "http://pt.wikipedia.org/wiki/Categoria:"
				.toLowerCase();
		this.cont = 0;

		this.graph = new GraphViz();
		this.graph.addln(graph.start_graph());
		this.url = "";

		this.connection = new ConnectionFactory().getConnection();
		this.pagesToVisit = 10000;
	}

	/**
	 * Return true if the url is trash, false otherwise
	 * 
	 * @param url
	 * @return
	 */
	private boolean isTrash(String url) {
		boolean isAnotherCategory = url.contains("categoria");
		if (!isAnotherCategory) {
			String[] splited = url.split(":");
			if (splited.length > 2) {
				return true;
			}
		}
		return false;
	}

	/**
	 * You should implement this function to specify whether the given url
	 * should be crawled or not (based on your crawling logic).
	 */
	@Override
	public boolean shouldVisit(WebURL url) {
		String href = url.getURL().toLowerCase();
		boolean isTrash = isTrash(href);
		boolean shouldVisit = !FILTERS.matcher(href).matches()
				&& (!isTrash)
				&& ((href.startsWith(urlCategory) && !isCategory) || (href
						.startsWith(urlWiki) && isCategory));

		return shouldVisit;
	}

	/**
	 * This function is called when a page is fetched and ready to be processed
	 * by your program.
	 */
	@Override
	public void visit(Page page) {
		if (page.getParseData() instanceof HtmlParseData) {
			HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
			List<WebURL> links = htmlParseData.getOutgoingUrls();

			if (cont <= pagesToVisit) {
				cont++;
				url = page.getWebURL().getURL();
				System.out.println("\nCount: " + cont + "\nURL: " + url);
				isCategory = ex.isCategory(url);

				if (isCategory) {
					List<Person> intermediatePersons = ex.extractPersons(links);
					
					for (Iterator<Person> it1 = intermediatePersons.iterator(); it1
							.hasNext();) {
						boolean isContained = false;
						Person person1 = (Person) it1.next();
						if (persons.contains(person1)) {
							person1 = persons.get(persons.indexOf(person1));
							isContained = true;
						} else {
							persons.add(person1);
						}
						
						String name = ex.extractCategory(page.getWebURL());
						for (Iterator<Person> it2 = intermediatePersons
								.iterator(); it2.hasNext();) {
							Person person2 = (Person) it2.next();
							if (persons.contains(person2)) {
								person2 = persons.get(persons.indexOf(person2));
							} else {
								persons.add(person2);
							}
							
							RelationshipLink rlink = new RelationshipLink();
							rlink.setLink(url);
							rlink.setOccurrenceNumber(1);
							rlink.setName(name);

							Relationship r = new Relationship();
							List<Relationship> rels = new ArrayList<>();

							if (isContained) {
								rels = person1.getRelationships();
								for (Relationship relationship : rels) {
									if (relationship.getPerson2().equals(
											person2)) {
										r = relationship;
										break;
									}
								}
							}

							this.graph.addln(person1.getName() + " -> "
									+ person2.getName() + " [label=\"" + name
									+ "\"];");

							r.setPerson1(person1);
							r.setPerson2(person2);
							r.addRelationshipLink(rlink);
							rels.add(r);
							person1.setRelationships(rels);
						}
					}
				}

			} else {
				this.exit();
			}
		}
	}

	public void exit() {
		graph.addln(graph.end_graph());
		graph.increaseDpi();

		GraphManipulator gm = new GraphManipulator("focusedCrawler.dot");
		gm.saveGraph(graph);

		System.out.println("Inserting persons...");
		PersonDAO pdao = new PersonDAO(connection);
		pdao.insert(persons);

		for (Person person : persons) {
			Person p = pdao.getPerson(person.getName()).get(0);
			person.setId(p.getId());
		}

		System.out.println("Inserting relationships...");
		RelationshipDAO rdao = new RelationshipDAO(connection);
		for (Person person : persons) {
			rdao.insert(person.getRelationships());
		}

		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("\n" + persons);

		System.exit(0);
	}

}

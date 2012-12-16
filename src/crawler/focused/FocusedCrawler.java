package crawler.focused;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import jdbc.ConnectionFactory;
import model.Category;
import model.Person;
import dao.CategoryDAO;
import dao.PersonDAO;
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
	private List<Person> persons;
	private List<Category> categories;
	private Connection connection;
	private int pagesToVisit;

	private final static Pattern FILTERS = Pattern
			.compile(".*(\\.(css|js|bmp|gif|jpe?g"
					+ "|png|tiff?|mid|mp2|mp3|mp4"
					+ "|wav|avi|mov|mpeg|ram|m4v|pdf"
					+ "|rm|smil|wmv|swf|wma|zip|rar|gz))$");

	public FocusedCrawler() {
		this.persons = new ArrayList<>();
		this.categories = new ArrayList<>();
		this.ex = new Extractor();
		this.isCategory = false;
		this.urlWiki = "http://pt.wikipedia.org/wiki/".toLowerCase();
		this.urlCategory = "http://pt.wikipedia.org/wiki/Categoria:"
				.toLowerCase();
		this.cont = 0;

		this.graph = new GraphViz();
		this.graph.addln(graph.start_graph());
		this.url = "";

		this.connection = new ConnectionFactory().getConnection();
		this.pagesToVisit = 20;
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
					String name = ex.extractCategory(page.getWebURL());
					Category category = new Category();
					category.setLink(page.getWebURL().getURL());
					category.setName(name);
					categories.add(category);

					List<Person> intermediatePersons = ex.extractPersons(links);

					for (Person person : intermediatePersons) {
						if (persons.contains(person)) {
							person = persons.get(persons.indexOf(person));
						} else {
							persons.add(person);
						}

						if (!category.getPersons().contains(person)) {
							category.addPerson(person);
						}
					}
				}
			} else {
				this.exit();
			}
		}
	}

	public void exit() {
		this.getMyController().Shutdown();
		
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

		System.out.println("Inserting categories...");
		CategoryDAO cdao = new CategoryDAO(connection);
		cdao.insert(categories);

		System.out.println("Inserting person_category...");
		for (Category category : categories) {
			Category c = cdao.getCategory(category.getName()).get(0);
			category.setId(c.getId());
			cdao.insertPersons(category);
		}
		
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.exit(0);
	}

}

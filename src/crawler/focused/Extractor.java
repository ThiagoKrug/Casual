package crawler.focused;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import model.Person;

import edu.uci.ics.crawler4j.url.WebURL;

public class Extractor {

	private final static Pattern URLCATEGORY = Pattern
			.compile("^(http\\://pt\\.wikipedia\\.org/wiki/Categoria\\:|http\\://pt\\.wikipedia\\.org/w/index\\.php\\?title=Categoria\\:).*");
	private final static Pattern URLWIKI = Pattern
			.compile("^(http\\://pt\\.wikipedia\\.org/wiki/).*");
	
	private final static Pattern keywords = Pattern
			.compile(".*(Emma Thompson).*");
	
	/**
	 * teste do pattern
	 * @param args
	 */
	public static void main(String[] args) {
		String text1 = "Emma Thompson";
		System.out.println(keywords.matcher(text1).matches());
		
		String text2 = "Emma Thompson a";
		System.out.println(keywords.matcher(text2).matches());
		
		String text3 = "b Emma Thompson";
		System.out.println(keywords.matcher(text3).matches());
		
		String text4 = "Emma c Thompson";
		System.out.println(keywords.matcher(text4).matches());
		
		String text5 = "<li><a href=\"/wiki/Emma_Thompson\" title=\"Emma Thompson\">Emma Thompson</a></li>";
		System.out.println(keywords.matcher(text5).matches());
		
		String link1 = "http://pt.wikipedia.org/wiki/Categoria:abc";
		System.out.println(URLCATEGORY.matcher(link1).matches());
		
		String link2 = "http://pt.wikipedia.org/wiki/Categoria:";
		System.out.println(URLCATEGORY.matcher(link2).matches());
		
		String link3 = "http://pt.wikipedia.org/wiki/Categor";
		System.out.println(URLCATEGORY.matcher(link3).matches());
		
		String link4 = "abchttp://pt.wikipedia.org/wiki/Categoria:";
		System.out.println(URLCATEGORY.matcher(link4).matches());
		
		String link5 = "http://pt.wikipedia.org/w/index.php?title=Categoria:";
		System.out.println(URLCATEGORY.matcher(link5).matches());
	}

	public List<String> extractCategories(List<WebURL> links) {
		List<String> categories = new ArrayList<>();
		for (WebURL link : links) {
			categories.add(extractCategory(link));
		}
		return categories;
	}

	public String extractCategory(WebURL link) {
		String page = extractPageName(link);
		String[] categoryNames = page.split(":");
		return categoryNames[categoryNames.length - 1];
	}

	public boolean isCategory(String link) {
		return URLCATEGORY.matcher(link).matches();
	}
	
	public List<Person> extractPersons(List<WebURL> links) {
		List<Person> persons = new ArrayList<>();
		for (WebURL link : links) {
			if (this.isPerson(link.getURL())) {
				Person p = new Person();
				p.setName(this.extractPerson(link));
				persons.add(p);
			}
		}
		return persons;
	}

	public String extractPerson(WebURL link) {
		String name = extractPageName(link);
		name = name.replaceAll("_", " ");
		return name;
	}

	public String extractPageName(String link) {
		String[] paths = link.split("/");
		return paths[paths.length - 1];
	}

	public String extractPageName(WebURL link) {
		return extractPageName(link.getURL());
	}
	
	public boolean isPerson(String link) {
		return (URLWIKI.matcher(link).matches() && (link.lastIndexOf(":") <= 5));
	}

}

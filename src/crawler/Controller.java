package crawler;

import helpers.LoadPersons;

import java.util.Collection;

import jdbc.ConnectionFactory;

import model.Person;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class Controller {
	
	private Collection<Person> persons;
	private static Controller controller;
	
	private Controller() {
	}
	
	public static Controller getInstance() {
		if (controller == null) {
			controller = new Controller();
		}
		return controller;
	}
	
    public static void main(String[] args) throws Exception {

            String crawlStorageFolder = "/data/crawl/root";
            int numberOfCrawlers = 7;

            CrawlConfig config = new CrawlConfig();
            config.setCrawlStorageFolder(crawlStorageFolder);

            /*
             * Instantiate the controller for this crawl.
             */
            PageFetcher pageFetcher = new PageFetcher(config);
            RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
            RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
            CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

            /*
             * For each crawl, you need to add some seed urls. These are the first
             * URLs that are fetched and then the crawler starts following links
             * which are found in these pages
             */
            controller.addSeed("http://pt.wikipedia.org/wiki/Emma_Thompson");
    		controller.addSeed("http://pt.wikipedia.org/wiki/Anthony_Hopkins");
    		controller.addSeed("http://pt.wikipedia.org/wiki/Jodie_Foster");

            /*
             * Start the crawl. This is a blocking operation, meaning that your code
             * will reach the line after this only when crawling is finished.
             */
    		new LoadPersons(null).recreateDatabase("casual");
    		FileReader fr = new FileReader();
    		
    		Controller.getInstance().setPersons(fr.readNames("names"));
    		
            controller.start(MyCrawler.class, numberOfCrawlers);
    }
    
    public synchronized Collection<Person> getPersons() {
		return persons;
	}
    
    public synchronized void setPersons(Collection<Person> persons) {
		this.persons = persons;
	}
}
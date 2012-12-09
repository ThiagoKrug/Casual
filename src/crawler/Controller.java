package crawler;

import helpers.LoadPersons;

import java.util.Collection;
import java.util.List;

import crawler.focused.FocusedCrawler;

import model.Person;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
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
	
	public void initCrawler(int numberOfCrawlers, List<String> seeds, Class<? extends WebCrawler> crawler) throws Exception {
            String crawlStorageFolder = "/data/crawl/root";
            //int numberOfCrawlers = 7;

            CrawlConfig config = new CrawlConfig();
            config.setCrawlStorageFolder(crawlStorageFolder);

            /*
             * Instantiate the controller for this crawl.
             */
            PageFetcher pageFetcher = new PageFetcher(config);
            RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
            RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
            CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

            for (String seed : seeds) {
            	controller.addSeed(seed);
			}
            
            if (crawler.isInstance(new MyCrawler())) {
            	initMyCrawler();
            } else if (crawler.isInstance(new FocusedCrawler())) {
            	new LoadPersons(null).recreateDatabase("casual");
            }
            
            /*
             * Start the crawl. This is a blocking operation, meaning that your code
             * will reach the line after this only when crawling is finished.
             */
            controller.start(crawler, numberOfCrawlers);
    }
	
	public void initMyCrawler() {
		new LoadPersons(null).recreateDatabase("casual");
		FileReader fr = new FileReader();
		
		Controller.getInstance().setPersons(fr.readNames("names"));
	}
    
    public synchronized Collection<Person> getPersons() {
		return persons;
	}
    
    public synchronized void setPersons(Collection<Person> persons) {
		this.persons = persons;
	}
}
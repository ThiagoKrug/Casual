package crawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class Controller2 extends Thread {

	private CrawlController controller;
	private boolean isRunning;
	private int numberOfCrawlers;

	public Controller2() {
		this.isRunning = true;
		String crawlStorageFolder = "/data/crawl/root";
		numberOfCrawlers = 1;

		CrawlConfig config = new CrawlConfig();
		config.setCrawlStorageFolder(crawlStorageFolder);

		/*
		 * Instantiate the controller for this crawl.
		 */
		PageFetcher pageFetcher = new PageFetcher(config);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig,
				pageFetcher);
		try {
			this.controller = new CrawlController(config, pageFetcher,
					robotstxtServer);
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * For each crawl, you need to add some seed urls. These are the first
		 * URLs that are fetched and then the crawler starts following links
		 * which are found in these pages
		 */
		controller.addSeed("pt.wikipedia.org/wiki/Emma_Thompson");
		controller.addSeed("pt.wikipedia.org/wiki/Anthony_Hopkins");
		controller.addSeed("pt.wikipedia.org/wiki/Jodie_Foster");
	}

	@Override
	public void run() {
		while (isRunning) {
			/*
			 * Start the crawl. This is a blocking operation, meaning that your
			 * code will reach the line after this only when crawling is
			 * finished.
			 */
			controller.start(MyCrawler.class, numberOfCrawlers);
		}
		if (!controller.isShuttingDown() && !controller.isFinished()) {
			controller.Shutdown();
		}
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void done() {
		this.isRunning = false;
	}
	
}
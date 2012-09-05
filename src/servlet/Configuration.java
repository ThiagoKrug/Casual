package servlet;

import model.rank.AverageDistance;
import model.rank.Judge;

public class Configuration {
	
	private static Configuration config;
	private Class<? extends Judge> searchComputer;
	private boolean isCrawlerRunning;
	
	private Configuration() {
		this.searchComputer = AverageDistance.class;
		this.isCrawlerRunning = false;
	}
	
	public static Configuration getInstance() {
		if (config == null) {
			config = new Configuration();
		}
		return config;
	}
	
	public boolean isCrawlerRunning() {
		return isCrawlerRunning;
	}
	
	public void setCrawlerRunning(boolean isCrawlerRunning) {
		this.isCrawlerRunning = isCrawlerRunning;
	}

	public Class<? extends Judge> getSearchComputer() {
		return searchComputer;
	}

	public void setSearchComputer(Class<? extends Judge> searchComputer) {
		this.searchComputer = searchComputer;
	}
}

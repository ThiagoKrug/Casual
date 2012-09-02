package servlet;

import model.comparator.AverageDistanceAndPerson2RelationshipNumberComparator;
import model.comparator.RelationshipComparator;

public class Configuration {
	
	private static Configuration config;
	private Class<? extends RelationshipComparator> searchType;
	private boolean isCrawlerRunning;
	
	private Configuration() {
		this.searchType = AverageDistanceAndPerson2RelationshipNumberComparator.class;
		this.isCrawlerRunning = false;
	}
	
	public static Configuration getInstance() {
		if (config == null) {
			config = new Configuration();
		}
		return config;
	}
	
	public Class<? extends RelationshipComparator> getSearchType() {
		return searchType;
	}
	
	public void setSearchType(Class<? extends RelationshipComparator> searchType) {
		this.searchType = searchType;
	}
	
	public boolean isCrawlerRunning() {
		return isCrawlerRunning;
	}
	
	public void setCrawlerRunning(boolean isCrawlerRunning) {
		this.isCrawlerRunning = isCrawlerRunning;
	}
}

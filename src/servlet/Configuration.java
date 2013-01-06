package servlet;

import model.rank.Judge;
import model.rank.RelationshipLinksNumber;

public class Configuration {
	
	private static Configuration config;
	private Class<? extends Judge> searchComputer;
	private SearchType searchType;
	private boolean isCrawlerRunning;
	private int popularityDispersion;
	
	private Configuration() {
		this.searchComputer = RelationshipLinksNumber.class;
		this.isCrawlerRunning = false;
		this.searchType = SearchType.Category;
		this.popularityDispersion = 1;
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
	
	public SearchType getSearchType() {
		return searchType;
	}
	
	public void setSearchType(SearchType searchType) {
		this.searchType = searchType;
	}
	
	public int getPopularityDispersion() {
		return popularityDispersion;
	}
	
	public void setPopularityDispersion(int popularityDispersion) {
		this.popularityDispersion = popularityDispersion;
	}
}

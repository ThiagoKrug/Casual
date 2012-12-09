package crawler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import crawler.focused.FocusedCrawler;

import edu.uci.ics.crawler4j.crawler.WebCrawler;

public class Main {

	public static void main(String[] args) {
		List<String> seeds = new ArrayList<>();
		Class<? extends WebCrawler> crawler = null;
		int numberOfCrawlers = 0;
		
		System.out.println("Initializing crawler...");
		System.out.println("Choose option:");
		System.out.println("1 - MyCrawler");
		System.out.println("2 - FocusedCrawler");
		
		Scanner scanner = new Scanner(System.in);
		String option = scanner.next();
		int opt = Integer.parseInt(option);
		
		if (opt == 1) {
			seeds.add("http://pt.wikipedia.org/wiki/Emma_Thompson");
			seeds.add("http://pt.wikipedia.org/wiki/Anthony_Hopkins");
			seeds.add("http://pt.wikipedia.org/wiki/Jodie_Foster");
			
			numberOfCrawlers = 7;
			
			crawler = MyCrawler.class;
		} else if (opt == 2) {
			//seeds.add("http://pt.wikipedia.org/wiki/Emma_Thompson");
			seeds.add("http://pt.wikipedia.org/wiki/Categoria:Atores_premiados_com_o_Oscar");
			
			numberOfCrawlers = 1;
			
			crawler = FocusedCrawler.class;
		}
		
		try {
			Controller.getInstance().initCrawler(numberOfCrawlers, seeds, crawler);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

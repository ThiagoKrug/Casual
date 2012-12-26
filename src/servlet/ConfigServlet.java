package servlet;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.rank.Judge;

@WebServlet(name = "config", urlPatterns = { "/config" })
public class ConfigServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		super.init();
		Configuration.getInstance();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Class<? extends Judge> searchType = null;

		String searchtp = request.getParameter("search_type");
		if (searchtp != null) {
			if (!searchtp.isEmpty()) {
				Class<? extends Judge> comparator = null;
				try {
					comparator = (Class<? extends Judge>) Class.forName(searchtp);
				} catch (ClassNotFoundException e) {
					throw new ServletException(e);
				}
				Configuration.getInstance().setSearchComputer(comparator);
			}
		}

		searchType = Configuration.getInstance().getSearchComputer();

		List<Class> searchOptions;
		try {
			searchOptions = getClasses("model.rank");
		} catch (ClassNotFoundException e) {
			throw new ServletException(e);
		}

		boolean isCrawlerRunning = Configuration.getInstance()
				.isCrawlerRunning();

		String crawler = request.getParameter("is_crawler_running");
		if (crawler != null) {
			if (!crawler.isEmpty()) {
				if (crawler.equalsIgnoreCase("start")) {
					isCrawlerRunning = true;
					Configuration.getInstance().setCrawlerRunning(
							isCrawlerRunning);
					try {
						// controller.start();
						System.out.println("Iniciou a thread!");
					} catch (Exception e) {
						throw new ServletException(e);
					}
				} else if (crawler.equalsIgnoreCase("stop")) {
					isCrawlerRunning = false;
					Configuration.getInstance().setCrawlerRunning(
							isCrawlerRunning);
				} else {
					throw new ServletException("Configuração inválida!");
				}
			}
		}

		request.setAttribute("search_type", searchType);
		request.setAttribute("search_options", searchOptions);
		request.setAttribute("is_crawler_running", isCrawlerRunning);

		RequestDispatcher rd = request.getRequestDispatcher("/config.jsp");
		rd.forward(request, response);
	}

	/**
	 * Scans all classes accessible from the context class loader which belong
	 * to the given package and subpackages.
	 * 
	 * @param packageName
	 *            The base package
	 * @return The classes
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	private static List<Class> getClasses(String packageName)
			throws ClassNotFoundException, IOException {
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		assert classLoader != null;
		String path = packageName.replace('.', '/');
		Enumeration<URL> resources = classLoader.getResources(path);
		List<File> dirs = new ArrayList<File>();
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			dirs.add(new File(resource.getFile()));
		}
		List<Class> classes = new ArrayList<Class>();
		for (File directory : dirs) {
			classes.addAll(findClasses(directory, packageName));
		}
		classes = removeAbstractClasses(classes);
		return classes;
	}

	@SuppressWarnings("rawtypes")
	private static List<Class> removeAbstractClasses(List<Class> classes) {
		ArrayList<Class> abstracts = new ArrayList<>();
		for (Class classe : classes) {
			if (Modifier.isAbstract(classe.getModifiers())
					|| Modifier.isInterface(classe.getModifiers())) {
				abstracts.add(classe);
			}
		}
		classes.removeAll(abstracts);
		return classes;
	}

	/**
	 * Recursive method used to find all classes in a given directory and
	 * subdirs.
	 * 
	 * @param directory
	 *            The base directory
	 * @param packageName
	 *            The package name for classes found inside the base directory
	 * @return The classes
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("rawtypes")
	private static List<Class> findClasses(File directory, String packageName)
			throws ClassNotFoundException {
		List<Class> classes = new ArrayList<Class>();
		if (!directory.exists()) {
			return classes;
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				assert !file.getName().contains(".");
				classes.addAll(findClasses(file,
						packageName + "." + file.getName()));
			} else if (file.getName().endsWith(".class")) {
				classes.add(Class.forName(packageName
						+ '.'
						+ file.getName().substring(0,
								file.getName().length() - 6)));
			}
		}
		return classes;
	}

}

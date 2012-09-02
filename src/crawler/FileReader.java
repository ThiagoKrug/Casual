package crawler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

import model.Person;

public class FileReader {

	public Collection<Person> readNames(String dir) {
		HashMap<String, Person> persons = new HashMap();
		File[] files = this.getFilesName(dir);
		for (File file : files) {
			try {
				BufferedReader br = new BufferedReader(new java.io.FileReader(
						file));
				String line = "";
				while ((line = br.readLine()) != null) {
					if (!persons.containsKey(line)) {
						Person p = new Person();
						p.setName(line);
						persons.put(line, p);
						System.out.println("Pessoa " + line
								+ " lida do arquivo " + file.getName());
					}
				}
				br.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return persons.values();
	}

	public File[] getFilesName(String dir) {
		File directory = new File(dir);
		if (directory.isDirectory()) {
			System.out.println("Pasta: " + directory.getAbsolutePath());
			return directory.listFiles();
		}
		return null;
	}

}

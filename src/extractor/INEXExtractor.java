package extractor;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class INEXExtractor {
	public static void main(String[] args) throws ParserConfigurationException,
			SAXException, IOException {
		// cria a factory e o parser
		SAXParser parser = SAXParserFactory.newInstance().newSAXParser();

		List<File> files = new ArrayList<>();
		INEXExtractor inex = new INEXExtractor();

		System.out.println("Obtendo arquivos...");
		files = inex.getXMLS(new File("teste"), files);
		System.out.println("Arquivos obtidos...");

		int count = 0;
		for (File file : files) {
			if (count%100 == 0) {
				System.out.println(count + " arquivos lidos...");
			}
			// abre a conexao com o arquivo, buffer de 1 mega
			InputSource input = new InputSource(new FileReader(file));
			// inicia o parsing
			parser.parse(input, new XMLHandler());
			count++;
		}
	}

	public List<File> getXMLS(File file, List<File> files) {
		if (file.isFile()) {
			files.add(file);
		} else if (file.isDirectory()) {
			File[] candidateFiles = file.listFiles();
			for (File candidateFile : candidateFiles) {
				getXMLS(candidateFile, files);
			}
		}
		return files;
	}
}

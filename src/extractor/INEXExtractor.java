package extractor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class INEXExtractor {

    public static String textoCSV;
    private String fileName;

    public INEXExtractor(String fileName) {
        textoCSV = "";
        this.fileName = fileName;
    }

    public static void main(String[] args) throws ParserConfigurationException,
            SAXException, IOException {
        // cria a factory e o parser
        SAXParser parser = SAXParserFactory.newInstance().newSAXParser();

        List<File> files = new ArrayList();
        INEXExtractor inex = new INEXExtractor("part-630000.csv");

        System.out.println("Obtendo arquivos...");
        files = inex.getFilesRecursively(new File("../../Desktop/part-630000/"), files);
        System.out.println("Arquivos obtidos...");

        int count = 0;
        for (File file : files) {
            if (count % 100 == 0) {
                System.out.println(count + " arquivos lidos...");
            }
            // abre a conexao com o arquivo, buffer de 1 mega
            InputSource input = new InputSource(new FileReader(file));
            // inicia o parsing
            parser.parse(input, new XMLHandler());
            count++;
        }
        new CSVHandler().saveCSV(inex.getFileName(), INEXExtractor.textoCSV);
    }

    public static List<File> getFilesRecursively(File fileOrDir, List<File> files) {
        if (fileOrDir.isFile()) {
            files.add(fileOrDir);
        } else if (fileOrDir.isDirectory()) {
            System.out.println("Getting files from " + fileOrDir.getName() + "...");
            File[] candidateFiles = fileOrDir.listFiles();
            for (File candidateFile : candidateFiles) {
                getFilesRecursively(candidateFile, files);
            }
        }
        return files;
    }

    public String getFileName() {
        return fileName;
    }
}

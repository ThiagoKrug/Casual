package extractor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        files = inex.getXMLS(new File("../../Desktop/part-630000/"), files);
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
        inex.saveCSV();
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

    public void saveCSV() {
        try {
            File file = new File(fileName);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);

            printWriter.print(textoCSV);

            printWriter.flush();
            bufferedWriter.flush();
            fileWriter.flush();

            printWriter.close();
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(INEXExtractor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

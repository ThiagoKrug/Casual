/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package extractor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thiago
 */
public class CSVHandler {

    public void saveCSV(String fileName, String textoCSV) {
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
            ex.printStackTrace();
        }
    }
}

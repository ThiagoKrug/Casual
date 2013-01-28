/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package extractor.relationship;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thiago
 */
public class teste {

    public static void main2(String[] args) {
        try {
            File file = new File("./data/relationships/!!! albums.csv");
            System.out.println(file.getAbsoluteFile());
            System.out.println(file.getAbsolutePath());
            System.out.println(file.getCanonicalFile());
            System.out.println(file.getCanonicalPath());
            System.out.println(file.getName());
            System.out.println(file.getParent());
            System.out.println(file.getParentFile());
            System.out.println(file.getPath());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String path = "./data/relationships/";
        System.out.println("Getting files...");
        SharedList sl = new SharedList(path);
        int cont = 0;
        System.out.println("Importando...");
        for (Iterator<String> it = sl.getList().iterator(); it.hasNext();) {
            String file = it.next();
            cont++;
            if (cont % 10000 == 0) {
                System.out.println(cont + " arquivos importados...");
            }

            String dbname = "casual_relationship";
            String table = "relationship";
            String dbuser = "ruut";
            String dbpassword = "ruut";
            String scriptpath = file;
            String separator = "| ";
            String newLine = "\\r\\n";
            boolean verbose = true;

            try {
                String cmd = "mysql -u " + dbuser + " --password=" + dbpassword + " --execute=\"LOAD DATA LOCAL INFILE '" + scriptpath + "' INTO TABLE " + dbname + "." + table + " FIELDS TERMINATED BY '" + separator + "' LINES TERMINATED BY '" + newLine + "' (id_person1, id_person2, name)\"";
                //System.err.println(cmd);
                Process proc = null;
                proc = Runtime.getRuntime().exec(cmd);
                //proc = Runtime.getRuntime().exec("mysql -u ruut --password=ruut");
                if (verbose) {
                    InputStream inputstream = proc.getInputStream();
                    InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
                    BufferedReader bufferedreader = new BufferedReader(inputstreamreader);

                    // read the output
                    String line;
                    while ((line = bufferedreader.readLine()) != null) {
                        System.out.println(line);
                    }

                    // check for failur
                    try {
                        if (proc.waitFor() > 1) {
                            System.err.println("exit value = "
                                    + proc.exitValue());
                        }
                    } catch (InterruptedException e) {
                        System.err.println(e);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

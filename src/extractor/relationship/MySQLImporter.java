package extractor.relationship;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

/**
 *
 * @author thiago
 */
public class MySQLImporter {

    public static void main(String[] args) {
        String path = "C:/data/relationships/";
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
            
            
            MySQLImporter.executeScript("casual_relationship",
                    "relationship",
                    "ruut",
                    "ruut",
                    file,
                    "| ",
                    "\\r\\n",
                    true);
            break;
        }
    }

    public static String ondeEstou() {
        try {
            File file = new File("C:/data/relationships/!!! albums.csv");
            System.out.println(file.getAbsoluteFile());
            System.out.println(file.getAbsolutePath());
            System.out.println(file.getCanonicalFile());
            System.out.println(file.getCanonicalPath());
            System.out.println(file.getName());
            System.out.println(file.getParent());
            System.out.println(file.getParentFile());
            System.out.println(file.getPath());
            String fileName = file.getParent();
            fileName += "/" + file.getName();
            fileName = fileName.replace("\\", "/");
            return fileName;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void executeScript(String dbname, String table, String dbuser,
            String dbpassword, String scriptpath, String separator, String newLine, boolean verbose) {
        //scriptpath = MySQLImporter.ondeEstou();
        try {
            String[] cmd = new String[]{"mysql",
                "-u " + dbuser,
                "--password=" + dbpassword,
                "--execute=\"LOAD DATA LOCAL INFILE '" + scriptpath + "' INTO TABLE " + dbname + "." + table + " FIELDS TERMINATED BY '" + separator + "' LINES TERMINATED BY '" + newLine + "' (id_person1, id_person2, name)\"",};
            System.err.println(cmd[0] + " " + cmd[1] + " " + cmd[2] + " " + cmd[3]);
            Process proc = null;
            proc = Runtime.getRuntime().exec(cmd.toString());
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
                    if (proc.waitFor() != 0) {
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

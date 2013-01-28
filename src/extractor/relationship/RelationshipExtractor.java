package extractor.relationship;

import control.Carla;
import control.HashMapData;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import model.Category;
import model.Person;

/**
 *
 * @author thiago
 */
public class RelationshipExtractor implements Runnable {

    private String name;
    private String separador = "| ";
    private String path;
    private SharedList list;
    private List<Category> categories;

    public static void main(String[] args) {
        String path = "data/relationships/";
        SharedList sl = new SharedList(path);
        HashMapData hmd = null;
        try {
            hmd = new HashMapData(new Carla(2, 0.1, 0.9), 1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println("Total de categorias: " + hmd.getCategories().size());
        for (int i = 19; i < 20; i++) {
            RelationshipExtractor re = new RelationshipExtractor(sl, hmd.getCategories(), "RE" + i, path + "" + i + "/");
            System.out.println("Thread " + "RE" + i + " iniciada");
            new Thread(re).start();
        }
    }

    public RelationshipExtractor(SharedList list, List<Category> categories, String name, String path) {
        super();
        this.name = name;
        this.list = list;
        this.categories = categories;
        this.path = path;
    }

    @Override
    public void run() {
        this.mkdir();
        this.extractRelationships(this.categories);
        System.out.println("Thread " + this.name + " finalizada");
    }

    private void mkdir() {
        File dir = new File(path);
        dir.mkdir();
    }

    private String clearCategoryName(String name) {
        if (name.length() > 0) {
            String categoryName = name.substring(0, name.length() - 1);
            categoryName = categoryName.replace(":", "");
            categoryName = categoryName.replace("\\", "");
            categoryName = categoryName.replace("/", "");
            categoryName = categoryName.replace("*", "");
            categoryName = categoryName.replace("?", "");
            categoryName = categoryName.replace("|", "");
            categoryName = categoryName.replace("<", "");
            categoryName = categoryName.replace(">", "");
            categoryName = categoryName.replace("\"", "");
            return categoryName;
        }
        return name;
    }

    public void extractRelationships(List<Category> categories) {
        int cont = 0;
        for (Iterator<Category> itCategory = categories.iterator(); itCategory.hasNext();) {
            if (cont < 6000) { // salvar apenas 6000 categorias por pasta
                Category category = itCategory.next();
                String categoryName = this.clearCategoryName(category.getName());
                String fileName = categoryName + ".csv";

                if (fileName.length() > 211) {
                    fileName = fileName.substring(0, 211) + ".csv";
                }

                if (this.list.contains(fileName) == false) {
                    this.list.add(fileName);

                    FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream(new File(path + fileName));
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }

                    List<Person> persons = category.getPersons();
                    for (int i = 0; i < persons.size(); i++) {
                        Person p1 = persons.get(i);
                        for (int j = i + 1; j < persons.size(); j++) {
                            Person p2 = persons.get(j);
                            try {
                                fos.write((p1.getId() + separador + p2.getId() + separador + category.getName() + "\n").getBytes());
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                    try {
                        fos.flush();
                        fos.close();
                        cont++;
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            } else {
                break;
            }
        }
    }
}

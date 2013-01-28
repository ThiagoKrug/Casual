package extractor.relationship;

import extractor.INEXExtractor;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thiago
 */
public class SharedList {

    private List<String> list;

    public SharedList(String path) {
        this.list = new ArrayList<>();
        List<File> files = new ArrayList<>();
        files = INEXExtractor.getFilesRecursively(new File(path), files);
        for (Iterator<File> it = files.iterator(); it.hasNext();) {
            File file = it.next();
//            String fileName = file.getParent();
//            fileName += "/" + file.getName();
//            fileName = fileName.replace("\\", "/");
            String fileName = null;
            try {
                fileName = file.getCanonicalPath();
            } catch (IOException ex) {
                Logger.getLogger(SharedList.class.getName()).log(Level.SEVERE, null, ex);
            }
            fileName = fileName.replace("\\", "/");
            this.list.add(fileName);
        }
    }

    public synchronized boolean add(String fileName) {
        return list.add(fileName);
    }

    public synchronized boolean contains(String fileName) {
        return list.contains(fileName);
    }

    public List<String> getList() {
        return list;
    }
}

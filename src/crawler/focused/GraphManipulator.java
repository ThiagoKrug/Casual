package crawler.focused;

import graphviz.GraphViz;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GraphManipulator {
	
	private File out;
	
	public GraphManipulator(String file) {
		this.out = new File(file);
	}
	
	public void saveGraph(GraphViz graph) {		
		out.delete();
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(out));
			bw.write(graph.getDotSource());
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

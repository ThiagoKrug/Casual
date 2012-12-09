package crawler.focused;

public class RelationshipExtractor {
	
	private boolean generateGraph;
	
	public RelationshipExtractor(boolean generateGraph) {
		this.generateGraph = generateGraph;
	}
	
	public boolean isGeneratingGraph() {
		return generateGraph;
	}
	
	public void setGenerateGraph(boolean generateGraph) {
		this.generateGraph = generateGraph;
	}

}

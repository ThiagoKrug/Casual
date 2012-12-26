package extractor;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class XMLHandler extends DefaultHandler {

	private StringBuffer valorAtual = new StringBuffer(255);

	@Override
	public void startElement(String uri, String localName, String tag,
			Attributes atributos) {
		if (tag.equalsIgnoreCase("name")) {
			//System.out.print(atributos.getValue("id") + ", ");
		}
		valorAtual.delete(0, valorAtual.length());
	}

	@Override
	public void endElement(String uri, String localName, String tag) {
		if (tag.equalsIgnoreCase("name")) {
			//System.out.print(valorAtual.toString().trim() + "\n");
		}
		valorAtual.delete(0, valorAtual.length());
	}

	@Override
	public void characters(char[] ch, int start, int length) {
		valorAtual.append(ch, start, length);
	}
}
import java.io.File;
import java.io.IOException;

import javax.lang.model.element.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LlegirXML {
	
	public static void imprimirContingutNodes(Node nodeArrel) {
		NodeList child;
		NamedNodeMap atributs;
		
		//Desem els nodes fills
		child = nodeArrel.getChildNodes();
		
		//Loop per cada Node
		for (int i = 0; i < child.getLength(); i++) {
			//En cas que el nom del text no sigui #text accedeix als nodes fills
			if(!child.item(i).getNodeName().equals("#text")) {
				//Comprobar si hi ha atributs
				if (child.item(i).hasAttributes()) {
					//Desem els atributs
					atributs = child.item(i).getAttributes();
					for (int j = 0; j < atributs.getLength(); j++) {
						//Imprimir la informació dels atributs
						System.out.println("Id: " + atributs.item(j).getNodeName() + atributs.item(j).getNodeValue());
					}
				}else {
					//Imprimir el valor de Nodes
					System.out.println(child.item(i).getTextContent());
					System.out.println(" ");
				}//Recursiva, en cas que els nodes fills tinguin altres nodes fills repeteixi el procés
				if(child.item(i).hasChildNodes()) {
					imprimirContingutNodes(child.item(i));
				}
			}
		}
				
	}

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
		
		// per a carregar en memòria un arxiu xml
		File file = new File("alumnes.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(file);

		//per obtenir el node arrel
		Node nodeArrel = doc.getDocumentElement();
		
		//mostrar resultat
		imprimirContingutNodes(nodeArrel);		

	}

}
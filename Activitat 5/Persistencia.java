import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class Persistencia {
	
	public static void menu() {
		
		System.out.println("Selecciona opcio");
		System.out.println("1. Mostrar l'arxiu");
		System.out.println("2. Situarse en un node");
		System.out.println("3. Afegir, modificar o eliminar nodes");
		System.out.println("4. Afegir, modificar o eliminar atributs");
		System.out.println("5. Guardar canvis");
		System.out.println("0. Sortir i finalitzar programa");
	}
	
	public static void submenu() {
		System.out.println("Selecciona opcio");
		System.out.println("1. Afegir");
		System.out.println("2. Modificar");
		System.out.println("3. Eliminar");
	}
	
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

	public static void cercarElement(Document doc, String id) {
		// per cercar elements per un atribut anomenat id
		Node posicio = doc.getElementById(id);
		
		//Mostrem info del node posicionat
		imprimirContingutNodes(posicio);
	}
	
	//calcular nova id
	public static int calculaId(Node nodeArrel) {
		
		NodeList child;
		NamedNodeMap atributs;
		
		int num_id = 1;
		
		//Desem els nodes fills
		child = nodeArrel.getChildNodes();
		
		for (int i = 0; i < child.getLength(); i++) {	
			//En cas que el nom del text no sigui #text accedeix als nodes fills
			if(!child.item(i).getNodeName().equals("#text")) {
//				//Comprobar si hi ha atributs
				if (child.item(i).hasAttributes()) {
//					//Desem els atributs
					atributs = child.item(i).getAttributes();
					for (int j = 0; j < atributs.getLength(); j++) {
						if (atributs.item(j).getNodeName().equals("id")) {
							num_id++;
						}
						//Imprimir la informació dels atributs
//						System.out.println("Id: " + atributs.item(j).getNodeName() + atributs.item(j).getNodeValue());
					}
				}
			}
		}
		
		return num_id;
	}
	
	//afegir NODES
	public static void afegir(Document doc, Node nodeArrel) {
		Scanner teclat = new Scanner(System.in);
		
		// per crear elements
		//Crear node
		Element node = doc.createElement("alumne");
		//afegim nou atribut id al node que creem
		node.setAttribute("id", String.valueOf(calculaId(nodeArrel)));
		node.setIdAttribute("id", true);
		//Crear nodes fills
		Element nom = doc.createElement("nom");
		Element cognom = doc.createElement("cognom");
		
		// per afegir element sota d’un altre
		System.out.println("Introdueix nom");
		String name = teclat.next();
		nom.appendChild(doc.createTextNode(name));
		System.out.println("Introdueix cognom");
		String sname = teclat.next();
		cognom.appendChild(doc.createTextNode(sname));
		
		//afegir els nodes fills al node principal
		node.appendChild(nom);
		node.appendChild(cognom);
		
		//afegir el node principal al nodearrel
		nodeArrel.appendChild(node);
	}
	
	//afegir Atributs
	public static void afegirAtribut(Document doc, String id) {
		Scanner teclat = new Scanner(System.in);
		
		//accedir al node que vols
		Element posicio = doc.getElementById(id);
		
		NodeList node = posicio.getChildNodes();
		
		//escull node
		System.out.println("Introdueix node a afegir atribut");
		String subnode = teclat.next();
		
		for (int i = 0; i < node.getLength(); i++) {
			if (node.item(i).getNodeName().equals(subnode)) {
				System.out.println("Introdueix atribut");
				String text = teclat.next();
				System.out.println("Introdueix contingut de l'atribut");
				String text2 = teclat.next();
				
				//Afegim tots dos textos com a atribut
				((Element) node.item(i)).setAttribute(text, text2);
				
			}
		}
	}
	
	public static void modificar (Document doc, String id) {
		Scanner teclat = new Scanner(System.in);
		
		//accedir al node que vols
		Element posicio = doc.getElementById(id);
		
		NodeList node = posicio.getChildNodes();
		
		//modificar
		System.out.println("Introdueix node a modificar");
		String subnode = teclat.next();
		
		for (int i = 0; i < node.getLength(); i++) {
			if (node.item(i).getNodeName().equals(subnode)) {
				System.out.println("Introdueix nou text");
				String text = teclat.next();
				
				//comanda per modificar
				node.item(i).setTextContent(text);
				
			}
		}
	}
	
	public static void modificarAtribut(Document doc, String id) {
		Scanner teclat = new Scanner(System.in);
		
		//accedir al node que vols
		Element posicio = doc.getElementById(id);
		
		NodeList node = posicio.getChildNodes();
		
		//escull node
		System.out.println("Introdueix node a modificar atribut");
		String subnode = teclat.next();
		
		for (int i = 0; i < node.getLength(); i++) {
			if (node.item(i).getNodeName().equals(subnode)) {
				System.out.println("Introdueix atribut a modificar");
				String text = teclat.next();
				System.out.println("Introdueix nou contingut de l'atribut");
				String text2 = teclat.next();
				
				//Afegim tots dos textos com a atribut
				((Element) node.item(i)).setAttribute(text, text2);
				
			}
		}
	}
	
	public static void eliminar (Document doc, String id) {
		Scanner teclat = new Scanner(System.in);
		
		//accedir al node que vols
		Element posicio = doc.getElementById(id);
		
		NodeList node = posicio.getChildNodes();
		
		//modificar
		System.out.println("Introdueix node a eliminar");
		String subnode = teclat.next();
		
		for (int i = 0; i < node.getLength(); i++) {
			if (node.item(i).getNodeName().equals(subnode)) {
				Node nodeEliminar = node.item(i);
				
				posicio.removeChild(nodeEliminar);
			}
		}
	}
	
	public static void eliminarAtribut(Document doc, String id) {
		Scanner teclat = new Scanner(System.in);
		
		//accedir al node que vols
		Element posicio = doc.getElementById(id);
		
		NodeList node = posicio.getChildNodes();
		
		//escull node
		System.out.println("Introdueix node a eliminar atribut");
		String subnode = teclat.next();
		
		for (int i = 0; i < node.getLength(); i++) {
			if (node.item(i).getNodeName().equals(subnode)) {
				System.out.println("Introdueix atribut a eliminar");
				String text = teclat.next();
				
				
				//Afegim tots dos textos com a atribut
//				((Element) node.item(i)).setAttribute(text, text2);
				//Eliminar atribut
				Element e = (Element) node.item(i);
				e.removeAttribute(text);
				
			}
		}
	}
	
	public static void guardar(Document doc) throws TransformerFactoryConfigurationError, TransformerException {
		//Creacio instancia transformer
		Transformer trans = TransformerFactory.newInstance().newTransformer();
//		
//		//Creacio adaptadors Source i Results a partir d'un doc i un file
		StreamResult result = new StreamResult(new File("alumnes2.xml")); 
		DOMSource source = new DOMSource(doc);
		trans.transform(source, result);
	}

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
//		// per cercar elements per un atribut anomenat id
//		document.getElementByID
//
//		// per crear elements
//		document.createElement
//
//		// per afegir element sota d’un altre
//		document.appendChild
//
//		// per crear elements/nodes de text
//		document.createTextNode
//
//		// per crear atributs i per obtenir-ne els valors
//		element.setAttribute
//		element.getAttribute
		
		Scanner teclat = new Scanner(System.in);
		boolean sortir = false;
		int opcio;
		
		// per a carregar en memòria un arxiu xml
				File file = new File("alumnes2.xml");
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(file);

				//per obtenir el node arrel
				Node nodeArrel = doc.getDocumentElement();
				
				
				NodeList child;
				NamedNodeMap atributs;
				
		
				child = nodeArrel.getChildNodes();
				
				for (int i = 0; i < child.getLength(); i++) {
					if(child.item(i).getNodeName().equals("alumne")) {
						if (child.item(i).hasAttributes()) {
							atributs = child.item(i).getAttributes();
							for (int j = 0; j < atributs.getLength(); j++) {
								if(atributs.item(j).getNodeName().equals("id")) {
									((Element) child.item(i)).setIdAttribute("id", true);
								}
							}
						}
					}
				}
				
				
				while (!sortir) {
					//Menu
					menu();
					opcio = teclat.nextInt();
					
					if (opcio == 1) {
						imprimirContingutNodes(nodeArrel);
					}else if (opcio == 2) {
						System.out.println("Introdueix id");
						String id = teclat.next();
						cercarElement(doc, id);
					}else if (opcio == 3) {
						submenu();
						opcio = teclat.nextInt();
						if (opcio == 1) {
							afegir(doc, nodeArrel);
						}else if (opcio == 2) {
							System.out.println("Introdueix id");
							String id = teclat.next();
							modificar(doc, id);
						}else if (opcio == 3) {
							System.out.println("Introdueix id");
							String id = teclat.next();
							eliminar(doc, id);
						}
					}else if (opcio == 4) {
						submenu();
						opcio = teclat.nextInt();
						if (opcio == 1) {
							System.out.println("Introdueix id");
							String id = teclat.next();
							afegirAtribut(doc, id);
						}else if (opcio == 2) {
							System.out.println("Introdueix id");
							String id = teclat.next();
							modificarAtribut(doc, id);
						}else if (opcio == 3) {
							System.out.println("Introdueix id");
							String id = teclat.next();
							eliminarAtribut(doc, id);
						}
					}else if(opcio == 5){
						guardar(doc);
					}else {
						sortir = true;
					}
				}
		
				

	}

}

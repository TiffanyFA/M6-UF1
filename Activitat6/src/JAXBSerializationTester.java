/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class JAXBSerializationTester {

	private static final String NAUTICS_XML_FILE = "nautics.xml";

	public static void main(String[] args) throws JAXBException, IOException {

		JAXBContext context = JAXBContext.newInstance(Response.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		Response responses = ompleRows();

		// Mostrem el document XML generat por la sortida estandard
		marshaller.marshal(responses, System.out);

		FileOutputStream fos = new FileOutputStream(NAUTICS_XML_FILE);
		// guardem l'objecte serializat en un document XML
		marshaller.marshal(responses, fos);
		fos.close();

		Unmarshaller unmarshaller = context.createUnmarshaller();
		// Deserialitzem a partir de un document XML
		Response responsesAux = (Response) unmarshaller.unmarshal(new File(
				NAUTICS_XML_FILE));
		System.out
				.println("********* Nautics carregat desde fitxer XML***************");
		// Mostrem l'objeto Java obtingut
		marshaller.marshal(responsesAux, System.out);

	}

	private static Response ompleRows() {
		
		String[] idRow = { "row-rxjr~35j4_7fbr", "row-247k~gzqu.sv9d", "row-8z4v.5h8u.x9ih" };
		String[] uuidRow = { "00000000-0000-0000-1326-B878077BA22C", "00000000-0000-0000-44FA-DA0A8406DFD1", "00000000-0000-0000-D58A-FD6C916DBFEF" };
		int[] positionRow = { 0, 0, 0 };
		String[] addressRow = { "https://analisi.transparenciacatalunya.cat/resource/_9qmy-ujbd/row-rxjr~35j4_7fbr", "https://analisi.transparenciacatalunya.cat/resource/_9qmy-ujbd/row-247k~gzqu.sv9d", "https://analisi.transparenciacatalunya.cat/resource/_9qmy-ujbd/row-8z4v.5h8u.x9ih" };
		String[] nomRow = { "AIGUADOLÇ-VELA", "BASE NÀUTICA SURF, SAIL &amp; FUN", "BUSINESS YACHTCLUB BARCELONA" };
		String[] adreçaRow = { "Port d'Aiguadolç, s/n", "C.Marquès de Casa Riera, s/n", "Moll Gregal, 18" };
		String[] codiPostalRow = { "08870", "08394", "08005" };
		String[] municipiRow = { "Sitges", "Sant Vicenç de Montal", "Barcelona" };
		String[] comarcaRow = { "Garraf", "Maresme", "Barcelonès" };
		String[] modalitatRow = { "Vela lleugera", "Formació, lloguer de vela lleugera, caiacs i pàdels surfs", "Lloguer d'embarcacions" };
		
		Row[] arrayRows = new Row[3];		

		for (int i = 0; i < 3; i++) {
			arrayRows[i] = new Row();
			arrayRows[i].setId(idRow[i]);
			arrayRows[i].setUuid(uuidRow[i]);
			arrayRows[i].setPosition(positionRow[i]);
			arrayRows[i].setAddress(addressRow[i]);
			arrayRows[i].setNom(nomRow[i]);
			arrayRows[i].setAdreça(adreçaRow[i]);
			arrayRows[i].setCodi_postal(codiPostalRow[i]);
			arrayRows[i].setMunicipi(municipiRow[i]);
			arrayRows[i].setComarca(comarcaRow[i]);
			arrayRows[i].setModalitat(modalitatRow[i]);
		}

		Response responses = new Response();
		responses.setRows(arrayRows);

		return responses;
	}

}
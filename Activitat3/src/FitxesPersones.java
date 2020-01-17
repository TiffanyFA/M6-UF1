import java.io.*;
import java.util.Scanner;

public class FitxesPersones {
	// ecriure fitxer
	public static void escriureFitxer(File fitxer) throws IOException {
		Scanner teclat = new Scanner(System.in);
		String[] noms = new String[4];
		String[] ciutats = new String[4];
		int[] edats = new int[4];
		String[] dni = new String[4];

		// Crea un flux (stream) d'arxiu d'accés aleatori per llegir
		RandomAccessFile aleatoriFile = new RandomAccessFile(fitxer, "rw");
		// Les dades per inserir
		for (int i = 0; i < noms.length; i++) {
			System.out.println("Introdueix nom i cognoms:");
			noms[i] = teclat.nextLine();
			System.out.println("Introdueix ciutat:");
			ciutats[i] = teclat.nextLine();
			System.out.println("Introdueix DNI:");
			dni[i] = teclat.nextLine();
			System.out.println("Introdueix edat:");
			edats[i] = teclat.nextInt();
			teclat.nextLine();
		}
		// Construeix un buffer (memòria intermèdia) de strings
		StringBuffer buffer = null;

		for (int i = 0; i < noms.length; i++) {
			aleatoriFile.writeInt(i + 1);// 1 enter ocupa 4 bytes
			// 50 caràcters a 2bytes/caràcter 100 bytes
			buffer = new StringBuffer(noms[i]);
			buffer.setLength(50);
			aleatoriFile.writeChars(buffer.toString());
			// 20 caràcters a 2bytes/caràcter 40 bytes
			buffer = new StringBuffer(ciutats[i]);
			buffer.setLength(20);
			aleatoriFile.writeChars(buffer.toString());
			// 9 caràcters a 2bytes/caràcter 18 bytes
			buffer = new StringBuffer(dni[i]);
			buffer.setLength(9);
			aleatoriFile.writeChars(buffer.toString());
			// 1 enter ocupa 4 bytes
			aleatoriFile.writeInt(edats[i]);
			// Total 166 bytes
		}
		aleatoriFile.close();

	}

	// llegir el fitxer al complet
	public static void llegirFitxer(File fitxer) throws IOException {

		// Crea un flux (stream) d'arxiu d'accés aleatori només lectura
		RandomAccessFile aleatoriFile = new RandomAccessFile(fitxer, "r");

		// Apuntador s'inicialitza apuntant a l'inici del fitxer
		int apuntador = 0;
		char nom[] = new char[50];
		char ciutat[] = new char[20];
		char dni[] = new char[9];
		int id;
		int edat;
		char aux;

		// Recorrer el fitxer persones
		for (;;) {
			aleatoriFile.seek(apuntador);// Apuntar a l'inici de cada persona al
											// fitxer
			// Llegeix ID
			id = aleatoriFile.readInt();
			// Llegeix nom
			for (int i = 0; i < nom.length; i++) {
				aux = aleatoriFile.readChar();
				nom[i] = aux;
			}
			String noms = new String(nom);
			// Llegeix ciutat
			for (int i = 0; i < ciutat.length; i++) {
				aux = aleatoriFile.readChar();
				ciutat[i] = aux;
			}
			String ciutats = new String(ciutat);
			// Llegir dni
			for (int i = 0; i < dni.length; i++) {
				aux = aleatoriFile.readChar();
				dni[i] = aux;
			}
			String dnis = new String(dni);
			// Llegir edat
			edat = aleatoriFile.readInt();

			// Sortida de les dades de cada llibre
			System.out.println("ID: " + id + "\nNom: " + noms + "\nCiutat: "
					+ ciutats + "\nDNI: " + dnis + "\nEdats: " + edat);
			// S'ha de posicionar l'apuntador al següent llibre
			apuntador += 166;
			// Si coincideix on s'està apuntat amb el final del fitxer, sortim
			if (aleatoriFile.getFilePointer() == aleatoriFile.length())
				break;
		}
		aleatoriFile.close();// Tancar el fitxer
	}

	// llegir fitxer persona concreta
	public static void consultarPersona(File fitxer) throws IOException {
		// Crea un flux (stream) d'arxiu d'accés aleatori només lectura
		RandomAccessFile aleatoriFile = new RandomAccessFile(fitxer, "r");

		int apuntador = 0;
		char nom[] = new char[50];
		char ciutat[] = new char[20];
		char dni[] = new char[9];
		int id;
		int edat;
		char aux;
		int seleccio;

		// Demana a l'usuari que seleccioni el llibre pel seu identificador
		System.out.print("Introdueixi el ID de la persona a consultar: ");
		Scanner stdin = new Scanner(System.in);

		seleccio = stdin.nextInt();
		apuntador = (seleccio - 1) * 166;

		if (apuntador >= aleatoriFile.length()) {
			System.out
					.println("ERROR: ID incorrecte, no existeix aquesta persona");
		} else {// Apuntar a l'inici del llibre seleccionat al fitxer
			aleatoriFile.seek(apuntador);
			id = aleatoriFile.readInt();// Llegeix ID
			// Llegeix nom
			for (int i = 0; i < nom.length; i++) {
				aux = aleatoriFile.readChar();
				nom[i] = aux;
			}
			String noms = new String(nom);
			// Llegeix ciutat
			for (int i = 0; i < ciutat.length; i++) {
				aux = aleatoriFile.readChar();
				ciutat[i] = aux;
			}
			String ciutats = new String(ciutat);
			// Llegir dni
			for (int i = 0; i < dni.length; i++) {
				aux = aleatoriFile.readChar();
				dni[i] = aux;
			}
			String dnis = new String(dni);
			// Llegir edat
			edat = aleatoriFile.readInt();

			// Sortida de les dades de cada llibre
			System.out.println("ID: " + id + "\nNom: " + noms + "\nCiutat: "
					+ ciutats + "\nDNI: " + dnis + "\nEdats: " + edat);
		}
		aleatoriFile.close();// Tancar el fitxer

	}

	// consultar persona segons camp
	public static void consultarCamp(File fitxer) throws IOException {
		// Crea un flux (stream) d'arxiu d'accés aleatori només lectura
		RandomAccessFile aleatoriFile = new RandomAccessFile(fitxer, "r");

		int apuntador = 0;
		char nom[] = new char[50];
		char ciutat[] = new char[20];
		char dni[] = new char[9];
		int id;
		int edat;
		char aux;
		String seleccio;

		// Buscar per nom
		System.out.print("Introdueixi el nom de la persona: ");
		Scanner teclat = new Scanner(System.in);

		seleccio = teclat.nextLine();
		
		// Recorrer el fitxer persones
		// Apuntar a l'inici de cada persona al fitxer
		aleatoriFile.seek(0);
		for (;;) {			
			id = aleatoriFile.readInt();// Llegeix ID
			// Llegeix nom
			for (int i = 0; i < nom.length; i++) {
				aux = aleatoriFile.readChar();
				nom[i] = aux;
			}
			String noms = new String(nom);
			// Llegeix ciutat
			for (int i = 0; i < ciutat.length; i++) {
				aux = aleatoriFile.readChar();
				ciutat[i] = aux;
			}
			String ciutats = new String(ciutat);
			// Llegir dni
			for (int i = 0; i < dni.length; i++) {
				aux = aleatoriFile.readChar();
				dni[i] = aux;
			}
			String dnis = new String(dni);
			// Llegir edat
			edat = aleatoriFile.readInt();

			// comparar
			if (noms.equalsIgnoreCase(seleccio)) {
				// Sortida de les dades de cada llibre
				System.out.println("Nom: " + noms + "\nCiutat: " + ciutats
						+ "\nDNI: " + dnis + "\nEdats: " + edat);
			}
			// S'ha de posicionar l'apuntador al següent llibre
			//apuntador += 166;
			// Si coincideix on s'està apuntat amb el final del fitxer, sortim
			if (aleatoriFile.getFilePointer() == aleatoriFile.length())
				break;
		}

		if (apuntador >= aleatoriFile.length()) {
			System.out
					.println("ERROR: Nom incorrecte, no existeix aquesta persona");
		}
		aleatoriFile.close();// Tancar el fitxer
	}

	public static void main(String[] args) throws IOException {
		Scanner teclat = new Scanner(System.in);
		int[] ids = new int[4];
		String[] noms = new String[4];
		String[] cognoms = new String[4];
		int[] edats = new int[4];
		boolean sortir = false;
		int resposta;

		File fitxer = new File("persones.txt");

		// menu
		while (!sortir) {
			// menu
			System.out.println();
			System.out.println("Tria una opció");
			System.out.println("1 per desar en fitxer");
			System.out.println("2 per mostrar contingut fitxer");
			System.out
					.println("3 per consultar una persona en concret (amb punter)");
			System.out.println("4 per cercar persones segons un camp");
			System.out.println("5 per sortir");
			resposta = teclat.nextInt();

			// crida metodes
			if (resposta == 1) {
				escriureFitxer(fitxer);
			} else if (resposta == 2) {
				llegirFitxer(fitxer);
			} else if (resposta == 3) {
				consultarPersona(fitxer);
			} else if (resposta == 4) {
				consultarCamp(fitxer);
			} else {
				sortir = true;
			}
		}

	}

}

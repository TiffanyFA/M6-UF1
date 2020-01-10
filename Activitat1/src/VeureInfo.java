import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VeureInfo {
	public static String tempsModificacio(long file){
		long actual = System.currentTimeMillis();
		long data = file;
		long dies = (actual - data) /( 1000L * 60L * 60L * 24L);
		if (dies <= 3){
			return "Ha estat modificat en els ultims 3 dies";
		} else {
			return "";
		}
	}
	
	public static String formatar(long file){
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
		Date date = new Date(file);
		return (formatter.format(date));
	}

	public static void main(String[] args) {
		String comentari;		
		String directori = args[0];		
		File file = new File(directori);
		//si el file existeix..
		if (file.exists()) {
			//si file és directori..
			if (file.isDirectory()) {
				String[] arxius = file.list();
				if (file.isHidden()){
					System.out.println("Es un directori ocult.");
				}
				System.out.println("Fitxers al directori actual: ");
				for (int i = 0; i < arxius.length; i++) {
					System.out.println(arxius[i]);
				}
			}
			//si file és fitxer..
			if (file.isFile()) {
				System.out.println("INFORMACIÓ SOBRE EL FITXER");
				System.out.println("Nom del fitxer : " + file.getName());
				System.out.println("Ruta           : " + file.getPath());
				System.out.println("Ruta absoluta  : " + file.getAbsolutePath());
				System.out.println("Es pot escriure: " + file.canRead());
				System.out.println("Es pot llegir  : " + file.canWrite());
				System.out.println("Grandaria      : " + file.length());
				System.out.println("Es un directori: " + file.isDirectory());
				System.out.println("Es un fitxer   : " + file.isFile());
				System.out.println("Ultima modificació  : " + formatar(file.lastModified()));
				//si ha estat modificat en menys de 3 dies mostra comentari
				comentari = tempsModificacio(file.lastModified());
				System.out.println(comentari);
				//si es fitxer ocult..
				if (file.isHidden()){
					System.out.println("Es un fitxer ocult.");
				}
			}
		}
	}
}

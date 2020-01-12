import java.io.Serializable;

public class Cotxe implements Serializable {
	// Implementa la interfície Serializable
	private String marca;
	private String model;
	private String matricula;
	private String any;

	// constructor amb paràmetres
	public Cotxe(String marca, String model, String matricula, String any) {
		super();
		this.marca = marca;
		this.model = model;
		this.matricula = matricula;
		this.any = any;
	}

	// constructor per defecte
	public Cotxe() {
	}

	// getters i setters
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getAny() {
		return any;
	}

	public void setAny(String any) {
		this.any = any;
	}

}

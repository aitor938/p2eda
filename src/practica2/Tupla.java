
package practica2;

/**
 * @author Jose Luis Varo Guzmán - NIP: 697662
 * @author Aitor Arcos Almazaán - NIP: 705303
 */

public class Tupla<Id extends Comparable<Id>, In> implements Comparable<Tupla<Id,In>>{
	private Id identificador;
	private In informacion;

	public Tupla() {}

	public Tupla(Id identificador, In informacion) {
		this.identificador = identificador;
		this.informacion = informacion;
	}

	public Id getIdentificador() {
		return identificador;
	}

	public void setIdentificador(Id identificador) {
		this.identificador = identificador;
	}

	public In getInformacion() {
		return informacion;
	}

	public void setInformacion(In informacion) {
		this.informacion = informacion;
	}

	public String toString(){
		return identificador.toString() + "; "
		 + informacion.toString();
	}

	public int compareTo(Tupla<Id,In> tupla){
		return getIdentificador().compareTo(tupla.getIdentificador());
	}
}

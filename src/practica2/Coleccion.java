
package practica2;

/**
 * @author Jose Luis Varo Guzmán - NIP: 697662
 * @author Aitor Arcos Almazaán - NIP: 705303
 */

public interface Coleccion<Id extends Comparable<Id>,In> {

	public boolean meter(Id identificador, In informacion);

	public boolean esta(Id identificador); //esta?

	public In obtenerInformacion(Id identificador);

	public boolean borrar(Id identificador);

	public int size(); //tamaño

	public boolean esVacia();

	public String listar();
}

public class ColeccionAbb<Id extends Comparable<Id>,In> implements Coleccion<Id,In> {

	private BSTree<Tupla<Id,In>> raiz = new BSTree<>();
	private int size = 0;

	public boolean meter(Id identificador, In informacion) {
		Tupla<Id,In> nuevaTupla = new Tupla<>(identificador,
		 informacion);

		//Si no existía, meter
		if (!esta(identificador)){
			if (raiz.add(nuevaTupla, BSTree.ADD_IS_EXCLUSIVE)) {
				size++;
				return true;
			}
		//Si ya existia, actualizar
		}else{
			raiz.findTree(nuevaTupla).setE(nuevaTupla);
		}
		return false;
	}

	public boolean esta(Id identificador) {
		//Compueba la existencia de la Tupla mediante una Tupla "dummy"
		return raiz.isThere(new Tupla<Id,In>(identificador, null));
	}

	public In obtenerInformacion(Id identificador) {
		//Buscamos mediante una Tupla "dummy"
		Tupla<Id,In> tupla = new Tupla<>(identificador,null);
		return raiz.findTree(tupla).getE().getInformacion();
	}

	public boolean borrar(Id identificador) {
		//Borramos mediante una Tupla "dummy"
		Tupla<Id,In> tupla = new Tupla<>(identificador, null);
		boolean deleteResult;
		if (esta(identificador)){
			if (deleteResult = raiz.delete(tupla)){
				size--;
				return true;
			}
		}
		return false;
	}

	public int size() {
		return size;
	}

	public boolean esVacia() {
		return size == 0;
	}

	public String listar(){
		return recorrer(raiz, "");
	}

	private String recorrer(BSTree<Tupla<Id,In>> arbol, String cadena){
		if (arbol != null){
			cadena = recorrer(arbol.getLeft(), cadena);
			cadena = recorrer(arbol.getRight(), cadena);
			if (arbol.getE() != null){
				cadena += arbol.getE().toString() + "\n";
			}
		}
		return cadena;
	}
}

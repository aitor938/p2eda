public class ColeccionAbb<Id extends Comparable<Id>, In>extends
 BSTree<Tupla<Id,In>> implements Coleccion<Id, In> {

    //private NodoArbol<Id, In> raiz;
    private int size;
    //private NodoArbol<Id, In> iterador;

    public boolean meter(Id identificador, In informacion) {
        //Modificar para BSTree
        /*NodoArbol<Id, In> elemento = new NodoArbol<>(identificador, informacion);
        if (raiz == null) {
            raiz = elemento;
            size++; //contador de elementos
            return true;
        } else if (raiz.esMayor(elemento)) {
            return meterRec(raiz.getIzquierda(), elemento, raiz, false);
        } else {
            return meterRec(raiz.getDerecha(), elemento, raiz, true);
        }*/
        return true;
    }

    
    //Modificar para BSTree
    //el booleano es para cuando lleguemos a nil saber que camino es 
    //sin necesidad de volver a comparar el anterior con el elemento 
    //recorrido; el que era el actial ahora es el anterior
    /*private boolean meterRec(NodoArbol<Id, In> actual,
            NodoArbol<Id, In> elemento, NodoArbol<Id, In> anterior, boolean esMayor) {
        if (actual == null) {
            if (esMayor) {
                anterior.setDerecha(elemento);
            } else {
                anterior.setIzquierda(elemento);
            }
            size++;
            return true;
        } else if (actual.esMayor(elemento)) {
            return meterRec(actual.getIzquierda(), elemento, actual, false);
        } else {

            return meterRec(actual.getDerecha(), elemento, actual, true);
        }
    }*/

    public boolean estaElemento(Id identificador) {
        //Compueba la existencia de la Tupla mediante una Tupla "dummy"
		return isThere(new Tupla<Id,In>(identificador, null));
    }
    
    //!!IMPORTANTE!!//
    //public In obtenerInformacion(Id identificador) {}

    public boolean borrar(Id identificador) {
        //WIP
        return true;
    }

    public int size() {
        return size;
    }

    public boolean esVacia() {
        //WIP
        return true;
    }

    public String listar() {String listado = "";
	/*
		iniciarIterador();
		while (haySiguiente()) {
			try {
				siguiente();
				listado = listado + elemento.toString() + "\n";
			} catch (NoHaySiguienteException e) {}
		}
	*/
		return listado;
	}
    }

    //Necesario?
    //public void iniciarIterador() {}

    public boolean existeSiguiente() {
        //WIP
        return true;
    }

    //public Tupla<Id, In> siguiente() {}
}

package practica2.P1;

//Jose Luis Varo Guzmán - NIP: 697662
class Elemento<Id extends Comparable<Id>, In>{
	
	Elemento siguiente = null;
	Elemento anterior = null;
	private Id id;
	public In in;

	public Elemento(){}
	
	public Elemento(Id id, In in){
		newData(id, in);
	}
	
	public void newSiguiente(Elemento siguiente){
		this.siguiente = siguiente;
	}
	
	public void newAnterior(Elemento anterior){
		this.anterior = anterior;
	}
	
	public Elemento getSiguiente(){return siguiente;}
	
	public Elemento getAnterior(){return anterior;}
	
	public void newId(Id id){
		this.id = id;
	}
	
	public void newIn(In in){
		this.in = in;
	}	

	public void newData(Id id, In in){
		newId(id);
		newIn(in);
	}
	
	public Id getId(){return id;}

	public In getIn(){return in;}
	
	public String toString(){
		return (id.toString() + "; " + in.toString());
	}
}

//Jose Luis Varo Guzmán - NIP: 697662
public class ColeccionLista<Id extends Comparable<Id>, In> implements Coleccion<Id, In>{

	private int iterador = 0;
	private int totalElementos = 0;
	private Elemento primero;
	private Elemento elemento;

	public ColeccionLista(){	
	}

	//Devuelve el mayor Elemento que sea menor al especificado
	private Elemento menorAnterior(Elemento nuevoElemento){
		Id id1;
		Id id2 = (Id)nuevoElemento.getId();
		boolean found = false;

		//Buscamos un Elemento mayor que el especificado
		iniciarIterador();
		while (haySiguiente() && !found){ 
			try{
				siguiente();
				id1 = (Id)elemento.getId();
				//Cuando lo encontremos, devolvemos el anterior
				if (esMayor(id1,id2)){
					found = true;
					//Si no existe un anterior, devolvemos null
					if (elemento.getAnterior() != null){
						return elemento.getAnterior();
					}else{
						return null;
					}
				}
			}catch(NoHaySiguienteException e){
				System.out.println(e);
			}
		}
		return elemento;
	}

	//Introduce un nuevo Elemento en la colección
	public boolean meter(Id id, In info){
		boolean nuevo = false;
		Elemento nuevoElemento = new Elemento(id, info);
		
		//Si no estaba antes:
		if (!esta(id)){
			// y no había ningun otr,o será el primero.
			if (esVacia()){
				primero = nuevoElemento;
			} else {
			// y el menorAnterior,
				elemento = menorAnterior(nuevoElemento);
				// sí existe, añadimos al nuevo entre el menorAnterior y
				// el siguiente. Si no hay siguiente, será null, tal
				// como era para menorAnterior
				if (elemento != null){
					nuevoElemento.newAnterior(elemento);
					nuevoElemento.newSiguiente(elemento.getSiguiente());
					elemento.newSiguiente(nuevoElemento);
				// no existe, añadimos al nuevo al principio de la lista
				} else {
					primero.newAnterior(nuevoElemento);
					nuevoElemento.newSiguiente(primero);
					primero = nuevoElemento;
				}
			}
			totalElementos++;
			nuevo = true;
		
		} else {
			//Si ya existe, obtenemos el Elemento y cambiamos su In
			buscarElemento(id).newIn(info);
		}
		return nuevo;
	}
	
	//Devuelve el elemento con la Id especificada
	public Elemento buscarElemento(Id id){
		iniciarIterador();
		while (haySiguiente()){ 
			try{
				siguiente();
				Id id2 = (Id)elemento.getId();
				if (esIgual(id, id2)){
					return elemento;
				}
			}catch(NoHaySiguienteException e){}
		}
		return null;
	}
	
	//Devuelve true si existe un Elemento con la Id
	// especificada
	public boolean esta(Id id){
		boolean existe = false;
		if (buscarElemento(id) != null){
			existe = true;
		}
		return existe;
	}

	//Devuelve la información en el Elemento con la Id especificada 
	public In obtenerInformacion(Id id){
		Elemento elemento;
		In info = null;
		
		if (esta(id)){
			elemento = buscarElemento(id);
			info = (In)elemento.getIn();
		}
		return info;
	}

	//Elimina el Elemento con la Id especificada
	public boolean borrar(Id id){
		boolean borrado = false;
		
		if (esta(id)){
			elemento = buscarElemento(id);
			//Si hay un siguiente, conectamos el siguiente con el
			// anterior.
			if (elemento.getSiguiente() != null){ 
				elemento.getSiguiente().newAnterior(elemento.getAnterior());
			}
			//Si el anterior es null, el siguiente se transforma en
			// primero. Si no, conectamos el anterior con el siguiente
			if (elemento.getAnterior() != null){
				elemento.getAnterior().newSiguiente(elemento.getSiguiente());
			} else {
				primero = elemento.getSiguiente();
			}
				
			totalElementos--;
			borrado = true;
		}
		return borrado;
	}
	
	//Compara las Ids de dos Elementos y devuelve true si id1
	// es mayor que id2
	private boolean esMayor(Id id1, Id id2){
		return id1.compareTo(id2) == 1;
	}
	
	//Compara las Ids de dos Elementos y devuelve true si id1
	// es igual a id2
	private boolean esIgual(Id id1, Id id2){
		return id1.compareTo(id2) == 0;
	}
	
	//Devuelve el número de Elementos en la colección
	public int tamanyo(){
		return totalElementos;
	}
	
	//Devuelve true si no hay Elementos en la colección
	private boolean esVacia(){
		return totalElementos == 0; 
	}
	
	//Reinicia el iterador, y apuntamos al primero
	private void iniciarIterador(){
		elemento = primero;
		iterador = 0;
	}
	
	//Devuelve true si hay un Elemento siguiente en la lista
	private boolean haySiguiente(){
		boolean hay = false;
		if (elemento != null){
			if (elemento.getSiguiente() != null){hay = true;}
		}
		return hay;
	}
	
	//Avanza el iterador, cambia elemento a elemento.siguiente
	private void siguiente() throws NoHaySiguienteException{
		
		if(haySiguiente()){
			if (iterador == 0){
				elemento = primero;
			}else{
				if (elemento != null){
					elemento = elemento.getSiguiente();
				} else {
					throw new NoHaySiguienteException("No se ha "
					  + "encontrado un elemento siguiente");	
				}
			}
			iterador++;

		}
	}

	//Devuelve una cadena de texto con todos los elementos de la
	// colección 
	//@Override
	public String listar() {
		String listado = "";
		iniciarIterador();
		while (haySiguiente()) {
			try {
				siguiente();
				listado = listado + elemento.toString() + "\n";
			} catch (NoHaySiguienteException e) {}
		}		
			
		return listado;
	}
	
}
